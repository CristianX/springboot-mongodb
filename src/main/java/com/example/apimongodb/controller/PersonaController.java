package com.example.apimongodb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apimongodb.model.Informacion;
import com.example.apimongodb.model.Persona;
import com.example.apimongodb.service.PersonaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping("/save")
    public Informacion save(@RequestBody Informacion persona) {
        personaService.save(persona);
        return persona;
    }

    @GetMapping("/all")
    public List<Informacion> findAll() {
        return personaService.findAll();
    }

    @GetMapping("/informacion-identificacion-buscar-crear/{identificacion}")
    public Informacion findByIdIfFalseCreate(@PathVariable("identificacion") String identificacion) {
        try {
            var existe = personaService.findById(identificacion).get();
            System.out.println("Existe el documento");
            return existe;
        } catch (Exception e) {
            System.out.println("No existe el documento");
            var documentPersona = personaService.obtenerDatosWSPersonas(identificacion);
            personaService.save(documentPersona);
            return documentPersona;
        }

    }

    @GetMapping("/informacion-identificacion/{identificacion}")
    public Informacion findById(@PathVariable("identificacion") String identificacion) {
        return personaService.findById(identificacion).get();
    }

    @DeleteMapping("/personas/{id}")
    public void deleteById(@PathVariable String id) {
        personaService.deleteById(id);
    }
    /*
     * @PutMapping("/personas")
     * public void update(@RequestBody Persona persona) {
     * personaService.save(persona);
     * }
     */

    @GetMapping(path = "/informacion/{identificacion}", produces = "application/json")
    public Object buscarPersona(@PathVariable("identificacion") String identificacion) {
        var documentPersona = personaService.obtenerDatosWSPersonas(identificacion);
        personaService.save(documentPersona);
        return documentPersona;
    }

}
