package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;

public class Contact2 implements Serializable {
    @Serial
    private static final long serialVersionUID = 349L;
    private final String name;
    private final String email;
    private final transient int age;

    public Contact2(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "Contact2{"
                + "name='" + name + '\''
                + ", email='" + email + '\''
                + ", age=" + age
                + '}';
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact2 contact2 = new Contact2("Name", "email@email.com", 33);
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(contact2);
        }
        try (FileInputStream fileInputStream = new FileInputStream(tempFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            final Contact2 contact2FromTempFile = (Contact2) objectInputStream.readObject();
            System.out.println(contact2FromTempFile);
        }
    }
}
