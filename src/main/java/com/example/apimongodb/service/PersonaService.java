package com.example.apimongodb.service;

import java.util.List;
import java.util.Optional;

import com.example.apimongodb.utils.WSPersonas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apimongodb.model.Informacion;
import com.example.apimongodb.model.Persona;
import com.example.apimongodb.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository personaRepository;

    public void save(Informacion persona) {
        personaRepository.save(persona);
    }

    public List<Informacion> findAll() {
        return personaRepository.findAll();
    }

    public Optional<Informacion> findById(String id) {
        return personaRepository.findById(id);
    }

    public void deleteById(String id) {
        personaRepository.deleteById(id);
    }

    @Autowired
    private WSPersonas consultaWebServices;

    public Informacion obtenerDatosWSPersonas(String identificacion) {
        var documentPersona = consultaWebServices.obtenerPersona(identificacion);
        System.out.println("DAVID:" + documentPersona);
        return documentPersona;
    }
}
