import java.net.*;
import java.io.*;

public class ConnectionThread extends Thread {
  private Socket s;
  private int localPeer;
  private int remotePeerAddress;
  static DataInputStream inStream;
  static DataOutputStream outStream;

  public ConnectionThread(Socket s, int localPeer) {
    this.s = s;
    this.localPeer = localPeer;
  }

  public void run() {
    Thread.currentThread().setName("Thread-" + localPeer);
    try {
      inStream	= new DataInputStream(s.getInputStream());
      outStream = new DataOutputStream(s.getOutputStream());
      outStream.flush();
      Handshake_Messages hm = new Handshake_Messages(localPeer);
      while(true) {
        hm.Recieve_Message(inStream, outStream);
      }
    } catch(IOException ioException){
      System.out.println("Disconnect with peer " + localPeer);
    } finally {
      //Close ss
      try{
        inStream.close();
        outStream.close();
        s.close();
      } catch(IOException ioException){
        System.out.println("Disconnect with peer " + localPeer);
      }
    }
}
}
