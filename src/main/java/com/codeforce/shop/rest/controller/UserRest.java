package com.codeforce.shop.rest.controller;

import com.codeforce.shop.dto.UserAddressDTO;
import com.codeforce.shop.dto.UserDTO;
import com.codeforce.shop.dto.UserPaymentDTO;
import com.codeforce.shop.dto.composite.UserTableDto;
import com.codeforce.shop.helper.CookieHelper;
import com.codeforce.shop.jwt.JwtRequest;
import com.codeforce.shop.jwt.JwtResponse;
import com.codeforce.shop.jwt.JwtTokenUtil;
import com.codeforce.shop.jwt.JwtUserDetailsService;
import com.codeforce.shop.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRest {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private CookieHelper cookieHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/all/table")
    public List<UserTableDto> userGetAll(HttpServletRequest request, HttpServletResponse response) {
        List<UserTableDto> utDto = userService.findAllUserTableDto();
        return utDto;
    }

    @GetMapping("/all")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) throws Exception{
        authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        UserDTO user = userService.findByUsername(jwtRequest.getUsername());
        Cookie cookie1 = cookieHelper.setCookie("Authentication",token,5 * 60 * 60 * 1000,"/");
        Cookie cookie2 = cookieHelper.setCookie("UserID", user.getId(),5 * 60 * 60 * 1000,"/");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/add")
    public UserDTO saveUser(@RequestBody UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }

    @PostMapping("/add-user-address")
    public UserAddressDTO saveUserAddress(@RequestBody UserAddressDTO dto, @RequestParam String userId){
        return userService.saveUserAddress(dto, userId);
    }

    @PostMapping("/add/payment")
    public UserPaymentDTO saveUserPayment(@RequestBody UserPaymentDTO dto, @RequestParam String userId){
        return userService.saveUserPayment(dto, userId);
    }



    private void authenticate(String username, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException e){
            throw new Exception("User is disabled", e);
        }catch(BadCredentialsException e){
            throw new Exception("Invalid credentials", e);
        }
    }
}
