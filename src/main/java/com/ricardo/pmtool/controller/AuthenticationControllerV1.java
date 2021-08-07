package com.ricardo.pmtool.controller;

import com.ricardo.pmtool.data.AuthenticationData;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import com.ricardo.pmtool.security.JwtTokenProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Profile("!test")
public class AuthenticationControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationControllerV1(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationData request){
       try{
        String email = request.getEmail();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User does not exist"));
        String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name(), user.getId(), user.getUsername());
        Map<Object,Object> response = new HashMap<>();
        response.put("email", request.getEmail());
        response.put("token", token);
        return ResponseEntity.ok(response);
       }catch (AuthenticationException e){
           return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
       }
    }

    @PostMapping("/logout")
    public void logout(@RequestBody HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response,null);
    }
}
