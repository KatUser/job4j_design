package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main2 {
    public static void main(String[] args) throws Exception {
        Building building = new Building(
                true,
                5,
                true,
                new Address("St. Petersburg", "Antonenko", 3),
                "Shawarma",
                "Dixi"
        );
        JAXBContext context = JAXBContext.newInstance(Building.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter stringWriter = new StringWriter()) {
            marshaller.marshal(building, stringWriter);
            xml = stringWriter.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader stringReader = new StringReader(xml)) {
            Building result = (Building) unmarshaller.unmarshal(stringReader);
            System.out.println(result);
        }
    }
}
