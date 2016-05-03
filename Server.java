

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

    public Server(int port) throws  IOException
    {
        serverSocket = new ServerSocket(port,2);

    }


    //Thread to execute once t.Start is called
    public void run()
    {


        //Just as we are awaiting,print to the user.
        System.out.println("Waiting for client on port : " + serverSocket.getLocalPort() + " ... ");
        Socket server = null;



        try {
                // Get a client trying to connect
                server = serverSocket.accept();
            // Client has connected
            System.out.println("Player "+ clients.size()+1 +" has connected.");
            // Add user to list
            clients.add(new Client(server));
            ;

            //If the server is accepted, connect the user
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
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
                System.out.println("Player "+ (clients.size()+1) +" has connected.");
                // Add user to list
                clients.add(new Client(socket));
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

    int p1 = 0;
    int p2 = 0;
    int score = 0;
    Random rand;
    int temp = 0;
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
