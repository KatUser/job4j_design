package ru.job4j.io.serialization.xml;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "building")
@XmlAccessorType(XmlAccessType.FIELD)
public class Building {
    @XmlAttribute
    private boolean residential = false;
    @XmlAttribute
    private int height = 100;
    @XmlAttribute
    private boolean undergroundParking = true;
    private Address address;
    @XmlElementWrapper(name = "companies")
    @XmlElement (name = "company")
    private String[] companies;
    public Building() { }
    public Building(boolean residential, int height, boolean undergroundParking,
                    Address address, String... companies) {
        this.residential = residential;
        this.height = height;
        this.undergroundParking = undergroundParking;
        this.address = address;
        this.companies = companies;
    }
    @Override
    public String toString() {
        return "Building{"
                + "residential=" + residential
                + ", height=" + height
                + ", undergroundParking=" + undergroundParking
                + ", address=" + address
                + ", companies=" + Arrays.toString(companies)
                + '}';
    }
    public static void main(String[] args) throws JAXBException {
        final Building building = new Building(true, 1000,
                true, new Address("Moscow", "Bolshaya Yamskaya", 125),
                "5-ka", "Roga & Kopita LLC");
        JAXBContext context = JAXBContext.newInstance(Building.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(building, writer);
            String res = writer.getBuffer().toString();
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
