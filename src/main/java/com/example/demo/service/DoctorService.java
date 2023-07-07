package com.example.demo.service;

import com.example.demo.models.Doctor;
import com.example.demo.models.Patient;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private List<Doctor> doctors1 = new ArrayList<>();
    private List<Doctor> doctors2 = new ArrayList<>();

    @PostConstruct
    private void setupDoctor() {
        doctors1 = Arrays.asList(
                new Doctor(1L, "Gustavo", Arrays.asList(new Patient(1L, "Teste", 24, 1L), new Patient(4L, "Testee", 18, 1L))),
                new Doctor(2L, "Fernando", Arrays.asList(new Patient(2L, "Teste2", 27, 2L))),
                new Doctor(3L, "Tiago", Arrays.asList(new Patient(3L, "Teste3", 20, 3L)))
        );

        doctors2 = Arrays.asList(
                new Doctor(4L, "Fulano", Arrays.asList(new Patient(4L, "Teste", 24, 4L), new Patient(7L, "Testee", 18, 4L))),
                new Doctor(5L, "Cicrano", Arrays.asList(new Patient(5L, "Teste2", 27, 5L))),
                new Doctor(6L, "Beltrano", Arrays.asList(new Patient(6L, "Teste3", 20, 6L)))
        );
    }

    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.addAll(doctors1);
        doctors.addAll(doctors2);

        return doctors;
    }

    public Doctor findById(Long id) throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        doctors.addAll(doctors1);
        doctors.addAll(doctors2);

        if (id.equals(2L)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema ao processar dados");
        }

        return doctors.stream()
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Patient> findAllByIdDoctor(Long idDoctor) {
        List<Doctor> doctors = new ArrayList<>();
        doctors.addAll(doctors1);
        doctors.addAll(doctors2);

        List<Patient> patients = new ArrayList<>();

        doctors.forEach(doctor -> {
            patients.addAll(doctor.getPatients().stream().filter(patient -> patient.getIdDoctor().equals(idDoctor)).collect(Collectors.toList()));
        });

        return patients;
    }
}
