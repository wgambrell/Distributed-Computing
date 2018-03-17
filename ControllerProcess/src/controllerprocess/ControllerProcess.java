
package controllerprocess;
import java.net.*;
import java.io.*;
import java.util.*;


public class ControllerProcess {
    
    private static ArrayList<String> arr1;
    private static ArrayList<ProcessInfo> arr2;
    private static String[][] mat = new String[3][30];
    
    public static void main(String[] args) {
      arr1 = new ArrayList<String>();
      arr2 = new ArrayList<ProcessInfo>();
      System.out.println("Controller Process started");
        int port = Integer.parseInt("5477");
        final int MAX_LEN = 1024;
        boolean b = true;
        try{
            DatagramSocket mySocket = new DatagramSocket(port);
            String message = "";
            byte[] buffer = new byte[1024];
            while(b == true){    
                DatagramPacket datagram1 = new DatagramPacket(buffer, buffer.length);
                mySocket.receive(datagram1);
                System.out.println("message was received");
                message = new String(datagram1.getData());
                arr1.add(message);
                System.out.println(message); 
                if(arr1.size() == 6)
                    b = false;
            }
            addToList(arr1);
            calculateVector(arr2);
            calculateScalar(arr2);
            printTable();
            mySocket.close();
        }
        catch(Exception ex){
                ex.printStackTrace();
                
            }
        }
    public static void addToList(ArrayList<String> arr){
        String s1,s2,s3;
        int a, b;
        for(int i = 0; i < arr.size(); i ++){
            String st = arr.get(i);
            s1 = st.substring(0,2);
            s2 = st.substring(3,4);
            s3 = st.substring(5,8);
            a = Integer.parseInt(s2);
            b = Integer.parseInt(s3);
           
            ProcessInfo pi = new ProcessInfo(s1,a,b) ;
            arr2.add(pi);
               
    }
    }
    public static void printTable(){
        System.out.println(" ");
        System.out.println("   Scalar  Vector");
        for(int i = 0; i < 30; i++){
            String s = "" + mat[0][i] +  " | " + mat[1][i] + " | " + mat[2][i];
            System.out.println(s);
        }
    }
    //finds if vector is happen before, after or is concurrent
    public static void calculateVector(ArrayList<ProcessInfo> arr){
        int b, temp1, temp2, count = 0;
        String time1 = "";
        boolean dec1= false,dec2 =false;
        for(int i = 0; i < arr.size(); i++){
            for(int k = 0; k < arr.size(); k++){
                if(arr.get(i).event.equals(arr.get(k).event))
                    b = 0;
                else{
                    temp1 = arr.get(i).vector;
                    temp2 = arr.get(k).vector;
                    if(temp1 > temp2)
                        dec1 = false;
                    else
                        dec1 = true;
                   
                    int[] eve1 = new int[3];
                    int[] eve2 = new int[3];
                    for(int p = 0; p < 3; p++){
                        eve1[p] = temp1 % 10;
                        temp1 = temp1/10;
                    }
                    for(int j = 0; j < 3; j++){
                        eve2[j] = temp2 % 10;
                        temp2 = temp2/10;
                    }  
                        
                    if(dec1 == true){
                        for(int m = 0; m < 3; m++){
                            if(eve2[m] >= eve1[m])
                                dec2 = true;
                            else{
                                dec2 = false;
                                break;
                            }
                        }
                        if(dec2 == false)
                            time1 = "c";
                        else
                            time1 = "h";
                    }
                    else{
                        for(int m = 0; m < 3; m++){
                            if(eve1[m] >= eve2[m])
                                dec2 = true;
                            else{
                                dec2 = false;
                                break;
                            }
                        }
                        if(dec2 == false)
                            time1 = "c";
                        else
                            time1 = "n";
                    }
                    //matrix here
                    mat[0][count] = "" + arr.get(i).event + "," + arr.get(k).event;
                    mat[2][count] = "" + time1;
                    mat[1][count] = "--";
                    count++;
                }
                    
            }
        }
    }
    public static void calculateScalar(ArrayList<ProcessInfo> arr){
        int b, count = 0;
        String time1 = "";
        for(int i = 0; i < arr.size(); i++){
            for(int k = 0; k < arr.size(); k++){
                if(arr.get(i).event.equals(arr.get(k).event))
                    b = 0;
                else{
                    if(arr.get(i).scalar > arr.get(k).scalar){
                        time1 = "n";
                    }
                    else
                        time1 = "h";
                    mat[1][count] = "" + time1;
                    count++;
                }
                }
            }
        }
    }
    
    


    
