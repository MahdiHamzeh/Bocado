package client.Control;

import server.userHandler.User;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class UserGetter {

    private String ip;
    private int port;
    private Socket socket;
    private DataOutputStream dos;
    private ObjectInputStream ois;

    public UserGetter(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket(ip, port);
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Connected to server on IP " + socket.getInetAddress() + " and port " + socket.getLocalPort());
    }

    public void disconnect() throws IOException {
        socket.close();
    }
    public User getUser(String username) {

        try {
            connect();
            dos.writeUTF(username);
            dos.flush();

            User user = (User) ois.readObject();

            disconnect();

            return user;
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException c) {
            c.printStackTrace();
        }
        return null;
    }

}
