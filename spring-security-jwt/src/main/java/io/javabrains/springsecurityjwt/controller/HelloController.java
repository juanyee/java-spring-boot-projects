package io.javabrains.springsecurityjwt.controller;

import io.javabrains.springsecurityjwt.model.AuthenticationRequest;
import io.javabrains.springsecurityjwt.model.AuthenticationResponse;
import io.javabrains.springsecurityjwt.service.MyUserDetailsService;
import io.javabrains.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    /*
    * To return a simple text using Controller:
    *
    * @Controller
    * @RequestMapping({"/hello"})
    * @ResponseBody //without @ResponseBody, the controller responds 404
    *
    * @RestController
    * @RequestMapping("/hello")
    *
    * @RestController
    * @GetMapping("/hello")
    * */
}
