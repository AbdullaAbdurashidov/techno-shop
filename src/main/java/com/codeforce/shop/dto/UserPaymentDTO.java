package com.codeforce.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentDTO {

    private Long id;
    private String userId;
    private Integer paymentType;
    private String provider;
    private LocalDate expiry;

}
