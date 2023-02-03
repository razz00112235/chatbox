import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;
import java.awt.event.*;
import java.io.*;  
import java.net.*;
import java.util.Scanner;  
import javax.swing.border.Border;
public class Server_end {
static JFrame frame_main;
static JPanel server;
static JLabel lb2,lb3;
static ServerSocket se;
static Socket so;
static Scanner sc1 = new Scanner(System.in);
static Scanner sca;
static  PrintWriter ps;
static String str="",str1="";
static int i=110,count=1;
static JButton send,recv_data;
static JTextField sendtext,receivetext;
static JTextArea tx1;
static void home ()
    {
        
        Color textcolor=new Color(247,232,227); 
        
        frame_main=new JFrame("Server Chat Box");
        frame_main.setSize(400, 400);
        frame_main.getContentPane().setBackground(textcolor);
        sendtext=new JTextField();
        sendtext.setBounds(5, 0, 310, 40);
        //Icon icon=new ImageIcon("send.jpg"); 
        
        send=new JButton("Send");
        send.setBounds(315, 0, 70, 40);      
        Color btclor=new Color(163,36,103); 
        send.setBackground(btclor);
        send.setForeground(Color.WHITE);
       
      
        tx1=new JTextArea("");
        tx1.setBackground(textcolor);
        tx1.setBounds(10, 45, 350, 300);
        tx1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JScrollPane sp = new JScrollPane(tx1);
       
        frame_main.add(sendtext);
        frame_main.add(send);frame_main.add(tx1);
        frame_main.setLayout(null);
        frame_main.setVisible(true);
       
        
        ActionListener send_msg=new ActionListener(){
            public void actionPerformed(ActionEvent e)
            { 
               send_data();  
               count++;
                
            }
        };
        send.addActionListener(send_msg);  
        conn();
    }
    public static void main(String[] args) {
      home();

    }

    static void conn()
    {
        try {
            se = new ServerSocket(8035);
            System.out.println("Server is running....");
            Socket s = se.accept();
            System.out.println("connection Established");
            sca = new Scanner(s.getInputStream());
            ps = new PrintWriter(s.getOutputStream(),true);
            receive_data();
            send_data();
          
        } catch (Exception e) {
            System.out.println(e);         
        }
    }

    static void send_data()
    {
        Thread send_sever = new Thread(new Runnable(){
            public void run(){                                   
                
                   
                   str = sendtext.getText();
                    ps.println(str); 
                    sendtext.setText(""); 
                    tx1.append("Me:- "+str+"\n"); 
                    
                                  

            }
        });
        send_sever.start(); 
    }
    static void receive_data()
    {
        Thread receive_sever = new Thread(new Runnable(){
            public void run(){ 
                while(true){
                        str1 = sca.nextLine();  
                        System.out.println(str1);                      
                        tx1.append("Client:- "+str1+"\n");
                        if(str.equals("Bye")){break;}
                        
                }
                        
                   
            }
        });
        receive_sever.start();  
    }

}