package com.codeforce.shop.service.impl;


import com.codeforce.shop.dto.FileDTO;
import com.codeforce.shop.mapper.FileMapper;
import com.codeforce.shop.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper){
        this.fileMapper = fileMapper;
    }

    private Date now=new Date();

    private String uploadFolder="D:/CODING/Spring Boot/shop/src/main/webapp/resource/files";

    private String getExtension(String fileName){
        String ext="";
        if(fileName!=null && !fileName.isEmpty()){
            int dot=fileName.lastIndexOf('.');
            if(dot>0&& dot<=fileName.length()-2){
                ext=fileName.substring(dot+1);
            }
        }
        return ext;
    }

    public FileDTO saveFile(MultipartFile file, String ownerHashID){

        if(ownerHashID==null || ownerHashID.equals("")){
            System.out.println("ownerHashId is null or empty:  " + ownerHashID);
            return null;
        }
        String fileName=file.getOriginalFilename();
        String fileExtension=getExtension(fileName);
        String contentType=file.getContentType();

        String halfPath=String.format("/uploaded/%d/%d/%d/",
                1900+now.getYear(),
                1+now.getMonth(),
                now.getDate());

        System.out.println(halfPath);

        File folderForUpload=new File(String.format("%s"+halfPath, this.uploadFolder));
        if(!folderForUpload.exists() && folderForUpload.mkdirs()){
            System.out.println("Folders created");
        }
        else{
            System.out.println("Folders existed");
        }

        folderForUpload=folderForUpload.getAbsoluteFile();


        File fileToUpload=new File(folderForUpload,String.format("%s.%s",
                ownerHashID,
                fileExtension));

        FileDTO fileDTO=new FileDTO();
        fileDTO.setOwnerId(ownerHashID);
        fileDTO.setExtension(fileExtension);
        fileDTO.setPath(String.format(halfPath+"%s.%s",
                ownerHashID,
                fileExtension));
        fileDTO.setContentType(contentType);

        fileRepository.save(fileMapper.toEntity(fileDTO));

        try {
            file.transferTo(fileToUpload);
            return fileDTO;
        }
        catch (IOException e){
            System.out.println("Error while transferring file");
            return null;
        }

    }

    public FileDTO getFileByOwnerHashID(String hashID){
        return fileMapper.toDto(fileRepository.findByOwnerId(hashID));
    }



}
