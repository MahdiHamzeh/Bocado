package client.Control;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import server.recipeHandler.Recipe;

/**
 * Handles the connection between the client and server, from the client end.
 * @version 1.0
 * @author Andreas Månsson, Gustaf Hermansson
 *
 */


public class ServerConnection {

    private String ip;
    private int port;
    private Socket socket;
    private DataOutputStream dos;
    private ObjectInputStream ois;


    public ServerConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;

    }

    /**
     * Connects to the server and opens up both an output steam and an input stream.
     *
     * @throws IOException
     */
    public void connect() throws IOException {
        socket = new Socket(ip, port);
        //socket.setSoTimeout(5000);
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Connected to server on ip " + ip + " and port " + port);
    }

    /**
     * Disconnects from the server.
     *
     * @throws IOException
     */
    public void disconnect() throws IOException {
        socket.close();
    }

    /**
     * Accepts a filter in the form of a String, containing all the ingredients
     * and dietary preferences. After that it sends the filter to the server
     * and recieves back an ArrayList of recipes that match the filter.
     *
     * @param filter String in a specific format containing ingredients and preferences
     * @return ArrayList of Recipe-objects that match the filter
     */
    public ArrayList<Recipe> sendRequest(String filter) {
        try {
            connect();
            dos.writeUTF(filter);
            dos.flush();

            ArrayList<Recipe> filteredRecipes = (ArrayList<Recipe>) ois.readObject();

            disconnect();
            return filteredRecipes;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Recipe> noConnection = new ArrayList<Recipe>();
        noConnection.add(new Recipe("No Connection"));
        return noConnection;
    }

    public Recipe sendSearch(String search) {
        try {
            connect();
            dos.writeUTF(search);
            dos.flush();

            Recipe result = (Recipe) ois.readObject();

            disconnect();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


}
