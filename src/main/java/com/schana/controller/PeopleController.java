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

    @GetMapping("/listview")
    public String getPeopleViewInfo(Model model){
        HashMap<String,Object> resultMap = new HashMap<>();
        //등록된 방정보
        List<PeopleViewEntity> List = peopleService.getPeopleViewList();
        resultMap.put("list",List);
        model.addAllAttributes(resultMap);

        PeopleViewEntity peopleViewEntity = new PeopleViewEntity();
        model.addAttribute("peopleViewEntity",peopleViewEntity);


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

}
