package com.example.apimongodb.utils;

import org.springframework.stereotype.Component;

@Component
public class ClsUtilitario {

    public String fichaGeneral(String identificacion) {
        String ficha = String.format(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://servicio.interoperadorws.interoperacion.dinardap.gob.ec/\">\n"
                        +
                        "   <soapenv:Header />\n" +
                        "   <soapenv:Body>\n" +
                        "      <ser:getFichaGeneral>\n" +
                        "         <!--Optional:-->\n" +
                        "         <codigoPaquete>1038</codigoPaquete>\n" +
                        "         <!--Optional:-->\n" +
                        "         <numeroIdentificacion>%1$s</numeroIdentificacion>\n" +
                        "      </ser:getFichaGeneral>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>",
                identificacion);
        return ficha;
    }

    public String fichaGeneral1(String identificacion) {
        String ficha = String.format(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" 
                +"\n<soapenv:Envelope"
                +"\n    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interoperabilidad.dinardap.gob.ec/interoperador/\">"
                +"\n    <soapenv:Header />"
                +"\n    <soapenv:Body>"
                +"\n        <int:consultar>"
                +"\n            <!--Optional:-->"
                +"\n            <parametros>"
                +"\n                <!--Zero or more repetitions:-->"
                +"\n                <parametro>"
                +"\n                    <!--Optional:-->"
                +"\n                    <nombre>codigoPaquete</nombre>"
                +"\n                    <!--Optional:-->"
                +"\n                    <valor>1098</valor>"
                +"\n                </parametro>"
                +"\n                <parametro>"
                +"\n                    <!--Optional:-->"
                +"\n                    <nombre>cedula</nombre>"
                +"\n                    <!--Optional: 1102703426-->"
                +"\n                    <valor>%1$s</valor>"
                +"\n                </parametro>"
                +"\n            </parametros>"
                +"\n        </int:consultar>"
                +"\n    </soapenv:Body>"
                +"\n</soapenv:Envelope>",
                identificacion);
        return ficha;
    }

    public String cabeceraPersonasGenerica() {

        String cabecera = String.format(
                "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:mdmq=\"http://mdmq.quito.gob.ec/\">\n"
                        + "<soap:Header>\n"
                        + "<mdmq:Cabecera>\n"
                        + "<!--Optional:-->\n"
                        + "<mdmq:UserName>30</mdmq:UserName>\n"
                        + "<!--Optional:-->\n"
                        + "<mdmq:Password>123456</mdmq:Password>\n"
                        + "</mdmq:Cabecera>\n"
                        + "</soap:Header>\n");

        return cabecera;
    }

    public String bodyPersonas(String identificacion, char tipoIdentificacion) {

        String body = String.format("<soap:Body >\n"
                + "      <mdmq:fn_consulta_buscarPersonaCompleto>\n"
                + "         <!--Optional:-->\n"
                + "         <mdmq:strIdentificacion>%1$s</mdmq:strIdentificacion>\n"
                + "         <!--Optional:-->\n"
                + "         <mdmq:strTipoIdentificacion>%2$s</mdmq:strTipoIdentificacion>\n"
                + "      </mdmq:fn_consulta_buscarPersonaCompleto>\n"
                + "   </soap:Body>\n"
                + "</soap:Envelope>", identificacion, tipoIdentificacion);

        return body;
    }

    public String bodyPersonasCim(String identificacion) {

        String body = String.format("<soap:Body>\n"
                + "      <mdmq:fn_consulta_buscarPersona_CIM>\n"
                + "         <mdmq:intPersonaID>%1$s</mdmq:intPersonaID>\n"
                + "      </mdmq:fn_consulta_buscarPersona_CIM>\n"
                + "   </soap:Body>\n"
                + "</soap:Envelope>", identificacion);

        return body;
    }

    public String bodyDireccionPersona(String cim) {

        String body = String.format(" <soap:Body>\n"
                + "      <mdmq:fn_consulta_telefonos_direcciones>\n"
                + "         <mdmq:intPersonaID>%1$s</mdmq:intPersonaID>\n"
                + "      </mdmq:fn_consulta_telefonos_direcciones>\n"
                + "   </soap:Body>\n"
                + "</soap:Envelope>", cim);

        return body;
    }

}
