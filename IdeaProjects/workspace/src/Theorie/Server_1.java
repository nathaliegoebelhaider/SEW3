package Theorie;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Nathalie on 08.05.2017.
 */
public class Server_1 {
    public  static void main (String [] args) {
        //Server auf Port 10023 hoeren lassen
        try (ServerSocket server = new ServerSocket(10023)) {
            System.out.println("Server bereit");


            while(true) {

                //Auf ankommende Verbindung warten, accept() blockiert so lange,

                Socket verbindung = server.accept();            // externer Client
                System.out.println("Verbindung angenommen");

                //Zum Senden eines TExts wird ein Writer benötigt
                // diesen über den OutputStream stülpen

                Writer w = new OutputStreamWriter(verbindung.getOutputStream());
                BufferedWriter bw = new BufferedWriter(w);

                Reader r = new InputStreamReader(verbindung.getInputStream());
                BufferedReader br = new BufferedReader(r);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
