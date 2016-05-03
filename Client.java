/**
 * Created by Salad on 3/6/2016.
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;




public class Client {

    private Socket socket;
    private boolean connected;
    private Inport inport;
    static boolean isTurn = false;
    String standOrHit = "";

    public Client(Socket newSocket)
    {
        // Set properties
        socket = newSocket;
        connected = true;
        // Get input
        inport = new Inport();
        inport.start();
    }

    /**
     * Handles all incoming data from this user.
     */
    private class Inport extends Thread
    {
        private ObjectInputStream in;
        public void run()
        {
            // Open the InputStream
            try
            {
                in = new ObjectInputStream(socket.getInputStream());
            }
            catch(IOException e)
            {
                System.out.println("Could not get input stream from "+toString());
                return;
            }
            // Announce
            System.out.println(socket+" has connected input.");
            // Enter process loop
            while(true)
            {
                // Sleep
                try
                {
                    Thread.sleep(2000);
                }
                catch(Exception e)
                {
                    System.out.println(toString()+" has input interrupted.");
                }
            }
        }
    }

    public static void main (String[] args) throws IOException{
        String selection = "";
        String serverName = "127.0.0.1"; //Our Server Name
        int score = 0;
        String sh = "It's your turn. Enter 'H' for Hit or 'S' for stand) \n Score:"+score;
        int port = 9999; //Our Server port
        Scanner keyboard = new Scanner(System.in);
        //System.out.println("Connecting : " + serverName + " To Port : "  + port);

        Socket client = new Socket(serverName, port); //Create a Client Socket and attempt to connect to the server @ port

        OutputStream outputStream = client.getOutputStream(); //Create a stream for sending data
        DataOutputStream out = new DataOutputStream(outputStream); //Wrap that stream around a DataOutputStream

        System.out.println("Just connected to " + client.getRemoteSocketAddress());

        while(selection.equals("S") == false || selection.equals("H") == false) {
            while (selection != "S") {
                //Ask to Hit or Stand
                System.out.println(sh);
                selection = keyboard.nextLine();
                //Send the H to the Server

                //And server score to our score

                //Loop back until stand
            }
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
                out.flush( );


                //InputStream is used for reading
                InputStream inputStream = client.getInputStream(); //Read the incoming stream as bytes
                DataInputStream dataInputStream = new DataInputStream(inputStream); //Read the inputStream and convert to primative times


        if(Integer.parseInt(dataInputStream.readUTF()) >= 11)
        {
            //score = 10;
        }
        else
        {
            //value = value + Integer.parseInt(dataInputStream.readUTF());
        }

        //System.out.println("New score : " + value); // Return what is read as a string

        //client.close();
    }



}
