package com.schana.controller;

import com.schana.dto.RoomDto;
import com.schana.entity.PeopleEntity;
import com.schana.service.ApiService;
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
    @Autowired
    private ApiService apiService;

    @RequestMapping(value = "/getPeopleApi" ,method = RequestMethod.POST)
    public String getPeopleApi(
             ModelAndView modelAndView
            , Model model) throws IOException {

        apiService.syncPeople();

        HashMap<String,String> paramMap = new HashMap<>();
        paramMap.put("message","참석자 데이터 연동이 완료되었습니다.");
        paramMap.put("href","reginfo");

        model.addAllAttributes(paramMap);

        return "pages/message";
    }
}
