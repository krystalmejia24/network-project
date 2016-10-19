import java.io.*;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class Server_Socket implements Runnable {
    private final int sPort;
    private final int peerId;
    private final boolean hasFile;
    private final Properties commonProp;
    private final PeerLogger peerLogger;
    private final Collection<ConnectionThread> connections =
            Collections.newSetFromMap(new ConcurrentHashMap<ConnectionThread, Boolean>());

    public Server_Socket(int peerId, String address, int port, boolean hasFile, Collection<RemotePeerInfo> peerInfo, Properties cProp) {
        this.peerId = peerId;
        this.sPort = port;
        this.hasFile = hasFile;
        this.commonProp = cProp;
        ArrayList<RemotePeerInfo> remotePeers = new ArrayList<>(peerInfo);
        for (RemotePeerInfo rp : remotePeers) {
            if (Integer.parseInt(rp.peerId) == peerId) {
                remotePeers.remove(rp);
                break;
            }
        }
        this.peerLogger = new PeerLogger(peerId);
    }

    public void run() {
        try {
            System.out.println("Server Started");
            ServerSocket ss = new ServerSocket(sPort);
            System.out.println(ss);
            while (true) {
                try {
                    ConnectionThread c = new ConnectionThread(ss.accept(), peerId);
                    connections.add(c);
                    c.start();
                    System.out.print(connections);
                    System.out.println("PEER " + peerId + " CONNECTED");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
