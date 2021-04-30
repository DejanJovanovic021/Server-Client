
package client;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client {

    
    public static void main(String[] args) {
       
        try{
            
            Socket server = new Socket("localhost" , 100);                          
            DataInputStream dis = new DataInputStream(server.getInputStream());     
            DataOutputStream dos = new DataOutputStream(server.getOutputStream());  
            
            Frame f = new Frame("Assignment");                                     
            f.setLayout(null);                                                     
            f.setBackground(Color.GRAY);                                         
            
            Label l = new Label("Choose operation!");                               
            l.setBounds(200, 50, 175, 20);                                         
            l.setFont(new Font("Arial" , Font.BOLD , 20));                          
            
            
            CheckboxGroup myRadioGroup = new CheckboxGroup();                      
            Checkbox cb = new Checkbox("+", myRadioGroup, false);                  
            Checkbox cb1 = new Checkbox("-", myRadioGroup, false);
            Checkbox cb2 = new Checkbox("*", myRadioGroup, false);
            Checkbox cb3 = new Checkbox("/", myRadioGroup, false);
            
            cb.setBounds(100, 100, 50, 50);                                         
            cb1.setBounds(220, 100, 50, 50);
            cb2.setBounds(340, 100, 50, 50);
            cb3.setBounds(460, 100, 50, 50);
            
            cb.setFont(new Font("Arial", Font.BOLD, 30));                           
            cb1.setFont(new Font("Arial", Font.BOLD, 30));
            cb2.setFont(new Font("Arial", Font.BOLD, 30));
            cb3.setFont(new Font("Arial", Font.BOLD, 30));
            
            Label l1 = new Label();                                                 
            l1.setBounds(285, 230, 50, 50);
            l1.setFont(new Font("Arial" , Font.BOLD , 50));
            l1.setForeground(Color.red);
            
            
            TextField num1 = new TextField();                                      
            num1.setBounds(50, 200, 200, 100);
            num1.setFont(new Font("Arial" , Font.BOLD , 70));
            
            TextField num2 = new TextField();                                       
            num2.setBounds(350, 200, 200, 100);
            num2.setFont(new Font("Arial" , Font.BOLD , 70));
            
            TextField score = new TextField();                                      
            score.setBounds(200, 450, 200, 100);
            score.setFont(new Font("Arial" , Font.BOLD , 70));
            score.setForeground(java.awt.Color.red);
            
            Button bScore = new Button("Calculate");                                
            bScore.setBounds(200, 350, 200, 50);
            bScore.setFont(new Font("Arial" , Font.BOLD , 30));
            bScore.setForeground(java.awt.Color.red);
            
            bScore.addActionListener(new ActionListener() {                         
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    double number1 = Double.parseDouble(num1.getText());            
                    double number2 = Double.parseDouble(num2.getText());
                    String operation = l1.getText();                                
                    
                    try{
                        
                        dos.writeDouble(number1);                                   
                        dos.writeDouble(number2);
                        dos.writeUTF(operation);                                    
                        
                        double result;
                        result = dis.readDouble();                                  
                        
                        score.setText(Double.toString(result));                     
                        
                    } catch (IOException ex) {
                        
                        System.out.println(ex.getMessage());
                    }
                }
            });
            
            
            
            cb.addItemListener(new ItemListener() {                                 
                
                @Override
                public void itemStateChanged(ItemEvent e) {
                    
                    String sign = cb.getLabel();                                    
                    l1.setText(sign);                                               
                }
            });
            
            cb1.addItemListener(new ItemListener() {
                
                @Override
                public void itemStateChanged(ItemEvent e) {
                    
                    String sign = cb1.getLabel();
                    l1.setText(sign);
                }
            });
            
            cb2.addItemListener(new ItemListener() {
                
                @Override
                public void itemStateChanged(ItemEvent e) {
                    
                    String sign = cb2.getLabel();
                    l1.setText(sign);
                }
            });
            
            cb3.addItemListener(new ItemListener() {
                
                @Override
                public void itemStateChanged(ItemEvent e) {
                    
                    String sign = cb3.getLabel();
                    l1.setText(sign);
                }
            });
            
            
            
            
            
            f.add(l);
            f.add(cb);
            f.add(cb1);
            f.add(cb2);
            f.add(cb3);
            f.add(l1);
            f.add(num1);
            f.add(num2);
            f.add(score);
            f.add(bScore);
            f.setSize(600, 600);
            f.setResizable(false);
            f.setVisible(true);
            
            f.addWindowListener(new WindowAdapter() {                              

            @Override
            public void windowClosing(WindowEvent we) {

                try {
                    System.exit(0);                                                 
                    server.close();                                                 
                    dis.close();
                    dos.close();
                    
                } catch (IOException ex) {
                    
                    System.out.println(ex.getMessage());
                }

            }

        });

 
    }   catch (IOException ex) {  
            
            System.out.println(ex.getMessage());
        }  

      
    }
    
}
