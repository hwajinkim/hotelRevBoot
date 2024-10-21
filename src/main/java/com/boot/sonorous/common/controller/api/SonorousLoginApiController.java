package com.boot.sonorous.common.controller.api;
import com.boot.sonorous.common.dto.SignInDto;
import com.boot.sonorous.common.entity.JwtToken;
import com.boot.sonorous.common.service.SonorousMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class SonorousLoginApiController {

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
