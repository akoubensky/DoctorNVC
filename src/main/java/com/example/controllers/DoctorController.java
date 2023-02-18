package com.example.controllers;

import com.example.model.DoctorDto;
import com.example.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorRepository doctorRepository;

    @GetMapping
    public List<DoctorDto> findAll() {
        return doctorRepository.findAll();
    }

    @GetMapping("/{name}")
    public List<DoctorDto> findDoctor(@PathVariable("name") String name) {
        return doctorRepository.findDoctors(name);
    }

    @PostMapping
    public void addDoctor(@RequestBody DoctorDto d) {
        doctorRepository.addDoctor(d);
    }

    @PostMapping("/position/{name}")
    public void addPosition(@PathVariable("name") String name) {
        doctorRepository.addPosition(name);
    }
}
