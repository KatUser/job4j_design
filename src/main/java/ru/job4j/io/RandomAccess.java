package ru.job4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {
    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("data/random.txt", "rw");
            randomAccessFile.writeInt(100);
            randomAccessFile.writeChar('A');
            randomAccessFile.writeBoolean(true);
            randomAccessFile.seek(0);
            System.out.println(randomAccessFile.readInt());
            System.out.println(randomAccessFile.readChar());
            System.out.println(randomAccessFile.readBoolean());
            randomAccessFile.seek(4);
            System.out.println(randomAccessFile.readChar());
            System.out.println(randomAccessFile.getFilePointer());
            /* Перезапись char */
            randomAccessFile.seek(4);
            randomAccessFile.writeChar('B');
            randomAccessFile.seek(4);
            System.out.println(randomAccessFile.readChar());
            /* Запись новых данных в конец файла */
            randomAccessFile.seek(randomAccessFile.length());
            System.out.println("Pointer position after Boolean: " + randomAccessFile.getFilePointer());
            randomAccessFile.writeDouble(3.01);
            randomAccessFile.seek(7);
            System.out.println(randomAccessFile.readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
