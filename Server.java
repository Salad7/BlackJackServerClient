

import java.net.*;
import java.io.*;
import java.util.Random;

/**
 * Created by Salad on 3/6/2016.
 */
public class Server extends Thread implements Runnable{

private ServerSocket serverSocket;

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
            server = serverSocket.accept();
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

                //Read input from the client
                DataInputStream dataInputStream = new DataInputStream(server.getInputStream());
                //If we decide to hit
                if(dataInputStream.readUTF().equals("1"))
                {
                    //We want to send a random score 1-14
                    rand = new Random();
                    int temp = rand.nextInt(14);
                    if(temp >= 11)
                    {
                        score = 10;
                    }
                    else
                    {
                        score = temp;
                        p1 = p1 + score;
                    }

                }

                ;


               DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
                dataOutputStream.writeUTF("Your score is : "+p1);
                dataOutputStream.flush();


                   server.close();
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
    public static void main(String [] args)
    {
        int port = 80;
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
