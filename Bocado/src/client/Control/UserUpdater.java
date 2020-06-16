package client.Control;

import server.userHandler.User;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class UserUpdater {

    private String ip;
    private int port;
    private Socket socket;
    private DataOutputStream dos;

    public UserUpdater(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket(ip, port);
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        System.out.println("Connected to server on IP " + socket.getInetAddress() + " and port " + socket.getLocalPort());
    }

    public void disconnect() throws IOException {
        socket.close();
    }

    public void sendUser(String userData) {

        try {
            connect();

            dos.writeUTF(userData);
            dos.flush();

            disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
