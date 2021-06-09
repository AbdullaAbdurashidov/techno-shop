package com.codeforce.shop.controller;

import com.codeforce.shop.dto.ProductDTO;
import com.codeforce.shop.dto.composite.ProductTableDto;
import com.codeforce.shop.dto.composite.UserCartDto;
import com.codeforce.shop.dto.UserDTO;
import com.codeforce.shop.dto.composite.UserTableDto;
import com.codeforce.shop.helper.CookieHelper;
import com.codeforce.shop.repository.impl.CustomRepository;
import com.codeforce.shop.service.impl.ProductService;
import com.codeforce.shop.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CookieHelper cookieHelper;

    @Autowired
    private CustomRepository customRepository;

    @GetMapping("")
    public String index(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model){
        String Id = cookieHelper.getHashID(request);
        if(Id!=null){
            UserDTO user = userService.getUserById(Id);
            model.addAttribute("user",user);
            List<ProductDTO> ptDto =productService.findAllProductTableDto();
            model.addAttribute("products",ptDto);
        }
        else{
            List<ProductDTO> ptDto=productService.findAllProductTableDto();
            model.addAttribute("products",ptDto);
        }
        customRepository.getUsers();
        return "home";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String hashID = cookieHelper.getHashID(request);
        if(hashID!=null){
            return "redirect:/home";
        }
        else{
            return "login";
        }
    }

    @GetMapping("/cart")
    public String cart(HttpServletRequest request, Model model){
        String Id = cookieHelper.getHashID(request);
        if(Id!=null){
            UserDTO user = userService.getUserById(Id);
            model.addAttribute("user",user);
            List<UserCartDto> userCartList = null;
            model.addAttribute("products",userCartList);
            return "cart";
        }
        else{
            return "redirect:/login";
        }
    }

    @GetMapping("/user/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model){
        Cookie auth = new Cookie("Authentication", null);
        auth.setPath("/");
        auth.setMaxAge(0);
        Cookie user = new Cookie("UserID",null);
        user.setPath("/");
        user.setMaxAge(0);
        response.addCookie(auth);
        response.addCookie(user);
        return "redirect:/home";
    }

    @GetMapping("/about")
    public String aboutMe(HttpServletRequest request, Model model){
        String hashID = cookieHelper.getHashID(request);
        if(hashID!=null){
            UserTableDto userDto = userService.getUserTableByHashID(hashID);
            model.addAttribute("user", userDto);
            return "about";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/orders")
    public String purchases(HttpServletRequest request, Model model){
        String hashID = cookieHelper.getHashID(request);
        if(hashID!=null){
            UserTableDto userDto = userService.getUserTableByHashID(hashID);
            model.addAttribute("user", userDto);
            List<UserCartDto> userCartList = null;
            model.addAttribute("products",userCartList);
            return "orders";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin")
    public String adminPage(HttpServletRequest request, Model model){
        String Id = cookieHelper.getHashID(request);
        if(Id!=null) {
            UserDTO dto = userService.getUserById(Id);
            model.addAttribute("user",dto);
        }
        return "admin";
    }

}
