

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
        DataOutputStream dataOutputStream ;
        //InputStream is used for reading
        InputStream inputStream; //Read the incoming stream as bytes
        DataInputStream dataInputStream; //Read the inputStream and convert to primative times



        try {
            //If the server is accepted, connect the user
            //System.out.println("Connection Established.");

            // Get a client trying to connect
            server = serverSocket.accept();
            //dataOutputStream = new DataOutputStream(server.getOutputStream());
            //inputStream = server.getInputStream();
            //dataInputStream = new DataInputStream(inputStream);

            // Client has connected
            System.out.println("Found Client "+ (clients.size()+1));
            // Add user to list
            clients.add(new Client(server,1));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        while (true)
        {
            try {
                    socket = serverSocket.accept();
                dataOutputStream = new DataOutputStream(server.getOutputStream());
                inputStream = server.getInputStream();
                dataInputStream = new DataInputStream(inputStream);
                // Client has connected
                System.out.println("Found Client "+ (clients.size()+1));
                System.out.println("Initiating Game...");
                // Add user to list
                clients.add(new Client(socket,2));
                clients.get(0).isTurn = true;

                while(isGameOver == false || ((clients.get(0).standOrHit.equals("stand") && clients.get(1).standOrHit.equals("stand")) == false))
                {
                    if(clients.get(0).isTurn)
                    {
                        System.out.println("Player 1's Turn");
                        if(dataInputStream.readUTF().equals("H"))
                        {
                            //Generate a random number (Works)
                            System.out.println(rand.nextInt(13)+1);

                            //Send the random number in a outputbuffer (Works)
                            dataOutputStream.write(rand.nextInt(13)+1);
                            dataOutputStream.flush();
                            clients.get(0).isTurn = false;
                            clients.get(1).isTurn = true;
                        }
                        else
                        {
                        if(dataInputStream.readUTF().equals("S"))
                        {
                            //If player 2 stands, end game compare results
                            //if(clients
                            //Else say got s from client
                        }
                        }
                    }
                    else if (clients.get(1).isTurn)
                    {
                        System.out.println("Player 2's Turn");
                        if(dataInputStream.readUTF().equals("H"))
                        {
                            //Generate a random number (Works)
                            System.out.println(rand.nextInt(13)+1);

                            //Send the random number in a outputbuffer (Works)
                            dataOutputStream.write(rand.nextInt(13)+1);
                            dataOutputStream.flush();
                            clients.get(1).isTurn = false;
                            clients.get(0).isTurn = true;
                        }

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
