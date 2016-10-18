import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class peerProcess {

    public static void main (String[] args) throws IOException {
        //read input args 'peerId'
        if (args.length != 1) {
            System.out.println("Usage: java peerProcess peerId");
        }
        final int peerId = Integer.parseInt(args[0]);

        //read Common Properties
        Reader cReader = null;
        Properties cProp = null;
        try {
            cReader = new FileReader (CommonProperties.CONFIG_FILE);
            cProp = CommonProperties.read(cReader);
            System.out.println(cProp);
            System.out.println("Number Of Preferred Neighbors: " + cProp.getProperty("NumberOfPreferredNeighbors"));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                cReader.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
