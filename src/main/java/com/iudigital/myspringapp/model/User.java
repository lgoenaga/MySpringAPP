package com.iudigital.myspringapp.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@OnDelete(action = OnDeleteAction.CASCADE)
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    @Size(max = 60, message = "Name must be max 60 characters")
    private String name;
    @Column(unique = true, nullable = false, length = 20)
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    private String userName;
    @Column(unique = true, nullable = false, length = 50)
    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    @Column(nullable = false)
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;
    @Column(nullable = false)
    @NotNull(message = "Enabled is required")
    private Boolean enabled;

    private LocalDate dateBirth;
    private String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}
