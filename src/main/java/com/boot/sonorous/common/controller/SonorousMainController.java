package com.boot.sonorous.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SonorousMainController {

    @GetMapping("/")
    public String index(){
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }
}
