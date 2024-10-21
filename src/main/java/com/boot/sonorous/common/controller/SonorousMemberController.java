package com.boot.sonorous.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SonorousMemberController {

    @GetMapping("/common/memberInfo")
    public String memInfo(){
        return "common/memInfo";
    }

}
