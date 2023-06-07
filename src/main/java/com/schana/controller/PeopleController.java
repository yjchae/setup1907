package com.schana.controller;

import com.schana.entity.PeopleEntity;
import com.schana.entity.RoomEntity;
import com.schana.service.PeopleService;
import com.schana.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/people")
public class PeopleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PeopleService peopleService;

    @GetMapping("/listall")
    public String getPeopleInfo(Model model){
        //등록된 방정보
        List<PeopleEntity> List = peopleService.getRoomList();

        HashMap<String,List<PeopleEntity>> resultMap = new HashMap<>();
        resultMap.put("list",List);
        model.addAllAttributes(resultMap);

        return "pages/people";
    }

}
