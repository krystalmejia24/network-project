import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.Properties;

public enum CommonProperties {

    NumberOfPreferredNeighbors,
    UnchokingInterval,
    OptimisticUnchokingInterval,
    FileName,
    FileSize,
    PieceSize;

    public static final String CONFIG_FILE = "Common.cfg";

    public static Properties read (Reader reader) throws Exception {
        final Properties config = new Properties () {
            @Override
            public void load(Reader reader) throws IOException {
                BufferedReader in = new BufferedReader(reader);
                int i = 0;
                for (String line; (line = in.readLine()) != null; i++) {
                    line = line.trim();
                    if (line.length() <= 0) { continue; }
                    String[] tokens = line.split("\\s+");
                    if (tokens.length != 2) {
                        throw new IOException (new ParseException (line, i));
                    }
                    setProperty(tokens[0].trim(), tokens[1].trim());
                }
            }
        };
        config.load(reader);

        //make sure all properties are in file
        for (CommonProperties prop : CommonProperties.values()) {
            if (!config.containsKey(prop.toString())) {
                throw new Exception ("This config file does not contain the following property: " + prop);
            }
        }

        return config;
    }
}
