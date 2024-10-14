package com.boot.sonorous.user.controller;

import com.boot.sonorous.common.Util.HttpUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SonorousRsvContoller {

    @GetMapping("/user/rsvList")
    public String rsvList (Model model){
        JSONObject param = new JSONObject();
        JSONObject apiResult = HttpUtil.callApi(param,"GET");
        String score = apiResult.getString("score");
        String reviewContent = apiResult.getString("reviewContent");
        String mid = apiResult.getString("mid");
        String roomId = apiResult.getString("roomId");
        model.addAttribute("score", score);
        model.addAttribute("reviewContent", reviewContent);
        model.addAttribute("mid", mid);
        model.addAttribute("roomId", roomId);

        return "user/rsvList";
    }
}
