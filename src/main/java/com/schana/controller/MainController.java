package com.schana.controller;

import com.schana.entity.AssemblyInfoEntity;
import com.schana.service.AssemblyService;
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
@RequestMapping(value = "/")
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AssemblyService assemblyService;

    @GetMapping("/index")
    public String mainIndex(Model model){

        AssemblyInfoEntity assemblyinfo = assemblyService.getAssemblyInfo();
        model.addAttribute("assemblyinfoDto",assemblyinfo);

        return "pages/index";
    }
    
}
