package com.example.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DoctorDto {
    private long id;
    private String name;
    private String position;
}
