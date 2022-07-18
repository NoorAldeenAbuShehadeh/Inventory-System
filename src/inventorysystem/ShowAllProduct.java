/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Noor Aldeen Muneer
 */
public class ShowAllProduct extends javax.swing.JFrame {

    /**
     * Creates new form ShowAllProduct
     */
    String Method,Server,LAT;
    public ShowAllProduct(String method,String server,String lat) {
        initComponents();
        this.Method=method;
        this.Server=server;
        this.LAT=lat;
        if(server.equals("PHP Server")){
            if(this.Method.equals("GET"))
                sendData_GET("http://localhost/inventorysystem/PHP_Server.php");
            else 
                sendData_POST("http://localhost/inventorysystem/PHP_Server.php");
        }
        else{
            if(this.Method.equals("GET"))
                sendData_GET("http://localhost:8085/InventorySystemServlet/Server");
            else 
                sendData_POST("http://localhost:8085/InventorySystemServlet/Server");
        }
        jTable1.setRowHeight(40);
        jTable1.setShowGrid(true);
        jTable1.setGridColor(Color.BLACK);
        jTable1.setBackground(Color.WHITE);
        jTable1.setForeground(Color.BLACK);
        jTable1.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
        jTable1.getTableHeader().setFont(new Font("Comic Sans MS",Font.ITALIC,15));
        TableColumnModel mod1=jTable1.getColumnModel();
        TableColumn TC=mod1.getColumn(1);
        TC.setPreferredWidth(250);
        
    }

        void sendData_GET(String url) {
        String SS="";
        DataInputStream dis;
        try {
            String str = url+"?show="+"all";
            URL u = new URL(str);
            dis = new DataInputStream(u.openConnection().getInputStream());
            URLConnection myConn = u.openConnection();
            InputStream is = myConn.getInputStream();
            int b;
            while ((b = is.read()) != -1) {
                    SS = SS + (char) b;
            }
                 System.out.println(SS);
                    String [] res = SS.split("@");
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    for (int i=0;i<res.length;i++)
                    {
                        String [] items =new String[5];
                        String [] tmp= res[i].split(":");
                        System.arraycopy(tmp, 0, items, 0, 4);
                        items[4]=String.valueOf(n.API(Double.parseDouble(items[3])));
                        model.addRow(items);
                    }
                    
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
        
    String dataStr = "";
    String contentStr = "application/x-www-form-urlencoded";

    public void addParameter(String ps, String vs) {
        if (ps == null || vs == null || ps.length() == 0 || vs.length() == 0) {
            return;
        }
        if (dataStr.length() > 0) {
            dataStr += "&";
        }
        try {
            dataStr += ps + "=" + URLEncoder.encode(vs, "ASCII");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
        void sendData_POST(String url) {
        dataStr = "";
        OutputStream os;
        InputStream is;
        addParameter("show","all");
        String urlStr = url;
        String SS = "";
        try {
            URL myURL = new URL(urlStr);
            URLConnection myConn = myURL.openConnection();
            myConn.setDoOutput(true);
            myConn.setDoInput(true);
            myConn.setRequestProperty("Content-Type", contentStr);
            myConn.setUseCaches(false);
            BufferedOutputStream out = new BufferedOutputStream(myConn.getOutputStream());
            out.write(dataStr.getBytes());
            out.close();
            int b = -1;
            is = myConn.getInputStream();
            while ((b = is.read()) != -1) {
                SS = SS + (char) b;
            }
           System.out.println(SS);
                    String [] res = SS.split("@");
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    for (int i=0;i<res.length;i++)
                    {
                        String [] items =new String[5];
                        String [] tmp= res[i].split(":");
                        System.arraycopy(tmp, 0, items, 0, 4);
                        items[4]=String.valueOf(n.API(Double.parseDouble(items[3])));
                        model.addRow(items);
                    }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }
        InventorySystem n =new InventorySystem();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation(new java.awt.Point(250, 220));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(240, 197, 220));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("All information about products in inventory");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 390, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product id", "Name", "Amount", "Price / item ($)", "Price / item (EUR)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 850, 230));

        jButton2.setBackground(new java.awt.Color(178, 152, 220));
        jButton2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 285, 100, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new MainPage(this.Method, this.Server, this.LAT).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
