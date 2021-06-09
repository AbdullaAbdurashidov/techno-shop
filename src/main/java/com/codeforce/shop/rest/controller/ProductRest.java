package com.codeforce.shop.rest.controller;

import com.codeforce.shop.domain.Discount;
import com.codeforce.shop.domain.ProductCategory;
import com.codeforce.shop.domain.ProductInventory;
import com.codeforce.shop.dto.CartDTO;
import com.codeforce.shop.dto.OrderDto;
import com.codeforce.shop.dto.ProductDTO;
import com.codeforce.shop.dto.composite.ProductTableDto;
import com.codeforce.shop.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRest {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-table")
    public List<ProductDTO> getAll(HttpServletRequest request, HttpServletResponse response){
        List<ProductDTO> ptDto =productService.findAllProductTableDto();
        return ptDto;
    }

    @GetMapping("/test")
    public ProductCategory Test(){
        return productService.getProductCategoryList();
    }

    @PostMapping("/add-product")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO,HttpServletRequest request, HttpServletResponse response){
        return productService.saveProduct(productDTO);
    }

    @PostMapping("/add-product-category")
    public ProductCategory saveProductCategory(@RequestBody ProductCategory item, @RequestParam String productId){
        return productService.saveCategory(item);
    }

    @PostMapping("/ad-product-inventory")
    public ProductInventory saveProductInventory(@RequestBody ProductInventory item, @RequestParam String userId){
        return productService.saveProductInventory(item, userId);
    }

    @PostMapping("/add-product-discount")
    public Discount saveDiscount(@RequestBody Discount item, @RequestParam String userId){
        return productService.saveDiscount(item, userId);
    }

    @PostMapping("/add-product-cart")
    public CartDTO addCart(@RequestBody CartDTO dto){
        return productService.saveCart(dto);
    }

    @PostMapping("/add-product-order")
    public OrderDto saveOrder(@RequestParam String userId){
        return productService.saveOrder(userId);
    }


}
