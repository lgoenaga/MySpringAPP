package com.iudigital.myspringapp.dto.response;

import com.iudigital.myspringapp.model.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserDtoResponse {

    private Long id;
    private String name;
    private String userName;
    private String email;

    private LocalDate dateBirth;
    private boolean enabled;

    private List<Role> roles;
    private String image;

    private LocalDate createdAt;
    private LocalDate updatedAt;

}
