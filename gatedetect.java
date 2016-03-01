/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfid;
import com.sun.jndi.toolkit.ctx.Continuation;
import com.sun.org.apache.bcel.internal.Constants;
import jaco.mp3.player.MP3Player;
import java.awt.event.InputMethodEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
 import java.util.Properties;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.table.DefaultTableModel;
import com.sun.media.sound.PortMixerProvider;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.xerces.internal.util.XMLChar;
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

/**
 *
 * @author Pratik
 */
public class gatedetect extends javax.swing.JFrame  implements SerialPortEventListener{

    /**
     * Creates new form gatedetect
     */
    public gatedetect() {
        initComponents();
                   
        try
        {
            con=DriverManager.getConnection("jdbc:odbc:rfid","sa","123456");
            System.out.println("Connection to the database is done.");
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
       RecieveData();
       
    }

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
                            
                           jTextField1.setText(ss);
                           //jTextField64.setText(ss);
                           jTextField1.requestFocus();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Gate Detection System");

        jButton1.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 51));
        jButton1.setText("Stop Alarm");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText("jTextField2");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Accession No", "Book ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

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
        jScrollPane1.setViewportView(jTable2);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Issued  Books :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 255));
        jLabel3.setText("Theft Books :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(287, 287, 287))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(157, 157, 157)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel1)
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(126, 126, 126))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
Connection con;
    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:
        System.out.print("aala ithe.......");
        String rfid=jTextField1.getText().trim();
                
 if(!rfid.isEmpty())
 {
        System.out.println("..."+rfid);
        try{
        Statement s1=con.createStatement();
    
           ResultSet rs=s1.executeQuery("select AccNum,CatRefNum,circulationstatuscd  from circulation where rfidcd='"+rfid+"'");
           System.out.println("..."+rfid);
           while(rs.next())
           {
                    DefaultTableModel m,m2;
        m=(DefaultTableModel) jTable1.getModel();
        //m2=(DefaultTableModel) jTable2.getModel();
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
                jTable1.setFillsViewportHeight(true);
                // JScrollPane scroll = new JScrollPane(table);
                jScrollPane1.setHorizontalScrollBarPolicy(
                    jScrollPane1.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jScrollPane1.setVerticalScrollBarPolicy(
                    jScrollPane1.VERTICAL_SCROLLBAR_AS_NEEDED);
                
                
                m2=(DefaultTableModel) jTable2.getModel();
        jTable2.setAutoResizeMode(jTable2.AUTO_RESIZE_ALL_COLUMNS);
                jTable2.setFillsViewportHeight(true);
                // JScrollPane scroll = new JScrollPane(table);
                jScrollPane2.setHorizontalScrollBarPolicy(
                    jScrollPane1.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jScrollPane1.setVerticalScrollBarPolicy(
                    jScrollPane1.VERTICAL_SCROLLBAR_AS_NEEDED);

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
                jTable1.setModel(m);

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
           //i=rs.next();   
               
            

            } 
           
    jButton1.requestFocus();
            rs.close();
        s1.close();
        }
       
        catch(SQLException e)
        {
            System.out.println(e);
        }
        jTextField1.setText("");
            /*DefaultListModel listmodel = new DefaultListModel();
            listmodel.addElement(rfid);
        jList1= new JList(listmodel);*/
     //jButton1.setFocusCycleRoot(true);
        //jTextField1.transferFocusBackward();
        
       
 }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1ActionPerformed

    


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
            java.util.logging.Logger.getLogger(gatedetect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gatedetect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gatedetect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gatedetect.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gatedetect().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
