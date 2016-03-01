/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfid;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.concurrent.ExecutionException;
import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.swing.JOptionPane;


/**
 *
 * @author Pratik
 */
public class tagingsys extends javax.swing.JFrame implements SerialPortEventListener {
    Connection con;
    /**
     * Creates new form tagingsys
     */
    public tagingsys() {
        initComponents();
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
                           if(jTextField2.getText().isEmpty())
                           jTextField2.setText(ss);
                            
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
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Vijaya", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 51, 0));
        jLabel1.setText("RFID Tagging System");

        jLabel2.setText("Accession Number:");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField1.setNextFocusableComponent(jTextField2);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        jLabel3.setText("RFID Tag Number:");

        jTextField2.setEditable(false);

        jLabel5.setText("Book Name :");

        jLabel6.setText("Auther :");

        jLabel7.setText("Publication Year :");

        jButton1.setText("SAVE RFID");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete RFID");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(396, 396, 396))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(448, 448, 448)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel2)
                        .addGap(61, 61, 61)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(49, 49, 49)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addComponent(jButton2)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jButton3)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        jLabel2.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:
        jTextField1.setEditable(false);
String accno,catrefnum = null;
        try
        {
        accno= jTextField1.getText();
        System.out.println("..."+accno+"...1");
        Statement s1=con.createStatement();
        Statement s2=con.createStatement();

        
        if(accno.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter AccetionNumber !!!" );  
            jTextField1.setEditable(true);
        }
        else
        {
                ResultSet rs=s1.executeQuery("Select catrefnum,rfidcd from circulation where accnum='"+accno+"'");//+"Select top 2 CardTitle,cardauthor,publicationyear from Card where CatRefNum='"+bukid+"'" );
        
        if(rs.next())
        {
        //while(rs.next())
         //  jTextField46.setText(Integer.toString(rs1.getInt("iss")-rs2.getInt("ret")));
            
               catrefnum=rs.getString("catrefnum");
            jTextField2.setText(rs.getString("rfidcd"));
            /*if(jTextField2.getText()==null)
            {
                jTextField2.setEditable(true);
            } */  
        }
        else
        {
        JOptionPane.showMessageDialog(null,"Accetion Number Is Wrong.!!!" );
        jTextField1.setEditable(true);
        jTextField1.setFocusable(true);
        }
        
         ResultSet rs2=s2.executeQuery("select * from card where catrefnum='"+catrefnum+"'");
        if(rs2.next())
               {
                   jTextField4.setText(rs2.getString("cardauthor"));
                   jTextField3.setText(rs2.getString("cardtitle"));
                   jTextField5.setText(rs2.getString("publicationyear"));
                   
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
    }//GEN-LAST:event_jTextField1FocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int selection=JOptionPane.showConfirmDialog(null,"Are you really want to chang RFID TAG??","Confermation Alert",JOptionPane.OK_CANCEL_OPTION);
        if(selection==JOptionPane.OK_OPTION)
        {
            jTextField2.setText("");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  
        String accno;
        if(jTextField1.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter AccetionNumber !!!" );
            jTextField1.setEditable(true);
        }else{
        if(jTextField2.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Swipe RFID TAG !!!" );        
        }else{
        try
        {
        accno= jTextField1.getText().toUpperCase();
        System.out.println("..."+accno+"...1");
        Statement s1=con.createStatement();
        
        ResultSet rs=s1.executeQuery("select rfidcd from circulation where rfidcd='"+jTextField2.getText().trim()+"'");
        if(rs.next())
        {
            JOptionPane.showMessageDialog(null,"Duplicate TAG is NOT Allowed !!!" );        
        }

else{
            s1.executeUpdate("update circulation set rfidcd='"+jTextField2.getText().trim()+"' where accnum='"+accno+"'");
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
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField1.setEditable(true);
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         int selection=JOptionPane.showConfirmDialog(null,"Are you really want to DELETE RFID TAG??","Confermation Alert",JOptionPane.OK_CANCEL_OPTION);
        if(selection==JOptionPane.OK_OPTION)
        {
        jTextField2.setText("");
        try{
            Statement s= con.createStatement();
            s.executeUpdate("update circulation set rfidcd=NULL where accnum='"+jTextField1.getText().trim()+"'");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(tagingsys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tagingsys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tagingsys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tagingsys.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tagingsys().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
