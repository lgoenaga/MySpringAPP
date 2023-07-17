package com.iudigital.myspringapp.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
public class CaseDtoResponse {

    private Long id;
    private String description;
    private LocalDate dateCase;
    private Float latitude;
    private Float longitude;
    private Float altitude;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Long user_id;
    private String user_name;
    private Long crime_id;
    private String crime_name;
}
