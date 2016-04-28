

import java.net.*;
import java.io.*;

/**
 * Created by Salad on 3/6/2016.
 */
public class Server extends Thread implements Runnable{

private ServerSocket serverSocket;

    public Server(int port) throws  IOException
    {
        serverSocket = new ServerSocket(port);

    }

    //Converter method that will translate a xF or xC to appropriate values
    public String conversion(String degree)
    {
        String value = "";
        char CorF = degree.charAt(degree.length()-1);
        for(int i = 0; i < degree.length()-1; i++)
        {
            value = value + Character.toString(degree.charAt(i));
        }

        if(CorF == 'F') // If we're given a Fahrenheit convert to Celsius
        {
            return  Float.toString(((Float.parseFloat(value)-32)*5)/9) + "C";

        }
        else if (CorF == 'C') { // If we're given a Celsius convert to Fahrenheit
            return Float.toString(((Float.parseFloat(value) * 9) / 5) + 32) + "F";
        }
        else
            return null;
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
                dataOutputStream.writeUTF("Converted value is : "+conversion(dataInputStream.readUTF()));
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
