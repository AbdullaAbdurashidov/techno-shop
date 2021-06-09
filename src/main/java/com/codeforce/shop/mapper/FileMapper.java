package com.codeforce.shop.mapper;

import com.codeforce.shop.domain.File;
import com.codeforce.shop.dto.FileDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper extends EntityMapper<FileDTO, File> {

    File toEntity(FileDTO dto);

    FileDTO toDto(File entity);

    List<File> toEntity(List<FileDTO> dtoList);

    List <FileDTO> toDto(List<File> entityList);

    default File from(Long id) {
        if (id == null) {
            return null;
        }
        File file = new File();
        file.setId(id);
        return file;
    }

}
