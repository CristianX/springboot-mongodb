package com.example.apimongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.apimongodb.model.Informacion;

@Repository
public interface PersonaRepository extends MongoRepository<Informacion, String> {

}
