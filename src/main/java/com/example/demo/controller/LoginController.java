package com.example.demo.controller;

import com.example.demo.entity.AuthRequest;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/getCos")
    public String getCos() {
        return "Cos";
    }
    @GetMapping("/")
    public String hello() {
        return "hello :)";
    }

    @PostMapping("/auth")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("invalid credetials");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }
    @GetMapping("/info")
    @ResponseBody
    public String userInfo(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            return "Hello, " + principal.getAttribute("name");
        }
        return "User not authenticated";
    }
    @GetMapping("/info/user")
    @ResponseBody
    public String userInfo2(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            return "Hello, " + principal.getAttribute("name");
        }
        return "User not authenticated";
    }
}
