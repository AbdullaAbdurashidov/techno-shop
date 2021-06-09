package com.codeforce.shop.rest.controller;

import com.codeforce.shop.domain.Cart;
import com.codeforce.shop.helper.CookieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartRest {

    @Autowired
    private CookieHelper cookieHelper;

    @GetMapping("/cart/add/{product}/{quantity}")
    public Cart addToCart(HttpServletRequest request, @PathVariable("product") String product, @PathVariable("quantity") Integer quantity ){
        String owner = cookieHelper.getHashID(request);
        return null;
    }

    @GetMapping("/cart/delete/{product}")
    public Long deleteFromCart(HttpServletRequest request, @PathVariable("product") String product ){
        String owner = cookieHelper.getHashID(request);
        return null;
    }

    @GetMapping("/order/add")
    public List<Cart> purchase(HttpServletRequest request){
        String owner = cookieHelper.getHashID(request);
        return null;
    }

}
