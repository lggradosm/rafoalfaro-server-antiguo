package com.rafoalfaro.server.controller;

import com.rafoalfaro.server.conf.jwt.JwtTokenUtil;
import com.rafoalfaro.server.domain.Employee;
import com.rafoalfaro.server.domain.TokenResponse;
import com.rafoalfaro.server.domain.User;
import com.rafoalfaro.server.service.EmployeeService;
import com.rafoalfaro.server.service.UserDetailsServiceImp;
import com.rafoalfaro.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api")
@CrossOrigin(origins = "*")
public class AuthenticateController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsServiceImp userDetailsService;

    @Autowired private UserService userService;

    @Autowired private EmployeeService employeeService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> getToken(@RequestBody User user) throws Exception {
        authenticate(user.getUsername(),user.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(token));
//        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/verify")
    public ResponseEntity<?> validateToken(@RequestBody TokenResponse token){
            token.setToken(token.getToken().substring(7));
        String username = jwtTokenUtil.getUsernameFromToken(token.getToken());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(token.getToken(),userDetails))
         return ResponseEntity.ok(getEmployeeByToken(username));
        else return ResponseEntity.notFound().build();
    }

    private Employee getEmployeeByToken(String username){
        return employeeService.findEmployeeByUser(userService.findUserByUsername(username));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
