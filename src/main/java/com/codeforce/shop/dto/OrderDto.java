package com.codeforce.shop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {

    private Long id;
    private String userId;
    private Double total;
    private Long paymentId;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
