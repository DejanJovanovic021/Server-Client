
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    
    public static void main(String[] args) {
        
        while(true){                                                                
                
    try(ServerSocket server = new ServerSocket(100);                            
        Socket sck = server.accept();                                           
        DataOutputStream dos = new DataOutputStream(sck.getOutputStream());     
        DataInputStream dis = new DataInputStream(sck.getInputStream());){      
        
                 
                
            while(true){                                                        
            
                
            double number1 = dis.readDouble();                                  
            double number2 = dis.readDouble();                                  
            
            
            String operation = dis.readUTF();                                   
            
                
            switch(operation){                                                  
                    
                case "+":
                    dos.writeDouble(number1 + number2);                         
                    break;
                case "-":
                    dos.writeDouble(number1 - number2);
                    break;
                case "*":
                    dos.writeDouble(number1 * number2);
                    break;
                case "/":
                    if(number2 == 0){
                        System.out.println("You cant division with zero!");
                        return;
                    }
                    else{
                        dos.writeDouble(number1 / number2);
                    }
                    break;
                default:
                    System.out.println("You must choose operation!");
            }
            
                  } 
                    
            }
            catch (IOException ex) { 
            
                System.out.println(ex.getMessage());
        } 
                  
        }
    }
}
    
