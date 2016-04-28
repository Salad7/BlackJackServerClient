/**
 * Created by Salad on 3/6/2016.
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    static boolean isError = true;
    public static void main (String[] args) throws IOException{
        String term = "";
        int selection;
        String serverName = "127.0.0.1"; //Our Server Name
        int value = 0;
        String sh = "Take a hit (1) or Stand (2)";
        int port = 80; //Our Server port
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Connecting : " + serverName + " To Port : "  + port);

            Socket client = new Socket(serverName, port); //Create a Client Socket and attempt to connect to the server @ port

            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());


            OutputStream outputStream = client.getOutputStream(); //Create a stream for sending data
            DataOutputStream out = new DataOutputStream(outputStream); //Wrap that stream around a DataOutputStream

        while(isError == true) {
            System.out.println("Player One has : " + value);
            System.out.println(sh);
            selection = keyboard.nextInt();
            while (__handler(selection) == 999) {

            }
        }
                out.writeUTF(Integer.toString(value));
                out.flush( );


                //InputStream is used for reading

                InputStream inputStream = client.getInputStream(); //Read the incoming stream as bytes
                DataInputStream dataInputStream = new DataInputStream(inputStream); //Read the inputStream and convert to primative times
                System.out.println("The Converted value is : " + dataInputStream.readUTF()); // Return what is read as a string



        client.close();
    }

    public static int __handler(int selection)
    {
     if(selection == 1)
     {

     }
     else if (selection == 2)
     {

     }
        else
     {
        return 999;
     }
        return 111;
     }

}
