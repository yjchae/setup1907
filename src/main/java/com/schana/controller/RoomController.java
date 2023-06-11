package com.schana.controller;

import com.schana.entity.PeopleEntity;
import com.schana.entity.PeopleViewEntity;
import com.schana.entity.RoomEntity;
import com.schana.entity.RoomMasterEntity;
import com.schana.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/listmaster")
    public String getRoomMasterInfo(Model model){

        int roomnum = 1;
        //등록된 방정보
        List<RoomMasterEntity> roomList = roomService.getRoomMasterList("");

        HashMap<String,List<RoomMasterEntity>> resultMap = new HashMap<>();
        resultMap.put("roomlist",roomList);
        model.addAllAttributes(resultMap);

        return "pages/roomMaster";
    }

    @GetMapping(value = "/selectroom")
    public String getRoom(@ModelAttribute PeopleViewEntity peopleViewEntity
                          ,HttpServletRequest request
                          ,Model model)throws Exception{

     Long aa  = peopleViewEntity.getSeqno();
        String ss = request.getParameter("seqno");
        //등록가능한 방정보
        List<RoomMasterEntity> roomList = roomService.getRoomMasterList("");

        HashMap<String,List<RoomMasterEntity>> resultMap = new HashMap<>();
        resultMap.put("roomlist",roomList);
        model.addAllAttributes(resultMap);

        return "pages/selectRoomPop";
    }

}
