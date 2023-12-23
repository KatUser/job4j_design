package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer2 {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer2.class.getName());
    public static void main(String[] args) {
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
                                ? "Hello, dear friend"
                                : "What").getBytes());
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("IOException occurred", e);
        }
    }
}
