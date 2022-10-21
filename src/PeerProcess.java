import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.lang.Math;

public class PeerProcess {


    // variables for storing information from PeerInfo.cfg
    public static ArrayList<Integer> peerIDs = new ArrayList<Integer>();

    public static ArrayList<String> hostNames = new ArrayList<String>();

    public static ArrayList<Integer> ports_list = new ArrayList<Integer>();

    public static ArrayList<Boolean> hasFile = new ArrayList<Boolean>();

    public static int NumberOfPreferredNeighbors = 0;
    public static int UnchokingInterval = 0;
    public static int OptimisticUnchokingInterval = 0;
    public static String FileName = "default_file.dat";
    public static int FileSize = 0;
    public static int PieceSize = 0;
    public static FileReader input = null;



    public static void ReadPeer() {
        //Reads PeerInfo.cfg
        //Create an array list of all the read lines
        //Start in read Peers based on previous reading and call peerProcess
        try {
            input = new FileReader("src/PeerInfo.cfg");
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

    }

    public static void peerProcess(int peerId) {

        //Function that starts peer process

    }

    public static void ReadCommon()  {
        try {
            input = new FileReader("src/Common.cfg");
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

    public static void main(String[] args) {
        PeerProcess p = new PeerProcess();
        //Making a instance of the peer process so that we see multiple instances
        p.ReadCommon();
        p.ReadPeer();


    }
}

