

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Salad on 3/6/2016.
 */
public class Server extends Thread implements Runnable{

    private ServerSocket serverSocket;
    private Socket socket;
    private ArrayList<Client> clients = new ArrayList<Client>();
    private boolean isGameOver = false;
    private Random rand;
    private int scoreToAdd;
    OutputStream outputStream;
    DataOutputStream out;

    //InputStream is used for reading
    InputStream inputStream;
    DataInputStream dataInputStream;

    public Server(int port) throws  IOException
    {
        serverSocket = new ServerSocket(port,2);
        rand = new Random();

    }


    //Thread to execute once t.Start is called
    public void run()
    {


        //Just as we are awaiting,print to the user.
        System.out.println("Server started. Finding Clients...");
        //System.out.println("Waiting for client on port : " + serverSocket.getLocalPort() + " ... ");
        Socket server = null;



        try {
            //If the server is accepted, connect the user
            //System.out.println("Connection Established.");

            // Get a client trying to connect
            server = serverSocket.accept();
           outputStream = server.getOutputStream(); //Create a stream for sending data
           out = new DataOutputStream(outputStream); //Wrap that stream around a DataOutputStream

            //InputStream is used for reading
            inputStream = server.getInputStream(); //Read the incoming stream as bytes
            dataInputStream = new DataInputStream(inputStream); //Read the inputStream and convert to primative times

            // Client has connected
            System.out.println("Found Client "+ (clients.size()+1));
            // Add user to list
            clients.add(new Client(server));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        while (true)
        {
            try {
                    socket = serverSocket.accept();
                // Client has connected
                System.out.println("Found Client "+ (clients.size()+1));
                System.out.println("Initiating Game...");
                // Add user to list
                clients.add(new Client(socket));
                clients.get(0).isTurn = false;

                while(isGameOver == false || ((clients.get(0).standOrHit.equals("stand") && clients.get(1).standOrHit.equals("stand")) == false))
                {
                    if(clients.get(0).isTurn)
                    {
                        System.out.println("Player 1's Turn");
                        if(dataInputStream.readUTF().equals("H"))
                        {
                            //Send a random number 1-13
                            out.writeUTF(Integer.toString(rand.nextInt(13)+1));
                        }

                    }
                    else if (clients.get(1).isTurn)
                    {
                        System.out.println("Player 2's Turn");
                    }


                }
                //Read input from the client
                //DataInputStream dataInputStream = new DataInputStream(server.getInputStream());
                //If we decide to hit
                //if(dataInputStream.readUTF().equals("1"))
                //{
                    //We want to send a random score 1-14
                    //rand = new Random();
                  //  temp = rand.nextInt(14);

                //}


               //DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
                //dataOutputStream.writeUTF(Integer.toString(temp));
                //dataOutputStream.flush();


                  // server.close();
            }
             catch (IOException e)
            {
                e.printStackTrace();
                break;
            }

        }


    }


    public static void main(String [] args)
    {
        int port = 9999;

        try
        {
            Thread t = new Server(port);
            t.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
