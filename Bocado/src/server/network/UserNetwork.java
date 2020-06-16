package server.network;

import server.controller.Controller;
import server.userHandler.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Handles the sending and receiving user-information from the server side.
 * @version 1.0
 * @author Andreas Månsson, Gustaf Hermansson, Mahdi Hamzeh
 *
 */

public class UserNetwork {

    private Controller controller;
    private ServerSocket serverSocket;
    private Thread server;

    public UserNetwork(Controller controller, int port) {
        this.controller = controller;
        try {
            serverSocket = new ServerSocket(port);
            server = new UserHandler();
            server.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inner class that listens for User-requests from the client.
     */
    private class UserHandler extends Thread {

        public void run() {
            String input;
            System.out.println("Listening to port: " + serverSocket.getLocalPort());
            while(true) {
                try(Socket socket = serverSocket.accept();
                    DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

                    input = dis.readUTF();

                    String[] userData = input.split(",");

                    User user;
                    if(userData[0].equals("LOG")) {
                        user = controller.getUser(userData);
                        oos.writeObject(user);
                        oos.flush();
                    }
                    else {
                        controller.updateUser(userData);
                    }



                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
