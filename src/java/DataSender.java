package com.example.frontendclient;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class DataSender implements Runnable{

    Socket socket;
    DataOutputStream output;

    DataSender(Socket socket) throws IOException {
        this.socket = socket;
        output = new DataOutputStream(socket.getOutputStream());
    }

    public void sendString(String stringData) throws IOException {
        output.writeUTF(stringData);
    }

    @Override
    public void run() {

    }
}
