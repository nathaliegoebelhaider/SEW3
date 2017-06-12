package ueb23;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;
/**
 * Created by Nathalie on 08.05.2017.
 */
public class Server {


    public static void main(String[] args) {
        new Server().init();


    }
    static Map<String,ClientHandler>  users = new TreeMap<>();
    private void init() {

        try (ServerSocket server = new ServerSocket(10023)) {
            System.out.println("Echo-Server bereit");

            while (true) {

                Socket verbindung = server.accept();

                ClientHandler newClient = new ClientHandler(this, verbindung);
                newClient.start();

             //   users.put(verbindung,newClient.getName());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nachrichtVerteilen(String message, ClientHandler client) throws IOException {
        for(ClientHandler c : users.values()) {
            if (c != client) {
                c.writeLine(message);
            }
        }
    }

    public  static boolean anmelden(ClientHandler client,String name) throws  IOException{
        if(users.containsKey(name)){
            return false;
        }
        else {
            users.put(name, client);
            return true;

        }

    }

    public static void abmelden(String name, ClientHandler client){
            users.remove(name,client);
        System.out.println("User wurde entfernt");
    }
    public static void getUsers(){

    }


}