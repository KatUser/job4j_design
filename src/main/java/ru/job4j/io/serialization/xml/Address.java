package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {
    @XmlAttribute
    private String city;
    @XmlAttribute
    private String street;
    @XmlAttribute
    private int building;
    public Address() { }

    public Address(String city, String street, int building) {
        this.city = city;
        this.street = street;
        this.building = building;
    }

    @Override
    public String toString() {
        return "Address{"
                + "city='" + city + '\''
                + ", street='" + street + '\''
                + ", building=" + building
                + '}';
    }
}
