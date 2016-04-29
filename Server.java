

import java.net.*;
import java.io.*;

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


                ;


               DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
                dataOutputStream.writeUTF("Converted value is : ");
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

    public static void main(String [] args)
    {
        int port = 80;
        int p1 = 0;
        int p2 = 0;
        int dealer = 0;
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
