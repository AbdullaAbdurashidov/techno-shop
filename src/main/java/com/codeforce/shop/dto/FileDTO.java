package com.codeforce.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

    private Long Id;
    private String ownerId;
    private String extension;
    private String contentType;
    private String path;

}
