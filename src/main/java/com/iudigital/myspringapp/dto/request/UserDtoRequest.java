package com.iudigital.myspringapp.dto.request;

import com.iudigital.myspringapp.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserDtoRequest {


    @Size(max = 60, message = "Name must be max 60 characters")
    private String name;

    @NotNull(message = "Username is required DTO")
    @NotEmpty(message = "Username is required DTO")
    private String userName;


    @NotNull(message = "Email is required")
    @Size(min=6, max = 60, message = "Email must be between 6 and 60 characters")
    @Email(message = "Email must be valid")
    private String email;
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String image;
    private LocalDate dateBirth;

    private Boolean enabled;

    private List<Role> roles;

}
