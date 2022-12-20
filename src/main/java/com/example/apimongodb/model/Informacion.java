package com.example.apimongodb.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value = "Informacion")
@Data
public class Informacion {
    @Id
    private String identificacion;
    private List<Persona> persona;
}
