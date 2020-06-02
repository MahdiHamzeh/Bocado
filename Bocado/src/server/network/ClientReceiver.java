package server.network;

import server.controller.Controller;
import server.recipeHandler.Recipe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles the connection between the client and server, from the server end.
 * @version 1.0
 * @author Andreas Månsson
 *
 */
public class ClientReceiver {

    private ServerSocket serverSocket;
    private Thread server;
    private Controller controller;

    public ClientReceiver(Controller controller, int port) {

        this.controller = controller;
        try {
            serverSocket = new ServerSocket(port);
            server = new ClientHandler();
            server.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Inner class that handles connections from the clients.
     * @author Andreas Månsson
     */
    private class ClientHandler extends Thread {

        public void run() {
            String input;

            System.out.println("Server started, listening for client requests on port " + serverSocket.getLocalPort());

            while(true) {
                try (Socket socket = serverSocket.accept();
                     DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                     ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()))
                {
                    System.out.println("Client connected");

                    input = dis.readUTF();
                    System.out.println(input);
                    String[] ingredients = input.split(",");
                    ArrayList<String> filter = new ArrayList<String>();

                    for(int i = 0; i<ingredients.length; i++) {
                        filter.add(ingredients[i]);
                    }


                    ArrayList<Recipe> recipes = controller.getFilteredAndSortedRecipes(filter);

                    oos.writeObject(recipes);
                    oos.flush();

                } catch(IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
