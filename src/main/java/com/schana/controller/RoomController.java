package com.schana.controller;

import com.schana.dto.Message;
import com.schana.dto.RoomDto;
import com.schana.entity.*;
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
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String getRoomMasterInfo(Model model
                                    ,HttpServletRequest request
                                    ){

        HttpSession session = request.getSession();
        String type = (String)session.getAttribute("logintype");

        //등록된 방정보
        List<RoomInfoEntity> roomList = roomService.getRoomInfoList(type);

        HashMap<String,List<RoomInfoEntity>> resultMap = new HashMap<>();

        resultMap.put("roomlist",roomList);
        model.addAllAttributes(resultMap);

        return "pages/roomMaster";
    }


    @GetMapping(value = "/selectroom")
    public String getRoom(@ModelAttribute PeopleViewEntity peopleViewEntity
                          ,HttpServletRequest request
                          ,HttpSession session1
                          ,Model model)throws Exception{

        Long peopleSeqno  = peopleViewEntity.getSeqno();

        HttpSession session = request.getSession();
        session1.setAttribute("filtertext",request.getParameter("filtertext"));
        String type = (String)session.getAttribute("logintype");

        String peopleSeqArr = request.getParameter("seqno_arr");

        //등록가능한 방정보
        List<RoomInfoEntity> roomList = roomService.getRoomInfoList(type);

        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("roomlist",roomList);
        resultMap.put("peopleSeqno",peopleSeqno);
        resultMap.put("peopleSeqArr",peopleSeqArr);

        model.addAllAttributes(resultMap);

        return "pages/selectRoomPop";
    }

//    @GetMapping(value = "/save")
//    public String saveRoom(
//            HttpServletRequest request
//            ,Model model)throws Exception{
//
//        String roomSeqno  = request.getParameter("rseqno"); //방 seqno
//        String peopleSeqno  = request.getParameter("pseqno");   //사람 seqno
//
//        //방등록
//        String result = roomService.saveRoom(roomSeqno,peopleSeqno);
//
////        HashMap<String,Object> resultMap = new HashMap<>();
////
////        resultMap.put("message",result);
////        model.addAttribute("params",resultMap);
//
//        return "pages/selectRoomPop";
//    }

    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String saveRoom(
            HttpServletRequest request
            , HttpSession session
            , Model model)throws Exception{

        String roomSeqno  = request.getParameter("rseqno"); //방 seqno
        String peopleSeqno  = request.getParameter("pseqno");   //사람 seqno
        String push = request.getParameter("push");
        String peopleSeqnoArr = request.getParameter("pseqnoarr");

        //방등록
        String result = roomService.saveRoom(roomSeqno,peopleSeqno,push,peopleSeqnoArr);

        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/deleteroom",method = RequestMethod.GET)
    public String deleteRoom(
            HttpServletRequest request
            , HttpSession session
            , Model model)throws Exception{

        String seqno  = request.getParameter("seqno");   //seqno


        return roomService.deleteRoom(seqno);
    }

    @GetMapping("/reginfo")
    public String regRoom(Model model){

        model.addAttribute("roomDto",new RoomDto());
        return "pages/makeRoom";
    }


    @RequestMapping(value = "/make" ,method = RequestMethod.POST)
    public String makeRoom(
            @ModelAttribute("roomDto") RoomDto roomDto
            , ModelAndView modelAndView
            , Model model){

        roomService.createRoom(roomDto);

        HashMap<String,String> paramMap = new HashMap<>();
        paramMap.put("message","저장되었습니다.");
        paramMap.put("href","reginfo");
//
        model.addAllAttributes(paramMap);

        return "pages/message";
    }

    /**
     * 방정보조회 master - 선택삭제
     * @param request
     * @param modelAndView
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deletemaster",method = RequestMethod.GET)
    public String deleteRoom(
            HttpServletRequest request
            , ModelAndView modelAndView
            , Model model){

        String seqnoarr = request.getParameter("roomSeqnoArr");

        roomService.deleteRoomMaster(seqnoarr);

        HashMap<String,String> paramMap = new HashMap<>();
        paramMap.put("message","삭제되었습니다.");
        paramMap.put("href","room");
//
        model.addAllAttributes(paramMap);

        return "pages/message";
    }

}
