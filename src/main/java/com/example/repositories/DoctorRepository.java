package com.example.repositories;

import com.example.model.DoctorDto;

import java.util.List;

public interface DoctorRepository {
    List<DoctorDto> findAll();
    int addDoctor(DoctorDto d);
    List<DoctorDto> findDoctors(String name);
    void addPosition(String name);
}
