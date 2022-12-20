package com.example.apimongodb.utils;

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
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

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

    public Persona obtenerPersona(String identificacion) {

        Document document = null;
        try {

            RestTemplate restTemplate = new RestTemplate();
            String urlString = "http://interoperabilidad.dinardap.gob.ec/interoperador?WSDL";

            var body = String.format(clsUtilitario.fichaGeneral(identificacion));

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
            headers.add("Content-type", "text/xml;charset=utf-8");
            headers.add("Authorization", getBasicAuthenticationHeader("InDrOmQdTa", "2QK@71PSp/eDcH"));
            HttpEntity<String> response = restTemplate.exchange(
                    urlString,
                    HttpMethod.POST,
                    new HttpEntity<Object>(body, headers),
                    String.class);

            var rawXmlResponse = response.getBody();

            XmlMapper xmlMapper = new XmlMapper();
            JsonNode node = xmlMapper.readTree(rawXmlResponse.getBytes());

            // ArrayNode arrayNode = mapper.createArrayNode().add(node);

            ArrayNode arrayNode = mapper.createArrayNode();
            arrayNode.addAll(Arrays.asList(node, node));

            JsonNode jsonNode = mapper.readTree(arrayNode.toString());

            System.out.println("DATOSSSSSS Devueltos así bien chevere:" + arrayNode);
            System.out.println("DATOSSSSSS 2:" + jsonNode);

            // Agragar el nodo de tipo persona
            Persona persona = new Persona();
            persona = xmlMapper.treeToValue(node, Persona.class);
            persona.setIdentificacion(identificacion);
            System.out.println("DATOSSSSSS:" + persona);
            // ObjectMapper jsonMapper = new ObjectMapper();
            // String json = jsonMapper.writeValueAsString(node);

            // document = stringAXml(rawXmlResponse);
            // document.getDocumentElement().normalize();
            return persona;
        } catch (Exception e) {
            return null;
        }

    }

    public Document stringAXml(String xmlString) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.xmlDocument = builder.parse(new InputSource(new StringReader(xmlString)));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
        return xmlDocument;
    }
}
