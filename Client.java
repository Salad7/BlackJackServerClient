/**
 * Created by Salad on 3/6/2016.
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;




public class Client {

    String standOrHit = "";
    private Socket socket;
    private boolean connected;
    private Inport inport;
    public static boolean isTurn = false;
    public static int score = 0;
    public static int serverValue = 0;
    public static String sh = "It's your turn. Enter 'H' for Hit or 'S' for stand) \n Score:" + score;
    public static String selection = "";
    public static Scanner keyboard = new Scanner(System.in);
    public static int id;

    public Client(Socket newSocket, int ID) {
        // Set properties
        id = ID;
        socket = newSocket;
        connected = true;
        // Get input
        inport = new Inport();
        inport.start();
    }

    public static int conversion(int serverValue) {
        if (serverValue >= 10) {
            return 10;
        } else
            return serverValue;
    }

    /**
     * Handles all incoming data from this user.
     */
    private class Inport extends Thread {
        private ObjectInputStream in;

        public void run() {
            // Open the InputStream
            try {
                in = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                System.out.println("Could not get input stream from " + toString());
                return;
            }
            // Announce
            System.out.println(socket + " has connected input.");
            // Enter process loop
            while (true) {
                // Sleep
                try {
                    System.out.println("It's your turn");
                } catch (Exception e) {
                    System.out.println(toString() + " has input interrupted.");
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        String selection = "";
        String serverName = "127.0.0.1"; //Our Server Name
        int score = 0;
        int serverValue = 0;
        String sh = "It's your turn. Enter 'H' for Hit or 'S' for stand) \n Score:" + score;
        int port = 9999; //Our Server port
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Fetching server connection...");
        Socket client = new Socket(serverName, port); //Create a Client Socket and attempt to connect to the server @ port
        System.out.println("Connection Established.");
        System.out.println("Fetching Player ID...");
        System.out.println("Game Found you're Player " + Client.id + ".");
        System.out.println("Waiting for server...");

        OutputStream outputStream = client.getOutputStream(); //Create a stream for sending data
        DataOutputStream out = new DataOutputStream(outputStream); //Wrap that stream around a DataOutputStream

        //InputStream is used for reading
        InputStream inputStream = client.getInputStream(); //Read the incoming stream as bytes
        DataInputStream dataInputStream = new DataInputStream(inputStream); //Read the inputStream and convert to primative times

        while (selection.equals("S") == false || selection.equals("H") == false) {
            //if (isTurn) {

                //Ask to Hit or Stand
                System.out.println(sh);
                selection = keyboard.nextLine();
                while (selection.equals("H")) {
                    //Send the H to the Server
                    out.writeUTF(selection);
                    out.flush();
                    //Add server score to our score
                    System.out.println(dataInputStream.readUTF());
                    //serverValue = Integer.parseInt(dataInputStream.readUTF());
                    //score += conversion(serverValue);
                    //System.out.println("Score now : " + score);
                    //Loop back until stand
                }
                {
                }


                //System.out.println("Player One has : " + value);
                //System.out.println(sh);
                //selection = keyboard.nextInt();

                //while (__handler(selection) != 1 ){//&& __handler(selection) != 2) {
                //System.out.println("Try Again Invalid Selection");
                //System.out.println(sh);
                //selection = keyboard.nextInt();
                //}

                //out.writeUTF(Integer.toString(selection));


                //client.close();
           // }
        }
    }
}

