package com.codeforce.shop.rest.controller;

import com.codeforce.shop.dto.FileDTO;
import com.codeforce.shop.service.impl.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class CommonRest {

    public CommonRest(FileService fileService){
        this.fileService = fileService;
    }

    private final FileService fileService;



    @PostMapping("/add/file")
    public FileDTO saveFileWithOwner(MultipartFile file, String ownerId){
        return fileService.saveFile(file, ownerId);
    }



}
