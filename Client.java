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
        String serverName = "127.0.0.1"; //Our Server Name
        int port = 80; //Our Server port
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Connecting : " + serverName + " To Port : "  + port);

            Socket client = new Socket(serverName, port); //Create a Client Socket and attempt to connect to the server @ port

            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());


            OutputStream outputStream = client.getOutputStream(); //Create a stream for sending data
            DataOutputStream out = new DataOutputStream(outputStream); //Wrap that stream around a DataOutputStream

        while(isError == true) {
            System.out.print("Enter a degree to be converted : ");
            term = keyboard.next();
            __handler(term);
        }
                out.writeUTF(term);
                out.flush( );


                //InputStream is used for reading

                InputStream inputStream = client.getInputStream(); //Read the incoming stream as bytes
                DataInputStream dataInputStream = new DataInputStream(inputStream); //Read the inputStream and convert to primative times
                System.out.println("The Converted value is : " + dataInputStream.readUTF()); // Return what is read as a string



        client.close();
    }

    public static void __handler(String degree)
    {
        String value = "";
        char CorF = degree.charAt(degree.length()-1);
        for(int i = 0; i < degree.length()-1; i++)
        {
            value = value + Character.toString(degree.charAt(i));
        }

        if(CorF == 'F')
        {
            isError = false;

        }
        else if (CorF == 'C') {
            isError = false;
        }
        else //Other wise we have an error
        {
            System.out.println( "Could not understand that request, please try again (End with C or F)");
            isError = true;
        }

    }

}
