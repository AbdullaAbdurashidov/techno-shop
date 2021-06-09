package com.codeforce.shop.dto.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingDto<T> {

    private Integer draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;

}
