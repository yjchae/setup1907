package com.schana.controller;

import com.schana.dto.RoomDto;
import com.schana.entity.RoomEntity;
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
@RequestMapping(value = "/room")
public class RoomController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoomService roomService;

    @GetMapping("/list")
    public String getRoomInfo(Model model){

        int roomnum = 1;
        //등록된 방정보
        List<RoomEntity> roomList = roomService.getRoomList(roomnum);

        HashMap<String,List<RoomEntity>> resultMap = new HashMap<>();
        resultMap.put("roomlist",roomList);
        model.addAllAttributes(resultMap);

        return "pages/room";
    }

}
