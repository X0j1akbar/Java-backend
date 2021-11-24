package uz.malga.logisticcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.malga.logisticcompany.entity.User;
import uz.malga.logisticcompany.payload.SignIn;
import uz.malga.logisticcompany.secret.JwtProvider;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtProvider provider;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody SignIn signIn){
        Authentication authenticate = manager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = provider.generateToken((User) authenticate.getPrincipal());
        return ResponseEntity.ok(token);
    }
}
