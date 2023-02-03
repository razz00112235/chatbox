import javax.sound.midi.Soundbank;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;  
import java.net.*;
import java.util.Scanner;  

public class Client_end{
static JFrame frame_main;
static JPanel server;
static JLabel lb2,lb3;
static ServerSocket se;
static Socket s;
static Scanner sc1 = new Scanner(System.in);
static Scanner sca;
static  PrintWriter ps;
static String str="",str1="";
static int i=110;
static JButton send,recv_data;
static JTextField sendtext,receivetext;
static JTextArea tx2;
static void home()
    {
        
        Color textcolor=new Color(247,232,227);
        frame_main=new JFrame("Client Chat Box");
        frame_main.setSize(400, 400);
        frame_main.getContentPane().setBackground(textcolor);
        sendtext=new JTextField();
        sendtext.setBounds(5, 0, 310, 40);
        
        send=new JButton("Send");
        send.setBounds(315, 0, 70, 40);       
        Color btclor=new Color(163,36,103); 
        send.setBackground(btclor);
        send.setForeground(Color.WHITE);

          
        tx2=new JTextArea("");
        tx2.setBackground(textcolor);
        tx2.setBounds(10, 45, 350, 300);
        
       
        frame_main.add(sendtext);
        frame_main.add(send);
        frame_main.add(tx2);
       
        frame_main.setLayout(null);
        frame_main.setVisible(true);
        
        ActionListener sent_msg=new ActionListener(){
            public void actionPerformed(ActionEvent e)
            { 
                send_msg();
            }
        };
        send.addActionListener(sent_msg);  
        client_conn(); 
    }
    public static void main(String[] args) {
      home();
      

    }

    static void client_conn()
    {
        try {
            
            s  = new Socket(InetAddress.getLocalHost(),8035);
            System.out.println("Connected to server");

            sca = new Scanner(s.getInputStream());
            ps = new PrintWriter(s.getOutputStream(),true);
            rec_msg();
        } catch (Exception e) {
           System.out.println(e);
        }       
    }

    static public void send_msg()
    {      Thread sendmsg = new Thread(new Runnable(){
                        public void run(){   
                            

                            str1= sendtext.getText();
                            ps.println(str1); 
                            sendtext.setText("");
                            tx2.append("Me:- "+str1+"\n");                    
                           
                        }
                    });
                    sendmsg.start();
                     
    }

   static public void rec_msg()
    {
       
                    Thread recievemsg = new Thread(new Runnable(){
                        public void run(){ 
                           
                            while(true)
                            {
                                str = sca.nextLine();                                                                                                   
                                tx2.append("Server:- "+str+"\n");
                                if(str.equals("Bye")){break;}
                            }
                                  
                                  
                        }
                    });
                    recievemsg.start();  
                  
    }

}