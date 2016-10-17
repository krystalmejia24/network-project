
import java.net.*;
import java.io.*;

public class Server_Socket  {
	ServerSocket ss;
    Socket s;
    static DataInputStream inStream;
    static DataOutputStream outStream;
    public Server_Socket()
    {
        try
        {
            System.out.println("Server Started");
            ss=new ServerSocket(6066);
            s=ss.accept();
            System.out.println(s);
            System.out.println("CLIENT CONNECTED");
            inStream	= new DataInputStream(s.getInputStream());
            outStream = new DataOutputStream(s.getOutputStream());
        }
        catch(Exception e)
        {
             System.out.println(e);
        }
    }

    public static void main (String as[]) throws IOException
    {
         new Server_Socket();
         Handshake_Messages hm = new Handshake_Messages();
         hm.Recieve_Message(inStream, outStream);
    }
}
