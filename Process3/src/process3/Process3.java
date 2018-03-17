
package process3;
import java.net.*;
import java.io.*;
import java.util.*;

public class Process3 {
    private static DatagramSocket mySocket;
   
    public static void main(String[] args) {
        System.out.println("Process 3 started");
        int port = Integer.parseInt("4452");
        final int MAX_LEN = 1024;
        String str = "Hello Process 1 from Process 3";
        try{
            //Event 5
                String message = "";
                byte[] buffer = new byte[1024];
                mySocket = new DatagramSocket(port);
                DatagramPacket datagram1 = new DatagramPacket(buffer, buffer.length);
                mySocket.receive(datagram1);
                System.out.println("message was received");
                message = new String(datagram1.getData());
                System.out.println(message);
                //ProcessController
                String s = "e5-4:121";
                controlContact(s);
           
                 //EVENT 6
                InetAddress receiverHost = InetAddress.getLocalHost();
                int receiverPort = Integer.parseInt("3241");
                byte[] buffer2 = str.getBytes();
                DatagramPacket datagram = new DatagramPacket(buffer2, buffer2.length, receiverHost, receiverPort);
                mySocket.send(datagram);
                System.out.println("message 2 sent");
                //ProcessController
                String t = "e6-5:122";
                controlContact(t);
                
                mySocket.close();
        }
        catch(Exception ex){
                ex.printStackTrace();
                
        }
    }
     public static void controlContact(String str){
        try{
            InetAddress address = InetAddress.getLocalHost();
            int controllerPort = Integer.parseInt("5477");
                byte[] controlBuffer = str.getBytes();
                DatagramPacket datagramControl = new DatagramPacket(controlBuffer, controlBuffer.length, address,controllerPort);
                mySocket.send(datagramControl);
        }
        catch(Exception ex){
                ex.printStackTrace();
                
            }
    }
    
}
