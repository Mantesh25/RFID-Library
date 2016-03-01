/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfid;

import com.sun.jndi.toolkit.ctx.Continuation;
import com.sun.media.sound.PortMixerProvider;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import jaco.mp3.player.MP3Player;
//import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import sun.management.ManagementFactoryHelper;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.sound.sampled.Port;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTML;
import javax.swing.*;
import javax.swing.JFrame;

//************************************************************
 import java.util.Properties;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//*************************************************************
     /**
 *
 * @author Pratik
 */
public class mainpg extends javax.swing.JFrame implements SerialPortEventListener {
int panelstatus=0;
int isuret=0;
    
    Connection con,con1,con2;
    public mainpg() {

        initComponents();
        
        
        
        this.setVisible(true);
        DateFormat df=new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date date=new Date();
        Calendar cal=Calendar.getInstance();
        jTextField21.setText(df.format(cal.getTime()));
        jTextField42.setText(df.format(cal.getTime()));
        System.out.print(df.format(cal.getTime()));
        String dat=df.format(cal.getTime());
        cal.add(Calendar.DATE , 1);
        String duedt= df.format(cal.getTime());
        System.out.print(df.format(cal.getTime()));
//        jTextField30.getInputMap();
        
        
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
            
        }
        catch(ClassNotFoundException ce)
        {
            System.out.println("driver is loaded");
        }
       try
        {
          
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            System.out.println("Connection to the database is done.");
            Statement s1=con.createStatement();
    
           ResultSet rs1=s1.executeQuery("select max(catrefnum) as maxsrno from card;");
          // String i1="";
           int i1=0;
           
           if(rs1.next())
           {
                i1=rs1.getInt("maxsrno");
                //i=rs1.next();    
            } 
           
          // i=Integer.parseInt(i1);
                 System.out.println(i1);
           i1=i1+1;
               System.out.println(i1);
           
     
           
         jTextField15.setText(Integer.toString(i1));
            rs1.close();
        s1.close();
        }
        
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
            
        }
        catch(ClassNotFoundException ce)
        {
            System.out.println("driver is loaded");
        }
        
        
       try
        {
          
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            System.out.println("Connection to the database is done.");
            Statement s1=con.createStatement();
    
           ResultSet rs=s1.executeQuery("select max(AccNum) as maxsrno from circulation;");
           String i="",f="",l="",nw="";
           int lens,m,lenw;
           if(rs.next())
           {
                i=rs.getString("maxsrno");
           //i=rs.next();    
            } 
           lens=i.length();
           
           l=i.substring(1,5);
           
           m=Integer.parseInt(l);
           m++;
           
           nw=Integer.toString(m);
           
           lenw=nw.length();
           
           
                    if(lenw==1)
                    {
                        f="Z000"+nw;
                    }
                      if(lenw==2)
                    {
                        f="Z00"+nw;
                    }
                        if(lenw==3)
                    {
                        f="Z0"+nw;
                    }
                         if(lenw==4)
                    {
                        f="Z"+nw;
                    }
                         System.out.println(f);
                         
                         if(lens==f.length())
                         {
                             
                              System.out.println("Both Length match");
                         }
                         
                         jTextField66.setText(f);
        }
       catch(Exception e)
       {
            System.out.println(e);
       }
       
        
          
        RecieveData();
               
    }
