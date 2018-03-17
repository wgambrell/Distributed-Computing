/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerprocess;

/**
 *
 * @author tyler_000
 */
public class ProcessInfo {
       public String event;
       public int scalar;
       public int vector;
       public ProcessInfo(String e1, int s2, int v3){
           event = e1;
           scalar = s2;
           vector = v3;
       }
       public String returnEvent(){
           return event;
       }
}
