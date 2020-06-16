package client.Control;


import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Sends updated user-info to the server.
 * @version 1.0
 * @author Andreas Månsson, Gustaf Hermansson, Mahdi Hamzeh
 *
 */


public class UserUpdater {

    private String ip;
    private int port;
    private Socket socket;
    private DataOutputStream dos;

    public UserUpdater(String ip, int port) {
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
     * Sends a formatted String to the server with info about the user.
     * @param userData formatted String containing user data.
     */
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
