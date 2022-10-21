import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.lang.Math;
//import statements

/**
 * @author      Yash  Patel  ypatel1@ufl.edu
 * @version     1.6          Version Midpoint
 * @since       1.8          JDK version 1.8
 *
 * @author      Aadesh Thirukonda aadeshthirukonda@ufl.edu
 *
 * @author      Noah Meininger    noahmeininger@ufl.edu
 */



public class PeerProcess {


    // variables for storing information from PeerInfo.cfg
    //All of the respective lists on the bottom will hold global info
    // on the connection settings for connecting to remote server
    public static ArrayList<Integer> peerIDs = new ArrayList<Integer>();

    public static ArrayList<String> hostNames = new ArrayList<String>();

    public static ArrayList<Integer> ports_list = new ArrayList<Integer>();

    public static ArrayList<Boolean> hasFile = new ArrayList<Boolean>();



    //Initialized variables for all the peers
    public static int NumberOfPreferredNeighbors = 0;
    public static int UnchokingInterval = 0;
    public static int OptimisticUnchokingInterval = 0;
    public static String FileName = "default_file.dat";
    public static int FileSize = 0;
    public static int PieceSize = 0;
    public static FileReader input = null;

    // read all the peer info independet of the peerProcess
    // part of the Peer class
    public static void ReadPeer() {
        //Reads PeerInfo.cfg
        //Create an array list of all the read lines
        //Start in read Peers based on previous reading and call peerProcess
        try {
            input = new FileReader("PeerInfo.cfg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> list_lines=new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(input)) {
            String line;
            while ((line = br.readLine()) != null) {
                list_lines.add(line);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        //all lines have been added from the file
        //the next steps are to delimit and

        // https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space

        //https://www.tutorialspoint.com/difference-between-arraylist-and-hashset-in-java

        //https://stackoverflow.com/questions/41541256/set-of-arraylists-in-java

        //put each line in its own tuple to represent a peer
        // connect to the cise remote server for each peer
        // move onto next steps for each peer

        //Instantiate java tuple list
        ArrayList<ArrayList<String> > delimited_peerinfo=
                new ArrayList<ArrayList<String> >();
        for(int i=0;i< list_lines.size();i++) {
                String[] line_of_peer=list_lines.get(i).split("\\s+");

                //add temp list for each iteration of lines
                // inner for loop[ is to ensure that we add all the elements
                ArrayList<String> arr = new ArrayList<String>();

                for(int j=0; j<line_of_peer.length;j++) {
                    arr.add(line_of_peer[j]);
                }
                // Add the delimited list of elements that represent a peer
                delimited_peerinfo.add(arr);
        }

        //After the previous steps we need to store into the array list of each category
        //This will help ensure that we have all the fields correctly matching to a peer

        for (int i = 0; i < delimited_peerinfo.size(); i++) {
                //Ensuring we recieve all peer info
                // System.out.print(delimited_peerinfo.get(i).get(j) + " ");
                //Add to the various ArrayList
                int peerId= Integer.valueOf(delimited_peerinfo.get(i).get(0));
                String hostName=delimited_peerinfo.get(i).get(1);
                int ports=Integer.valueOf(delimited_peerinfo.get(i).get(2));
                boolean hasfile=Boolean.parseBoolean(delimited_peerinfo.get(i).get(3));

                //Add to the respective ArrayLists
                peerIDs.add(peerId);
                hostNames.add(hostName);
                ports_list.add(ports);
                hasFile.add(hasfile);

        }
        //Now we can objects that represent peers
        // Within each peer we have 4 fields of data
        //This peer is created through the peer class
        // Each peer is created
        ArrayList<Peer> peer_list=new ArrayList<Peer>();
        for(int i=0;i<delimited_peerinfo.size();i++) {
            //To explain the reasoning here,
            if(peer_list.size()==0) {
                Peer newPeer = new Peer();
                newPeer.peerProcess(peerIDs.get(i));
                newPeer.peerhasFile=hasFile.get(i);
                if(newPeer.peerhasFile==true) {
                    //set bitfield to all 1s
                }
                else {
                    //set bitfield to all zeros
                }

                //Once this happens, ensure that we either write
                // a log file,


            }
            else {
                //Some peers had already existed
                //Thus we're going to reference the peers that exists in the list before
                //Iterating through that list of created peers
                //Connecting a new peer to all the other peers that are connected to the server

                //This list will help us keep track of how many peers came before this current peer
                //in doing so, it help us make other TCP connections
                for(int k=0; k<peer_list.size();k++) {
                    //Within this we will connect peer i to peer k that range from whatever was preceding
                }

            }


        }



    }

    /*public static void peerProcess(int peerId) {
        ReadCommon();

        //Function that starts peer process

    }*/

    public static void ReadCommon()  {
        try {
            input = new FileReader("Common.cfg");
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

            if(numPref.find()) {
                NumberOfPreferredNeighbors= Integer.valueOf(numPref.group(1));
            }
            else {
                NumberOfPreferredNeighbors=0;
            }

            if(unchokInt.find()) {
                UnchokingInterval=Integer.valueOf(unchokInt.group(1));
            }
            else {
                UnchokingInterval=0;
            }


            if(optunInt.find()) {
                OptimisticUnchokingInterval=Integer.valueOf(optunInt.group(1));
            }
            else {
                OptimisticUnchokingInterval=0;
            }

            if(filename.find()) {
                FileName=filename.group(1);
            }
            else {
              FileName= "No file name";
            }

            if(filesize.find()) {
                FileSize=Integer.valueOf(filesize.group(1));
            }
            else {
                FileSize=0;
            }

            if(pieceSize.find()) {
                PieceSize=Integer.valueOf(pieceSize.group(1));
            }
            else {
                PieceSize=0;
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}

class Peer extends PeerProcess {

    boolean peerhasFile;
    public char[] bitfield=new char[16];

    public static void peerProcess(int peerId){
        //Making a connection server
        try {
            String path = System.getProperty("user.dir");
            int total_list_size = peerIDs.size() / 3;
            for (int i = 0; i < total_list_size; i++) {
                RemotePeerInfo pInfo = new RemotePeerInfo(peerIDs.get(i).toString(), hostNames.get(i), ports_list.get(i).toString());

                System.out.println("Start remote peer " + pInfo.peerId +  " at " + pInfo.peerAddress );

                // *********************** IMPORTANT *************************** //
                // If your program is JAVA, use this line.
                Runtime.getRuntime().exec("ssh " + pInfo.peerAddress + " cd " + path + "; java peerProcess " + pInfo.peerId);

                // If your program is C/C++, use this line instead of the above line.
                //Runtime.getRuntime().exec("ssh " + pInfo.peerAddress + " cd " + path + "; ./peerProcess " + pInfo.peerId);
            }
            System.out.println("Starting all remote peers has done." );
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        ReadCommon();

        //Setting up the bit field

    }

    public static void main(String[] args) {
        PeerProcess p = new PeerProcess();
        //Making a instance of the peer process so that we see multiple instances
        p.ReadPeer();

    }


}

class BitField extends Peer {
    ArrayList<Pieces> piece_for_bitfield= new ArrayList<Pieces>();

}

class Pieces extends BitField {

    //Each piece should have filled value
    // Each piece should have log message saying its been logged

    boolean filled;
    boolean isLogged;

}


class Logged extends Peer {
    //This variable will represent where the path of the logged file is for each peer

    String filepath;
}