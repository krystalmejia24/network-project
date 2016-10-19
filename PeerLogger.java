import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PeerLogger {
    private static final Logger l = Logger.getLogger("CNT4007");
    private final String peerMsgHeader;

    public PeerLogger(int peerId) {
        this.peerMsgHeader = ": Peer [" + peerId + "]";
        try {
            Handler fh = new FileHandler ("log_peer_" + peerId + ".log");
            l.setUseParentHandlers(false);
            l.addHandler(fh);
            fh.setFormatter(new CustomFormatter());
        } catch (SecurityException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void tcpConnectionMsg(int peerId, boolean connectingPeer) {
        if (connectingPeer) {
            l.info(getPeerMsgHeader() + " makes a connection to Peer " + formatPeerId(peerId));
        } else {
            l.info(getPeerMsgHeader() + " is connected from Peer " + formatPeerId(peerId));
        }
    }

    public void changeOfPreferredNeighborsMsg(String preferredNeighbors) {
        l.info(getPeerMsgHeader() + " has preferred neighbors " + formatPreferredNeighbors(preferredNeighbors));
    }

    public void changeOfOptimisticallyUnchokedNeighborsMsg(String preferredNeighbors) {
        l.info(getPeerMsgHeader() + " has the optimistically unchoked neighbor " + formatPreferredNeighbors(preferredNeighbors));
    }

    public void unchokeMsg(int peerId) {
        l.info(getPeerMsgHeader() + " is unchoked by " + formatPeerId(peerId));
    }

    public void chokingMsg(int peerId) {
        l.info(getPeerMsgHeader() + " is choked by " + formatPeerId(peerId));
    }

    public void haveMsg(int peerId, int pieceIdx) {
        l.info(getPeerMsgHeader() + " received the 'have' message from " + formatPeerId(peerId) + " for the piece [" + pieceIdx + "]");
    }

    public void interestedMsg(int peerId) {
        l.info(getPeerMsgHeader() + " received the 'interested' message from " + formatPeerId(peerId));
    }

    public void notInterestedMsg(int peerId) {
        l.info(getPeerMsgHeader() + " received the 'not interested' message from " + formatPeerId(peerId));
    }

    public void downloadingPieceMsg(int peerId, int pieceIdx, int currNumberOfPieces) {
        l.info(getPeerMsgHeader() + " has downloaded the piece [" + pieceIdx + "] from peer " + formatPeerId(peerId) + " Now the number of pieces it has is [" + currNumberOfPieces + "]");
    }

    public void downloadCompletedMsg() {
        l.info(getPeerMsgHeader() + " has downloaded the complete file.");
    }

    private String getPeerMsgHeader(){
        return this.peerMsgHeader;
    }

    private String formatPeerId(int peerId){
        return "[" + peerId + "]";
    }

    private String formatPreferredNeighbors(String preferredNeighbors ){
        return "[" + preferredNeighbors + "]";
    }

    public class CustomFormatter extends Formatter {
        DateFormat df = new SimpleDateFormat("HH:mm:ss a");
        Date dateobj = new Date();

        @Override
        public String format(LogRecord record) {
            return "[" + df.format(dateobj) + "]" +  record.getMessage() + "\n";
        }
    }
}
