import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

public class Handshake_Messages implements Serializable{
    private int peerId;
    private static final long serialVersionUID = 1L;

    public Handshake_Messages() {}

    public Handshake_Messages(int peerId) {
        this.peerId = peerId;
	}

	public void Send_Message(DataInputStream inStream, DataOutputStream outStream) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sent;
        do {
            sent=br.readLine();
            outStream.writeUTF(sent);
            outStream.flush();
            System.out.println("Server Message: " + inStream.readUTF());
        } while(!sent.equals("stop"));
        }

	public void Recieve_Message(DataInputStream inStream, DataOutputStream outStream) throws IOException {
        String str, s1;
        do {
            str=inStream.readUTF();
            System.out.println("Client " + peerId + " Message:" + str);
            BufferedReader br=new BufferedReader(new   InputStreamReader(System.in));
            s1=br.readLine();
            outStream.writeUTF(s1);
            outStream.flush();
        } while(!s1.equals("bye"));
        }

    public int getPeerId() {
        return this.peerId;
    }
}
