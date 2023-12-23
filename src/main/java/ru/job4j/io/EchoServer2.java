package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2 {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("msg=Exit")) {
                        out.write("The end.".getBytes());
                        server.close();
                    } else {
                        out.write((str.contains("msg=Hello")
                                ? "Hello, dear friend\r\n\r\n"
                                : "What\r\n\r\b").getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}