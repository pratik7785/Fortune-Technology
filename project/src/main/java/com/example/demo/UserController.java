package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")



public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired

    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/authentication")
    public  ResponseEntity<?> createauthenticationreq(@RequestBody AuthenticationVer authenticationVer) throws Exception{
        try{
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationVer.getUsername(),authenticationVer.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt=jwtUtil.generateToken(userService);

            return ResponseEntity.ok(new AuthenticationVer(jwt));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username and password");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationReq req){
        userService.registerUser(req);
        return ResponseEntity.ok("Resgistartion Sucssefull");
    }


}
