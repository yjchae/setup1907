package com.schana.controller;

import com.schana.entity.PeopleEntity;
import com.schana.entity.PeopleViewEntity;
import com.schana.entity.RoomMasterEntity;
import com.schana.service.PeopleService;
import com.schana.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/people")
public class PeopleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PeopleService peopleService;
    @Autowired
    private RoomService roomService;

    @GetMapping("/listall")
    public String getPeopleInfo(Model model){
        //등록된 방정보
        List<PeopleEntity> List = peopleService.getPeopleList();

        HashMap<String,List<PeopleEntity>> resultMap = new HashMap<>();
        resultMap.put("list",List);
        model.addAllAttributes(resultMap);

        return "pages/people";
    }

    /**
     * 참석자 정보 조회
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/listview")
    public String getPeopleViewInfo(Model model, HttpServletRequest request){
        HashMap<String,Object> resultMap = new HashMap<>();
        HttpSession session = request.getSession();
        String type = (String)session.getAttribute("logintype");
        String filtertext = (String)session.getAttribute("filtertext");
        session.setAttribute("filtertext","");

        //등록된 방정보
        List<PeopleViewEntity> List = peopleService.getPeopleViewList(type);
        resultMap.put("list",List);
        model.addAllAttributes(resultMap);

        PeopleViewEntity peopleViewEntity = new PeopleViewEntity();
        model.addAttribute("peopleViewEntity",peopleViewEntity);
        model.addAttribute("filtertext",filtertext);


//        List<RoomMasterEntity> roomlist = roomService.getRoomMasterList("Y");
//        resultMap.put("roomlist",roomlist);
//        model.addAllAttributes(resultMap);

        return "pages/peopleview";
    }

    @ResponseBody
    @RequestMapping(value = "/insertpay",method = RequestMethod.GET)
    public String insertPay(
            @ModelAttribute PeopleEntity peopleEntity
            ,HttpServletRequest request
            , HttpSession session
            , Model model)throws Exception{

        //입금액 수정
        peopleService.updatePeople(peopleEntity);

        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/updatepaystatus",method = RequestMethod.GET)
    public String updatePayStatus(
            HttpServletRequest request
            , HttpSession session
            , Model model)throws Exception{

        String pseqnoArr = request.getParameter("peopleSeqnoArr");
        //입금액 수정
        peopleService.updatePayStatus(pseqnoArr);

        return "success";
    }

    /**
     * 참석자 삭제
     * @param request
     * @param session
     * @param model
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/deletepeople",method = RequestMethod.GET)
    public String deletePeople(
            HttpServletRequest request
            , HttpSession session
            , Model model)throws Exception{

        String seqno  = request.getParameter("seqno");   //seqno

        return peopleService.deletePeople(seqno);
    }

}
