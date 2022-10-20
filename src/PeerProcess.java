import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeerProcess {
    public static void peerProcess(int peerId) {

        //Function that starts peer process

    }

    public static void ReadPeer() {
        //Reads PeerInfo.cfg
        //Create an array list of all the read lines
        //Start in read Peers based on previous reading and call peerProcess
    }

    public static int NumberOfPreferredNeighbors = 0;
    public static int UnchokingInterval = 0;
    public static int OptimisticUnchokingInterval = 0;
    public static String FileName = "default_file.dat";
    public static int FileSize = 0;
    public static int PieceSize = 0;
    public static FileReader input = null;

    public static void ReadCommon()  {
        try {
            input = new FileReader("/Users/ynpatel/Desktop/BitTorrent/BitTorrent/src/Common.cfg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> list_lines=new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(input)) {
            String line;
            while ((line= br.readLine()) != null) {
                 list_lines.add(line);
            }
            Pattern to_match= Pattern.compile("([0-9]+){1,}");
            String numberofPreffered=list_lines.get(0);
            String unchokingInter=list_lines.get(1);
            String optunchokingInter= list_lines.get(2);
            String fileName=list_lines.get(3);
            String fileSize=list_lines.get(4);
            String piecesize=list_lines.get(5);
            Matcher numPref = to_match.matcher(numberofPreffered);
            Matcher unchokInt=to_match.matcher(unchokingInter);
            Matcher optunInt=to_match.matcher(optunchokingInter);
            Matcher filename=to_match.matcher(fileName);
            Matcher filesize=to_match.matcher(fileSize);
            Matcher pieceSize=to_match.matcher(piecesize);

            NumberOfPreferredNeighbors= numPref.find() ? Integer.valueOf(numPref.group(1)) : 0;
            UnchokingInterval=unchokInt.find() ? Integer.valueOf(unchokInt.group(1)) :0;
            OptimisticUnchokingInterval=optunInt.find() ? Integer.valueOf(optunInt.group(1)):0;
            FileName=filename.find() ? filename.group(1): "No file name";
            FileSize=filesize.find() ? Integer.valueOf(filesize.group(1)):0;
            PieceSize=pieceSize.find() ? Integer.valueOf(pieceSize.group(1)) :0;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        PeerProcess p = new PeerProcess();
        //Making a instance of the peer process so that we see multiple instances
        p.ReadCommon();
        p.ReadPeer();


    }
}

