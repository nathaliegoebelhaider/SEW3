package Theorie;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Nathalie on 08.05.2017.
 */
public class Server {
    public  static void main (String [] args) {
        //Server auf Port 10023 hoeren lassen
        try (ServerSocket server = new ServerSocket(10023)) {
            System.out.println("Server bereit");


            //Auf ankommende Verbindung warten, accept() blockiert so lange,

            Socket verbindung = server.accept();            // externer Client
            System.out.println("Verbindung angenommen");

            //Zum Senden eines TExts wird ein Writer benötigt
            // diesen über den OutputStream stülpen

            Writer w = new OutputStreamWriter(verbindung.getOutputStream());

            // Nachricht senden
            w.append("Hallo, Welt! \n");

            //Verbindung beenden
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
