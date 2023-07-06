package com.schana.controller;

import com.schana.dto.AdminMemberDto;
import com.schana.dto.LoginDto;
import com.schana.dto.PeopleDto;
import com.schana.dto.RoomDto;
import com.schana.entity.AssemblyInfoEntity;
import com.schana.entity.MemberEntity;
import com.schana.entity.PeopleViewEntity;
import com.schana.entity.RoomInfoEntity;
import com.schana.service.AssemblyService;
import com.schana.service.CommonService;
import com.schana.service.PeopleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AssemblyService assemblyService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private PeopleService peopleService;

    @GetMapping("/dashboard")
    public String mainIndex(Model model
                           ,HttpServletRequest request
    ){

        HttpSession session = request.getSession();
        String type = (String)session.getAttribute("logintype");
        AssemblyInfoEntity assemblyinfo = assemblyService.getAssemblyInfo();
        PeopleDto peopleDto = peopleService.getPeopleDayCnt();

        model.addAttribute("assemblyinfoDto",assemblyinfo);
        model.addAttribute("peopleDto",peopleDto);



        return "pages/index";
    }

    @GetMapping("/index")
    public String mainLogin(Model model){
        model.addAttribute("LoginDto",new LoginDto());

        return "pages/login";
    }

//    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String mainDashboard(Model model
                                , @ModelAttribute LoginDto loginDto
                                , ModelAndView modelAndView
                                , HttpSession session
                                )  {

        LoginDto logininfo = commonService.getMember(loginDto);
        HashMap<String,String> paramMap = new HashMap<>();

        if(logininfo == null){
//            model.addAttribute("loginType",null);
            paramMap.put("message","비밀번호가 다릅니다.");
            paramMap.put("href","index");
        }else{
//            model.addAttribute("loginType",logininfo.getType());
            paramMap.put("message","");
            paramMap.put("href","dashboard");
            session.setAttribute("logintype",logininfo.getType());

        }
        model.addAllAttributes(paramMap);

        return "pages/message";
    }

    /**
     * 관리자등록
     * @param model
     * @return
     */
    @GetMapping("/regAdmin")
    public String regAdmin(Model model){

        model.addAttribute("adminMemberDto",new AdminMemberDto());
        return "pages/makeMember";
    }

    /**
     * 관리자 등록
     * @param adminMemberDto
     * @param modelAndView
     * @param model
     * @return
     */
    @RequestMapping(value = "/makeAdmin" ,method = RequestMethod.POST)
    public String makeRoom(
            @ModelAttribute("adminMemberDto") AdminMemberDto adminMemberDto
            , ModelAndView modelAndView
            , Model model){

        commonService.createAdmin(adminMemberDto);

        HashMap<String,String> paramMap = new HashMap<>();
        paramMap.put("message","저장되었습니다.");
        paramMap.put("href","regAdmin");
//
        model.addAllAttributes(paramMap);

        return "pages/message";
    }

    /**
     * 관리자 조회
     * @param request
     * @param session1
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/viewMember")
    public String getRoom(HttpServletRequest request
            ,HttpSession session1
            ,Model model)throws Exception{

        //등록가능한 방정보
        List<MemberEntity> memberList = commonService.getMemberList();

        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("memberList",memberList);

        model.addAllAttributes(resultMap);

        return "pages/viewMember";
    }
    
}
