package com.schana.controller;

import com.schana.dto.RoomDto;
import com.schana.entity.PeopleEntity;
import com.schana.service.ApiService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class ApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApiService apiService;

    @RequestMapping(value = "/getPeopleApi" ,method = RequestMethod.POST)
    public String getPeopleApi(
             ModelAndView modelAndView
            , Model model) throws IOException {

        //TODO: 동기화 오류난 참석자 정보를 화면에 표시 해준다 엑셀을 수정할수 있도록
        String result = apiService.syncPeople();
        String message = "";
        if(result.length()>3){
            message = " - 동기화 되지 않은 대상자가 있습니다. "+ result;
        }

        HashMap<String,String> paramMap = new HashMap<>();
        paramMap.put("message","참석자 데이터 연동이 완료되었습니다." + message );
        paramMap.put("href","/dashboard");

        model.addAllAttributes(paramMap);

        return "pages/message";
    }

    /**
     * 참석자 등록시 DB로 전송
     * @param request
     * @param model
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/setPeopleApi")
    public String setPeopleApi(
            HttpServletRequest request
            , Model model) throws IOException {

        PeopleEntity people = new PeopleEntity();

        people.setName(request.getParameter("이름"));
        people.setMobile(request.getParameter("번호"));
        people.setGender(request.getParameter("성별"));
        people.setAge(Integer.parseInt(request.getParameter("나이")));
        people.setChurch(request.getParameter("소속"));
        people.setPastor(request.getParameter("목회자"));
        people.setNorthkorean(request.getParameter("탈북민"));
        people.setLayman(request.getParameter("평신도"));
        people.setCar(request.getParameter("자차"));
        people.setBus(request.getParameter("대중교통"));
        people.setBicycle(request.getParameter("도보 및 자전거"));
        people.setHow(request.getParameter("알게된 경로"));
        people.setPeople_key_sec(request.getParameter("신청인"));
        people.setPeoplekey(request.getParameter("대표인"));
        people.setFirst_pay(Integer.parseInt(request.getParameter("1차금액")));
        people.setSec_pay(Integer.parseInt(request.getParameter("2차금액")));
        people.setReg_dt(request.getParameter("등록날짜"));
        people.setMon(request.getParameter("월"));
        people.setTue(request.getParameter("화"));
        people.setWed(request.getParameter("수"));
        people.setThu(request.getParameter("목"));
        people.setFri(request.getParameter("금"));
        people.setSat(request.getParameter("토"));

        apiService.setPeople(people);

        return "success";
    }
}
