package com.example.apimongodb.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.Data;

@Document(value = "Personas")
@Data
public class Persona {

    Map<String, Object> persona = new LinkedHashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getPersona() {
        return persona;
    }

    @JsonAnySetter
    public void setPersona(String key, Object value) {
        persona.put(key, value);
    }

}
