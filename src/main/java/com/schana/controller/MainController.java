package com.schana.controller;

import com.schana.dto.LoginDto;
import com.schana.entity.AssemblyInfoEntity;
import com.schana.service.AssemblyService;
import com.schana.service.CommonService;
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

    @GetMapping("/dashboard")
    public String mainIndex(Model model
                           ,HttpServletRequest request
    ){

        HttpSession session = request.getSession();
        String type = (String)session.getAttribute("logintype");
        AssemblyInfoEntity assemblyinfo = assemblyService.getAssemblyInfo();
        model.addAttribute("assemblyinfoDto",assemblyinfo);

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
    
}
