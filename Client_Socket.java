
import java.net.*;
import java.io.*;

public class Client_Socket {

		Socket s;
    static DataInputStream inStream;
    static DataOutputStream outStream;

    public Client_Socket() {
			try {
      	//String serverName = "192.168.1.1";  // Indicating the place to put Server's IP
      	String serverName = "localhost";
        s = new Socket(serverName, 6066);
				System.out.println(s);
        inStream = new DataInputStream(s.getInputStream());
        outStream= new DataOutputStream(s.getOutputStream());
      } catch(Exception e) {
        	System.out.println(e);
        }
     }

    public static void main(String as[]) throws IOException
    {
        new Client_Socket();
				Handshake_Messages hm = new Handshake_Messages();
				while(true) {
        		hm.Send_Message(inStream, outStream);
				}
    }
}
