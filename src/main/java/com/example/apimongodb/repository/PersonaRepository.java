package com.example.apimongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.apimongodb.model.Persona;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, String> {

}
