package client.Control;

import server.userHandler.User;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Recieves user-objects from the server on login.
 * @version 1.0
 * @author Andreas Månsson, Gustaf Hermansson, Mahdi Hamzeh
 *
 */

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

    /**
     * Connects to the server.
     * @throws IOException
     */
    public void connect() throws IOException {
        socket = new Socket(ip, port);
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Connected to server on IP " + socket.getInetAddress() + " and port " + socket.getLocalPort());
    }

    /**
     * Disconnects from the server.
     * @throws IOException
     */
    public void disconnect() throws IOException {
        socket.close();
    }

    /**
     * Sends a formatted String to the server containing a username, and gets back a user-object.
     * @param username formatted String containing username.
     * @return user-object matching the username.
     */
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
