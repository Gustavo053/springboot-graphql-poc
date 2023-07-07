package com.example.demo.controller;

import com.example.demo.models.Doctor;
import com.example.demo.models.Patient;
import com.example.demo.service.DoctorService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @QueryMapping
    public List<Doctor> allDoctor() {
        return doctorService.findAll();
    }

    @QueryMapping
    public Doctor doctorById(@Argument Long id, DataFetchingEnvironment env) throws Exception {
        return doctorService.findById(id);
    }

    @SchemaMapping
    public List<Patient> patients(Doctor doctor) {
        return doctorService.findAllByIdDoctor(doctor.getId());
    }
}
