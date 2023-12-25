package ru.job4j.io.serialization.xml;


import javax.xml.bind.annotation.*;
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
}
