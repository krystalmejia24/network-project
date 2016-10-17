
import java.net.*;
import java.io.*;
import java.util.*;

public class Server_Socket  {
		ServerSocket ss;
		Socket s;

		public Server_Socket()
    {
      try {
				System.out.println("Server Started");
				ss = new ServerSocket(6066);
				int peerId = 1000;
				while(true) {
					s=ss.accept();
					new ConnectionThread(s, peerId).start();
					System.out.println("PEER " + peerId + " CONNECTED");
					peerId++;
				}
      } catch(Exception e) {
        	System.out.println(e);
      }
    }

    public static void main (String as[]) throws IOException {
         new Server_Socket();
    }
}
