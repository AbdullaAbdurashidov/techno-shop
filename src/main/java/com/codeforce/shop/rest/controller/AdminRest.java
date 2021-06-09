package com.codeforce.shop.rest.controller;

import com.codeforce.shop.dto.composite.PagingDto;
import com.codeforce.shop.dto.composite.UserTableDto;
import com.codeforce.shop.service.impl.ProductService;
import com.codeforce.shop.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminRest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/users")
    public PagingDto getUsers(@RequestParam(value = "draw", required = false, defaultValue = "1") int draw,
                              @RequestParam(value = "start", required = false, defaultValue = "0") int start,
                              @RequestParam(value = "length", required = false, defaultValue = "10") int length,
                              @RequestParam(value = "order[0][column]", required = false, defaultValue = "0") int order,
                              @RequestParam(value = "order[0][dir]", required = false, defaultValue = "desc") String orderType,
                              @RequestParam(value = "search[value]", required = false, defaultValue = "") String search){
        int page = start == 0 ? 1 : start/length+1;
        PagingDto<UserTableDto> pagingDto = userService.getUserPagingDto(page,length,"col",orderType,search);
        pagingDto.setDraw(draw);
        return pagingDto;
    }

    @RequestMapping("/products")
    public PagingDto getProducts(@RequestParam(value = "draw", required = false, defaultValue = "1") int draw,
                              @RequestParam(value = "start", required = false, defaultValue = "0") int start,
                              @RequestParam(value = "length", required = false, defaultValue = "10") int length,
                              @RequestParam(value = "order[0][column]", required = false, defaultValue = "0") int order,
                              @RequestParam(value = "order[0][dir]", required = false, defaultValue = "desc") String orderType,
                              @RequestParam(value = "search[value]", required = false, defaultValue = "") String search){
        int page = start == 0 ? 1 : start/length+1;
        PagingDto<UserTableDto> pagingDto = productService.getProductPagingDto(page,length,"col",orderType,search);
        pagingDto.setDraw(draw);
        return pagingDto;
    }

}
