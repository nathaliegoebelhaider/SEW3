package ueb23;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by Nathalie on 08.05.2017.
 */
public class ClientHandler extends  Thread{
    public Server server;
    public Socket socket;
    private static final String STZ_DAVOR = new String(new byte[] {0x0b, 0x1b, '[', '1', 'A', 0x1b, '7', 0x1b, '[', '1', 'L', '\r'});
    private static final String STZ_DANACH = new String(new byte[] {0x1b, '8',0x1b, '[', '1', 'B'});
    public String username;

    private BufferedWriter writer;

    public ClientHandler(Server s, Socket so){
        server = s;
        socket = so;

    }

    @Override
    public void run() {
        super.run();
        try (
                Writer w = new OutputStreamWriter(socket.getOutputStream(), Charset.forName("ISO-8859-1"));
                BufferedWriter bw = new BufferedWriter(w);

                Reader r = new InputStreamReader(socket.getInputStream());
                BufferedReader br = new BufferedReader(r);
        )
        {
            writer = bw;
            System.out.println("Verbindung angenommen von " + socket.getRemoteSocketAddress());

            bw.write("Willkommen  beim Chat-Server der 3AI. Um die Verbindung zu beenden gib quit ein.\nWelchen Spitznamen moechtest du haben: ");
            bw.flush();

            username = br.readLine();

            while(Server.anmelden(this, username) == false){
                bw.write(STZ_DAVOR + "Username schon vergeben. Bitte waehle einen anderen: "+ STZ_DANACH);
                username = "";
                bw.flush();
                username = br.readLine();
            }

            Server.nachrichtVerteilen(STZ_DAVOR + username + " hat den Chat betreten" + STZ_DANACH, this);

            while (true) {
                bw.write(username +">");
                bw.flush();

                String zeile = br.readLine();

                if(zeile == null || zeile.trim().isEmpty() || zeile.equals("quit")) {
                    Server.nachrichtVerteilen(STZ_DAVOR + username + " hat den Chat verlassen" + STZ_DANACH, this);
                    Server.abmelden(username,this);
                    break;
                }
                System.out.println("Sende...");
                Server.nachrichtVerteilen(STZ_DAVOR + username + ": " + zeile + STZ_DANACH ,this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void writeLine(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }


}
