package com.example.apimongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value = "Personas")
@Data
public class Persona {

    @Id
    private String id;
    private String nombres;
    private String apellidos;
    private String email;
    private Integer edad;
    private Object data;

}
