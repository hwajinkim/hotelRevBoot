package com.boot.sonorous.common.controller.api;
import com.boot.sonorous.common.dto.SignInDto;
import com.boot.sonorous.common.entity.JwtToken;
import com.boot.sonorous.common.entity.RefreshToken;
import com.boot.sonorous.common.service.SonorousMemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class SonorousLoginApiController {

    @Autowired
    SonorousMemberService sonorousMemberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInDto signInDto, HttpServletResponse response){
        try {
            String username = signInDto.getUsername();
            String password = signInDto.getPassword();
            JwtToken jwtToken = sonorousMemberService.signIn(username, password);

            Cookie cookie = new Cookie("refreshToken", jwtToken.getRefreshToken());
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(cookie);

            sonorousMemberService.insertRefreshToken(jwtToken.getRefreshToken());

            return ResponseEntity.ok(jwtToken.getAccessToken());
        } catch(AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    @GetMapping("/authorization")
    public ResponseEntity<?> authorization() {
        return ResponseEntity.ok("success");
    }

    @GetMapping("/accessChk")
    public ResponseEntity<?> checkAccessToken(@RequestHeader("Authorization") String token){
        String accessToken = token.replace("Bearer ", "");
        String reply= "";
        if(!sonorousMemberService.isTokenExpired(accessToken)){
            reply = "AccessToken expired";
        }else{
            reply = "AccessToken is valid";
        }
        return ResponseEntity.ok(reply);
    }

    @GetMapping("/reissue")
    public ResponseEntity<?> reissueAccessToken(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String refreshToken = "";
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("refreshToken".equals(cookie.getName())){
                    refreshToken = cookie.getValue();
                }
            }
        }
        String newAccessToken="";
        // refreshToken이 유효하면
        if(sonorousMemberService.isTokenExpired(refreshToken)){
            JwtToken jwtToken = sonorousMemberService.reissueToken(refreshToken);
            newAccessToken = jwtToken.getAccessToken();
        }
        return ResponseEntity.ok(newAccessToken);
    }

    @GetMapping("/refreshChk")
    public ResponseEntity<?> checkRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String refreshToken="";
        String reply="";
        if(cookies != null){
            for (Cookie cookie : cookies){
                if("refreshToken".equals(cookie.getName())){
                    refreshToken = cookie.getValue();
                }
            }
        }
        if(refreshToken != null && !refreshToken.isEmpty()){

            boolean result = sonorousMemberService.isTokenExpired(refreshToken);

            if(!sonorousMemberService.isTokenExpired(refreshToken)){

                //DB에서 만료된 refreshToken 삭제
                sonorousMemberService.deleteRefreshToken(refreshToken);

                // 쿠키에서 refreshToken 삭제
                Cookie cookie = new Cookie("refreshToken", null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                response.addCookie(cookie);

                reply = "Refresh token is expired";
            }else{
                reply = "Refresh token is valid";
            }
        }
        return ResponseEntity.ok(reply);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String refreshToken="";
        String reply="";
        if(cookies != null){
            for (Cookie cookie : cookies){
                if("refreshToken".equals(cookie.getName())){
                    refreshToken = cookie.getValue();
                }
            }
        }

        if(refreshToken != null && !refreshToken.isEmpty()) {
            if (sonorousMemberService.isTokenExpired(refreshToken)) {
                // DB에서 만료된 refreshToken 삭제
                sonorousMemberService.deleteRefreshToken(refreshToken);

                // 쿠키에서 refreshToken 삭제
                Cookie cookie = new Cookie("refreshToken", null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                response.addCookie(cookie);

                reply = "Logout completed";
            } else {
                reply = "Refresh token is expired";
            }
        }
        return ResponseEntity.ok(reply);
    }
}
