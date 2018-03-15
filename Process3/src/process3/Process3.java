
package process3;
import java.net.*;
import java.io.*;
import java.util.*;

public class Process3 {

   
    public static void main(String[] args) {
        System.out.println("Process 3 started");
        int port = Integer.parseInt("4452");
        final int MAX_LEN = 1024;
        String str = "Hello Process 1 from Process 3";
        try{
            //Event 4
                String message = "";
                byte[] buffer = new byte[1024];
                DatagramSocket mySocket = new DatagramSocket(port);
                DatagramPacket datagram1 = new DatagramPacket(buffer, buffer.length);
                mySocket.receive(datagram1);
                System.out.println("message was received");
                message = new String(datagram1.getData());
                System.out.println(message);
                //ProcessController
           
                 //EVENT 5
                InetAddress receiverHost = InetAddress.getLocalHost();
                int receiverPort = Integer.parseInt("3241");
                byte[] buffer2 = str.getBytes();
                DatagramPacket datagram = new DatagramPacket(buffer2, buffer2.length, receiverHost, receiverPort);
                mySocket.send(datagram);
                System.out.println("message 2 sent");
                //ProcessController
                
                mySocket.close();
        }
        catch(Exception ex){
                ex.printStackTrace();
                
        }
        // TODO code application logic here
    }
    
}
