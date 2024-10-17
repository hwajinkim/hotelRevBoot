package com.boot.sonorous.common.controller;
import com.boot.sonorous.common.dto.SignInDto;
import com.boot.sonorous.common.entity.JwtToken;
import com.boot.sonorous.common.security.JwtAuthenticationFilter;
import com.boot.sonorous.common.security.JwtTokenProvider;
import com.boot.sonorous.common.service.SonorousMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class SonorousAuthController {

    @Autowired
    SonorousMemberService sonorousMemberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInDto signInDto){
        try {
            String username = signInDto.getUsername();
            String password = signInDto.getPassword();
            JwtToken jwtToken = sonorousMemberService.signIn(username, password);
            return ResponseEntity.ok(jwtToken);
        } catch(AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    @GetMapping("/authorization")
    public ResponseEntity<?> authorization() {
        return ResponseEntity.ok("success");
    }
}
