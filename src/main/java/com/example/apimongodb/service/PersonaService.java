package com.example.apimongodb.service;

import java.util.List;
import java.util.Optional;

import com.example.apimongodb.utils.WSPersonas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apimongodb.model.Persona;
import com.example.apimongodb.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository personaRepository;

    public void save(Persona persona) {
        personaRepository.save(persona);
    }

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Optional<Persona> findById(String id) {
        return personaRepository.findById(id);
    }

    public void deleteById(String id) {
        personaRepository.deleteById(id);
    }

    @Autowired
    private WSPersonas consultaWebServices;

    public Persona obtenerDatosWSPersonas(String identificacion) {
        var documentPersona = consultaWebServices.obtenerPersona(identificacion);
        System.out.println("DAVID:" + documentPersona);
        return documentPersona;
    }
}
