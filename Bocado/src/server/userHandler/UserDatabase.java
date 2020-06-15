package server.userHandler;


import java.io.*;
import java.util.ArrayList;

public class UserDatabase {

    private ArrayList<User> userDatabase;

    public UserDatabase() {
        userDatabase = readUserDatabase();
        writeUserDatabase();
    }


    public User getUser(String[] userData) {

        for(int i = 0; i<userDatabase.size(); i++) {
            if(userData[1].equals(userDatabase.get(i).getName())) {
                return userDatabase.get(i);
            }
        }

        User user = new User(userData[1]);
        userDatabase.add(user);
        writeUserDatabase();
        return user;
    }

    public void updateUser(String[] userData) {
        for(int i = 0; i<userDatabase.size(); i++) {
            if(userData[1].equals(userDatabase.get(i).getName())) {
                ArrayList<String> updatedIngredients = new ArrayList<String>();

                for(int j = 2; j<userData.length; i++) {
                    updatedIngredients.add(userData[j]);

                }

                userDatabase.get(i).setSavedIngredients(updatedIngredients);

            }
        }

        writeUserDatabase();
    }

    private ArrayList<User> readUserDatabase() {
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("database/UserData.dat")))) {

            ArrayList<User> userDatabase = (ArrayList<User>)ois.readObject();
            return userDatabase;
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException c) {
            c.printStackTrace();
        }
        return new ArrayList<User>();
    }

    private void writeUserDatabase() {

        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("database/UserData.dat")))) {
            oos.writeObject(userDatabase);
            oos.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

