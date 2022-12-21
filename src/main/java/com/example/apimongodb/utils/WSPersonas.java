package com.example.apimongodb.utils;

import com.example.apimongodb.model.Informacion;
import com.example.apimongodb.model.Persona;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Component
public class WSPersonas {

    private Document xmlDocument;

    @Autowired
    private ClsUtilitario clsUtilitario;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Generar autentificacion basica
     * 
     * @param username
     * @param password
     * @return
     */
    private static final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    public Informacion obtenerPersona(String identificacion) {
        try {

            XmlMapper xmlMapper = new XmlMapper();
            Persona persona = new Persona();
            Informacion informacion = new Informacion();
            List<Persona> personaList = new ArrayList();

            String urlString = "http://interoperabilidad.dinardap.gob.ec/interoperador?WSDL";
            var body = String.format(clsUtilitario.fichaGeneral(identificacion));
            JsonNode node = consultaServicioWeb(urlString,identificacion,body,"InDrOmQdTa","2QK@71PSp/eDcH");
            
            String urlString1 = "http://interoperabilidad.dinardap.gob.ec/interoperador-v2";
            var body1 = String.format(clsUtilitario.fichaGeneral1(identificacion));
            JsonNode node1 = consultaServicioWeb(urlString1,identificacion,body1,"InDrOmQdTa","2QK@71PSp/eDcH");

            persona = xmlMapper.treeToValue(node.get("Body").get("getFichaGeneralResponse").get("return"),Persona.class);
            personaList.add(persona);

            persona = xmlMapper.treeToValue(node1.get("Body").get("consultarResponse"), Persona.class);
            personaList.add(persona);

            informacion.setPersona(personaList);
            informacion.setIdentificacion(identificacion);
            
            return informacion;
        } catch (Exception e) {
            return null;
        }

    }

    

    public JsonNode consultaServicioWeb(String urlString, String identificacion,String body, String username, String password) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
            headers.add("Content-type", "text/xml;charset=utf-8");
            headers.add("Authorization", getBasicAuthenticationHeader(username, password));
            HttpEntity<String> response = restTemplate.exchange(
                    urlString,
                    HttpMethod.POST,
                    new HttpEntity<Object>(body, headers),
                    String.class);

            var rawXmlResponse = response.getBody();

            XmlMapper xmlMapper = new XmlMapper();
            JsonNode node = xmlMapper.readTree(rawXmlResponse.getBytes());

            return node;
        } catch (Exception e) {
            return null;
        }

    }

}
