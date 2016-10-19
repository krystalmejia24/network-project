import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;

public class PeerInfo {
    public static final String CONFIG_FILE = "PeerInfo.cfg";
    private final Collection<RemotePeerInfo> peerInfo = new LinkedList<>();

    public void read (Reader reader) throws FileNotFoundException, IOException, ParseException {
        BufferedReader in = new BufferedReader(reader);
        int i = 0;
        for (String line; (line = in.readLine()) != null;) {
            line = line.trim();
            if (line.length() <= 0) { continue; }
            String[] tokens = line.split("\\s+");
            if (tokens.length != 4) {
                throw new ParseException (line, i);
            }
            final boolean hasFile = (tokens[3].trim().compareTo("1") == 0);
            peerInfo.add(new RemotePeerInfo(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), hasFile));
            i++;
        }
    }

    public Collection<RemotePeerInfo> getPeerInfo () {
        return new LinkedList<>(peerInfo);
    }
}
