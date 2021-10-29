package com.example.frontendclient;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DataReceiver implements Runnable{
    Socket socket;
    DataInputStream input;

    DataReceiver(Socket socket) throws IOException {
        this.socket = socket;
        input = new DataInputStream(socket.getInputStream());
    }

    public String receiveString() throws IOException {
        return input.readUTF();
    }

    @Override
    public void run() {
        try {
            receiveString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
