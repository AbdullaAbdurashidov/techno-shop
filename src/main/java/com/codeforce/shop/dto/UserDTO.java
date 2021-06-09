package com.codeforce.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull
    private String id;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @JsonProperty("firstName")
    private String firstName;
    private String lastName;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
