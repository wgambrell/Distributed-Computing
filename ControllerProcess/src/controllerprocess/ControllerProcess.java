
package controllerprocess;
import java.net.*;
import java.io.*;
import java.util.*;

public class ControllerProcess {
    
 
    public static void main(String[] args) {
      System.out.println("Controller Process started");
        int port = Integer.parseInt("5477");
        final int MAX_LEN = 1024;
        String str = "Hello Process 3 from Process 2";
        try{
            String message = "";
                byte[] buffer = new byte[1024];
                DatagramSocket mySocket = new DatagramSocket(port);
                DatagramPacket datagram1 = new DatagramPacket(buffer, buffer.length);
                mySocket.receive(datagram1);
                System.out.println("message was received");
                message = new String(datagram1.getData());
                System.out.println(message);
                mySocket.close();
        }
        catch(Exception ex){
                ex.printStackTrace();
                
            }
        }
        // TODO code application logic here
    }
    