//***************************rfid**********
  InputStream inputStream;
    private Enumeration portList;
    private CommPortIdentifier portId;
    private SerialPort serialPort;
    int i=0;
    String ss="";
    
     public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
                System.out.println("BI");
            case SerialPortEvent.OE:
                System.out.println("OE");
            case SerialPortEvent.FE:
                System.out.println("FE");
            case SerialPortEvent.PE:
                System.out.println("PE");
            case SerialPortEvent.CD:
                System.out.println("CD");
            case SerialPortEvent.CTS:
                System.out.println("CTS");
            case SerialPortEvent.DSR:
                System.out.println("DSR");
            case SerialPortEvent.RI:
                System.out.println("RI");
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                System.out.println("buffer empty");
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                byte[] readBuffer = new byte[20];
                try {
                    while (inputStream.available() > 10) {
                        int numBytes = inputStream.read(readBuffer);
                        System.out.println("massage" + numBytes);

                        String sdata = new String(readBuffer);
                        sdata = sdata.trim();
                        ss=ss+sdata;
                        if (!ss.equals("")) //&& i==1) 
                        {
                            //
                            //jTextField1.append(ss.substring(0,12));
                           /*if(jTextField1.getText().isEmpty())
                           {*///
                            switch(panelstatus)
                            {
                                case 1:
                           jTextField65.setText(ss);
                           jTextField65.requestFocus();
                           break;
                                case 2:
                                    if(jLabel14.getText().isEmpty()){
                                    jTextField9.setText(ss);
                                    jTextField9.requestFocus();
                                    
                                    }
                                     if((!(jTextField19.getText().trim()).isEmpty()))// && isuret==1)
                                    {
                                        jTextField64.setFocusable(true);
                                    jTextField64.setText(ss);  
                                  
                               jTextField64.requestFocus();
                               System.out.println("hmmmmm its here....");
                                jTextField64.requestFocus();
                                    }
                                     break;
                                case 3:
                                jTextField2.setText(ss);
                                jTextField2.requestFocus(); 
                                 break;
                            
                                case 4:
                                    jTextField10.setText(ss);
                                    jTextField10.requestFocus();
                                break;
                                
                                case 5:
                                      if(jTextField31.getText().isEmpty())
                                      jTextField31.setText(ss);
                                      jTextField31.requestFocus();
                                    
                                   break;
                                case 6 :
                          if(jTextField39.getText().isEmpty())
                           jTextField39.setText(ss);
                                    jTextField39.requestFocus();
                                    break;
                                case 7:
                                    jTextField71.setText(ss);
                                    jTextField71.requestFocus();
                           break;
                              case 8 :
//                          if(jTextField83.getText().isEmpty())
                           jTextField83.setText(ss);
                                    jTextField83.requestFocus();
                                    break;
                                  
                              case 9 :
  //                        if(jTextField89.getText().isEmpty())
                           jTextField89.setText(ss);
                                    jTextField89.requestFocus();
                                    break;
            
            
      
      
                                    /*                            
                              case 4:
                           if(!(jTextField19.getText().trim()).isEmpty())
                           {
                               jTextField64.setText(ss);
                               jTextField64.requestFocus();
                           }
                        
                           break; */
                            }
                           //jTextField1.requestFocus();
                          // jTextField1.setFocusable(true);
                           //}
                            //st.executeUpdate("INSERT into rfiddata(RfidNo) VALUES('"+ss.substring(0,12)+"')");
                            System.out.println(ss);
                            ss="";
                            i=0;
                        }
                        i++;
                    }
                
                } catch (IOException e) {
                    System.out.println(e);
                }/* catch (Exception e) {
                    System.out.println(e);
                }*/
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println(e);
                }

                break;
        }
    }


    public void RecieveData() {
        portList = CommPortIdentifier.getPortIdentifiers();
        System.out.println(portList);
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            System.out.println(portId.getName());
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals("COM6")) {
                    try {
                        serialPort = (SerialPort) portId.open("mainpg", 2000);
                        inputStream = serialPort.getInputStream();
                        serialPort.addEventListener(this);
                        serialPort.notifyOnDataAvailable(true);
                        serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }
    //*************************************************RFID<-
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jTextField54 = new javax.swing.JTextField();
        jTextField55 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jButton17 = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jTextField42 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jTextField43 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel55 = new javax.swing.JLabel();
        jTextField66 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jTextField81 = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jTextField45 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jTextField47 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jTextField53 = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel56 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jTextField58 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jTextField65 = new javax.swing.JTextField();
        jTextField60 = new javax.swing.JTextField();
        jTextField61 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jTextField62 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jTextField63 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jTextField46 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jTextField64 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jLabel104 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        jTextField67 = new javax.swing.JTextField();
        jButton28 = new javax.swing.JButton();
        jLabel105 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jTextField68 = new javax.swing.JTextField();
        jTextField69 = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        jLabel106 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jTextField70 = new javax.swing.JTextField();
        jTextField71 = new javax.swing.JTextField();
        jTextField72 = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jTextField73 = new javax.swing.JTextField();
        jTextField74 = new javax.swing.JTextField();
        jTextField75 = new javax.swing.JTextField();
        jTextField76 = new javax.swing.JTextField();
        jTextField77 = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jTextField78 = new javax.swing.JTextField();
        jTextField79 = new javax.swing.JTextField();
        jTextField80 = new javax.swing.JTextField();
        jButton32 = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jTextField82 = new javax.swing.JTextField();
        jLabel122 = new javax.swing.JLabel();
        jTextField84 = new javax.swing.JTextField();
        jTextField83 = new javax.swing.JTextField();
        jTextField85 = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jTextField86 = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jTextField87 = new javax.swing.JTextField();
        jButton33 = new javax.swing.JButton();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jTextField88 = new javax.swing.JTextField();
        jLabel130 = new javax.swing.JLabel();
        jTextField89 = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jTextField90 = new javax.swing.JTextField();
        jTextField91 = new javax.swing.JTextField();
        jTextField92 = new javax.swing.JTextField();
        jLabel134 = new javax.swing.JLabel();
        jTextField93 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        jTextField94 = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jLabel137 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setToolTipText("");
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setAutoscrolls(true);

        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane1.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1400, 500));

        jPanel9.setLayout(null);

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/MAINPG.jpg"))); // NOI18N
        jPanel9.add(jLabel52);
        jLabel52.setBounds(0, 0, 1360, 740);

        jTabbedPane1.addTab("HOME", jPanel9);
        jPanel9.getAccessibleContext().setAccessibleDescription("");

        jTabbedPane3.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N

        jPanel6.setLayout(null);

        jLabel13.setBackground(new java.awt.Color(102, 255, 51));
        jLabel13.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 255, 51));
        jLabel13.setText("Book Name    :");
        jPanel6.add(jLabel13);
        jLabel13.setBounds(40, 140, 146, 23);

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField13KeyTyped(evt);
            }
        });
        jPanel6.add(jTextField13);
        jTextField13.setBounds(210, 140, 280, 30);

        jButton3.setBackground(new java.awt.Color(102, 255, 255));
        jButton3.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 0, 51));
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3);
        jButton3.setBounds(550, 140, 110, 31);

        jTable4.setAutoCreateRowSorter(true);
        jTable4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Name", "Author Name", "Total Copies"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable4.setGridColor(new java.awt.Color(0, 204, 204));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel6.add(jScrollPane4);
        jScrollPane4.setBounds(20, 380, 720, 200);

        jButton7.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 255));
        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });
        jPanel6.add(jButton7);
        jButton7.setBounds(290, 300, 98, 30);

        jLabel29.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 255, 0));
        jLabel29.setText("Author Name :");
        jPanel6.add(jLabel29);
        jLabel29.setBounds(40, 210, 146, 21);
        jPanel6.add(jTextField23);
        jTextField23.setBounds(210, 210, 280, 30);

        jButton11.setBackground(new java.awt.Color(102, 255, 255));
        jButton11.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 0, 0));
        jButton11.setText("Search");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton11);
        jButton11.setBounds(550, 210, 110, 30);

        jTextField54.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField54FocusGained(evt);
            }
        });
        jPanel6.add(jTextField54);
        jTextField54.setBounds(1070, 150, 180, 30);
        jPanel6.add(jTextField55);
        jTextField55.setBounds(1070, 210, 180, 30);

        jButton20.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 0, 204));
        jButton20.setText("Update Information");
        jButton20.setEnabled(false);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton20);
        jButton20.setBounds(950, 300, 240, 30);

        jLabel90.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 255, 0));
        jLabel90.setText("Book ID :");
        jPanel6.add(jLabel90);
        jLabel90.setBounds(900, 150, 90, 21);

        jTable6.setAutoCreateRowSorter(true);
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Accesion No", "Book_ID", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable6);

        jPanel6.add(jScrollPane8);
        jScrollPane8.setBounds(790, 380, 470, 200);

        jButton17.setBackground(new java.awt.Color(153, 255, 255));
        jButton17.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 0, 153));
        jButton17.setText("Begin Search in Shelf");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton17);
        jButton17.setBounds(480, 300, 220, 30);

        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField10FocusGained(evt);
            }
        });
        jPanel6.add(jTextField10);
        jTextField10.setBounds(740, 300, 140, 30);

        jLabel91.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 255, 0));
        jLabel91.setText("Book Name :");
        jPanel6.add(jLabel91);
        jLabel91.setBounds(900, 220, 130, 21);

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/bksrch.jpg"))); // NOI18N
        jPanel6.add(jLabel42);
        jLabel42.setBounds(-10, -10, 1360, 840);

        jTabbedPane3.addTab("Check Availability", jPanel6);

        jPanel7.setLayout(null);

        jLabel16.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 255, 0));
        jLabel16.setText("Book Name :");
        jPanel7.add(jLabel16);
        jLabel16.setBounds(30, 180, 130, 21);

        jLabel17.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 255, 51));
        jLabel17.setText("Author Name :");
        jPanel7.add(jLabel17);
        jLabel17.setBounds(570, 180, 140, 30);

        jLabel18.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 255, 0));
        jLabel18.setText("Publishing Year :");
        jPanel7.add(jLabel18);
        jLabel18.setBounds(20, 370, 170, 21);

        jLabel19.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 255, 0));
        jLabel19.setText("Book ID :");
        jPanel7.add(jLabel19);
        jLabel19.setBounds(50, 120, 150, 20);

        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jPanel7.add(jTextField15);
        jTextField15.setBounds(210, 120, 260, 30);

        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField16KeyTyped(evt);
            }
        });
        jPanel7.add(jTextField16);
        jTextField16.setBounds(740, 180, 270, 30);

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField17KeyTyped(evt);
            }
        });
        jPanel7.add(jTextField17);
        jTextField17.setBounds(210, 180, 260, 30);

        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });
        jPanel7.add(jTextField18);
        jTextField18.setBounds(210, 360, 260, 30);

        jButton4.setBackground(new java.awt.Color(204, 255, 255));
        jButton4.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 51, 204));
        jButton4.setText("Add Book");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4);
        jButton4.setBounds(330, 510, 140, 30);

        jLabel38.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 255, 255));
        jLabel38.setText("***  Add  New  Book  ***");
        jPanel7.add(jLabel38);
        jLabel38.setBounds(170, 20, 620, 50);

        jButton13.setBackground(new java.awt.Color(204, 255, 255));
        jButton13.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 51, 255));
        jButton13.setText("Clear");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton13);
        jButton13.setBounds(590, 510, 120, 30);

        jLabel62.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(51, 255, 0));
        jLabel62.setText("No. Of Copies :");
        jPanel7.add(jLabel62);
        jLabel62.setBounds(570, 310, 150, 21);

        jTextField42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField42ActionPerformed(evt);
            }
        });
        jPanel7.add(jTextField42);
        jTextField42.setBounds(210, 420, 260, 30);

        jLabel63.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 255, 0));
        jLabel63.setText("Date :");
        jPanel7.add(jLabel63);
        jLabel63.setBounds(50, 430, 75, 21);

        jTextField43.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField43KeyTyped(evt);
            }
        });
        jPanel7.add(jTextField43);
        jTextField43.setBounds(740, 300, 280, 30);

        jLabel61.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 255, 0));
        jLabel61.setText("ISBN Number :");
        jPanel7.add(jLabel61);
        jLabel61.setBounds(30, 240, 143, 21);
        jPanel7.add(jTextField4);
        jTextField4.setBounds(210, 240, 260, 30);

        jLabel64.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 255, 0));
        jLabel64.setText("Language CD :");
        jPanel7.add(jLabel64);
        jLabel64.setBounds(570, 240, 160, 21);

        jComboBox4.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(255, 51, 51));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--", "ENG", "HIN", "MAR" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel7.add(jComboBox4);
        jComboBox4.setBounds(740, 240, 150, 30);

        jLabel65.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 255, 0));
        jLabel65.setText("Author Mark :");
        jPanel7.add(jLabel65);
        jLabel65.setBounds(20, 300, 150, 21);

        jTextField44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField44ActionPerformed(evt);
            }
        });
        jPanel7.add(jTextField44);
        jTextField44.setBounds(210, 300, 260, 30);

        jLabel66.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 255, 0));
        jLabel66.setText("Bibliography :");
        jPanel7.add(jLabel66);
        jLabel66.setBounds(570, 360, 139, 21);

        jTextArea1.setColumns(10);
        jTextArea1.setRows(5);
        jScrollPane7.setViewportView(jTextArea1);

        jPanel7.add(jScrollPane7);
        jScrollPane7.setBounds(740, 350, 280, 57);

        jLabel55.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(102, 255, 0));
        jLabel55.setText("Accession No :");
        jPanel7.add(jLabel55);
        jLabel55.setBounds(570, 120, 150, 40);

        jTextField66.setEditable(false);
        jPanel7.add(jTextField66);
        jTextField66.setBounds(740, 120, 270, 30);

        jLabel95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/addbk.jpg"))); // NOI18N
        jPanel7.add(jLabel95);
        jLabel95.setBounds(0, 0, 1350, 620);

        jTabbedPane3.addTab("Add Book", jPanel7);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Book Details", jPanel2);

        jTabbedPane2.setAutoscrolls(true);
        jTabbedPane2.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabbedPane2MousePressed(evt);
            }
        });

        jPanel5.setAutoscrolls(true);
        jPanel5.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Student ID :");
        jPanel5.add(jLabel5);
        jLabel5.setBounds(70, 200, 120, 20);

        jLabel6.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Student Name :");
        jPanel5.add(jLabel6);
        jLabel6.setBounds(70, 260, 150, 30);

        jLabel7.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 0));
        jLabel7.setText("Branch : ");
        jPanel5.add(jLabel7);
        jLabel7.setBounds(70, 380, 120, 20);

        jLabel8.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("Year :");
        jPanel5.add(jLabel8);
        jLabel8.setBounds(460, 380, 80, 21);

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField5);
        jTextField5.setBounds(230, 190, 200, 30);

        jTextField6.setToolTipText("Name");
        jTextField6.setName(""); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField6);
        jTextField6.setBounds(230, 260, 200, 30);
        jTextField6.getAccessibleContext().setAccessibleName("");

        jButton2.setBackground(new java.awt.Color(153, 255, 255));
        jButton2.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 255));
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);
        jButton2.setBounds(270, 560, 122, 37);

        jLabel11.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 0));
        jLabel11.setText("Phone No :");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(70, 440, 120, 20);

        jLabel12.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 0));
        jLabel12.setText("E_mail :");
        jPanel5.add(jLabel12);
        jLabel12.setBounds(70, 500, 93, 30);

        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField11);
        jTextField11.setBounds(230, 440, 200, 30);
        jPanel5.add(jTextField12);
        jTextField12.setBounds(230, 500, 200, 30);

        jLabel40.setFont(new java.awt.Font("Georgia", 3, 60)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(153, 255, 0));
        jLabel40.setText("*** Add New Member ***");
        jPanel5.add(jLabel40);
        jLabel40.setBounds(220, 50, 800, 70);

        jButton12.setBackground(new java.awt.Color(153, 255, 255));
        jButton12.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 51, 255));
        jButton12.setText("Clear");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton12);
        jButton12.setBounds(460, 560, 108, 37);

        jTextField24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField24KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField24);
        jTextField24.setBounds(600, 260, 160, 30);

        jTextField25.setToolTipText("Sirname");
        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });
        jTextField25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField25KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField25);
        jTextField25.setBounds(910, 260, 160, 30);

        jComboBox1.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 0, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FE", "SE", "TE", "BE", "MBA", "PG", "IN" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox1);
        jComboBox1.setBounds(600, 380, 160, 30);

        jComboBox2.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(255, 0, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CSE", "E&TC", "ELECTRICAL", "MECH", "CIVIL", "MBA", "FACULTY" }));
        jPanel5.add(jComboBox2);
        jComboBox2.setBounds(230, 380, 200, 30);

        jLabel39.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 0));
        jLabel39.setText("Mobile :");
        jPanel5.add(jLabel39);
        jLabel39.setBounds(460, 440, 90, 30);

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField7);
        jTextField7.setBounds(600, 440, 170, 30);

        jLabel46.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 0));
        jLabel46.setText("Address :");
        jPanel5.add(jLabel46);
        jLabel46.setBounds(70, 320, 130, 20);
        jPanel5.add(jTextField8);
        jTextField8.setBounds(230, 320, 200, 30);

        jLabel47.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 0));
        jLabel47.setText("Pin Code :");
        jPanel5.add(jLabel47);
        jLabel47.setBounds(790, 330, 130, 21);

        jTextField26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField26KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField26);
        jTextField26.setBounds(910, 320, 160, 30);

        jLabel48.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 0));
        jLabel48.setText("Current Operator:");
        jPanel5.add(jLabel48);
        jLabel48.setBounds(1059, 14, 150, 30);

        jTextField29.setEditable(false);
        jTextField29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField29ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField29);
        jTextField29.setBounds(1210, 20, 83, 20);

        jLabel49.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 0));
        jLabel49.setText("City :");
        jPanel5.add(jLabel49);
        jLabel49.setBounds(460, 320, 70, 30);

        jTextField34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField34ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField34);
        jTextField34.setBounds(600, 320, 160, 30);

        jTextField81.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField81KeyTyped(evt);
            }
        });
        jPanel5.add(jTextField81);
        jTextField81.setBounds(600, 190, 160, 30);

        jLabel120.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(255, 255, 0));
        jLabel120.setText("Admission Year");
        jPanel5.add(jLabel120);
        jLabel120.setBounds(440, 190, 180, 30);

        jLabel41.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 0));
        jLabel41.setText("Middle Name");
        jPanel5.add(jLabel41);
        jLabel41.setBounds(450, 260, 130, 30);

        jLabel51.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 0));
        jLabel51.setText("Last Name");
        jPanel5.add(jLabel51);
        jLabel51.setBounds(790, 270, 110, 20);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/addstud.jpg"))); // NOI18N
        jPanel5.add(jLabel15);
        jLabel15.setBounds(0, -30, 1360, 890);

        jTabbedPane2.addTab("Add Membership", jPanel5);

        jPanel4.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 255, 0));
        jLabel1.setText("Student ID  : ");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(40, 140, 130, 24);
        jPanel4.add(jTextField1);
        jTextField1.setBounds(190, 140, 216, 30);

        jButton1.setBackground(new java.awt.Color(102, 255, 255));
        jButton1.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 255));
        jButton1.setText("Check Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);
        jButton1.setBounds(470, 220, 140, 30);

        jLabel44.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 255, 255));
        jLabel44.setText(".. Search Student Details ..");
        jPanel4.add(jLabel44);
        jLabel44.setBounds(230, 40, 680, 50);
        jPanel4.add(jTextField33);
        jTextField33.setBounds(190, 220, 216, 30);

        jLabel43.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 255, 0));
        jLabel43.setText("Student Name :");
        jPanel4.add(jLabel43);
        jLabel43.setBounds(30, 211, 150, 30);

        jButton9.setBackground(new java.awt.Color(102, 255, 255));
        jButton9.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 0, 204));
        jButton9.setText("Check Details");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton9);
        jButton9.setBounds(463, 140, 150, 30);

        jLabel45.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 51));
        jLabel45.setText(" ....  Student Details .....");
        jPanel4.add(jLabel45);
        jLabel45.setBounds(187, 357, 383, 51);

        jButton14.setBackground(new java.awt.Color(102, 255, 255));
        jButton14.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 0, 51));
        jButton14.setText("Clear");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton14);
        jButton14.setBounds(240, 300, 100, 30);

        jTable5.setAutoCreateRowSorter(true);
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BorrowerID", "Borrower Name", "Branch", "Year", "Email", "Phone No."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable5.setDragEnabled(true);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jPanel4.add(jScrollPane5);
        jScrollPane5.setBounds(0, 420, 867, 175);

        jTextField45.setEditable(false);
        jTextField45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField45ActionPerformed(evt);
            }
        });
        jTextField45.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField45FocusGained(evt);
            }
        });
        jPanel4.add(jTextField45);
        jTextField45.setBounds(1040, 70, 154, 30);

        jTextField48.setEditable(false);
        jPanel4.add(jTextField48);
        jTextField48.setBounds(1040, 130, 250, 30);

        jButton21.setBackground(new java.awt.Color(153, 255, 255));
        jButton21.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 0, 255));
        jButton21.setText("Update Information");
        jButton21.setEnabled(false);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton21);
        jButton21.setBounds(1020, 490, 190, 30);

        jLabel68.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(204, 255, 0));
        jLabel68.setText("Borrower ID :");
        jPanel4.add(jLabel68);
        jLabel68.setBounds(900, 70, 110, 17);

        jLabel69.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(204, 255, 0));
        jLabel69.setText("Name :");
        jPanel4.add(jLabel69);
        jLabel69.setBounds(930, 130, 90, 17);

        jLabel70.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(204, 255, 0));
        jLabel70.setText("Branch :");
        jPanel4.add(jLabel70);
        jLabel70.setBounds(930, 250, 80, 17);

        jLabel71.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(204, 255, 0));
        jLabel71.setText("Year :");
        jPanel4.add(jLabel71);
        jLabel71.setBounds(1150, 250, 50, 17);

        jLabel72.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(204, 255, 0));
        jLabel72.setText("Email :");
        jPanel4.add(jLabel72);
        jLabel72.setBounds(930, 300, 60, 17);

        jLabel73.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(204, 255, 0));
        jLabel73.setText("Phone No :");
        jPanel4.add(jLabel73);
        jLabel73.setBounds(920, 350, 90, 17);

        jLabel74.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(204, 255, 0));
        jLabel74.setText("Address :");
        jPanel4.add(jLabel74);
        jLabel74.setBounds(930, 190, 80, 20);

        jTextField47.setEditable(false);
        jTextField47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField47ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField47);
        jTextField47.setBounds(1040, 190, 250, 30);

        jTextField49.setEditable(false);
        jPanel4.add(jTextField49);
        jTextField49.setBounds(1040, 240, 90, 30);

        jTextField50.setEditable(false);
        jPanel4.add(jTextField50);
        jTextField50.setBounds(1220, 240, 74, 30);

        jTextField51.setEditable(false);
        jPanel4.add(jTextField51);
        jTextField51.setBounds(1040, 300, 250, 30);

        jTextField52.setEditable(false);
        jPanel4.add(jTextField52);
        jTextField52.setBounds(1040, 350, 250, 30);

        jLabel75.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(204, 255, 0));
        jLabel75.setText("Mobile No :");
        jPanel4.add(jLabel75);
        jLabel75.setBounds(920, 410, 90, 17);

        jTextField53.setEditable(false);
        jTextField53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField53ActionPerformed(evt);
            }
        });
        jPanel4.add(jTextField53);
        jTextField53.setBounds(1040, 410, 250, 30);
        jPanel4.add(jLabel93);
        jLabel93.setBounds(630, 40, 0, 0);

        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/stddetails.jpg"))); // NOI18N
        jPanel4.add(jLabel96);
        jLabel96.setBounds(0, 0, 1330, 730);

        jTabbedPane2.addTab("Student Details", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Student Info", jPanel1);

        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel3ComponentShown(evt);
            }
        });

        jTabbedPane4.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jTabbedPane4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane4ComponentShown(evt);
            }
        });

        jPanel10.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel10ComponentShown(evt);
            }
        });
        jPanel10.setLayout(null);

        jLabel33.setFont(new java.awt.Font("Georgia", 3, 36)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 0));
        jLabel33.setText("Book Checked Out ");
        jPanel10.add(jLabel33);
        jLabel33.setBounds(80, 290, 360, 50);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Accession No", "Book ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel10.add(jScrollPane2);
        jScrollPane2.setBounds(40, 390, 440, 159);

        jLabel37.setFont(new java.awt.Font("Georgia", 3, 36)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 0));
        jLabel37.setText("Detected Theft");
        jPanel10.add(jLabel37);
        jLabel37.setBounds(590, 290, 330, 41);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Accession No", "Book ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel10.add(jScrollPane3);
        jScrollPane3.setBounds(530, 390, 420, 160);

        jLabel56.setFont(new java.awt.Font("Georgia", 3, 36)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 0));
        jLabel56.setText("Gate Detection System");
        jPanel10.add(jLabel56);
        jLabel56.setBounds(260, 50, 480, 50);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
        });
        jPanel10.add(jTextField2);
        jTextField2.setBounds(290, 160, 210, 30);

        jButton6.setBackground(new java.awt.Color(153, 255, 255));
        jButton6.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 0, 0));
        jButton6.setText("STOP ALARM");
        jPanel10.add(jButton6);
        jButton6.setBounds(590, 160, 150, 30);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/security.jpg"))); // NOI18N
        jPanel10.add(jLabel4);
        jLabel4.setBounds(-6, -30, 1670, 650);

        jTabbedPane4.addTab("Security Gate", jPanel10);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Security", jPanel3);

        jPanel11.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel11ComponentShown(evt);
            }
        });

        jTabbedPane5.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jTabbedPane5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane5ComponentShown(evt);
            }
        });

        jPanel13.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel13ComponentShown(evt);
            }
        });
        jPanel13.setLayout(null);

        jLabel24.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(29, 251, 251));
        jLabel24.setText("*** BOOK  RETURN ***");
        jPanel13.add(jLabel24);
        jLabel24.setBounds(160, 10, 590, 70);

        jLabel25.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 255, 0));
        jLabel25.setText("Book ID :");
        jPanel13.add(jLabel25);
        jLabel25.setBounds(110, 170, 127, 19);

        jTextField22.setEditable(false);
        jPanel13.add(jTextField22);
        jTextField22.setBounds(270, 170, 220, 30);

        jLabel30.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 255, 0));
        jLabel30.setText("Student Name : ");
        jPanel13.add(jLabel30);
        jLabel30.setBounds(100, 310, 143, 19);

        jLabel31.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 255, 0));
        jLabel31.setText("Book Name :");
        jPanel13.add(jLabel31);
        jLabel31.setBounds(530, 300, 130, 30);

        jButton8.setBackground(new java.awt.Color(153, 255, 255));
        jButton8.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 0, 51));
        jButton8.setText("Confirm");
        jButton8.setEnabled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton8);
        jButton8.setBounds(250, 500, 105, 32);

        jTextField27.setEditable(false);
        jPanel13.add(jTextField27);
        jTextField27.setBounds(271, 301, 220, 30);

        jTextField28.setEditable(false);
        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });
        jPanel13.add(jTextField28);
        jTextField28.setBounds(680, 300, 320, 30);

        jButton16.setBackground(new java.awt.Color(153, 255, 255));
        jButton16.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 0, 0));
        jButton16.setText("Clear");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton16);
        jButton16.setBounds(460, 500, 100, 32);

        jTextField58.setEditable(false);
        jPanel13.add(jTextField58);
        jTextField58.setBounds(271, 244, 220, 30);

        jLabel76.setForeground(new java.awt.Color(255, 255, 0));
        jLabel76.setText("_________________________________________________________________________________________________________________________________________________________________________________________________________________");
        jPanel13.add(jLabel76);
        jLabel76.setBounds(10, 210, 980, 14);

        jLabel77.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(51, 255, 0));
        jLabel77.setText("Accession No :");
        jPanel13.add(jLabel77);
        jLabel77.setBounds(110, 110, 130, 30);

        jTextField65.setEditable(false);
        jTextField65.setEnabled(false);
        jTextField65.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField65FocusGained(evt);
            }
        });
        jPanel13.add(jTextField65);
        jTextField65.setBounds(530, 110, 220, 30);

        jTextField60.setEditable(false);
        jTextField60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField60ActionPerformed(evt);
            }
        });
        jPanel13.add(jTextField60);
        jTextField60.setBounds(270, 360, 220, 30);

        jTextField61.setEditable(false);
        jPanel13.add(jTextField61);
        jTextField61.setBounds(680, 350, 190, 30);

        jLabel78.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(102, 255, 0));
        jLabel78.setText("Borrower ID :");
        jPanel13.add(jLabel78);
        jLabel78.setBounds(100, 250, 130, 19);

        jLabel10.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 255, 0));
        jLabel10.setText("Date on Issue:");
        jPanel13.add(jLabel10);
        jLabel10.setBounds(100, 370, 120, 19);

        jLabel79.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(102, 255, 0));
        jLabel79.setText("Date on submited:");
        jPanel13.add(jLabel79);
        jLabel79.setBounds(510, 360, 153, 19);

        jLabel80.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(102, 255, 0));
        jLabel80.setText("Dues (in Rs.) : ");
        jPanel13.add(jLabel80);
        jLabel80.setBounds(520, 420, 130, 20);

        jTextField62.setText("0");
        jTextField62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField62ActionPerformed(evt);
            }
        });
        jPanel13.add(jTextField62);
        jTextField62.setBounds(680, 420, 60, 30);

        jLabel89.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(102, 255, 0));
        jLabel89.setText("Lated Dayes: ");
        jPanel13.add(jLabel89);
        jLabel89.setBounds(100, 420, 120, 19);

        jTextField63.setEditable(false);
        jTextField63.setText("0");
        jPanel13.add(jTextField63);
        jTextField63.setBounds(270, 420, 50, 30);

        jTextField59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField59ActionPerformed(evt);
            }
        });
        jTextField59.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField59FocusLost(evt);
            }
        });
        jPanel13.add(jTextField59);
        jTextField59.setBounds(270, 110, 220, 30);

        jTextField3.setEditable(false);
        jTextField3.setEnabled(false);
        jTextField3.setFocusable(false);
        jPanel13.add(jTextField3);
        jTextField3.setBounds(530, 240, 300, 30);

        jTextField35.setEditable(false);
        jTextField35.setFocusable(false);
        jTextField35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField35ActionPerformed(evt);
            }
        });
        jPanel13.add(jTextField35);
        jTextField35.setBounds(1230, 10, 55, 20);

        jLabel57.setForeground(new java.awt.Color(255, 255, 0));
        jLabel57.setText("Current User :");
        jPanel13.add(jLabel57);
        jLabel57.setBounds(1130, 10, 79, 14);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/bookreturn.jpg"))); // NOI18N
        jPanel13.add(jLabel2);
        jLabel2.setBounds(0, 0, 1340, 750);

        jButton22.setBackground(new java.awt.Color(153, 255, 255));
        jButton22.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 0, 0));
        jButton22.setText("Update");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton22);
        jButton22.setBounds(590, 170, 100, 27);

        jTabbedPane5.addTab("Book Return", jPanel13);

        jPanel12.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel12ComponentShown(evt);
            }
        });
        jPanel12.setLayout(null);

        jLabel20.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 255, 255));
        jLabel20.setText("*** BOOK ISSUE ***");
        jPanel12.add(jLabel20);
        jLabel20.setBounds(187, 14, 510, 70);

        jLabel21.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 0));
        jLabel21.setText("Student ID :");
        jPanel12.add(jLabel21);
        jLabel21.setBounds(80, 110, 110, 20);

        jLabel22.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 0));
        jLabel22.setText("Rfid :");
        jPanel12.add(jLabel22);
        jLabel22.setBounds(440, 110, 60, 19);

        jLabel23.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 0));
        jLabel23.setText("Issue Date :");
        jPanel12.add(jLabel23);
        jLabel23.setBounds(80, 200, 140, 20);

        jTextField19.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField19FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField19FocusLost(evt);
            }
        });
        jPanel12.add(jTextField19);
        jTextField19.setBounds(200, 100, 225, 30);

        jTextField20.setEditable(false);
        jTextField20.setEnabled(false);
        jPanel12.add(jTextField20);
        jTextField20.setBounds(875, 150, 180, 30);

        jTextField21.setEditable(false);
        jPanel12.add(jTextField21);
        jTextField21.setBounds(200, 200, 225, 30);

        jLabel26.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 0));
        jLabel26.setText("Student Name :");
        jPanel12.add(jLabel26);
        jLabel26.setBounds(80, 340, 155, 19);

        jLabel27.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 0));
        jLabel27.setText("Book Name : ");
        jPanel12.add(jLabel27);
        jLabel27.setBounds(80, 430, 116, 30);

        jLabel81.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 0));
        jLabel81.setText("Branch : ");
        jPanel12.add(jLabel81);
        jLabel81.setBounds(80, 380, 80, 20);

        jLabel82.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 0));
        jLabel82.setText("Author :");
        jPanel12.add(jLabel82);
        jLabel82.setBounds(80, 480, 80, 20);

        jLabel83.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 0));
        jLabel83.setText("Pub. Year :");
        jPanel12.add(jLabel83);
        jLabel83.setBounds(450, 480, 100, 19);

        jLabel84.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 0));
        jLabel84.setText("Year :");
        jPanel12.add(jLabel84);
        jLabel84.setBounds(450, 380, 70, 19);

        jLabel14.setForeground(new java.awt.Color(51, 255, 255));
        jPanel12.add(jLabel14);
        jLabel14.setBounds(240, 330, 320, 30);

        jLabel50.setForeground(new java.awt.Color(51, 255, 255));
        jPanel12.add(jLabel50);
        jLabel50.setBounds(240, 370, 289, 30);

        jLabel85.setForeground(new java.awt.Color(0, 255, 255));
        jPanel12.add(jLabel85);
        jLabel85.setBounds(240, 430, 220, 30);

        jLabel86.setForeground(new java.awt.Color(0, 255, 255));
        jPanel12.add(jLabel86);
        jLabel86.setBounds(240, 470, 193, 30);

        jLabel87.setForeground(new java.awt.Color(0, 255, 255));
        jPanel12.add(jLabel87);
        jLabel87.setBounds(550, 380, 143, 30);

        jLabel88.setForeground(new java.awt.Color(0, 255, 255));
        jPanel12.add(jLabel88);
        jLabel88.setBounds(570, 480, 143, 20);

        jButton10.setBackground(new java.awt.Color(153, 255, 255));
        jButton10.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 0, 0));
        jButton10.setText("Issue");
        jButton10.setEnabled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton10);
        jButton10.setBounds(430, 280, 110, 30);

        jTextField14.setEditable(false);
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel12.add(jTextField14);
        jTextField14.setBounds(1250, 20, 55, 20);
        jTextField14.getAccessibleContext().setAccessibleDescription("");

        jLabel28.setForeground(new java.awt.Color(255, 255, 0));
        jLabel28.setText("Current User :");
        jPanel12.add(jLabel28);
        jLabel28.setBounds(1150, 20, 79, 14);

        jButton15.setBackground(new java.awt.Color(153, 255, 255));
        jButton15.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 51, 0));
        jButton15.setText("Clear");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton15);
        jButton15.setBounds(290, 530, 90, 30);

        jLabel67.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 0));
        jLabel67.setText("No. of  books already have :");
        jPanel12.add(jLabel67);
        jLabel67.setBounds(740, 110, 240, 19);

        jTextField46.setEditable(false);
        jPanel12.add(jTextField46);
        jTextField46.setBounds(980, 100, 77, 30);

        jTextField56.setEnabled(false);
        jPanel12.add(jTextField56);
        jTextField56.setBounds(520, 200, 200, 30);

        jTextField57.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField57FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField57FocusLost(evt);
            }
        });
        jPanel12.add(jTextField57);
        jTextField57.setBounds(200, 150, 220, 30);

        jTextField64.setEditable(false);
        jTextField64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField64ActionPerformed(evt);
            }
        });
        jTextField64.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField64FocusGained(evt);
            }
        });
        jPanel12.add(jTextField64);
        jTextField64.setBounds(520, 150, 200, 30);

        jTextField9.setEditable(false);
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
        });
        jPanel12.add(jTextField9);
        jTextField9.setBounds(520, 100, 200, 30);

        jLabel59.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 0));
        jLabel59.setText("Rfid :");
        jPanel12.add(jLabel59);
        jLabel59.setBounds(440, 150, 60, 19);

        jLabel58.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(204, 0, 255));
        jLabel58.setText("Rfid :");
        jPanel12.add(jLabel58);
        jLabel58.setBounds(440, 150, 60, 19);

        jLabel53.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 0));
        jLabel53.setText("Acc No : ");
        jPanel12.add(jLabel53);
        jLabel53.setBounds(100, 150, 80, 20);

        jLabel54.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 0));
        jLabel54.setText("Book ID :");
        jPanel12.add(jLabel54);
        jLabel54.setBounds(740, 160, 120, 19);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/bookisue.jpg"))); // NOI18N
        jPanel12.add(jLabel3);
        jLabel3.setBounds(0, -10, 1330, 680);

        jButton5.setBackground(new java.awt.Color(153, 255, 255));
        jButton5.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 0, 0));
        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton5);
        jButton5.setBounds(250, 280, 110, 30);

        jTabbedPane5.addTab("Book Issue", jPanel12);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Transaction", jPanel11);

        jPanel8.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel8ComponentShown(evt);
            }
        });

        jTabbedPane6.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jTabbedPane6.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane6ComponentShown(evt);
            }
        });

        jPanel14.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel14ComponentShown(evt);
            }
        });
        jPanel14.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 255, 0));
        jLabel9.setText("*** RFID Book Tagging System ***");
        jPanel14.add(jLabel9);
        jLabel9.setBounds(96, 37, 860, 60);

        jLabel32.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 0));
        jLabel32.setText("Accession No : ");
        jPanel14.add(jLabel32);
        jLabel32.setBounds(60, 160, 130, 19);

        jTextField30.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField30FocusLost(evt);
            }
        });
        jPanel14.add(jTextField30);
        jTextField30.setBounds(210, 160, 170, 30);

        jLabel34.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 0));
        jLabel34.setText("RFID tag No : ");
        jPanel14.add(jLabel34);
        jLabel34.setBounds(460, 160, 130, 20);

        jTextField31.setEditable(false);
        jPanel14.add(jTextField31);
        jTextField31.setBounds(610, 160, 152, 30);

        jButton18.setBackground(new java.awt.Color(153, 255, 255));
        jButton18.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 102, 0));
        jButton18.setText("Save RFID");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton18);
        jButton18.setBounds(490, 250, 130, 30);

        jButton19.setBackground(new java.awt.Color(153, 255, 255));
        jButton19.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 51, 0));
        jButton19.setText("Delete RFID");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton19);
        jButton19.setBounds(680, 250, 140, 30);

        jButton23.setBackground(new java.awt.Color(153, 255, 255));
        jButton23.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 102, 0));
        jButton23.setText("Update");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton23);
        jButton23.setBounds(800, 160, 110, 27);

        jLabel35.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 0));
        jLabel35.setText("Book Name : ");
        jPanel14.add(jLabel35);
        jLabel35.setBounds(74, 343, 116, 19);

        jLabel36.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 0));
        jLabel36.setText("Author Name :");
        jPanel14.add(jLabel36);
        jLabel36.setBounds(74, 404, 140, 19);

        jLabel60.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 0));
        jLabel60.setText("Publication Year : ");
        jPanel14.add(jLabel60);
        jLabel60.setBounds(74, 459, 160, 19);
        jPanel14.add(jTextField32);
        jTextField32.setBounds(249, 340, 276, 30);
        jPanel14.add(jTextField36);
        jTextField36.setBounds(249, 401, 276, 30);
        jPanel14.add(jTextField37);
        jTextField37.setBounds(249, 456, 276, 30);

        jButton24.setBackground(new java.awt.Color(153, 255, 255));
        jButton24.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 102, 0));
        jButton24.setText("Clear");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton24);
        jButton24.setBounds(320, 540, 110, 30);

        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/admin.jpg"))); // NOI18N
        jPanel14.add(jLabel104);
        jLabel104.setBounds(-4, -1, 1360, 660);

        jTabbedPane6.addTab("Book taging ", jPanel14);

        jPanel15.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel15ComponentShown(evt);
            }
        });
        jPanel15.setLayout(null);

        jLabel92.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(153, 255, 0));
        jLabel92.setText("*** RFID Student Tagging System ***");
        jPanel15.add(jLabel92);
        jLabel92.setBounds(80, 40, 940, 60);

        jLabel94.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 0));
        jLabel94.setText("Student Borrower ID : ");
        jPanel15.add(jLabel94);
        jLabel94.setBounds(50, 180, 190, 30);

        jTextField38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField38ActionPerformed(evt);
            }
        });
        jTextField38.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField38FocusLost(evt);
            }
        });
        jPanel15.add(jTextField38);
        jTextField38.setBounds(250, 180, 180, 30);

        jLabel97.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 0));
        jLabel97.setText("RFID tag No :");
        jPanel15.add(jLabel97);
        jLabel97.setBounds(480, 180, 130, 19);

        jTextField39.setEditable(false);
        jTextField39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField39ActionPerformed(evt);
            }
        });
        jPanel15.add(jTextField39);
        jTextField39.setBounds(630, 180, 160, 30);

        jButton25.setBackground(new java.awt.Color(153, 255, 255));
        jButton25.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton25.setForeground(new java.awt.Color(255, 102, 0));
        jButton25.setText("Update");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton25);
        jButton25.setBounds(830, 180, 90, 30);

        jButton26.setBackground(new java.awt.Color(153, 255, 255));
        jButton26.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 102, 0));
        jButton26.setText("Save RFID");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton26);
        jButton26.setBounds(550, 270, 120, 30);

        jButton27.setBackground(new java.awt.Color(153, 255, 255));
        jButton27.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 102, 0));
        jButton27.setText("Delete RFID");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton27);
        jButton27.setBounds(730, 270, 120, 30);

        jLabel98.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 0));
        jLabel98.setText("Borrower Name : ");
        jPanel15.add(jLabel98);
        jLabel98.setBounds(60, 330, 160, 19);
        jPanel15.add(jTextField40);
        jTextField40.setBounds(250, 330, 238, 30);

        jLabel99.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 0));
        jLabel99.setText("Class : ");
        jPanel15.add(jLabel99);
        jLabel99.setBounds(150, 390, 90, 30);
        jPanel15.add(jTextField41);
        jTextField41.setBounds(250, 390, 238, 30);

        jLabel100.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 0));
        jLabel100.setText("Year : ");
        jPanel15.add(jLabel100);
        jLabel100.setBounds(150, 460, 70, 19);
        jPanel15.add(jTextField67);
        jTextField67.setBounds(250, 450, 238, 30);

        jButton28.setBackground(new java.awt.Color(153, 255, 255));
        jButton28.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 102, 0));
        jButton28.setText("Clear");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton28);
        jButton28.setBounds(390, 530, 100, 30);

        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/admin.jpg"))); // NOI18N
        jPanel15.add(jLabel105);
        jLabel105.setBounds(0, -10, 1380, 860);

        jTabbedPane6.addTab("Student Tagging", jPanel15);

        jPanel16.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel16ComponentShown(evt);
            }
        });
        jPanel16.setLayout(null);

        jLabel101.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(153, 255, 0));
        jLabel101.setText("*** Book Circulation Rules ***");
        jPanel16.add(jLabel101);
        jLabel101.setBounds(70, 30, 780, 50);

        jComboBox3.setBackground(new java.awt.Color(153, 255, 255));
        jComboBox3.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(255, 0, 255));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select--", "FE", "SE", "TE", "BE", "IN", "POL", "NTS", "HOD", "PG" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel16.add(jComboBox3);
        jComboBox3.setBounds(120, 170, 130, 30);

        jLabel102.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 0));
        jLabel102.setText("Loan Days : ");
        jPanel16.add(jLabel102);
        jLabel102.setBounds(340, 170, 140, 30);

        jLabel103.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 0));
        jLabel103.setText("Fine Rate : ");
        jPanel16.add(jLabel103);
        jLabel103.setBounds(340, 240, 120, 30);

        jTextField68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField68ActionPerformed(evt);
            }
        });
        jPanel16.add(jTextField68);
        jTextField68.setBounds(480, 170, 160, 30);

        jTextField69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField69ActionPerformed(evt);
            }
        });
        jPanel16.add(jTextField69);
        jTextField69.setBounds(480, 240, 160, 30);

        jButton29.setBackground(new java.awt.Color(153, 255, 255));
        jButton29.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 102, 0));
        jButton29.setText("Update");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton29);
        jButton29.setBounds(410, 330, 120, 30);

        jLabel106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/admin.jpg"))); // NOI18N
        jPanel16.add(jLabel106);
        jLabel106.setBounds(0, -10, 1350, 860);

        jTabbedPane6.addTab("Loan Days", jPanel16);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 44, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Administrative", jPanel8);

        jPanel17.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel17ComponentShown(evt);
            }
        });
        jPanel17.setLayout(null);

        jLabel107.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(153, 255, 0));
        jLabel107.setText("*** Self  CHECK OUT System ***");
        jPanel17.add(jLabel107);
        jLabel107.setBounds(84, 34, 790, 50);

        jLabel108.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 0));
        jLabel108.setText("Accession No : ");
        jPanel17.add(jLabel108);
        jLabel108.setBounds(80, 130, 140, 19);

        jLabel109.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 0));
        jLabel109.setText("Book  ID : ");
        jPanel17.add(jLabel109);
        jLabel109.setBounds(80, 190, 100, 20);

        jTextField70.setEditable(false);
        jTextField70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField70ActionPerformed(evt);
            }
        });
        jTextField70.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField70FocusLost(evt);
            }
        });
        jPanel17.add(jTextField70);
        jTextField70.setBounds(230, 120, 190, 30);

        jTextField71.setEditable(false);
        jTextField71.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField71FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField71FocusLost(evt);
            }
        });
        jPanel17.add(jTextField71);
        jTextField71.setBounds(480, 120, 190, 30);
        jPanel17.add(jTextField72);
        jTextField72.setBounds(230, 190, 190, 30);

        jLabel111.setForeground(new java.awt.Color(0, 255, 255));
        jLabel111.setText("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jPanel17.add(jLabel111);
        jLabel111.setBounds(0, 260, 1368, 14);

        jLabel112.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(255, 255, 0));
        jLabel112.setText("Borrower ID :");
        jPanel17.add(jLabel112);
        jLabel112.setBounds(70, 300, 140, 14);

        jLabel113.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 255, 0));
        jLabel113.setText("Student Name : ");
        jPanel17.add(jLabel113);
        jLabel113.setBounds(70, 360, 140, 19);

        jLabel114.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 255, 0));
        jLabel114.setText("Date On Issued : ");
        jPanel17.add(jLabel114);
        jLabel114.setBounds(70, 420, 140, 19);

        jLabel115.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 255, 0));
        jLabel115.setText("Lated Days : ");
        jPanel17.add(jLabel115);
        jLabel115.setBounds(80, 470, 109, 19);
        jPanel17.add(jTextField73);
        jTextField73.setBounds(230, 290, 230, 30);
        jPanel17.add(jTextField74);
        jTextField74.setBounds(230, 350, 190, 30);
        jPanel17.add(jTextField75);
        jTextField75.setBounds(230, 410, 190, 30);
        jPanel17.add(jTextField76);
        jTextField76.setBounds(230, 470, 190, 30);
        jPanel17.add(jTextField77);
        jTextField77.setBounds(530, 290, 320, 30);

        jLabel116.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 255, 0));
        jLabel116.setText("Book Name : ");
        jPanel17.add(jLabel116);
        jLabel116.setBounds(500, 360, 109, 19);

        jLabel117.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 255, 0));
        jLabel117.setText("Date On Submited : ");
        jPanel17.add(jLabel117);
        jLabel117.setBounds(450, 420, 167, 19);

        jLabel118.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(255, 255, 0));
        jLabel118.setText("Dues (in Rs. )  : ");
        jPanel17.add(jLabel118);
        jLabel118.setBounds(480, 480, 126, 19);
        jPanel17.add(jTextField78);
        jTextField78.setBounds(630, 350, 220, 30);

        jTextField79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField79ActionPerformed(evt);
            }
        });
        jPanel17.add(jTextField79);
        jTextField79.setBounds(630, 410, 220, 30);
        jPanel17.add(jTextField80);
        jTextField80.setBounds(630, 470, 220, 30);

        jButton32.setBackground(new java.awt.Color(153, 255, 255));
        jButton32.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 102, 0));
        jButton32.setText("Clear");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton32);
        jButton32.setBounds(510, 570, 80, 30);

        jLabel110.setFont(new java.awt.Font("Tempus Sans ITC", 3, 36)); // NOI18N
        jPanel17.add(jLabel110);
        jLabel110.setBounds(470, 190, 860, 50);

        jLabel119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/admin.jpg"))); // NOI18N
        jPanel17.add(jLabel119);
        jLabel119.setBounds(0, 0, 1374, 720);

        jButton31.setBackground(new java.awt.Color(153, 255, 255));
        jButton31.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 102, 0));
        jButton31.setText("Confirm ");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton31);
        jButton31.setBounds(340, 570, 100, 30);

        jButton30.setBackground(new java.awt.Color(153, 255, 255));
        jButton30.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton30.setForeground(new java.awt.Color(255, 51, 0));
        jButton30.setText("Update");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton30);
        jButton30.setBounds(490, 190, 90, 30);

        jTabbedPane1.addTab("Self Check Out", jPanel17);

        jPanel18.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel18ComponentShown(evt);
            }
        });

        jTabbedPane7.setFont(new java.awt.Font("Georgia", 3, 12)); // NOI18N
        jTabbedPane7.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane7ComponentShown(evt);
            }
        });

        jPanel19.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel19ComponentShown(evt);
            }
        });
        jPanel19.setLayout(null);

        jLabel121.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(255, 255, 0));
        jLabel121.setText("Accession No: ");
        jPanel19.add(jLabel121);
        jLabel121.setBounds(150, 190, 130, 20);
        jPanel19.add(jTextField82);
        jTextField82.setBounds(320, 180, 220, 30);

        jLabel122.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(255, 255, 0));
        jLabel122.setText("Book ID : ");
        jPanel19.add(jLabel122);
        jLabel122.setBounds(150, 260, 130, 19);

        jTextField84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField84ActionPerformed(evt);
            }
        });
        jPanel19.add(jTextField84);
        jTextField84.setBounds(320, 250, 220, 30);

        jTextField83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField83ActionPerformed(evt);
            }
        });
        jTextField83.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField83FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField83FocusLost(evt);
            }
        });
        jPanel19.add(jTextField83);
        jTextField83.setBounds(650, 250, 200, 30);
        jPanel19.add(jTextField85);
        jTextField85.setBounds(320, 320, 530, 30);

        jLabel123.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(255, 255, 0));
        jLabel123.setText("Book Name : ");
        jPanel19.add(jLabel123);
        jLabel123.setBounds(150, 330, 109, 19);

        jLabel124.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(255, 255, 0));
        jLabel124.setText("Author : ");
        jPanel19.add(jLabel124);
        jLabel124.setBounds(150, 400, 80, 19);
        jPanel19.add(jTextField86);
        jTextField86.setBounds(320, 390, 530, 30);

        jLabel125.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(255, 255, 0));
        jLabel125.setText("Publication Year  :");
        jPanel19.add(jLabel125);
        jLabel125.setBounds(140, 460, 170, 19);

        jTextField87.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField87ActionPerformed(evt);
            }
        });
        jPanel19.add(jTextField87);
        jTextField87.setBounds(320, 450, 230, 30);

        jButton33.setBackground(new java.awt.Color(153, 255, 255));
        jButton33.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jButton33.setForeground(new java.awt.Color(255, 51, 0));
        jButton33.setText("Clear");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton33);
        jButton33.setBounds(280, 540, 80, 30);

        jLabel126.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(255, 255, 0));
        jLabel126.setText("RFID : ");
        jPanel19.add(jLabel126);
        jLabel126.setBounds(570, 260, 86, 19);

        jLabel127.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(102, 255, 0));
        jLabel127.setText("*** Check Tagged Book ***");
        jPanel19.add(jLabel127);
        jLabel127.setBounds(160, 50, 680, 60);

        jLabel136.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/admin.jpg"))); // NOI18N
        jPanel19.add(jLabel136);
        jLabel136.setBounds(-10, -10, 1360, 670);

        jTabbedPane7.addTab("Book Check", jPanel19);

        jPanel20.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel20ComponentShown(evt);
            }
        });
        jPanel20.setLayout(null);

        jLabel128.setFont(new java.awt.Font("Georgia", 3, 48)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(153, 255, 0));
        jLabel128.setText("*** Check Tagged Student***");
        jPanel20.add(jLabel128);
        jLabel128.setBounds(110, 40, 710, 60);

        jLabel129.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(255, 255, 0));
        jLabel129.setText("Borrower ID : ");
        jPanel20.add(jLabel129);
        jLabel129.setBounds(100, 180, 130, 19);
        jPanel20.add(jTextField88);
        jTextField88.setBounds(240, 170, 210, 30);

        jLabel130.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(255, 255, 0));
        jLabel130.setText("RFID : ");
        jPanel20.add(jLabel130);
        jLabel130.setBounds(480, 180, 57, 19);

        jTextField89.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField89FocusGained(evt);
            }
        });
        jPanel20.add(jTextField89);
        jTextField89.setBounds(560, 170, 230, 30);

        jLabel131.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(255, 255, 0));
        jLabel131.setText("Name  :");
        jPanel20.add(jLabel131);
        jLabel131.setBounds(110, 250, 83, 19);

        jLabel132.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(255, 255, 0));
        jLabel132.setText("Branch : ");
        jPanel20.add(jLabel132);
        jLabel132.setBounds(100, 320, 90, 19);

        jLabel133.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(255, 255, 0));
        jLabel133.setText("Year : ");
        jPanel20.add(jLabel133);
        jLabel133.setBounds(480, 320, 54, 19);
        jPanel20.add(jTextField90);
        jTextField90.setBounds(240, 240, 550, 30);
        jPanel20.add(jTextField91);
        jTextField91.setBounds(240, 310, 210, 30);
        jPanel20.add(jTextField92);
        jTextField92.setBounds(570, 310, 220, 30);

        jLabel134.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(255, 255, 0));
        jLabel134.setText("Contact No : ");
        jPanel20.add(jLabel134);
        jLabel134.setBounds(90, 390, 110, 19);
        jPanel20.add(jTextField93);
        jTextField93.setBounds(240, 380, 210, 30);

        jLabel135.setFont(new java.awt.Font("Georgia", 3, 16)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(255, 255, 0));
        jLabel135.setText("Email  :");
        jPanel20.add(jLabel135);
        jLabel135.setBounds(480, 390, 65, 19);
        jPanel20.add(jTextField94);
        jTextField94.setBounds(570, 380, 220, 30);

        jButton34.setBackground(new java.awt.Color(153, 255, 255));
        jButton34.setFont(new java.awt.Font("Georgia", 3, 14)); // NOI18N
        jButton34.setForeground(new java.awt.Color(255, 51, 0));
        jButton34.setText("Clear");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton34);
        jButton34.setBounds(430, 480, 90, 30);

        jLabel137.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/admin.jpg"))); // NOI18N
        jPanel20.add(jLabel137);
        jLabel137.setBounds(0, -10, 1360, 850);

        jTabbedPane7.addTab("Student Check", jPanel20);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7)
        );

        jTabbedPane1.addTab("RFID Check", jPanel18);

        jScrollPane6.setViewportView(jTabbedPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1353, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel12ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel12ComponentShown
          // TODO add your handling code here:
        panelstatus=2;
        jTextField19.requestFocus();
    }//GEN-LAST:event_jPanel12ComponentShown

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField46.setText("");
        jTextField56.setText("");
        jTextField57.setText("");
        //jTextField21.setText("");
        jLabel14.setText("");
        jLabel50.setText("");
        jLabel85.setText("");
        jLabel86.setText("");
        jLabel87.setText("");
        jLabel88.setText("");
        jTextField9.setText("");
        jTextField64.setText("");
        jTextField19.setEditable(true);
        jTextField19.requestFocus();
        jButton10.setEnabled(false);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        String duedt=null;
        DateFormat df=new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date date=new Date();
        Calendar cal=Calendar.getInstance();
        System.out.print(df.format(cal.getTime()));
        String dat=df.format(cal.getTime());

        System.out.print(df.format(cal.getTime()));
        try

        {/*
            Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");*/
            Statement s=con.createStatement();
            Statement s1=con.createStatement();
            //System.out.println("Connection to the database is done.");
            String bukid=jTextField20.getText();
            String studid=jTextField19.getText();

            String curuser=jTextField14.getText();
            /*if(studid.startsWith(studid, 'f'))
            {
                cal.add(Calendar.DATE , 15);
                duedt= df.format(cal.getTime());

            }
            else
            {            cal.add(Calendar.DATE , 8);
                duedt= df.format(cal.getTime());
            }*/

            ResultSet rs=s1.executeQuery("select loandays from circrules where categoryid='"+jLabel87.getText().trim()+"' and circcode='01' ");
            //System.out.println("yety ki ith 1"+jLabel87.getText());

            if(rs.next())
            {
                int loan=rs.getInt("loandays");
                System.out.println("\nloandayes...."+loan);
                cal.add(Calendar.DATE,loan);
                duedt=df.format(cal.getTime());
                System.out.println("yety ki ith 2"+duedt);
                s1.close();
            }

            if((bukid!=null) && (studid!=null))
            {
                // s.executeUpdate("INSERT INTO CircTransactions (BorrowerID,TranDateTime,TranType,AccNum,CatRefNum,EntryDateTime,DueDate,RtnDate,TransactionOperator,DomainName,MachineName,BarCodeNum) values('"+studid+"','"+dat+"','IS',0000,'"+bukid+"','"+dat+"','"+trnsid+"','"+duedt+"',null,'RFID','RFID','RFID','"+bukid+"')");
                s.executeUpdate("INSERT INTO CircTransactions (BorrowerID,TranDateTime,TranType,AccNum,CatRefNum,EntryDateTime,DueDate,RtnDate,TransactionOperator,DomainName,MachineName,BarCodeNum) values('"+studid+"','"+dat+"','IS','"+jTextField57.getText()+"','"+bukid+"','"+dat+"','"+duedt+"',null,'"+curuser
                    +"','RFID','RFID','"+bukid+"')");
                s.executeUpdate("UPDATE circulation SET CirculationStatusCD='C', borrowerID='"+studid+"',dateborrowedon='"+dat+"', ReturndueDate='"+duedt+"' where accnum='"+jTextField57.getText()+"'");
                JOptionPane.showMessageDialog(null,"Book Is Issued... \n\n Date of submission="+duedt);
                jTextField19.setText("");
                jTextField20.setText("");
jTextField9.setText("");
jTextField57.setText("");
jTextField64.setText("");
//jTextField21.setText("");
                jLabel14.setText("");
                //.setText("");
                jLabel50.setText("");
                jLabel85.setText("");
                jLabel86.setText("");
                jLabel87.setText("");
                jLabel88.setText("");
                s.close();
            }
            else {
                JOptionPane.showMessageDialog(null,"Enter BookID and BorrowerID");
                jButton10.setEnabled(false);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }

      /*  //***************************************************************************************************************
        String to ="morepravin056@gmail.com";// "kantilal85@gmail.com";//change accordingly
        //String to=jTextField56.getText().trim();     //actual mail of user
        // Sender's email ID needs to be mentioned
        String from = "rfid@gmail.com";//change accordingly
        final String username = "pratik.lokhande79";//change accordingly
        final String password = "pratik14391";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Create a default MimeMessage object.
                Message message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

                // Set Subject: header field

                message.setSubject("SGI Library-Book Issued");

                // Now set the actual message
                message.setText("Hello"+jLabel14.getText()+","
                    + "     Thank you for using SGI Library facility."
                    + ""
                    + "you had Issed "+jLabel85.getText()+"on the date "+dat+" and you have to submit this book on or before "+duedt+" otherwise you will be able to pay penalty amount"
                    + "thank you,"
                    + "SGI Library " );
                // Send message
                Transport.send(message);

               System.out.println("Sent message successfully....");

            } catch (MessagingException e) {
                throw new RuntimeException(e);            }*/
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int i=0;
        String studid=null;
        String accno="";
        //acc.number
        if(jTextField64.getText().trim().isEmpty())
        {
            jTextField64.setEnabled(false);
            try
            {
                accno= jTextField57.getText();
                System.out.println("..."+accno+"...");
                Statement s1=con.createStatement();

                if(accno.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Enter AccetionNumber !!!" );
                }
                else
                {
                    ResultSet rs=s1.executeQuery("Select catrefnum,rfidcd from circulation where accnum='"+accno+"'");//+"Select top 2 CardTitle,cardauthor,publicationyear from Card where CatRefNum='"+bukid+"'" );

                if(rs.next())
                {
                    //while(rs.next())
                    //  jTextField46.setText(Integer.toString(rs1.getInt("iss")-rs2.getInt("ret")));

                    jTextField20.setText(rs.getString("catrefnum"));
                    jTextField64.setText(rs.getString("rfidcd"));
                    rs.close();

                    s1.close();

                    i++;
                }

                else
                {
                    JOptionPane.showMessageDialog(null,"Accetion Number Is Wrong.!!!" );
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        }
        else{
            try
            {
                String rfidcode= jTextField64.getText();
                //System.out.println("..."+accno+"...");
                Statement s1=con.createStatement();

                ResultSet rs=s1.executeQuery("Select accnum,catrefnum from circulation where rfidcd='"+rfidcode+"'");//+"Select top 2 CardTitle,cardauthor,publicationyear from Card where CatRefNum='"+bukid+"'" );

            if(rs.next())
            {
                //while(rs.next())
                //  jTextField46.setText(Integer.toString(rs1.getInt("iss")-rs2.getInt("ret")));
                jTextField57.setText(rs.getString("accnum"));
                jTextField20.setText(rs.getString("catrefnum"));

                rs.close();
                s1.close();
                i++;
            }

        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        }
        // borrower id
        try
        {
            studid= jTextField19.getText();
            System.out.println(studid);
            Statement s1=con.createStatement();

            if(studid.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Enter Borrower ID !!!" );
            }
            else
            {

                ResultSet rs=s1.executeQuery("Select salutation,departmentname,emailname,CategoryID from Borrowers where BorrowerId='"+studid+"'");//+"Select top 2 CardTitle,cardauthor,publicationyear from Card where CatRefNum='"+bukid+"'" );

            if(rs.next())
            {
                //while(rs.next())
                //  jTextField46.setText(Integer.toString(rs1.getInt("iss")-rs2.getInt("ret")));

                jLabel14.setText(" "+rs.getString("Salutation")+" ");
                jLabel50.setText(" "+rs.getString("departmentname")+" ");
                jTextField56.setText(rs.getString("emailname"));
                jLabel87.setText(" "+rs.getString("CategoryID")+" ");

                rs.close();

                s1.close();

                i++;
            }

            else
            {
                JOptionPane.showMessageDialog(null,"BorrowerID Is Wrong.!!!" );
            }
        }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        //buk info
        try
        {/*
            Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");*/
            Statement s=con.createStatement();
            String bukid=null;
            bukid=jTextField20.getText();
            System.out.println(bukid);
            /*if(bukid.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Enter Book ID!!!" );
            }
            else
            {
                */
                ResultSet rs1=s.executeQuery("select CardTitle,CardAuthor,PublicationYear from Card where CatRefNum='"+bukid+"'");
                if(rs1.next())
                {

                    jLabel85.setText(rs1.getString("CardTitle"));
                    jLabel86.setText(" "+rs1.getString("CardAuthor")+" ");
                    jLabel88.setText(" "+rs1.getString("PublicationYear")+" ");

                    rs1.close();
                    s.close();
                    // i++;
                }
                else{
                    JOptionPane.showMessageDialog(null,"Book ID is Invalid.!!!");
                }
                //}
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        //*******************************
        //chk no. of books alredy issued

        int issue=0,retu=0;
        try{
            Statement s2=con.createStatement();
            ResultSet rs1=s2.executeQuery("select count(*) as iss from CircTransactions where BorrowerID='"+studid+"' and TranType='IS'");
            if(rs1.next())
            {
                issue=rs1.getInt("iss");
                System.out.println(issue);
            }
            rs1.close();
            s2.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }

        try{
            Statement s3=con.createStatement();
            ResultSet rs2=s3.executeQuery("select count(*) as ret from CircTransactions where BorrowerID='"+studid+"' and TranType='RC'");
            if(rs2.next())
            {
                //while(rs.next())
                //jTextField46.setText(Integer.toString( issue -rs2.getInt("ret")));
                retu=rs2.getInt("ret");
            }

            rs2.close();
            s3.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        System.out.println(i);
        jTextField46.setText(Integer.toString(issue-retu));
        //********************************
        if(i==2)
        {
            jButton10.setEnabled(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jPanel13ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel13ComponentShown
        // TODO add your handling code here:
        panelstatus=1;
        jTextField59.requestFocus();
    }//GEN-LAST:event_jPanel13ComponentShown

    private void jTextField35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField35ActionPerformed

    private void jTextField59FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField59FocusLost
        int avail=0;
        String btid="",studid="",bookid="",catid="";
        if(jTextField59.getText().isEmpty())
        {
            //JOptionPane.showMessageDialog(null,"please enter Valid Acc. Number");
            jTextField59.requestFocus();
        }
        else{
            //jTextField59.setEnabled(false);
            try{
                Statement s=con.createStatement();
                ResultSet rs=s.executeQuery("select accnum,rfidcd from Circulation where accnum='"+jTextField59.getText().trim()+"' and CirculationStatusCD='C'");
                if(rs.next())
                {
                    jTextField65.setText(rs.getString("rfidcd"));
                    avail=1;
                    System.out.println("avil...");
                jButton8.setEnabled(true);
                }
                rs.close();
                s.close();

            }
            catch(SQLException e)
            {System.out.println("catch 1...");
                System.out.println(e);
            }
            //}

        if(avail==1)
        {
            try
            {System.out.println("try2...");
                int flg=0;

                Statement s=con.createStatement();
                btid=jTextField59.getText();
                //String booknm=null;
                // String studnm=null;
                ResultSet rs3;
                System.out.println(".."+btid+"..");
                rs3 =s.executeQuery("select borrowerid,catrefnum,entrydatetime,duedate from circtransactions where accnum='"+btid+"' and TranType='IS' order  by EntryDateTime desc ");

                if(rs3.next())
                {
                    System.out.println("rs whil...");
                    studid=rs3.getString("borrowerid");
                    bookid=rs3.getString("catrefnum");
                    jTextField22.setText(bookid);
                    jTextField58.setText(studid);
                    jTextField60.setText(rs3.getString("entrydatetime"));
                    //  duedate=rs.getDate("duedate");
                    jTextField61.setText(rs3.getString("duedate"));
                    //jTextField61.setText(String.valueOf(duedate));
                }
                rs3.close();
                ResultSet rs1 =s.executeQuery("select salutation,emailname,categoryid from Borrowers where Borrowerid='"+studid+"' ");

                if(rs1.next())
                {System.out.println("rs1 while...");
                    flg=1;
                    jTextField27.setText(" "+rs1.getString("salutation")+" ");
                    jTextField3.setText(rs1.getString("emailname"));
                    catid=rs1.getString("categoryid");
                }
                rs1.close();
                ResultSet rs2 =s.executeQuery("select cardtitle from card where catrefnum='"+bookid+"' ");
                if(rs2.next())
                {System.out.println("rs2 while...");
                    //flg=1;
                    jTextField28.setText(" "+rs2.getString("cardtitle")+" ");
                }

                rs2.close();

                if(flg==0)
                {
                    JOptionPane.showMessageDialog(null,"Record not found");
                    jTextField59.requestFocus();
                }

                s.close();

                //Calendar cal=Calendar.getInstance();

            }
            catch(SQLException e)
            {System.out.println("catch .2...");
                System.out.println(e);
            }


        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");

        Date curdt1=new Date();
        Date duedt1=new Date();
        try{
            System.out.println("try 3 date wala...");
            String duedt=jTextField61.getText();
            String curdt=jTextField21.getText();

            duedt1=df.parse(duedt);
            // duedt1=df.parse(duedt);
            curdt1=df.parse(curdt);

            // curdt1=df.parse(curdt);

            long dyz=curdt1.getTime()-duedt1.getTime();

            System.out.println(dyz);
            int dat=(int)((dyz)/(1000*24*60*60));

            System.out.println(dat);
            jTextField63.setText(Integer.toString(dat));
            if(jTextField63.getText().startsWith("-"))
            {
                jTextField63.setText("0");
            }
            else{
            
                jTextField63.setText(Integer.toString(dat));
                try{
                    Statement s=con.createStatement();
                    ResultSet rs=s.executeQuery("select loandays,finerate from circrules where categoryid='"+catid+"'");
                    if(rs.next())
                    {
                        jTextField62.setText(Integer.toString(rs.getInt("finerate")*dat));
                    }
                    rs.close();
                    s.close();
                }
                catch(Exception e)
                {
                    System.out.print(e);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
                }
        else
        {
            JOptionPane.showMessageDialog(null,"Record not found");
            jTextField59.setEditable(true);
            jTextField59.requestFocus();
        }
          jButton8.requestFocus();
        }
    }//GEN-LAST:event_jTextField59FocusLost

    private void jTextField62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField62ActionPerformed

    private void jTextField60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField60ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        int avail=0;//DateAndTime duedate;
        if(jTextField65.getText().isEmpty())
        {
            jTextField65.setEnabled(false);
            try{
                Statement s=con.createStatement();
                ResultSet rs=s.executeQuery("select * from Circulation where accnum='"+jTextField59.getText().trim()+"' and CirculationStatusCD='C'");
                if(rs.next())
                {
                    jTextField65.setText(rs.getString("rfidcd"));
                    avail=1;
                    System.out.println("avil...");
                }
                rs.close();
                s.close();
            }
            catch(SQLException e)
            {System.out.println("catch 1...");
                System.out.println(e);
            }
        }
        else{
            jTextField59.setEnabled(false);
            try{
                Statement s=con.createStatement();
                ResultSet rs=s.executeQuery("select * from Circulation where rfidcd='"+jTextField65.getText().trim()+"' and CirculationStatusCD='C'");
                if(rs.next())
                {
                    jTextField59.setText(rs.getString("accnum"));
                    avail=1;
                    System.out.println("avil...");
                }
                rs.close();
                s.close();
            }
            catch(SQLException e)
            {System.out.println("catch 1...");
                System.out.println(e);
            }
        }

        if(avail==1)
        {
            try
            {System.out.println("try2...");
                int flg=0;

                Statement s=con.createStatement();
                String btid=jTextField59.getText();
                //String booknm=null;
                // String studnm=null;
                ResultSet rs,rs1,rs2;
                System.out.println(".."+btid+"..");
                rs =s.executeQuery("select borrowerid,catrefnum,entrydatetime,duedate from circtransactions where accnum='"+btid+"' and TranType='IS' order  by EntryDateTime desc ");

                if(rs.next())
                {
                    System.out.println("rs whil...");
                    String studid=rs.getString("borrowerid");
                    String bookid=rs.getString("catrefnum");
                    jTextField22.setText(bookid);
                    jTextField58.setText(studid);
                    jTextField60.setText(rs.getString("entrydatetime"));
                    //  duedate=rs.getDate("duedate");
                    jTextField61.setText(rs.getString("duedate"));
                    //jTextField61.setText(String.valueOf(duedate));

                    rs1 =s.executeQuery("select salutation from Borrowers where Borrowerid='"+studid+"' ");

                    while(rs1.next())
                    {System.out.println("rs1 while...");
                        flg=1;
                        jTextField27.setText(" "+rs1.getString("salutation")+" ");
                    }

                    rs2 =s.executeQuery("select cardtitle from card where catrefnum='"+bookid+"' ");
                    while(rs2.next())
                    {System.out.println("rs2 while...");
                        //flg=1;
                        jTextField28.setText(" "+rs2.getString("cardtitle")+" ");
                    }
                    rs1.close();
                    rs2.close();
                }
                if(flg==0)
                {
                    JOptionPane.showMessageDialog(null,"Record not found");
                }

                rs.close();
                s.close();

                //Calendar cal=Calendar.getInstance();

            }
            catch(SQLException e)
            {System.out.println("catch 2...");
                System.out.println(e);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Record not found");

        }

        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");

        Date curdt1=new Date();
        Date duedt1=new Date();
        try{
            System.out.println("try 3 date wala...");
            String duedt=jTextField61.getText();
            String curdt=jTextField21.getText();

            duedt1=df.parse(duedt);
            // duedt1=df.parse(duedt);
            curdt1=df.parse(curdt);

            // curdt1=df.parse(curdt);

            long dyz=curdt1.getTime()-duedt1.getTime();

            System.out.println(dyz);
            int dat=(int)((dyz)/(1000*24*60*60));
            System.out.println(dat);
            jTextField63.setText(Integer.toString(dat));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jTextField65FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField65FocusGained
        // TODO add your handling code here:
        int avail=0;
        String btid="",studid="",bookid="",catid="";
        //jTextField65.setEnabled(false);


        try{
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("select * from Circulation where rfidcd='"+jTextField65.getText().trim()+"' and CirculationStatusCD='C'");
            if(rs.next())
            {
                jTextField59.setText(rs.getString("accnum"));
                avail=1;
                System.out.println("avil...");
                                jButton8.setEnabled(true);
            }
            rs.close();
            s.close();
        }
        catch(SQLException e)
        {System.out.println("catch 1...");
            System.out.println(e);
        }

        if(avail==1)
        {
            try
            {System.out.println("try2...");
                int flg=0;

                Statement s=con.createStatement();
                btid=jTextField59.getText();
                //String booknm=null;
                // String studnm=null;
                ResultSet rs3,rs1,rs2;
                System.out.println(".."+btid+"..");
                rs3 =s.executeQuery("select borrowerid,catrefnum,entrydatetime,duedate from circtransactions where accnum='"+btid+"' and TranType='IS' order  by EntryDateTime desc ");

                if(rs3.next())
                {
                    System.out.println("rs whil...");
                    studid=rs3.getString("borrowerid");
                    bookid=rs3.getString("catrefnum");
                    jTextField22.setText(bookid);
                    jTextField58.setText(studid);
                    jTextField60.setText(rs3.getString("entrydatetime"));
                    //  duedate=rs.getDate("duedate");
                    jTextField61.setText(rs3.getString("duedate"));
                    //jTextField61.setText(String.valueOf(duedate));
                }
                rs3.close();
                rs1 =s.executeQuery("select salutation,emailname,categoryid from Borrowers where Borrowerid='"+studid+"' ");

                while(rs1.next())
                {System.out.println("rs1 while...");
                    flg=1;
                    jTextField27.setText(" "+rs1.getString("salutation")+" ");
                    jTextField3.setText(rs1.getString("emailname"));
                    catid=rs1.getString("categoryid");
                }
                rs1.close();
                rs2 =s.executeQuery("select cardtitle from card where catrefnum='"+bookid+"' ");
                while(rs2.next())
                {System.out.println("rs2 while...");
                    //flg=1;
                    jTextField28.setText(" "+rs2.getString("cardtitle")+" ");
                           jTextField59.setEditable(false);
        jButton8.requestFocus();
                }
                rs2.close();
                if(flg==0)
                {
                    JOptionPane.showMessageDialog(null,"Record not found");
                    jTextField59.requestFocus();
                }

                s.close();

                //Calendar cal=Calendar.getInstance();

            }
            catch(SQLException e)
            {System.out.println("catch.. 2...");
                System.out.println(e);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Record not found");
            jTextField22.setText("");
        jTextField27.setText("");
        jTextField28.setText("");
        jTextField58.setText("");
        jTextField65.setText("");
        jTextField60.setText("");
        jTextField61.setText("");
        jTextField59.setText("");
        jTextField62.setText("");
        jTextField63.setText("");
        jTextField3.setText("");
        jTextField59.setEditable(true);
        jTextField59.requestFocus();
        jButton8.setEnabled(false);

        }

        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");

        Date curdt1=new Date();
        Date duedt1=new Date();
        try{
            System.out.println("try 3 date wala...");
            String duedt=jTextField61.getText();
            String curdt=jTextField21.getText();

            duedt1=df.parse(duedt);
            // duedt1=df.parse(duedt);
            curdt1=df.parse(curdt);

            // curdt1=df.parse(curdt);

            long dyz=curdt1.getTime()-duedt1.getTime();

            System.out.println(dyz);
            int dat=(int)((dyz)/(1000*24*60*60));
            System.out.println(dat);
            jTextField63.setText(Integer.toString(dat));
            if(jTextField63.getText().startsWith("-"))
            {
                jTextField63.setText("0");
                jTextField62.setText("0");
            }
            else{
                //****calculate fine****
                jTextField63.setText(Integer.toString(dat));
                try{
                    Statement s=con.createStatement();
                    ResultSet rs=s.executeQuery("select loandays,finerate from circrules where categoryid='"+catid+"'");
                    if(rs.next())
                    {
                        jTextField62.setText(Integer.toString(rs.getInt("finerate")*dat));
                    }
                    rs.close();
                    s.close();
                }
                catch(Exception e)
                {
                    System.out.print(e);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
         
    }//GEN-LAST:event_jTextField65FocusGained

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        jTextField22.setText("");
        jTextField27.setText("");
        jTextField28.setText("");
        jTextField58.setText("");
        jTextField65.setText("");
        jTextField60.setText("");
        jTextField61.setText("");
        jTextField59.setText("");
        jTextField62.setText("");
        jTextField63.setText("");
        jTextField3.setText("");
        jTextField59.setEditable(true);
        jTextField59.requestFocus();
        jButton8.setEnabled(false);

    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField28ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        DateFormat df=new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date date=new Date();
        Calendar cal=Calendar.getInstance();
        System.out.print(df.format(cal.getTime()));
        String dat=df.format(cal.getTime());

        System.out.println("...........date:"+dat);
        System.out.print(df.format(cal.getTime()));
        try
        {/*
            Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            */
            Statement s=con.createStatement();
            System.out.println("Connection to the database is done.");
            String bukid=jTextField22.getText();
            int fine=Integer.parseInt(jTextField62.getText());
            //String studid;
            //String duedt=null;
            //login lg=new login();
            String curuser=jTextField14.getText();

            s.executeUpdate("INSERT INTO CircTransactions (BorrowerID,TranDateTime,TranType,AccNum,CatRefNum,EntryDateTime,fineamount,delaydays,DueDate,RtnDate,TransactionOperator,DomainName,MachineName,BarCodeNum) values('"+jTextField58.getText().toUpperCase()+"','"+dat+"','RC','"+jTextField59.getText().toUpperCase()+"','"+bukid+"','"+dat+"',"+fine+",'"+Integer.parseInt(jTextField63.getText().trim())+"',NULL,'"+dat+"','"+curuser+"','RFID','RFID','"+bukid+"')");
            //           s.executeUpdate("delete from CircTransactions where borrowerid='"+jTextField58.getText().trim()+"' and accnum='"+jTextField59.getText().trim()+"' and trantype='IS' and EntryDateTime=(select max(EntryDateTime) from CircTransactions where accnum='"+jTextField59.getText().trim()+"')");
            s.executeUpdate("update circulation set circulationstatuscd='B',borrowerid=NULL,DateBorrowedOn=NULL,returnduedate=NULL where accnum='"+jTextField59.getText().trim()+"'");

            JOptionPane.showMessageDialog(null,"Book Is Subimitted");
            jTextField22.setText("");
            jTextField27.setText("");
            jTextField28.setText("");
            jTextField58.setText("");
            jTextField59.setText("");
            jTextField61.setText("");
            jTextField60.setText("");
            jTextField63.setText("");
            jTextField65.setText("");
            jTextField62.setText("");
            s.close();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }        // TODO add your handling code here:
/*        String to ="";// "kantilal85@gmail.com";//change accordingly
        //String to=jTextField3.getText().trim();     //actual mail of user
        // Sender's email ID needs to be mentioned
        String from = "rfid@gmail.com";//change accordingly
        final String username = "pratik.lokhande79";//change accordingly
        final String password = "pratik14391";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Create a default MimeMessage object.
                Message message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

                // Set Subject: header field

                message.setSubject("SGI Library-Book Issued");

                // Now set the actual message
                message.setText("Hello"+jLabel27.getText()+","
                    + "     Thank you for using SGI Library facility."
                    + ""
                    + "you had Issed "+jLabel28.getText()+"on the date "+jTextField60+" and you have to submit this book on or before "+jTextField61.getText().trim()+" otherwise you will be able to pay penalty amount"
                    + "thank you,"
                    + "SGI Library " );
                // Send message
                Transport.send(message);

                System.out.println("Sent message successfully....");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }*/
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jPanel10ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel10ComponentShown
        // TODO add your handling code here:
        panelstatus=3;
    }//GEN-LAST:event_jPanel10ComponentShown

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        // TODO add your handling code here:
        System.out.print("aala ithe.......");
        String rfid=jTextField2.getText().trim();

        if(!rfid.isEmpty())
        {
            System.out.println("..."+rfid);
            try{
                Statement s1=con.createStatement();

                ResultSet rs=s1.executeQuery("select AccNum,CatRefNum,circulationstatuscd from circulation where rfidcd='"+rfid+"'");
                System.out.println("..."+rfid);
                while(rs.next())
                {
                    DefaultTableModel m,m2;
                    m=(DefaultTableModel) jTable3.getModel();
                    //m2=(DefaultTableModel) jTable2.getModel();
                    jTable3.setAutoResizeMode(jTable3.AUTO_RESIZE_ALL_COLUMNS);
                    jTable3.setFillsViewportHeight(true);
                    // JScrollPane scroll = new JScrollPane(table);
                    jScrollPane3.setHorizontalScrollBarPolicy(
                        jScrollPane3.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    jScrollPane3.setVerticalScrollBarPolicy(
                        jScrollPane3.VERTICAL_SCROLLBAR_AS_NEEDED);

                    m2=(DefaultTableModel) jTable2.getModel();
                    jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_ALL_COLUMNS);
                    jTable2.setFillsViewportHeight(true);
                    // JScrollPane scroll = new JScrollPane(table);
                    jScrollPane2.setHorizontalScrollBarPolicy(
                        jScrollPane2.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    jScrollPane2.setVerticalScrollBarPolicy(
                        jScrollPane2.VERTICAL_SCROLLBAR_AS_NEEDED);

                    String[] columnNames={"Accession No","Book_ID"};
                    String acc=rs.getString("AccNum");
                    String BookID=rs.getString("CatrefNum");

                    String cd =rs.getString("circulationstatuscd").trim();
                    System.out.println("..."+cd);

                    //String Name=rs.getString("CardTitle");
                    //String author=rs.getString("CardAuthor");

                    //DefaultTableModel sc;

                    /*m.setColumnIdentifiers(columnNames);
                    jTable1.setModel(m);

                    m.addRow(new Object[]{acc,BookID});*/
                    // m.setColumnIdentifiers(columnNames);
                    // jTable1.setModel(m);

                    //m.addRow(new Object[]{acc,BookID});

                    if(cd.equals("C"))
                    {

                        m2.setColumnIdentifiers(columnNames);
                        jTable2.setModel(m2);

                        m2.addRow(new Object[]{acc,BookID});

                        /* MP3Player m1=new MP3Player();
                        m1.addToPlayList(new File("src\\imgs\\alram.mp3"));
                        m1.play();

                        // new MP3Player(new File("src\\imgs\\alram.mp3")).play();
                        int selection=JOptionPane.showConfirmDialog(null,"Book Theft found... !!!","Book Theft found... !!!",JOptionPane.OK_CANCEL_OPTION);
                        if(selection==JOptionPane.OK_OPTION)
                        {
                            m1.stop();
                        }*/

                    }
                    else
                    {
                        //JOptionPane.showMessageDialog(null,"book issued u can go... !!!");

                        m.setColumnIdentifiers(columnNames);
                        jTable3.setModel(m);

                        m.addRow(new Object[]{acc,BookID});

                        MP3Player m1=new MP3Player();
                        m1.addToPlayList(new File("src\\imgs\\alram.mp3"));
                        m1.play();

                        // new MP3Player(new File("src\\imgs\\alram.mp3")).play();
                        int selection=JOptionPane.showConfirmDialog(null,"Book Theft found... !!!","Book Theft found... !!!",JOptionPane.OK_CANCEL_OPTION);
                        if(selection==JOptionPane.OK_OPTION)
                        {
                            m1.stop();
                        }
                    }
                }

                rs.close();
                s1.close();
            }

            catch(SQLException e)
            {
                System.out.println(e);
            }
            jTextField2.setText("");
            jTextField2.requestFocus();
            jButton6.requestFocus();
            /*DefaultListModel listmodel = new DefaultListModel();
            listmodel.addElement(rfid);
            jList1= new JList(listmodel);*/
            //jButton1.setFocusCycleRoot(true);
            //jTextField1.transferFocusBackward();

        }

    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTabbedPane2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2MousePressed

    private void jTextField53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField53ActionPerformed

    private void jTextField47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField47ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        stdupdate s=new stdupdate();
        s.setVisible(true);
        s.jTextField1.setText(jTextField45.getText());
        try
        {
            /*Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            System.out.println("Connection to the database is done.");*/
            Statement s1=con.createStatement();

            String stdt=  jTextField45.getText();
            //String bknm= jTextField13.getText();
            ResultSet rs1;
            //rs =s.executeQuery("Select CardTitle,CardAuthor,ISBNNumber,Copies from Card c where (c.CardTitle like'"+bknm+"%') ");
            //rs1 =s.executeQuery("Select CatRefNum,CardTitle,CardAuthor,Copies from Card c where c.CardAuthor like '%"+athrnm+"%' ");
            rs1 =s1.executeQuery("Select FirstName,LastName,Address,DepartmentName,City,PostalCode,PhoneNumber,EmailName,CategoryID,MobileNumber from Borrowers c where (c.BorrowerID like'%"+stdt+"%') ");
            while(rs1.next())
            {
                s.jTextField2.setText(" "+rs1.getString("FirstName")+" ");

                s.jTextField3.setText(" "+rs1.getString("LastName")+" ");
                s.jTextField5.setText(" "+rs1.getString("Address")+" ");
                //                s.jTextField7.setText(" "+rs1.getString("DepartmentName")+" ");
                String  bch="CSE";
                String bch2="Mech";
                String bch3="ETC";
                String bch4="ELECTRICAL";
                String bch5="CIVIL";
                String bch6="MBA";
                String bch7="FACULTY";
                String bch8="DIPLOMA";
                s.jComboBox2.addItem(rs1.getString("DepartmentName"));
                s.jComboBox2.addItem(bch);
                s.jComboBox2.addItem(bch2);
                s.jComboBox2.addItem(bch3);
                s.jComboBox2.addItem(bch4);
                s.jComboBox2.addItem(bch5);
                s.jComboBox2.addItem(bch6);
                s.jComboBox2.addItem(bch7);
                s.jComboBox2.addItem(bch8);
                //s.jComboBox1.setSelectedItem(rs1.getString("DepartmentName"));

                s.jTextField4.setText(" "+rs1.getString("City")+" ");
                s.jTextField10.setText(" "+rs1.getString("PostalCode")+" ");
                s.jTextField8.setText(" "+rs1.getString("phonenumber")+" ");
                s.jTextField9.setText(" "+rs1.getString("emailname")+" ");

                s.jComboBox1.addItem(rs1.getString("CategoryID"));
                String yr="FE";
                String yr2="SE";
                String yr3="TE";
                String yr4="BE";
                String yr5="DIPLOMA";
                String yr6="MBA";
                String yr7="Faculty";
                s.jComboBox1.addItem(yr);
                s.jComboBox1.addItem(yr2);
                s.jComboBox1.addItem(yr3);
                s.jComboBox1.addItem(yr4);
                s.jComboBox1.addItem(yr5);
                s.jComboBox1.addItem(yr6);
                s.jComboBox1.addItem(yr7);
                //   s.jTextField6.setText(" "+rs1.getString("CategoryID").trim()+" ");

                s.jTextField11.setText(" "+rs1.getString("mobilenumber")+" ");

            }
            rs1.close();
            s1.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jTextField45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField45ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField45ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked

        String tblclck="";
        // TODO add your handling code here:
        try{
            int row=jTable5.getSelectedRow();
            tblclck=(jTable5.getModel().getValueAt(row,0).toString());
            //jTable5.getModel().
            System.out.println(tblclck);
            Statement s=con.createStatement();
            String q="select * from Borrowers where BorrowerID='"+tblclck.trim()+"'";
            System.out.print(q);
            ResultSet rs=s.executeQuery("select * from Borrowers where BorrowerID='"+tblclck.trim()+"'");
            while(rs.next())
            {
                System.out.println(tblclck);

                jTextField45.setText(rs.getString("BorrowerID"));
                jTextField47.setText(rs.getString("address"));
                jTextField48.setText(rs.getString("salutation"));

                jTextField49.setText(rs.getString("departmentname"));

                jTextField52.setText(rs.getString("phonenumber"));
                jTextField51.setText(rs.getString("emailname"));
                jTextField50.setText(rs.getString("categoryid"));
                jTextField53.setText(rs.getString("mobilenumber"));

                //jTextField42.setText(rs.getString("salutation"));
                jButton21.setEnabled(true);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
        }
        /*try{

        }
        catch(Exception e)
        {
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("select * from Borrowers where BorrowerID='"+tblclck.trim()+"'");
            while(rs.next())
            {
                System.out.println(tblclck);

                String brid=rs.getString("BorrowerID");
                jTextField45.setText(brid);
                //jTextField4.setText(rs.getString("borrowerid"));
                //jTextField42.setText(rs.getString("salutation"));
            }
        }*/

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
jButton21.setEnabled(false);
        jTextField1.setText("");
        //jTextField4.setText("");
        //jTextField9.setText("");
        //jTextField10.setText("");
        jTextField33.setText("");
        jTextField45.setText("");
        jTextField47.setText("");
        jTextField48.setText("");
        jTextField49.setText("");
        jTextField50.setText("");
        jTextField51.setText("");
        jTextField52.setText("");
        jTextField53.setText("");

        //jTextField2.setText("");
        //jTextField3.setText("");
        DefaultTableModel m;
        m=(DefaultTableModel) jTable5.getModel();
        m.setRowCount(0);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        // TODO add your handling code here:
        try
        {
            // Connection con;
            //con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            // System.out.println("Connection to the database is done.");
            Statement s=con.createStatement();
            int flg=0;

            String stdt=  jTextField1.getText();
            //String bknm= jTextField13.getText();
            ResultSet rs1;
            //rs =s.executeQuery("Select CardTitle,CardAuthor,ISBNNumber,Copies from Card c where (c.CardTitle like'"+bknm+"%') ");
            //rs1 =s.executeQuery("Select CatRefNum,CardTitle,CardAuthor,Copies from Card c where c.CardAuthor like '%"+athrnm+"%' ");
            rs1 =s.executeQuery("Select BorrowerID,Salutation,CategoryID,DepartmentName,EmailName,MobileNumber from Borrowers c where (c.BorrowerID like'%"+stdt+"%') ");
            while(rs1.next())
            {
                flg=1;
                jTable4.setAutoResizeMode(jTable4.AUTO_RESIZE_ALL_COLUMNS);
                jTable4.setFillsViewportHeight(true);

                jScrollPane4.setHorizontalScrollBarPolicy(
                    jScrollPane4.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jScrollPane4.setVerticalScrollBarPolicy(
                    jScrollPane4.VERTICAL_SCROLLBAR_AS_NEEDED);

                String[] columnNames={"BorrowerID","Borrower Name","Branch","Year","Email","Contact No"};
                String sid=rs1.getString("BorrowerID");
                String SName=rs1.getString("Salutation");
                String year=rs1.getString("CategoryID");
                String Branch=rs1.getString("DepartmentName");
                String email=rs1.getString("EmailName");

                String phno=rs1.getString("MobileNumber");
                //DefaultTableModel sc;

                DefaultTableModel m;
                m=(DefaultTableModel) jTable5.getModel();
                m.setColumnIdentifiers(columnNames);
                jTable5.setModel(m);

                m.addRow(new Object[]{sid,SName,Branch,year,email,phno});
            }

            rs1.close();
            s.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        /*try
        {/*
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }
        catch(ClassNotFoundException ce)
        {
            System.out.println("driver is loaded");
        }
        try
        {
            Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            System.out.println("Connection to the database is done.");

            int flg=0;
            Statement s=con.createStatement();
            String stid=jTextField1.getText();
            //Statement s=con.prepareStatement("UPDATE rfid.dbo.Borrowers SET stock=? WHERE id=?");
            // stnm= jTextField33.getText();
            //String bukid=jTextField2.getText();

            // ResultSet rs=s.executeQuery("Select Photo from Borrowers where BorrowerID='09CS001'");
            ResultSet rs;
            rs =s.executeQuery("select salutation,departmentname,categoryid,phonenumber,mobilenumber,emailname from Borrowers where BorrowerId like '%"+stid+"%' ");

            while(rs.next())
            {
                flg=1;
                jTextField2.setText(" "+rs.getString("salutation")+" ");

                jTextField3.setText(" "+rs.getString("departmentname")+" ");
                jTextField4.setText(" "+rs.getString("categoryid")+" ");

                //if(rs.getString("phonenumber")==Null)
                //{
                    jTextField9.setText(" "+rs.getString("phonenumber")+" ");
                    // System.out.print(rs.getString("mobilenumber"));
                    //}

                jTextField9.setText(" "+rs.getString("mobilenumber")+" ");
                //System.out.print(" "+rs.getString("phonenumber")+" ");

                jTextField10.setText(" "+rs.getString("emailname")+" ");

            }
            if(flg==0)
            {
                JOptionPane.showMessageDialog(null,"Record not found");
            }

            rs.close();
            s.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }*/
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try
        {
            //Connection con;
            //con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            //System.out.println("Connection to the database is done.");
            Statement s=con.createStatement();
            int flg=0;

            String stdt=  jTextField33.getText();
            //String bknm= jTextField13.getText();
            ResultSet rs1;
            //rs =s.executeQuery("Select CardTitle,CardAuthor,ISBNNumber,Copies from Card c where (c.CardTitle like'"+bknm+"%') ");
            //rs1 =s.executeQuery("Select CatRefNum,CardTitle,CardAuthor,Copies from Card c where c.CardAuthor like '%"+athrnm+"%' ");
            rs1 =s.executeQuery("Select BorrowerID,Salutation,CategoryID,DepartmentName,EmailName,MobileNumber from Borrowers c where (c.Salutation like'%"+stdt+"%') ");
            while(rs1.next())
            {
                flg=1;
                jTable4.setAutoResizeMode(jTable4.AUTO_RESIZE_ALL_COLUMNS);
                jTable4.setFillsViewportHeight(true);

                jScrollPane4.setHorizontalScrollBarPolicy(
                    jScrollPane4.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jScrollPane4.setVerticalScrollBarPolicy(
                    jScrollPane4.VERTICAL_SCROLLBAR_AS_NEEDED);

                String[] columnNames={"BorrowerID","Borrower Name","Branch","Year","Email","Contact No"};
                String sid=rs1.getString("BorrowerID");
                String SName=rs1.getString("Salutation");
                String year=rs1.getString("CategoryID");
                String Branch=rs1.getString("DepartmentName");
                String email=rs1.getString("EmailName");
                //imageUpdate(null, i, i, i, i, i) email=rs1.getBlob("EmailName");
                String phno=rs1.getString("MobileNumber");
                //DefaultTableModel sc;

                DefaultTableModel m;
                m=(DefaultTableModel) jTable5.getModel();
                m.setColumnIdentifiers(columnNames);
                jTable5.setModel(m);

                m.addRow(new Object[]{sid,SName,Branch,year,email,phno});
            }

            rs1.close();
            s.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField34ActionPerformed

    private void jTextField29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField29ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:

        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField11.setText("");
        jTextField26.setText("");
        jTextField12.setText("");
        jTextField24.setText("");
        jTextField25.setText("");
        jTextField34.setText("");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String stdnm,fnm,mnm,lnm,phno,mobno,eid,browid,memenddt,adress,pcod,brnch,yr,curuser,city,adyear,cnt;
        int cnnt=0;
        fnm = jTextField6.getText().toUpperCase();

        mnm=jTextField24.getText().toUpperCase();

        lnm=jTextField25.getText().toUpperCase();

        stdnm=lnm+" "+fnm+" "+mnm;
        phno=jTextField11.getText();

        mobno=jTextField7.getText();

        eid=jTextField12.getText();

        browid=jTextField5.getText();
        adress=jTextField18.getText().toUpperCase();
        pcod=jTextField26.getText();

        brnch=(String)jComboBox2.getSelectedItem();
        yr=(String)jComboBox1.getSelectedItem();
        curuser=jTextField29.getText();
        city=jTextField33.getText().toUpperCase();
        DateFormat df=new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date date=new Date();
        Calendar cal=Calendar.getInstance();
        jTextField21.setText(df.format(cal.getTime()));
        System.out.print(df.format(cal.getTime()));
        String dat=df.format(cal.getTime());
        cal.add(Calendar.DATE , 365);
        memenddt= df.format(cal.getTime());

        String au=null;
          
             if(brnch=="CSE")
                   {
                       
                   au="CS";
                   }
           else if(brnch=="E&TC")
                   {
                      au="ET"; 
                   }
           else if(brnch=="ELECTRICAL")
           {
               au="EL";
           }
           else if(brnch=="MECH")
           {
               au="ME";
           }
            else if(brnch=="CIVIL")
           {
               au="CV";
           }
            else if(brnch=="MBA")
           {
               au="MB";
           }
            else if(brnch=="FACULTY")
           {
               au="F";
           }
           adyear=jTextField81.getText().substring(2, 4);
          
             try
           {
               System.out.println("hellow");
               Statement ssss=con.createStatement();
           ResultSet rss=ssss.executeQuery("select max(BorrowerID)as maxborrower from Borrowers where BorrowerID like'"+adyear+au+"%'");
while(rss.next())
{cnt=rss.getString("maxborrower");
 
System.out.println(cnt);
String gy=cnt.substring(0, 4);
int cu3=0;
String cu=cnt.substring(4, 7);
cu3=Integer.parseInt(cu);
//int cu1=cu.length();
                   int cu2=Integer.parseInt(cu);
                   cu2++;
                   String tr=Integer.toString(cu2);
                   if(tr.length()==3)
                   {  browid=gy+tr;
                       System.out.println(browid);
                   }
                   else if(tr.length()==2)
                   {  browid=gy+"0"+tr;
                       System.out.println(browid);
                   }
                   else
                       if(tr.length()==1)
                       {  browid=gy+"00"+tr;
                       System.out.println(browid);
                   }
                       
                   
                   
                   
                   
                   
            /*  cnnt=Integer.parseInt(cnt.substring(4,7));
             cnnt=cnnt+1;*/
            System.out.println(cnt);
}         
         rss.close();

        ssss.close();
          }
           catch(SQLException see)
           {
              System.out.print(see);
            JOptionPane.showMessageDialog(null,"Query not exected...." ); 
           }
            // stbid=adyear+au+cnnt; 
          
        jTextField5.setText(browid);
        JOptionPane.showMessageDialog(null,"Student id is:"+browid);
        
           
           
         
        
        try
        {
            System.out.println("Hi.....");
        /*    
            Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            System.out.println("Connection to the database is done.");*/
            Statement s=con.createStatement();
         
          //  rs1=s.executeQuery("select MenBegDate from Borrowers");
           //String dta=rs1.getString("MenBegDate");
           //SimpleDateFormat  dtaa=new SimpleDateFormat("yy");
           //dta=dtaa.format(Calendar.getInstance().getTime());
           //System.out.println(dta);
           
        
            //s.executeUpdate("INSERT INTO Borrowers(BorrowerID,FirstName,LastName,Address,Salutation,OrganizationName,DepartmentName,City,State,PostalCode,Country,ContactName,ContactTitle,PhoneNumber,Extension,FaxNumber,EmailName,Note,CategoryID,MemBegDate,MemEndDate,Priority,CurrentDues,Deposit,ReceiptNo,ReceiptDate,Password,FeesDue,FinesDue,OperatorID,Address2,City2,State2,PostalCode2,SupervisorID2,Country2,SIP2Blocked,SIP2BlockedMessage,SIP2CardRetained,ServiceLine,IndustryLine,Activated,WorkPlace,BorrBarcodeNum,trpBorrID,BirthDate,BloodGroup,revUserID,MobileNumber) VALUES('"+browid+"','"+fnm+"','"+lnm+"','"+adress+"','"+stdnm+"','""','"brnch"','""','""','""','"+pcod+"','""','""','"+phno+"','""','""','"+eid+"','""','"+yr+"','"+dat+"','"+memenddt+"',9, 0,0,'""',null,'MEMBER',0,0,'"+curuser+"','""','""','""','""','""','""','N','""','N','""','""',1,'Main','"+browid+"','""',null,'""','"+curuser+"','"+mobno+"')");
         s.executeUpdate("INSERT INTO Borrowers(BorrowerID,FirstName,LastName,Address,Salutation,OrganizationName,DepartmentName,City,State,PostalCode,Country,ContactName,ContactTitle,PhoneNumber,Extension,FaxNumber,EmailName,Note,CategoryID,MemBegDate,MemEndDate,Priority,CurrentDues,Deposit,ReceiptNo,ReceiptDate,Password,FeesDue,FinesDue,OperatorID,Address2,City2,State2,PostalCode2,SupervisorID2,Country2,SIP2Blocked,SIP2BlockedMessage,SIP2CardRetained,ServiceLine,IndustryLine,Activated,WorkPlace,BorrBarcodeNum,trpBorrID,BirthDate,BloodGroup,revUserID,MobileNumber) VALUES('"+browid+"','"+fnm+"','"+lnm+"','"+adress+"','"+stdnm+"','','"+brnch+"','"+city+"','','"+pcod+"','','','','"+phno+"','','','"+eid+"','','"+yr+"','"+dat+"','"+memenddt+"',9, 0,0,'',null,'MEMBER',0,0,'"+curuser+"','','','','','','','N','','N','','',00000001,'Main','"+browid+"','',null,'','"+curuser+"','"+mobno+"')");
         JOptionPane.showMessageDialog(null,"Student Added Successfully..");
         jTextField5.setText("");
         jTextField6.setText("");
         jTextField7.setText("");
         jTextField8.setText("");
         jTextField11.setText("");
         jTextField26.setText("");
         jTextField12.setText("");
         jTextField24.setText("");
         jTextField25.setText("");
         jTextField34.setText("");
        // s.close();
        
        }
        catch(SQLException se)
        {
            System.out.print(se);
           // JOptionPane.showMessageDialog(null,"Oops... Duplicate Borrower ID is not Allowd" );
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField44ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField42ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField18.setText("");
        //        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jTextField4.setText("");
        jTextField44.setText("");
        jTextArea1.setText("");
        jTextField43.setText("");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        String sr="hi";
        //  String  bkt = jTextField4.getText().toUpperCase();
        // int fl=0;

        /* if(jTextField4.getText().matches(".*\\d.*"))
        {
            JOptionPane.showMessageDialog(null,"No Numaric Value");

            jTextField4.setText(" ");
            // fl=1;
            sr="bye";
        }  */

        if(jTextField16.getText().matches(".*\\d.*"))
        {
            JOptionPane.showMessageDialog(null,"No Numaric Value");
            jTextField16.setText(" ");
            // fl=2;
            sr="bye";
        }

        if(!jTextField18.getText().matches(".*\\d{4}.*"))
        {
            JOptionPane.showMessageDialog(null,"Enter the valid no...");
            jTextField18.setText(" ");
            // fl=3;
            sr="bye";
        }

        if(!jTextField43.getText().matches(".*\\d.*"))
        {
            JOptionPane.showMessageDialog(null,"Enter the valid no...");
            jTextField43.setText(" ");
            //fl=1;
            sr="bye";
        }

        if(!jTextField4.getText().matches(".*\\d{15}.*"))
        {
            JOptionPane.showMessageDialog(null,"Enter the valid no...");
            jTextField4.setText(" ");
            // fl=3;
            sr="bye";
        }

        if(jTextField44.getText().matches(".*\\d.*"))
        {
            JOptionPane.showMessageDialog(null,"No Numaric Value");
            jTextField44.setText(" ");
            // fl=2;
            sr="bye";
        }

        /*          if(jComboBox3.getSelectedIndex()==0)
        {
            JOptionPane.showMessageDialog(null,"Please Select the Book Type");
            sr="bye";
        }*/

        if(jComboBox4.getSelectedIndex()==0)
        {
            JOptionPane.showMessageDialog(null,"Please Select the Language CD");
            sr="bye";
        }
        //int ncp=Integer.parseInt(jTextField43.getText());

        String sd="005";
        Object b=sd;
        //Integer ncp=new Integer(0);
        //int ncp=(int)b;
        //  System.out.println(ncp);
        
         String bks=jTextField15.getText();

        if(sr=="hi")
        {
            try
            {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            }
            catch(ClassNotFoundException ce)
            {
                System.out.println("driver is loaded");
            }
                try
            {

                Statement s=con.createStatement();
             int ic;
                int   cop=Integer.parseInt(jTextField43.getText());
                String i="",f="",l="",nw="",set="";
                 int lens,m,lenw,flag=0;
                
              if(cop>1)
                  
              {
                  flag=1;
                 for(ic=1;ic<=cop;ic++)
                 {
                 
                 i=jTextField66.getText();
                 
                  lens=i.length();
           
                 l=i.substring(1,5);
           
                 m=Integer.parseInt(l);
                 m++;
                 
                  
           
                 nw=Integer.toString(m);
                  lenw=nw.length();
                
           
           
                    if(lenw==1)
                    {
                        f="Z000"+nw;
                    }
                      if(lenw==2)
                    {
                        f="Z00"+nw;
                    }
                        if(lenw==3)
                    {
                        f="Z0"+nw;
                    }
                         if(lenw==4)
                    {
                        f="Z"+nw;
                    }
                         System.out.println(f);
                         
                         if(lens==f.length())
                         {
                             
                              System.out.println("Both Length match");
                         }
                         
                      
                         set=f;
                         
                
                
               
              
                if(flag==1)
                {
                s.executeUpdate("insert into card(CatRefNum,CardType,TypeOfCataloguedItem,CardPrinted,DataEntryCompleted,CardAuthor,CardTitle,PublicationYear,GMDCD,Copies,Issued,PartCount,TxAddr,TxLen,AsIfNew,ClassificationSystemCode,AddDt,groupID,ISBNNumber,LanguageCD,AuthorMark,Bibliography) values('"+jTextField15.getText()+"','M','A',1,0,'"+jTextField16.getText().toUpperCase()+"','"+jTextField17.getText()+"','"+jTextField18.getText()+"',1,'"+jTextField43.getText()+"',1,1,1,1,'A','A','"+jTextField42.getText()+"',1,'"+jTextField4.getText()+"','"+jComboBox4.getSelectedItem()+"','"+jTextField44.getText()+"','"+jTextArea1.getText()+"')");
                flag=0;
                }
                
               
                 s.executeUpdate("insert into Circulation(AccNum,CatRefNum,InventoryStatusCD,CirculationStatusCD,CircCode,CurrReservations,LoanDays,ReIssueCount,RecallMark,ItemPhysicalCode,AccnRec,ReminderCount,IsCarol,BarcodeNum,libcode,LibMapRackID,CXCollection) values('"+set+"','"+jTextField15.getText()+"','O','B','01',0,0,0,0,'n','000123',0,0,'"+set+"',1,0,'a')");
                
                 
                 jTextField66.setText(set);
                 }
              }
              
              else
              {
                  
                  
                  i=jTextField66.getText();
                 
                  lens=i.length();
           
                 l=i.substring(1,5);
           
                 m=Integer.parseInt(l);
                 m++;
                 
                  
           
                 nw=Integer.toString(m);
                  lenw=nw.length();
                  
                  if(lenw==1)
                    {
                        f="Z000"+nw;
                    }
                      if(lenw==2)
                    {
                        f="Z00"+nw;
                    }
                        if(lenw==3)
                    {
                        f="Z0"+nw;
                    }
                         if(lenw==4)
                    {
                        f="Z"+nw;
                    }
                         System.out.println(f);
                         
                         if(lens==f.length())
                         {
                             
                              System.out.println("Both Length match");
                         }
                         
                      
                         set=f;
                         
                         s.executeUpdate("insert into card(CatRefNum,CardType,TypeOfCataloguedItem,CardPrinted,DataEntryCompleted,CardAuthor,CardTitle,PublicationYear,GMDCD,Copies,Issued,PartCount,TxAddr,TxLen,AsIfNew,ClassificationSystemCode,AddDt,groupID,ISBNNumber,LanguageCD,AuthorMark,Bibliography) values('"+jTextField15.getText()+"','M','A',1,0,'"+jTextField16.getText().toUpperCase()+"','"+jTextField17.getText()+"','"+jTextField18.getText()+"',1,'"+jTextField43.getText()+"',1,1,1,1,'A','A','"+jTextField42.getText()+"',1,'"+jTextField4.getText()+"','"+jComboBox4.getSelectedItem()+"','"+jTextField44.getText()+"','"+jTextArea1.getText()+"')");
                         
                 s.executeUpdate("insert into Circulation(AccNum,CatRefNum,InventoryStatusCD,CirculationStatusCD,CircCode,CurrReservations,LoanDays,ReIssueCount,RecallMark,ItemPhysicalCode,AccnRec,ReminderCount,IsCarol,BarcodeNum,libcode,LibMapRackID,CXCollection) values('"+set+"','"+jTextField15.getText()+"','O','B','01',0,0,0,0,'n','000123',0,0,'"+set+"',1,0,'a')");
                
                  jTextField66.setText(set);
              }
        
                  int bki=Integer.parseInt(bks);
                bki++;
           jTextField15.setText(Integer.toString(bki));
                JOptionPane.showMessageDialog(null,"Book is Added Successfully..!!!");
                 
              
                
                
                
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField18.setText("");

        jComboBox4.setSelectedIndex(0);
        jTextField4.setText("");
        jTextField44.setText("");
        jTextArea1.setText("");
        jTextField43.setText("");

                s.close();
 
       
            
            
       }
            catch(SQLException e)
            {
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"BookID Already Exisist...!!!");
                jTextField15.setText("");
            }
           /* try
            {

                //int nextsr=Integer.parseInt(jTextField66.getText());
                String i="",f="",l="",nw="",set="";
                int lens,m,lenw;

                i=jTextField66.getText();

                lens=i.length();

                l=i.substring(1,5);

                m=Integer.parseInt(l);
                m++;

                nw=Integer.toString(m);
                lenw=nw.length();

                if(lenw==1)
                {
                    f="Z000"+nw;
                }
                if(lenw==2)
                {
                    f="Z00"+nw;
                }
                if(lenw==3)
                {
                    f="Z0"+nw;
                }
                if(lenw==4)
                {
                    f="Z"+nw;
                }
                System.out.println(f);

                if(lens==f.length())
                {

                    System.out.println("Both Length match");
                }

                /* if(0==jComboBox3.getSelectedIndex())
                {
                    set="E"+f;
                }
                if(1==jComboBox3.getSelectedIndex())
                {
                    set="M"+f;
                }
                set=f;

                Statement s=con.createStatement();

                s.executeUpdate("insert into card(CatRefNum,CardType,TypeOfCataloguedItem,CardPrinted,DataEntryCompleted,CardAuthor,CardTitle,PublicationYear,GMDCD,Copies,Issued,PartCount,TxAddr,TxLen,AsIfNew,ClassificationSystemCode,AddDt,groupID,ISBNNumber,LanguageCD,AuthorMark,Bibliography) values('"+jTextField15.getText()+"','M','A',1,0,'"+jTextField16.getText().toUpperCase()+"','"+jTextField17.getText()+"','"+jTextField18.getText()+"',1,'"+jTextField43.getText()+"',1,1,1,1,'A','A','"+jTextField42.getText()+"',1,'"+jTextField4.getText()+"','"+jComboBox4.getSelectedItem()+"','"+jTextField44.getText()+"','"+jTextArea1.getText()+"')");

                //-------------------ERROR IS HERE-------------------
                s.executeUpdate("insert into Circulation(AccNum,CatRefNum,InventoryStatusCD,CirculationStatusCD,CircCode,CurrReservations,LoanDays,ReIssueCount,RecallMark,ItemPhysicalCode,AccnRec,ReminderCount,IsCarol,BarcodeNum,libcode,LibMapRackID,CXCollection) values('"+set+"','"+jTextField15.getText()+"','O','B','01',0,0,0,0,'n','000123',0,0,'"+set+"',1,0,'a')");

                JOptionPane.showMessageDialog(null,"Book is Added Successfully..!!!");

                jTextField15.setText("");
                jTextField16.setText("");
                jTextField17.setText("");
                jTextField18.setText("");

                jComboBox4.setSelectedIndex(0);
                jTextField4.setText("");
                jTextField44.setText("");
                jTextArea1.setText("");
                jTextField43.setText("");

                s.close();

                // nextsr++;
                jTextField66.setText(set);
            }
            catch(SQLException e)
            {
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"BookID Already Exisist...!!!");
                jTextField15.setText("");
            }*/
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        /*bukupdate bu=new bukupdate();
        bu.setVisible(true);
        bu.jTextField1.setText(jTextField54.getText());*/
        bukupdate bu=new bukupdate();
        bu.setVisible(true);
        String s1=jTextField54.getText();
        try
        {
            /*   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }
        catch(ClassNotFoundException ce)
        {
            System.out.println("driver is loaded");
        }
        try
        {
            Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            System.out.println("Connection to the database is done.");
            */

            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("select * from card where catrefnum='"+s1+"'");
            while(rs.next())
            {
                /* s2=(rs.getString("isbnnumber"));
                s3=(rs.getString("authormark"));
                s4=(rs.getString("cardtype"));
                s5=(rs.getString("languagecd"));

                s6=(rs.getString("publicationyear"));*/

                bu.jTextField1.setText(rs.getString("catrefnum"));
                bu.jTextField4.setText(rs.getString("isbnnumber"));
                bu.jTextField44.setText(rs.getString("authormark"));
                // bu.jTextField5.setText(rs.getString("cardtype"));
                //bu.jTextField6.setText(rs.getString("languagecd"));
                bu.jTextField16.setText(rs.getString("cardauthor"));
                bu.jTextField17.setText(rs.getString("cardtitle"));
                bu.jTextField43.setText(Integer.toString(rs.getInt("copies")));

                // int cp=rs.getInt("copies");
                // bu.jTextField43.setText(cp);
                bu.jTextField18.setText(rs.getString("publicationyear"));
                bu.jTextArea1.setText(rs.getString("bibliography"));
                bu.jTextField42.setText(rs.getString("adddt"));

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jTextField54FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField54FocusGained
        // TODO add your handling code here:

        DefaultTableModel m1;
        m1=(DefaultTableModel) jTable6.getModel();
        m1.setRowCount(0);

        String g=jTextField54.getText().trim();
        System.out.println(g);
        try
        {
            Statement s1=con.createStatement();
            ResultSet rs1=s1.executeQuery("select * from circulation where catrefnum='"+g+"'");
            while(rs1.next())
            {
                jTable6.setAutoResizeMode(jTable6.AUTO_RESIZE_ALL_COLUMNS);
                jTable6.setFillsViewportHeight(true);
                // JScrollPane scroll = new JScrollPane(table);
                jScrollPane2.setHorizontalScrollBarPolicy(
                    jScrollPane2.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jScrollPane2.setVerticalScrollBarPolicy(
                    jScrollPane2.VERTICAL_SCROLLBAR_AS_NEEDED);

                String[] columnNames={"Accession No","BookID","Status"};
                String acc_no=rs1.getString("AccNum");
                String bid=rs1.getString("Catrefnum");
                //String status=rs1.getString("CardAuthor");
                String status=rs1.getString("CirculationStatusCD");
                System.out.println(".."+status+"..");
                String st1=null;
                if(status.equals("B"))
                {
                    st1="On shelf";
                }
                else
                {
                    st1="Issued";
                }

                m1.setColumnIdentifiers(columnNames);
                jTable6.setModel(m1);

                m1.addRow(new Object[]{acc_no,bid,st1});
            }
        }
        catch(Exception e)
        {

            System.out.println(e);
        }
        jTextField54.requestFocus();
    }//GEN-LAST:event_jTextField54FocusGained

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:try
        DefaultTableModel m;
        m=(DefaultTableModel) jTable4.getModel();
        m.setRowCount(0);
        try
        {/*
            Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            System.out.println("Connection to the database is done.");
            */ Statement s=con.createStatement();
            int flg=0;
            // String bknm= XMLChar.trim( jTextField13.getText());
            String athrnm=  jTextField23.getText().toUpperCase();
            //String bknm= jTextField13.getText();
            ResultSet rs1;
            //rs =s.executeQuery("Select CardTitle,CardAuthor,ISBNNumber,Copies from Card c where (c.CardTitle like'"+bknm+"%') ");
            //rs1 =s.executeQuery("Select CatRefNum,CardTitle,CardAuthor,Copies from Card c where c.CardAuthor like '%"+athrnm+"%' ");
            rs1 =s.executeQuery("Select CatRefNum,CardTitle,CardAuthor,Copies from Card c where (c.CardAuthor like'%"+athrnm+"%') ");
            while(rs1.next())
            {
                flg=1;
                jTable4.setAutoResizeMode(jTable4.AUTO_RESIZE_ALL_COLUMNS);
                jTable4.setFillsViewportHeight(true);
                // JScrollPane scroll = new JScrollPane(table);
                jScrollPane4.setHorizontalScrollBarPolicy(
                    jScrollPane4.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jScrollPane4.setVerticalScrollBarPolicy(
                    jScrollPane4.VERTICAL_SCROLLBAR_AS_NEEDED);

                String[] columnNames={"Book ID","Book Name","Author Name","Total Copies"};
                String BookID=rs1.getString("CatrefNum");
                String Name=rs1.getString("CardTitle");
                String author=rs1.getString("CardAuthor");
                int Status=rs1.getInt("Copies");
                //DefaultTableModel sc;

                m.setColumnIdentifiers(columnNames);
                jTable4.setModel(m);

                m.addRow(new Object[]{BookID,Name,author,Status});
            }

            if(flg==0)
            {
                JOptionPane.showMessageDialog(null,"Record not found");
            }

            rs1.close();
            s.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }

        /*
        try
        {/*
            Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            System.out.println("Connection to the database is done.");
            Statement s=con.createStatement();
            int flg=0;
            // String bknm= XMLChar.trim( jTextField13.getText());
            String athrnm=  jTextField23.getText().toUpperCase();
            //String bknm= jTextField13.getText();
            ResultSet rs1;
            //rs =s.executeQuery("Select CardTitle,CardAuthor,ISBNNumber,Copies from Card c where (c.CardTitle like'"+bknm+"%') ");
            //rs1 =s.executeQuery("Select CatRefNum,CardTitle,CardAuthor,Copies from Card c where c.CardAuthor like '%"+athrnm+"%' ");
            rs1 =s.executeQuery("Select CatRefNum,CardTitle,CardAuthor,Copies from Card c where (c.CardAuthor like'%"+athrnm+"%') ");
            while(rs1.next())
            {
                flg=1;
                jTable4.setAutoResizeMode(jTable4.AUTO_RESIZE_ALL_COLUMNS);
                jTable4.setFillsViewportHeight(true);
                // JScrollPane scroll = new JScrollPane(table);
                jScrollPane4.setHorizontalScrollBarPolicy(
                    jScrollPane4.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jScrollPane4.setVerticalScrollBarPolicy(
                    jScrollPane4.VERTICAL_SCROLLBAR_AS_NEEDED);

                String[] columnNames={"Book ID","Book Name","Author Name","Total Copies"};
                String BookID=rs1.getString("CatrefNum");
                String Name=rs1.getString("CardTitle");
                String author=rs1.getString("CardAuthor");
                String Status=rs1.getString("Copies");
                //DefaultTableModel sc;

                DefaultTableModel m;
                m=(DefaultTableModel) jTable4.getModel();
                m.setColumnIdentifiers(columnNames);
                jTable4.setModel(m);

                m.addRow(new Object[]{BookID,Name,author,Status});
            }

            if(flg==0)
            {
                JOptionPane.showMessageDialog(null,"Record not found");
            }

            rs1.close();
            s.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }*/
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jTextField13.setText("");
        jTextField23.setText("");
        jTextField54.setText("");
        jTextField55.setText("");
jButton20.setEnabled(false);
        DefaultTableModel m;
        m=(DefaultTableModel) jTable4.getModel();
        m.setRowCount(0);

        DefaultTableModel m1;
        m1=(DefaultTableModel) jTable6.getModel();
        m1.setRowCount(0);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        String tblclck="";
        // TODO add your handling code here:
        try{
            int row=jTable4.getSelectedRow();
            tblclck=(jTable4.getModel().getValueAt(row,0).toString());
            //jTable5.getModel().
            System.out.println(tblclck);
            Statement s=con.createStatement();
            String q="select * from card where catrefnum='"+tblclck.trim()+"'";
            System.out.print(q);
            ResultSet rs=s.executeQuery("select * from card where catrefnum='"+tblclck.trim()+"'");
            while(rs.next())
            {
                System.out.println(tblclck);
                jTextField54.setText(rs.getString("catrefnum"));
                jTextField55.setText(rs.getString("cardtitle"));
jButton20.setEnabled(true);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        jTextField54.requestFocus();
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // jPanel7.setVisible(true);
        DefaultTableModel m;
        m=(DefaultTableModel) jTable4.getModel();
        m.setRowCount(0);
        try
        {/*
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }
        catch(ClassNotFoundException ce)
        {
            System.out.println("driver is loaded");
        }
        try
        {
            Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            System.out.println("Connection to the database is done.");
            */
            int flg=0;
            Statement s=con.createStatement();
            String bknm= XMLChar.trim( jTextField13.getText());
            //       String athrnm= jTextField23.getText();
            //String bknm= jTextField13.getText();
            ResultSet rs;
            //rs =s.executeQuery("Select CardTitle,CardAuthor,ISBNNumber,Copies from Card c where (c.CardTitle like'"+bknm+"%') ");
            rs =s.executeQuery("Select CatRefNum,CardTitle,CardAuthor,Copies from Card c where (c.CardTitle like'%"+bknm+"%') ");
            while(rs.next())
            {
                flg=1;
                jTable4.setAutoResizeMode(jTable4.AUTO_RESIZE_ALL_COLUMNS);
                jTable4.setFillsViewportHeight(true);
                // JScrollPane scroll = new JScrollPane(table);
                jScrollPane4.setHorizontalScrollBarPolicy(
                    jScrollPane4.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jScrollPane4.setVerticalScrollBarPolicy(
                    jScrollPane4.VERTICAL_SCROLLBAR_AS_NEEDED);

                String[] columnNames={"Book ID","Book Name","Author Name","Total Copies"};
                String BookID=rs.getString("CatrefNum");
                String Name=rs.getString("CardTitle");
                String author=rs.getString("CardAuthor");
                int Status=rs.getInt("Copies");
                //DefaultTableModel sc;

                m.setColumnIdentifiers(columnNames);
                jTable4.setModel(m);

                m.addRow(new Object[]{BookID,Name,author,Status});
            }

            if(flg==0)
            {
                JOptionPane.showMessageDialog(null,"Record not found");

            }

            rs.close();
            s.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13KeyTyped

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField19FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField19FocusLost
             if(jTextField19.getText().trim().isEmpty())
            {
              //  JOptionPane.showMessageDialog(null,"Enter Borrower ID !!!" );
                jTextField19.requestFocus();
            }
            else
            {
        try
        {

            Statement s1=con.createStatement();
                ResultSet rs=s1.executeQuery("Select salutation,departmentname,emailname,CategoryID,srfidcd from Borrowers where BorrowerId='"+jTextField19.getText().trim()+"'");//+"Select top 2 CardTitle,cardauthor,publicationyear from Card where CatRefNum='"+bukid+"'" );

            if(rs.next())
            {
                //while(rs.next())
                //  jTextField46.setText(Integer.toString(rs1.getInt("iss")-rs2.getInt("ret")));

                jLabel14.setText(" "+rs.getString("Salutation")+" ");
                jLabel50.setText(" "+rs.getString("departmentname")+" ");
                jTextField56.setText(rs.getString("emailname"));
                jLabel87.setText(" "+rs.getString("CategoryID")+" ");
                String rfid1=rs.getString("srfidcd");
                System.out.println(".."+rfid1+"..");
                if(rfid1!="null")
                { 
                    jTextField9.setText(rfid1);
                }
                jTextField19.setEditable(false);
                rs.close();

                s1.close();

                i++;
                jTextField57.setEditable(true);
                        jTextField57.requestFocus();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"BorrowerID Is Wrong.!!!" );
                jTextField19.requestFocus();
            }
        
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
            
        int issue=0,retu=0;
        try{
            Statement s2=con.createStatement();
            ResultSet rs1=s2.executeQuery("select count(*) as iss from CircTransactions where BorrowerID='"+jTextField19.getText().trim()+"' and TranType='IS'");
            if(rs1.next())
            {
                issue=rs1.getInt("iss");
               // System.out.println(issue);
            }
            rs1.close();
            s2.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }

        try{
            Statement s3=con.createStatement();
            ResultSet rs2=s3.executeQuery("select count(*) as ret from CircTransactions where BorrowerID='"+jTextField19.getText().trim()+"' and TranType='RC'");
            if(rs2.next())
            {
                //while(rs.next())
                //jTextField46.setText(Integer.toString( issue -rs2.getInt("ret")));
                retu=rs2.getInt("ret");
            }

            rs2.close();
            s3.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
       // System.out.println(i);
        jTextField46.setText(Integer.toString(issue-retu));
            }

    }//GEN-LAST:event_jTextField19FocusLost

    private void jTextField57FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField57FocusLost
                        //jTextField64.setFocusable(false);
        String statcd="";
        try
                {
             /*  String accno= jTextField57.getText().trim();
                System.out.println("..."+accno+"...");*/
                Statement s1=con.createStatement();

                if(jTextField57.getText().trim().isEmpty())
                {
                   // JOptionPane.showMessageDialog(null,"Enter AccetionNumber !!!" );
                    jTextField57.requestFocus();
                }
                else
                {
                    ResultSet rs=s1.executeQuery("Select CirculationStatusCD,catrefnum,rfidcd from circulation where accnum='"+jTextField57.getText().trim()+"'");//+"Select top 2 CardTitle,cardauthor,publicationyear from Card where CatRefNum='"+bukid+"'" );

                if(rs.next())
                {
                    //while(rs.next())
                    //  jTextField46.setText(Integer.toString(rs1.getInt("iss")-rs2.getInt("ret")));
                  statcd=rs.getString("CirculationStatusCD");
                    jTextField20.setText(rs.getString("catrefnum"));
                    jTextField64.setText(rs.getString("rfidcd"));
  
                    System.out.println("."+statcd+".");
                    rs.close();

                    s1.close();

                    i++;
                    if(statcd.equals("B"))
                            {
                  try
        {/*
            Connection con;
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");*/
            Statement s=con.createStatement();
            String bukid=null;
            bukid=jTextField20.getText().trim();
            System.out.println(bukid);
            /*if(bukid.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Enter Book ID!!!" );
            }
            else
            {
                */
                ResultSet rs1=s.executeQuery("select CardTitle,CardAuthor,PublicationYear from Card where CatRefNum='"+jTextField20.getText().trim()+"'");
                if(rs1.next())
                {

                    jLabel85.setText(rs1.getString("CardTitle"));
                    jLabel86.setText(" "+rs1.getString("CardAuthor")+" ");
                    jLabel88.setText(" "+rs1.getString("PublicationYear")+" ");
                    jTextField57.setEditable(false);
                    rs1.close();
                    s.close();
                    jButton10.setEnabled(true);
                    jButton10.requestFocus();
                    // i++;
                }
                else{
                   // JOptionPane.showMessageDialog(null,"Book ID is Invalid.!!!");
                    jTextField57.requestFocus();
                }
                //}
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        }
        else
        {
           // JOptionPane.showMessageDialog(null,"Book is Already ISSUED11!!!");
        }
                }

                else
                {
                    JOptionPane.showMessageDialog(null,"Accetion Number Is Wrong.!!!" );
                   jTextField57.requestFocus();
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
        
    }//GEN-LAST:event_jTextField57FocusLost

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained
       int flg=0;
       
       System.out.println("srfidcd gained");
        try{
                Statement s1=con.createStatement();
                ResultSet rs=s1.executeQuery("select borrowerid from borrowers where srfidcd='"+jTextField9.getText().trim()+"'");
                if(rs.next())
                {
                    jTextField19.setText(rs.getString("borrowerid"));
                     flg++;
                }
rs.close();
s1.close();
            }   
            catch(SQLException e)
            {
                System.out.println(e);
            }
       
        if(flg==1)
        {
        try
        {
            Statement s2=con.createStatement();
                ResultSet rs1=s2.executeQuery("Select salutation,departmentname,emailname,CategoryID from Borrowers where BorrowerId='"+jTextField19.getText().trim()+"'");//+"Select top 2 CardTitle,cardauthor,publicationyear from Card where CatRefNum='"+bukid+"'" );

            if(rs1.next())
            {
                //while(rs.next())
                //  jTextField46.setText(Integer.toString(rs1.getInt("iss")-rs2.getInt("ret")));

                jLabel14.setText(" "+rs1.getString("Salutation")+" ");
                jLabel50.setText(" "+rs1.getString("departmentname")+" ");
                jTextField56.setText(rs1.getString("emailname"));
                jLabel87.setText(" "+rs1.getString("CategoryID")+" ");
                rs1.close();
                s2.close();
                isuret=1;
                jTextField57.setEditable(true);
                   jTextField57.requestFocus();
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        }
                        else
                {
                    //JOptionPane.showMessageDialog(null,"Invalid BorrowerID222.!!!" );
                    jTextField9.setText("");
                    jTextField19.requestFocus();
                }
    }//GEN-LAST:event_jTextField9FocusGained

    private void jTextField64FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField64FocusGained
        // TODO add your handling code here:
        System.out.println("brfidcd gained");
    try{
           Statement s=con.createStatement();
           ResultSet rs=s.executeQuery("select accnum,catrefnum,CirculationStatusCD from circulation where rfidcd='"+jTextField64.getText().trim()+"'");
       if(rs.next())
       {
           jTextField57.setText(rs.getString("accnum"));
           jTextField20.setText(rs.getString("catrefnum"));
           if(rs.getString("CirculationStatusCD").equals("B"))
           {
              

    
    try
        {
            Statement s1=con.createStatement();
            String bukid=null;
            bukid=jTextField20.getText().trim();
            System.out.println(bukid);
            /*if(bukid.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Enter Book ID!!!" );
            }
            else
            {
                */
                ResultSet rs1=s1.executeQuery("select CardTitle,CardAuthor,PublicationYear from Card where CatRefNum='"+jTextField20.getText().trim()+"'");
                if(rs1.next())
                {
                    jLabel85.setText(rs1.getString("CardTitle"));
                    jLabel86.setText(" "+rs1.getString("CardAuthor")+" ");
                    jLabel88.setText(" "+rs1.getString("PublicationYear")+" ");
                    rs1.close();
                    s1.close();
                    
                    jButton10.setEnabled(true);
jButton10.requestFocus();
// i++;
                }
                else{
                   // JOptionPane.showMessageDialog(null,"Book ID is Invalid.!!!");
                    jTextField57.requestFocus();
                }
                //}
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
               }
           
           else{
               
           JOptionPane.showMessageDialog(null,"Book is already Issued !!!");
                  jTextField57.setEditable(true);
        jTextField20.setText("");

        jTextField56.setText("");
        jTextField57.setText("");
        //jTextField21.setText("");
 
        //.setText("");
    
        jLabel85.setText("");
        jLabel86.setText("");
   
        jLabel88.setText("");
 
        jTextField64.setText("");
  
        jButton10.setEnabled(false);
           }
       }
       else{
          jTextField64.setText("");
       }
           }
       catch(SQLException e)
       {
           System.out.println(e);
       }
    }//GEN-LAST:event_jTextField64FocusGained

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField64ActionPerformed

    private void jTextField19FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField19FocusGained
    }//GEN-LAST:event_jTextField19FocusGained

    private void jTextField59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField59ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField59ActionPerformed

    private void jTextField57FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField57FocusGained
    }//GEN-LAST:event_jTextField57FocusGained

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
       panelstatus=4;
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTextField10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusGained
        System.out.println("aaaaa");
        try{
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("select catrefnum from circulation where rfidcd='"+jTextField10.getText().trim()+"'");
            while(rs.next())
            {String cat=rs.getString("catrefnum").trim();
                System.out.println(cat);
                if(cat.equals(jTextField54.getText().trim()))
                {
                     new MP3Player(new File("src\\imgs\\bukfound.mp3")).play();
                    JOptionPane.showMessageDialog(null,"Book Found !!!");
                    //jTextField10.setText("");
                    //jButton7.requestFocus();
                    
                    
                }
                else{
                    jTextField10.setText("");
                }
                }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        jTextField10.setText("");
         jTextField10.requestFocus();
         jButton17.requestFocus();
    }//GEN-LAST:event_jTextField10FocusGained

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        
                int selection=JOptionPane.showConfirmDialog(null,"Are you really want to chang RFID TAG??","Confermation Alert",JOptionPane.OK_CANCEL_OPTION);
        if(selection==JOptionPane.OK_OPTION)
        {
            jTextField31.setText("");
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        
        int selection=JOptionPane.showConfirmDialog(null,"Are you really want to DELETE RFID TAG??","Confermation Alert",JOptionPane.OK_CANCEL_OPTION);
        if(selection==JOptionPane.OK_OPTION)
        {
        jTextField31.setText("");
        try
        {
            Statement s= con.createStatement();
            s.executeUpdate("update circulation set rfidcd=NULL where accnum='"+jTextField30.getText().trim()+"'");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        
          String accno;
        if(jTextField30.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter AccetionNumber !!!" );
            jTextField30.setEditable(true);
        }else{
        if(jTextField31.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Swipe RFID TAG !!!" );        
        }else{
        try
        {
        accno= jTextField30.getText().toUpperCase();
        System.out.println("..."+accno+"...1");
        Statement s1=con.createStatement();
        
        ResultSet rs=s1.executeQuery("select rfidcd from circulation where rfidcd='"+jTextField31.getText().trim()+"'");
        if(rs.next())
        {
            JOptionPane.showMessageDialog(null,"Duplicate TAG is NOT Allowed !!!" );        
        }

else{
            s1.executeUpdate("update circulation set rfidcd='"+jTextField31.getText().trim()+"' where accnum='"+accno+"'");
            JOptionPane.showMessageDialog(null,"RFID COde Is Updated Successfully !!!" );        

        }
        rs.close();
        s1.close();
                }
        catch(SQLException e){
            System.out.println(e);
        }
        }
        }
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        jTextField30.setText("");
        jTextField31.setText("");
        jTextField32.setText("");
        jTextField36.setText("");
        jTextField37.setText("");
        jTextField30.setEditable(true);
        
        
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTextField30FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField30FocusLost
        // TODO add your handling code here:
        
        //jTextField30.setEditable(false);
String accno,catrefnum = null;
        try
        {
        accno= jTextField30.getText();
        System.out.println("..."+accno+"...1");
        Statement s1=con.createStatement();
        Statement s2=con.createStatement();

        
        if(accno.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter AccetionNumber !!!" );  
            jTextField30.requestFocus(true);
        }
        else
        {
                ResultSet rs=s1.executeQuery("Select catrefnum,rfidcd from circulation where accnum='"+accno+"'");//+"Select top 2 CardTitle,cardauthor,publicationyear from Card where CatRefNum='"+bukid+"'" );
        
        if(rs.next())
        {
        //while(rs.next())
         //  jTextField46.setText(Integer.toString(rs1.getInt("iss")-rs2.getInt("ret")));
            
               catrefnum=rs.getString("catrefnum");
            jTextField31.setText(rs.getString("rfidcd"));
            /*if(jTextField2.getText()==null)
            {
                jTextField2.setEditable(true);
            } */  
        }
        else
        {
        JOptionPane.showMessageDialog(null,"Accetion Number Is Wrong.!!!" );
        jTextField30.setEditable(true);
        jTextField31.setFocusable(true);
        }
        
         ResultSet rs2=s2.executeQuery("select * from card where catrefnum='"+catrefnum+"'");
        if(rs2.next())
               {
                   jTextField36.setText(rs2.getString("cardauthor"));
                   jTextField32.setText(rs2.getString("cardtitle"));
                   jTextField37.setText(rs2.getString("publicationyear"));
                   
               }

        


        s2.close();
        rs2.close();

             
                s1.close();
                rs.close();
        }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        } 
        
    }//GEN-LAST:event_jTextField30FocusLost

    private void jTextField39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField39ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        
                int selection=JOptionPane.showConfirmDialog(null,"Are you really want to change RFID TAG??","Confermation Alert",JOptionPane.OK_CANCEL_OPTION);
        if(selection==JOptionPane.OK_OPTION)
        {
            jTextField39.setText("");
        }
        
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        
          
        String accno;
        if(jTextField38.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter BorrowerID !!!" );
            jTextField38.setEditable(true);
        }else{
        if(jTextField39.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Swipe RFID TAG !!!" );        
        }else{
        try
        {
        accno= jTextField38.getText().toUpperCase();
        System.out.println("..."+accno+"...1");
        Statement s1=con.createStatement();
        
        ResultSet rs=s1.executeQuery("select srfidcd from borrowers where srfidcd='"+jTextField39.getText().trim()+"'");
        if(rs.next())
        {
            JOptionPane.showMessageDialog(null,"Duplicate TAG is NOT Allowed !!!" );        
        }

else{
            s1.executeUpdate("update borrowers set srfidcd='"+jTextField39.getText().trim()+"' where borrowerid='"+accno+"'");
            JOptionPane.showMessageDialog(null,"RFID COde Is Updated Successfully !!!" );        

        }
        rs.close();
        s1.close();
                }
        catch(SQLException e){
            System.out.println(e);
        }
        }
        }
        
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        
        int selection=JOptionPane.showConfirmDialog(null,"Are you really want to DELETE RFID TAG??","Confermation Alert",JOptionPane.OK_CANCEL_OPTION);
        if(selection==JOptionPane.OK_OPTION)
        {
        jTextField39.setText("");
        try{
            Statement s= con.createStatement();
            s.executeUpdate("update borrowers set srfidcd=NULL where borrowerid='"+jTextField38.getText().trim()+"'");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        }
        
        
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        
                jTextField38.setText("");
        jTextField39.setText("");
        jTextField40.setText("");
        jTextField41.setText("");
        jTextField67.setText("");
        jTextField38.setEditable(true);
        
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jTextField38FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField38FocusLost
        // TODO add your handling code here:
        
        jTextField38.setEditable(false);
String accno,catrefnum = null;
        try
        {
        accno= jTextField38.getText().trim();
        System.out.println("..."+accno+"...1");
        Statement s1=con.createStatement();
        Statement s2=con.createStatement();

        
        if(accno.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter BorrowerID !!!" );  
            jTextField38.setEditable(true);
        }
        else
        {
                ResultSet rs=s1.executeQuery("Select borrowerid,srfidcd from borrowers where borrowerid='"+accno+"'");
        
        if(rs.next())
        {
        //while(rs.next())
         //  jTextField46.setText(Integer.toString(rs1.getInt("iss")-rs2.getInt("ret")));
            

            jTextField39.setText(rs.getString("srfidcd"));
            /*if(jTextField2.getText()==null)
            {
                jTextField2.setEditable(true);
            } */  
        }
        else
        {
        JOptionPane.showMessageDialog(null,"BorrowerID Is Wrong.!!!" );
        jTextField38.setEditable(true);
        jTextField38.setFocusable(true);
        }
      //  rs.close();
        //s1.close();
         ResultSet rs2=s2.executeQuery("select * from borrowers where borrowerid='"+jTextField38.getText().trim()+"'");
        if(rs2.next())
               {
                   jTextField40.setText(rs2.getString("salutation"));
                   jTextField41.setText(rs2.getString("Departmentname"));
                   jTextField67.setText(rs2.getString("categoryid"));         
               }
        rs2.close();
        s2.close();

             
             
               rs.close();   
               s1.close();
        }
        }
        catch(SQLException e)
        {
            System.out.println("..."+e);
        }
        
    }//GEN-LAST:event_jTextField38FocusLost

    private void jTextField68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField68ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField68ActionPerformed

    private void jTextField69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField69ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField69ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        
                 try
        {
            Statement s=con.createStatement();
             ResultSet rs1=null;
             int ld;
             String lnm="";
            
            
            /*
            if(1==jComboBox1.getSelectedIndex())
            {
                lnm="FE";
                 rs1 =s.executeQuery("Select min(LoanDays) as ldays from CircRules where categoryid='"+lnm+"'");
            
            }
            if(2==jComboBox1.getSelectedIndex())
            {
                lnm="SE";
                 rs1 =s.executeQuery("Select min(LoanDays) as ldays from CircRules where categoryid='"+lnm+"'");
            
            }
            if(3==jComboBox1.getSelectedIndex())
            {
                lnm="TE";
                 rs1 =s.executeQuery("Select min(LoanDays) as ldays from CircRules where categoryid='"+lnm+"'");
            
            }
            if(4==jComboBox1.getSelectedIndex())
            {
                lnm="BE";
                 rs1 =s.executeQuery("Select min(LoanDays) as ldays from CircRules where categoryid='"+lnm+"'");
            
            }
            if(5==jComboBox1.getSelectedIndex())
            {
                lnm="IN";
                 rs1 =s.executeQuery("Select min(LoanDays) as ldays from CircRules where categoryid='"+lnm+"'");
            
            }
            if(6==jComboBox1.getSelectedIndex())
            {
                lnm="POL";
                 rs1 =s.executeQuery("Select min(LoanDays) as ldays from CircRules where categoryid='"+lnm+"'");
            
            }
            if(7==jComboBox1.getSelectedIndex())
            {
                lnm="NTS";
                 rs1 =s.executeQuery("Select min(LoanDays) as ldays from CircRules where categoryid='"+lnm+"'");
            
            }if(8==jComboBox1.getSelectedIndex())
            {
                lnm="HOD";
                 rs1 =s.executeQuery("Select min(LoanDays) as ldays from CircRules where categoryid='"+lnm+"'");
            
            }if(9==jComboBox1.getSelectedIndex())
            {
                lnm="PG";
                 rs1 =s.executeQuery("Select min(LoanDays) as ldays from CircRules where categoryid='"+lnm+"'");
            
            }
            */
             
            rs1=s.executeQuery("select loandays,finerate from circrules where categoryid='"+jComboBox3.getSelectedItem()+"' and circcode='01'");
            System.out.println(jComboBox3.getSelectedItem());
            while(rs1.next())
            {
                ld=rs1.getInt("loandays");
                 jTextField68.setText(Integer.toString(ld));
                 jTextField69.setText(rs1.getString("finerate"));
             }
            
           
                 rs1.close();
            s.close();
            
        }
        catch(SQLException e)
        {
            
            System.out.println("...."+e);
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        
          
    try
        {

            Statement s=con.createStatement();
            // ResultSet rs1=null;
             
             int lf1;
             //lds=Integer.parseInt(jTextField1.getText());
             lf1=Integer.parseInt(jTextField68.getText());
             System.out.println(lf1);
            // int i=jComboBox1.getSelectedIndex();
             String tlds="";
   
            
            if(1==jComboBox3.getSelectedIndex())
            {
                tlds="FE";
                 s.executeUpdate("update circrules set LoanDays='"+lf1+"' where CategoryID='"+tlds+"' and circcode='01'");
                
            }
           if(2==jComboBox3.getSelectedIndex())
            {
                tlds="SE";
                 s.executeUpdate("update circrules set LoanDays='"+lf1+"' where CategoryID='"+tlds+"' and circcode='01'");
            
            }
            if(3==jComboBox3.getSelectedIndex())
            {
                tlds="TE";
                 s.executeUpdate("update circrules set LoanDays='"+lf1+"' where CategoryID='"+tlds+"' and circcode='01'");
            
            }
            if(4==jComboBox3.getSelectedIndex())
            {
                tlds="BE";
                s.executeUpdate("update circrules set LoanDays='"+lf1+"' where CategoryID='"+tlds+"' and circcode='01'");
            
            }
            if(5==jComboBox3.getSelectedIndex())
            {
                tlds="IN";
                 s.executeUpdate("update circrules set LoanDays='"+lf1+"' where CategoryID='"+tlds+"' and circcode='01'");
            }
            if(6==jComboBox3.getSelectedIndex())
            {
                tlds="POL";
               s.executeUpdate("update circrules set LoanDays='"+lf1+"' where CategoryID='"+tlds+"'and circcode='01'");
            
            }
            if(7==jComboBox3.getSelectedIndex())
            {
                tlds="NTS";
               s.executeUpdate("update circrules set LoanDays='"+lf1+"' where CategoryID='"+tlds+"' and circcode='01'");
            
            }if(8==jComboBox3.getSelectedIndex())
            {
                tlds="HOD";
                 s.executeUpdate("update circrules set LoanDays='"+lf1+"' where CategoryID='"+tlds+"' and circcode='01'");
            
            }if(9==jComboBox3.getSelectedIndex())
            {
                tlds="PG";
                  s.executeUpdate("update circrules set LoanDays='"+lf1+"' where CategoryID='"+tlds+"' and circcode='01'");
            
            }
             JOptionPane.showMessageDialog(null,"LoanDays Updated Sucessfully...!!!");
           
           // rs1.close();
            s.close();
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
        
        
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jTextField38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField38ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jTextField70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField70ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField70ActionPerformed

    private void jTextField79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField79ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField79ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        
                int avail=0;//DateAndTime duedate;
        if(jTextField71.getText().isEmpty())
        {
            jTextField71.setEnabled(false);
            try{
                Statement s=con.createStatement();
                ResultSet rs=s.executeQuery("select * from Circulation where accnum='"+jTextField70.getText().trim()+"' and CirculationStatusCD='C'");
                if(rs.next())
                {
                    jTextField71.setText(rs.getString("rfidcd"));
                    avail=1;
                    System.out.println("avil...");
                }
                rs.close();
                s.close();
            }
            catch(SQLException e)
            {System.out.println("catch 1...");
                System.out.println(e);
            }
        }
        else{
            jTextField70.setEnabled(false);
            try{
                Statement s=con.createStatement();
                ResultSet rs=s.executeQuery("select * from Circulation where rfidcd='"+jTextField71.getText().trim()+"' and CirculationStatusCD='C'");
                if(rs.next())
                {
                    jTextField70.setText(rs.getString("accnum"));
                    avail=1;
                    System.out.println("avil...");
                }
                rs.close();
                s.close();
            }
            catch(SQLException e)
            {System.out.println("catch 1...");
                System.out.println(e);
            }
        }

        if(avail==1)
        {
            try
            {System.out.println("try2...");
                int flg=0;

                Statement s=con.createStatement();
                String btid=jTextField70.getText();
                //String booknm=null;
                // String studnm=null;
                ResultSet rs,rs1,rs2;
                System.out.println(".."+btid+"..");
                rs =s.executeQuery("select borrowerid,catrefnum,entrydatetime,duedate from circtransactions where accnum='"+btid+"' and TranType='IS' order  by EntryDateTime desc ");

                if(rs.next())
                {
                    System.out.println("rs whil...");
                    String studid=rs.getString("borrowerid");
                    String bookid=rs.getString("catrefnum");
                    jTextField71.setText(bookid);
                    jTextField73.setText(studid);
                    jTextField75.setText(rs.getString("entrydatetime"));
                    //  duedate=rs.getDate("duedate");
                    jTextField79.setText(rs.getString("duedate"));
                    //jTextField61.setText(String.valueOf(duedate));

                    rs1 =s.executeQuery("select salutation from Borrowers where Borrowerid='"+studid+"' ");

                    while(rs1.next())
                    {System.out.println("rs1 while...");
                        flg=1;
                        jTextField74.setText(" "+rs1.getString("salutation")+" ");
                    }

                    rs2 =s.executeQuery("select cardtitle from card where catrefnum='"+bookid+"' ");
                    while(rs2.next())
                    {System.out.println("rs2 while...");
                        //flg=1;
                        jTextField78.setText(" "+rs2.getString("cardtitle")+" ");
                    }
                    rs1.close();
                    rs2.close();
                }
                if(flg==0)
                {
                    JOptionPane.showMessageDialog(null,"Record not found");
                }

                rs.close();
                s.close();

                //Calendar cal=Calendar.getInstance();

            }
            catch(SQLException e)
            {System.out.println("catch 2...");
                System.out.println(e);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Record not found");

        }

        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");

        Date curdt1=new Date();
        Date duedt1=new Date();
        try{
            System.out.println("try 3 date wala...");
            String duedt=jTextField79.getText();
            String curdt=jTextField21.getText();

            duedt1=df.parse(duedt);
            // duedt1=df.parse(duedt);
            curdt1=df.parse(curdt);

            // curdt1=df.parse(curdt);

            long dyz=curdt1.getTime()-duedt1.getTime();

            System.out.println(dyz);
            int dat=(int)((dyz)/(1000*24*60*60));
            System.out.println(dat);
            jTextField76.setText(Integer.toString(dat));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jTextField70FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField70FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField70FocusLost

    private void jTextField71FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField71FocusLost
        // TODO add your handling code here:
        
      
    }//GEN-LAST:event_jTextField71FocusLost

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        
        jTextField70.setText("");
        jTextField71.setText("");
        jTextField74.setText("");
        jTextField77.setText("");
        jTextField79.setText("");
        jTextField76.setText("");
        jTextField72.setText("");
        jTextField73.setText("");
        jTextField75.setText("");
        jTextField78.setText("");
        jTextField80.setText("");
        jTextField70.setEditable(true);
        jTextField70.requestFocus();
        jButton31.setEnabled(false);

        
        
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jPanel14ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel14ComponentShown
            panelstatus=5;
    }//GEN-LAST:event_jPanel14ComponentShown

    private void jPanel15ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel15ComponentShown
            panelstatus=6;
    }//GEN-LAST:event_jPanel15ComponentShown

    private void jPanel16ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel16ComponentShown
    }//GEN-LAST:event_jPanel16ComponentShown

    private void jPanel17ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel17ComponentShown
            panelstatus=7;
    }//GEN-LAST:event_jPanel17ComponentShown

    private void jTextField71FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField71FocusGained
        // TODO add your handling code here:   int avail=0;
        String btid="",studid="",bookid="",catid="";
        int avail=0;
        //jTextField65.setEnabled(false);


        try{
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("select * from Circulation where rfidcd='"+jTextField71.getText().trim()+"' and CirculationStatusCD='C'");
            if(rs.next())
            {
                jTextField70.setText(rs.getString("accnum"));
                avail=1;
                System.out.println("avil...");
                                jButton31.setEnabled(true);
            }
            rs.close();
            s.close();
        }
        catch(SQLException e)
        {System.out.println("catch 1...");
            System.out.println(e);
        }

        if(avail==1)
        {
            try
            {System.out.println("try2...");
                int flg=0;

                Statement s=con.createStatement();
                btid=jTextField70.getText();
                //String booknm=null;
                // String studnm=null;
                ResultSet rs3,rs1,rs2;
                System.out.println(".."+btid+"..");
                rs3 =s.executeQuery("select borrowerid,catrefnum,entrydatetime,duedate from circtransactions where accnum='"+btid+"' and TranType='IS' order  by EntryDateTime desc ");

                if(rs3.next())
                {
                    System.out.println("rs whil...");
                    studid=rs3.getString("borrowerid");
                    bookid=rs3.getString("catrefnum");
                        jTextField72.setText(bookid);
                    jTextField73.setText(studid);
                    jTextField75.setText(rs3.getString("entrydatetime"));
                    //  duedate=rs.getDate("duedate");
                    jTextField79.setText(rs3.getString("duedate"));
                    //jTextField61.setText(String.valueOf(duedate));
                }
                rs3.close();
                rs1 =s.executeQuery("select salutation,emailname,categoryid from Borrowers where Borrowerid='"+studid+"' ");

                while(rs1.next())
                {System.out.println("rs1 while...");
                    flg=1;
                    jTextField74.setText(" "+rs1.getString("salutation")+" ");
                    jTextField77.setText(rs1.getString("emailname"));
                    catid=rs1.getString("categoryid");
                }
                rs1.close();
                rs2 =s.executeQuery("select cardtitle from card where catrefnum='"+bookid+"' ");
                while(rs2.next())
                {System.out.println("rs2 while...");
                    //flg=1;
                    jTextField78.setText(" "+rs2.getString("cardtitle")+" ");
                           jTextField70.setEditable(false);
        jButton31.requestFocus();
        jLabel110.setText("OK!!! You can go outside Library....");
        jLabel110.setForeground(Color.GREEN);
                }
                rs2.close();
                s.close();

                //Calendar cal=Calendar.getInstance();

            }
            catch(SQLException e)
            {System.out.println("catch.. 2...");
                System.out.println(e);
            }
        }
        else
        {
            //JOptionPane.showMessageDialog(null,"Record not found");
            jTextField70.setText("");
        jTextField71.setText("");
        jTextField74.setText("");
        jTextField77.setText("");
        jTextField79.setText("");
        jTextField76.setText("");
        jTextField72.setText("");
        jTextField73.setText("");
        jTextField75.setText("");
        jTextField78.setText("");
        jTextField80.setText("");
        jTextField70.setEditable(true);
        jTextField70.requestFocus();
        //jButton31.setEnabled(false);
        jLabel110.setText("Warning : You will Not allowed to go outside of Libarary");
        jLabel110.setForeground(Color.RED);
            jButton32.requestFocus();
        }

        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");

        Date curdt1=new Date();
        Date duedt1=new Date();
        try{
            System.out.println("try 3 date wala...");
            String duedt=jTextField79.getText();
            String curdt=jTextField21.getText();

            duedt1=df.parse(duedt);
            // duedt1=df.parse(duedt);
            curdt1=df.parse(curdt);

            // curdt1=df.parse(curdt);

            long dyz=curdt1.getTime()-duedt1.getTime();

            System.out.println(dyz);
            int dat=(int)((dyz)/(1000*24*60*60));
            System.out.println(dat);
            jTextField76.setText(Integer.toString(dat));
            if(jTextField76.getText().startsWith("-"))
            {
                jTextField76.setText("0");
                jTextField80.setText("0");
            }
            else{
                //****calculate fine****
                jTextField76.setText(Integer.toString(dat));
                try{
                    Statement s=con.createStatement();
                    ResultSet rs=s.executeQuery("select loandays,finerate from circrules where categoryid='"+catid+"'");
                    if(rs.next())
                    {
                        jTextField80.setText(Integer.toString(rs.getInt("finerate")*dat));
                    }
                    rs.close();
                    s.close();
                }
                catch(Exception e)
                {
                    System.out.print(e);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jTextField71FocusGained

    private void jTextField45FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField45FocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField45FocusGained

    private void jTabbedPane4ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane4ComponentShown
        // TODO add your handling code here:
        panelstatus=3;
    }//GEN-LAST:event_jTabbedPane4ComponentShown

    private void jPanel3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentShown
        // TODO add your handling code here:
        panelstatus=3;
    }//GEN-LAST:event_jPanel3ComponentShown

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_jTextField11KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField26KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField26KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField26KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isLetter(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField24KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField24KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isLetter(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField24KeyTyped

    private void jTextField25KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField25KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isLetter(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField25KeyTyped

    private void jTextField81KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField81KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField81KeyTyped

    private void jTextField17KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isLetter(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField17KeyTyped

    private void jTextField16KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(!(Character.isLetter(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField16KeyTyped

    private void jTextField18KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyTyped
        // TODO add your handling code here:
         char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField18KeyTyped

    private void jTextField43KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField43KeyTyped
        // TODO add your handling code here:
         char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==KeyEvent.VK_BACK_SLASH)||(c==KeyEvent.VK_STOP)))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField43KeyTyped

    private void jPanel11ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel11ComponentShown
        // TODO add your handling code here:
        panelstatus=1;
    }//GEN-LAST:event_jPanel11ComponentShown

    private void jTabbedPane5ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane5ComponentShown
        // TODO add your handling code here:
        panelstatus=1;
    }//GEN-LAST:event_jTabbedPane5ComponentShown

    private void jPanel8ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel8ComponentShown
        // TODO add your handling code here:
        panelstatus=5;
    }//GEN-LAST:event_jPanel8ComponentShown

    private void jTabbedPane6ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane6ComponentShown
        // TODO add your handling code here:
        panelstatus=5;
    }//GEN-LAST:event_jTabbedPane6ComponentShown

    private void jTextField83FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField83FocusGained
        // TODO add your handling code here:
        String btid="",bookid="";
        int avail=0;
        //jTextField65.setEnabled(false);


        try{
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("select * from Circulation where rfidcd='"+jTextField83.getText().trim()+"'");
            if(rs.next())
            {
                jTextField82.setText(rs.getString("accnum"));
                avail=1;
                System.out.println("avil...");
                             jButton31.setEnabled(true);
            }
            rs.close();
            s.close();
        }
        catch(SQLException e)
        {System.out.println("catch 1...");
            System.out.println(e);
        }

        if(avail==1)
        {
            try
            {System.out.println("try2...");
                int flg=0;

                Statement s=con.createStatement();
                btid=jTextField82.getText();
                //String booknm=null;
                // String studnm=null;
                ResultSet rs3,rs1,rs2;
                System.out.println(".."+btid+"..");
                rs3 =s.executeQuery("select catrefnum from circtransactions where accnum='"+btid+"'");

                if(rs3.next())
                {
                    System.out.println("rs whil...");
                    //studid=rs3.getString("borrowerid");
                    bookid=rs3.getString("catrefnum");
                        jTextField84.setText(bookid);
                    //jTextField73.setText(studid);
                    //jTextField75.setText(rs3.getString("entrydatetime"));
                    //  duedate=rs.getDate("duedate");
                    //jTextField79.setText(rs3.getString("duedate"));
                    //jTextField61.setText(String.valueOf(duedate));
                }
                rs3.close();
                /*rs1 =s.executeQuery("select salutation,emailname,categoryid from Borrowers where Borrowerid='"+studid+"' ");

                while(rs1.next())
                {System.out.println("rs1 while...");
                    flg=1;
                    jTextField74.setText(" "+rs1.getString("salutation")+" ");
                    jTextField77.setText(rs1.getString("emailname"));
                    catid=rs1.getString("categoryid");
                }
                rs1.close();*/
                rs2 =s.executeQuery("select cardtitle,CardAuthor,PublicationYear from card where catrefnum='"+bookid+"' ");
                while(rs2.next())
                {System.out.println("rs2 while...");
                    //flg=1;
                    jTextField85.setText(" "+rs2.getString("cardtitle")+" ");
                           jTextField85.setEditable(false);
                              jTextField86.setText(" "+rs2.getString("CardAuthor")+" ");
                           jTextField86.setEditable(false);
                              jTextField87.setText(" "+rs2.getString("PublicationYear")+" ");
                           jTextField87.setEditable(false);
        jButton31.requestFocus();
       // jLabel110.setText("OK!!! You can go outside Library....");
        //jLabel110.setForeground(Color.GREEN);
                }
                rs2.close();
                s.close();

                //Calendar cal=Calendar.getInstance();

            }
            catch(SQLException e)
            {System.out.println("catch.. 2...");
                System.out.println(e);
            }
        }
        else
        {
            //JOptionPane.showMessageDialog(null,"Record not found");
            jTextField82.setText("");
        jTextField83.setText("");
        jTextField84.setText("");
        jTextField85.setText("");
        jTextField86.setText("");
        jTextField87.setText("");
        jTextField82.setEditable(true);
        jTextField82.requestFocus();
        
       //jTextField83.requestFocus();
        //jButton34.setEnabled(false);
        //jLabel110.setText("Warning : You will Not allowed to go outside of Libarary");
       // jLabel110.setForeground(Color.RED);
       jButton33.requestFocus();
        }

       
    }//GEN-LAST:event_jTextField83FocusGained

    private void jPanel18ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel18ComponentShown
        // TODO add your handling code here:
        panelstatus=8;
    }//GEN-LAST:event_jPanel18ComponentShown

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
      
        jTextField82.setText("");
        jTextField83.setText("");
        jTextField84.setText("");
        jTextField85.setText("");
        jTextField86.setText("");
        jTextField87.setText("");

    }//GEN-LAST:event_jButton33ActionPerformed

    private void jTextField83FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField83FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField83FocusLost

    private void jTabbedPane7ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane7ComponentShown
        // TODO add your handling code here:
          panelstatus=9;
    }//GEN-LAST:event_jTabbedPane7ComponentShown

    private void jPanel19ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel19ComponentShown
        // TODO add your handling code here:
        
         panelstatus=8;
         jTextField82.requestFocus();
    }//GEN-LAST:event_jPanel19ComponentShown

    private void jTextField87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField87ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField87ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
         jTextField88.setText("");
        jTextField89.setText("");
        jTextField90.setText("");
        jTextField91.setText("");
        jTextField92.setText("");
        jTextField93.setText("");
        jTextField94.setText("");
        
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jPanel20ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel20ComponentShown
        // TODO add your handling code here:
        panelstatus=9;
        jTextField88.requestFocus();
    }//GEN-LAST:event_jPanel20ComponentShown

    private void jTextField89FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField89FocusGained
        // TODO add your handling code here:
        
                String btid="",bookid="";
        int avail=0;
        //jTextField65.setEnabled(false);


        try{
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("select * from Borrowers where srfidcd='"+jTextField89.getText().trim()+"'");
            if(rs.next())
            {
                jTextField88.setText(rs.getString("BorrowerID"));
                avail=1;
                System.out.println("avil...");
                            // jButton34.setEnabled(true);
            }
            rs.close();
            s.close();
        }
        catch(SQLException e)
        {System.out.println("catch 1...");
            System.out.println(e);
        }

        if(avail==1)
        {
            try
            {System.out.println("try2...");
                int flg=0;

                Statement s=con.createStatement();
                btid=jTextField88.getText();
                //String booknm=null;
                // String studnm=null;
                ResultSet rs3,rs1,rs2;
                System.out.println(".."+btid+"..");
                
                rs2 =s.executeQuery("Select BorrowerID,Salutation,CategoryID,DepartmentName,EmailName,MobileNumber from Borrowers where BorrowerID='"+btid+"' ");
                while(rs2.next())
                {System.out.println("rs2 while...");
                    //flg=1;
                    jTextField90.setText(" "+rs2.getString("Salutation")+" ");
                           jTextField90.setEditable(false);
                           jTextField92.setText(" "+rs2.getString("CategoryID")+" ");
                           jTextField92.setEditable(false);
                              jTextField91.setText(" "+rs2.getString("DepartmentName")+" ");
                           jTextField91.setEditable(false);
                              
                           jTextField93.setText(" "+rs2.getString("EmailName")+" ");
                           jTextField93.setEditable(false);
                           jTextField94.setText(" "+rs2.getString("MobileNumber")+" ");
                           jTextField94.setEditable(false);
//jButton34.requestFocus();
       // jLabel110.setText("OK!!! You can go outside Library....");
        //jLabel110.setForeground(Color.GREEN);
                }
                rs2.close();
                s.close();

                //Calendar cal=Caendar.getInstance();

            }
            catch(SQLException e)
            {System.out.println("catch.. 2...");
                System.out.println(e);
            }
        }
        else
        {
            //JOptionPane.showMessageDialog(null,"Record not found");
            jTextField88.setText("");
        jTextField89.setText("");
        jTextField90.setText("");
        jTextField91.setText("");
        jTextField92.setText("");
        jTextField93.setText("");
        jTextField94.setText("");
        jTextField88.setEditable(true);
        jTextField88.requestFocus();
        
       //jTextField83.requestFocus();
        //jButton34.setEnabled(false);
        //jLabel110.setText("Warning : You will Not allowed to go outside of Libarary");
       // jLabel110.setForeground(Color.RED);
       jButton34.requestFocus();
        }

        
    }//GEN-LAST:event_jTextField89FocusGained

    private void jTextField84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField84ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField84ActionPerformed

    private void jTextField83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField83ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField83ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainpg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainpg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainpg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainpg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DateFormat df=new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date date=new Date();
        Calendar cal=Calendar.getInstance();
      //  jTextField21.setText(df.format(cal.getTime()));
                new mainpg();//.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    public static javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    public static javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    public static javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    public static javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField68;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField70;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField72;
    private javax.swing.JTextField jTextField73;
    private javax.swing.JTextField jTextField74;
    private javax.swing.JTextField jTextField75;
    private javax.swing.JTextField jTextField76;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField78;
    private javax.swing.JTextField jTextField79;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField80;
    private javax.swing.JTextField jTextField81;
    private javax.swing.JTextField jTextField82;
    private javax.swing.JTextField jTextField83;
    private javax.swing.JTextField jTextField84;
    private javax.swing.JTextField jTextField85;
    private javax.swing.JTextField jTextField86;
    private javax.swing.JTextField jTextField87;
    private javax.swing.JTextField jTextField88;
    private javax.swing.JTextField jTextField89;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextField90;
    private javax.swing.JTextField jTextField91;
    private javax.swing.JTextField jTextField92;
    private javax.swing.JTextField jTextField93;
    private javax.swing.JTextField jTextField94;
    // End of variables declaration//GEN-END:variables
}
