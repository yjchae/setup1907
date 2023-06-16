package com.schana.controller;

import com.schana.dto.RoomDto;
import com.schana.entity.PeopleEntity;
import com.schana.entity.PeopleViewEntity;
import com.schana.entity.RoomEntity;
import com.schana.entity.RoomMasterEntity;
import com.schana.service.PeopleService;
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
    @Autowired
    private PeopleService peopleService;

    @GetMapping("/list")
    public String getRoomInfo(Model model){

        //등록된 방정보
        List<RoomEntity> roomList = roomService.getRoomList();

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

        Long peopleSeqno  = peopleViewEntity.getSeqno();

        //String ss = request.getParameter("seqno");
        //등록가능한 방정보
        List<RoomMasterEntity> roomList = roomService.getRoomMasterList("");

        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("roomlist",roomList);
        resultMap.put("peopleSeqno",peopleSeqno);

        model.addAllAttributes(resultMap);

        return "pages/selectRoomPop";
    }

    @GetMapping(value = "/save")
    public String saveRoom(
            HttpServletRequest request
            ,Model model)throws Exception{

        String roomSeqno  = request.getParameter("rseqno"); //방 seqno
        String peopleSeqno  = request.getParameter("pseqno");   //사람 seqno

        //방등록
        String result = roomService.saveRoom(roomSeqno,peopleSeqno);

        HashMap<String,Object> resultMap = new HashMap<>();

        resultMap.put("message",result);
        model.addAttribute("params",resultMap);

        return "pages/selectRoomPop";
    }

}
