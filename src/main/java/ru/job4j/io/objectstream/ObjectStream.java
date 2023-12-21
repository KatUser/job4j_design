package ru.job4j.io.objectstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStream {
    public static void main(String[] args) {
        Car car = new Car("Brand", "Model", 2023);
        try (ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(new FileOutputStream("data/serialized.dat"));
             ObjectInputStream objectInputStream
                = new ObjectInputStream(new FileInputStream("data/serialized.dat"))) {
            objectOutputStream.writeObject(car);
            Car deserialized = (Car) objectInputStream.readObject();
            System.out.println(deserialized.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
