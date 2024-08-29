package com.nik.UserAuthService.config;

import com.nik.UserAuthService.Entities.JwtRequest;
import com.nik.UserAuthService.Entities.JwtResponse;
import com.nik.UserAuthService.Services.UserAuthServices;
import com.nik.UserAuthService.security.JwtHelper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserAuthServices userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;
    private final Logger logger = (Logger) LoggerFactory.getLogger(AuthController.class);
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
       try {
           this.doAuthenticate(request.getEmail(), request.getPassword());
           UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
           logger.warn("Here is userDetails {}",userDetails.getUsername());
           String token = this.helper.generateToken(userDetails);
           JwtResponse response = JwtResponse.builder()
                   .jwtToken(token)
                   .username(userDetails.getUsername()).build();
           return new ResponseEntity<>(response, HttpStatus.OK);
       }
       catch (UsernameNotFoundException e) {
           UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
           logger.warn("Here is userDetails2 {}",userDetails.getUsername());
           return new ResponseEntity<>("User not found, please register.", HttpStatus.NOT_FOUND);

       } catch (BadCredentialsException e) {
           UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
           logger.warn("Here is userDetails3 {}",userDetails.getUsername());
           logger.warn("Invalid login attempt: {}", e.getMessage());
           return new ResponseEntity<>("Invalid Username or Password!!", HttpStatus.UNAUTHORIZED);
       } catch (Exception e) {
           logger.error("Unexpected error during login: {}", e.getMessage(), e);
           return new ResponseEntity<>("An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestBody String token) {
        System.out.println("Hit from other source");
        try {
            String username = helper.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            boolean isValid = this.helper.validateToken(token, userDetails);
            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            logger.error("Token validation failed", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        }
        catch (UsernameNotFoundException e) {
            logger.error("User not found: {}", email);
            throw new UsernameNotFoundException("User not found. Please register.");
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for user: {}", email);
            throw new BadCredentialsException("Invalid Username or Password!!");
        }
    }


    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<String> exceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
