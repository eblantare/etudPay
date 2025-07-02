package com.blt.etud.etudpaybackend.securities.controlers;

// import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.AuthenticationException;
import com.blt.etud.etudpaybackend.securities.dtos.LoginRequest;

@RestController
@RequestMapping("/api/public")
public class AuthController {
    @Autowired 
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), 
                    request.getPassword())
            );
            String username = authentication.getName();
            var roles = authentication.getAuthorities();
            return ResponseEntity.ok("Bienvenue " + username + " avec rôles : " + roles.toString());
                // System.out.println("Username: " + request.getUsername());
                // System.out.println("Password: " + request.getPassword());

       
            
        }catch(AuthenticationException e){
           return ResponseEntity.status(401).body("Invalid credentials ");
        }
        
        
    }

}
