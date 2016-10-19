import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.Collection;
import java.util.LinkedList;

public class peerProcess {

    public static void main (String[] args) throws IOException {
        //input args peerId
        if (args.length != 1) {
            System.out.println("Invalid number of arguments.");
        }
        final int peerId = Integer.parseInt(args[0]);
        //variables to create Common Properties from Common.cfg
        Reader cReader = null;
        Properties cProp = null;
        //variables to create Peer Info and list of remote peers to connect to from PeerInfo.cfg
        Reader pReader = null;
        String address = "localhost";
        int port = 6066;
        boolean hasFile = false;
        PeerInfo peerInfo = new PeerInfo();
        Collection<RemotePeerInfo> peersToConnect = new LinkedList<>();
        try {
            cReader = new FileReader(CommonProperties.CONFIG_FILE);
            cProp = CommonProperties.read(cReader);
            pReader = new FileReader(PeerInfo.CONFIG_FILE);
            peerInfo.read(pReader);
            //
            for (RemotePeerInfo p : peerInfo.getPeerInfo()) {
                if (peerId == p.getPeerId()) {
                    address = p.getPeerAddress();
                    port = p.getPort();
                    hasFile = p.hasFile();
                    System.out.println(p);
                    break;
                } else {
                    peersToConnect.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println(cProp);
            System.out.println("Number Of Preferred Neighbors: " + cProp.getProperty("NumberOfPreferredNeighbors"));
            System.out.println(peersToConnect);
            try {
                cReader.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                pReader.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
