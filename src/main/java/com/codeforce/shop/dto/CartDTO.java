package com.codeforce.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private Long id;
    private String userId;
    private String productId;
    private Integer quantity;
    private String status;

}
