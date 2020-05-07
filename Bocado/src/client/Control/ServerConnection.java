package client.Control;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import server.recipeHandler.Recipe;

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

    public void connect() throws IOException {
        socket = new Socket(ip, port);
        //socket.setSoTimeout(5000);
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("Connected to server on ip " + ip + " and port " + port);
    }

    public void disconnect() throws IOException {
        socket.close();
    }

    public ArrayList<Recipe> sendRequest(String filter) {
        try {
            connect();
            dos.writeUTF(filter);
            dos.flush();

            ArrayList<Recipe> filteredRecipes = (ArrayList<Recipe>)ois.readObject();

            disconnect();
            return filteredRecipes;
        } catch(IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Recipe> noConnection = new ArrayList<Recipe>();
        noConnection.add(new Recipe("No Connection"));
        return noConnection;
    }

}
