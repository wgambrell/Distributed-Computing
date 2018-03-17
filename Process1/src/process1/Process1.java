
package process1;
import java.net.*;
import java.io.*;
import java.util.*;

public class Process1 {
    private static DatagramSocket mySocket;

    public static void main(String[] args) {
       System.out.println("Process 1 started"); 
       int port = Integer.parseInt("3241");
       final int MAX_LEN = 1024;
       String str = "Hello Process 2 from Process 1";
       
       try{
                // EVENT 1
                int receiverPort = Integer.parseInt("6990");
                mySocket = new DatagramSocket(port);
                byte[] buffer1 = str.getBytes();
                InetAddress address = InetAddress.getLocalHost();
                DatagramPacket datagram1 = new DatagramPacket(buffer1, buffer1.length, address,receiverPort);
                mySocket.send(datagram1);
                System.out.println("message was sent");
                //ProcessController
                String s = "e1-1:100";
                controlContact(s);
                
                //Event 2
                byte[] buffer2 = new byte[1024];
                DatagramPacket datagram2 = new DatagramPacket(buffer2, MAX_LEN);
                mySocket.receive(datagram2);
                String message = new String(datagram2.getData());
                System.out.println("message received");
                System.out.println(message);
                //ProcessController
                String t = "e2-6:222";
                controlContact(t);
                mySocket.close();
                
       }
        catch(Exception ex){
                ex.printStackTrace();
                
            }
        // TODO code application logic here
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
