package server.userHandler;


import java.io.*;
import java.util.ArrayList;

/**
 * Handles all user objects saved.
 * @version 1.0
 * @author Andreas Månsson, Gustaf Hermansson, Mahdi Hamzeh
 */

public class UserDatabase implements Serializable {

    private ArrayList<User> userDatabase;

    public UserDatabase() {
        userDatabase = readUserDatabase();
        writeUserDatabase();

        /*for(int i = 0; i<userDatabase.size(); i++) {
            System.out.println(userDatabase.get(i).getName());
            for(int j = 0; j<userDatabase.get(i).getSavedIngredients().size(); j++) {
                System.out.println(userDatabase.get(i).getSavedIngredients().get(j));
            }
        }*/
    }


    /**
     * Finds a saved User based on the userData-parameter and returns the object.
     * If none is found one is created and returned.
     * @param userData information about the User to be returned
     * @return User-object that matches userData
     */
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

    /**
     * Updating a specific User-object, based on the userData-parameter.
     * @param userData array with both username and information to be saved.
     */
    public void updateUser(String[] userData) {
        for(int i = 0; i<userDatabase.size(); i++) {
            if(userData[1].equals(userDatabase.get(i).getName())) {
                ArrayList<String> updatedIngredients = new ArrayList<String>();

                for(int j = 2; j<userData.length; j++) {
                    updatedIngredients.add(userData[j]);

                }

                userDatabase.get(i).setSavedIngredients(updatedIngredients);

            }
        }

        writeUserDatabase();
    }


    /**
     * Reads the database of user from a local file and saves it in userDatabase.
     * @return read data.
     */
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

    /**
     * Writes userDatabase to a local file for storage.
     */
    private void writeUserDatabase() {

        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("database/UserData.dat")))) {
            oos.writeObject(userDatabase);
            oos.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

