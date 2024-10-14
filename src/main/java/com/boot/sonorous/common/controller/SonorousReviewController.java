package com.boot.sonorous.common.controller;

import com.boot.sonorous.common.Util.HttpUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SonorousReviewController {

    @GetMapping("/common/review")
    public String getReview(){
        JSONObject param = new JSONObject();
        JSONObject apiResult = HttpUtil.callApi(param,"GET");
        String score = apiResult.getString("score");
        System.out.println(score);
        return "common/reviewView";
    }
}
