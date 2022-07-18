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
    String Method,Server;
    public ShowAllProduct(String method,String server) {
        initComponents();
        this.Method=method;
        this.Server=server;
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
        AllProd.setRowHeight(40);
        AllProd.setShowGrid(true);
        AllProd.setGridColor(Color.red);
        AllProd.setBackground(Color.BLACK);
        AllProd.setForeground(Color.WHITE);
        AllProd.setSelectionBackground(Color.WHITE);
        AllProd.setSelectionForeground(Color.BLACK);
        AllProd.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
        TableColumnModel mod1=AllProd.getColumnModel();
        TableColumn TC=mod1.getColumn(1);
        TC.setPreferredWidth(300);
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
                    DefaultTableModel model = (DefaultTableModel) AllProd.getModel();
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
                    DefaultTableModel model = (DefaultTableModel) AllProd.getModel();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        AllProd = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(250, 220));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AllProd.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(AllProd);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 37, 810, 232));

        jPanel1.setBackground(new java.awt.Color(240, 197, 220));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AllProd;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
