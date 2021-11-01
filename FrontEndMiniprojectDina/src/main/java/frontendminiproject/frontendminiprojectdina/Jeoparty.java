package frontendminiproject.frontendminiprojectdina;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Jeoparty {
    Socket socket;
    DataOutputStream output;
    DataInputStream input;

    public void joinServer(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }

    public void sendMessage(String message) throws IOException {
        output = new DataOutputStream(socket.getOutputStream());
        output.writeUTF(message);
    }

    public String getMessage() throws IOException {
        input = new DataInputStream(socket.getInputStream());
        return input.readUTF();
    }

    public void sendNumber(int number) throws IOException {
        output = new DataOutputStream(socket.getOutputStream());
        output.writeInt(number);
    }

    public int getNumber() throws IOException {
        input = new DataInputStream(socket.getInputStream());
        return input.readInt();
    }
}