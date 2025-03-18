package com.idat.alumnos.service;

import com.idat.alumnos.model.Alumno;
import com.idat.alumnos.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> findAll() {
        return alumnoRepository.findAll().stream().filter(alumno -> alumno.getActivo() == 1).toList();
    }

    public Optional<Alumno> findById(String id) {
        return alumnoRepository.findById(id);
    }

    public Alumno save(Alumno alumno) {
        alumno.setId(UUID.randomUUID().toString());
        alumno.setActivo(1);
        return alumnoRepository.save(alumno);
    }

    public Optional<Alumno> update(String id, Alumno alumno) {
        Optional<Alumno> alumnoEncontrado = alumnoRepository.findById(id);
        if (alumnoEncontrado.isPresent()) {
            alumno.setActivo(1);
            return Optional.of(alumnoRepository.save(alumno));
        }
        return Optional.empty();
    }

    public boolean delete(String id) {
        Optional<Alumno> alumnoEncontrado = alumnoRepository.findById(id);
        if (alumnoEncontrado.isPresent()) {
            Alumno alumno = alumnoEncontrado.get();
            alumno.setActivo(0);
            alumnoRepository.save(alumno);
            return true;
        }
        return false;
    }

}
