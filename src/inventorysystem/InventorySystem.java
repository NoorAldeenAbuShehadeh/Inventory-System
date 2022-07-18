/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.io.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.*;
import javax.swing.JOptionPane;
import org.json.*; 

/**
 *
 * @author Noor Aldeen Muneer
 */
public class InventorySystem {

    /**
     * @param args the command line arguments
     */
    public static double API(double USD)
    {
        Double EUR=0.0;
        try{
        String url_str = "https://api.exchangerate.host/latest?base=USD&symbols=EUR";
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
//        request.setRequestMethod(Method);
        if(request.getResponseCode() == HttpURLConnection.HTTP_OK)
        {
            BufferedReader in =new BufferedReader(new InputStreamReader(request.getInputStream())); 
            String inputLine=""; 
            StringBuffer stRes=new StringBuffer(); 
            while((inputLine = in.readLine()) != null)
            {
                stRes.append(inputLine);
            }
            in.close();
            System.out.println(stRes);
            JSONObject obj =new JSONObject(stRes.toString());
            EUR = obj.getJSONObject("rates").getDouble("EUR");
            System.out.println(EUR);
           
        }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return USD*EUR; 
        
    }
    
    //******************************************************************************************************
    public static String sendData_GET(String operation,String url) {
        String SS="";
        DataInputStream dis;
        try {
            String str =url;
            URL u = new URL(str);
            URLConnection myConn = u.openConnection();
            dis = new DataInputStream(myConn.getInputStream());
            InputStream is = myConn.getInputStream();
            int b;
            while ((b = is.read()) != -1) {
                    SS = SS + (char) b;
            }
//           if(SS.equals("found")){
//            JOptionPane.showMessageDialog(null,"update successfuly");
//           }
//           else{
//            JOptionPane.showMessageDialog(null,"the product not found","",JOptionPane.WARNING_MESSAGE);
//           }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return SS;
    }
    
        
    public static String dataStr = "";
    public static String contentStr = "application/x-www-form-urlencoded";

    public static void addParameter(String ps, String vs) {
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
    
        public static String sendData_POST(String namedata,String data,String url) {
        dataStr = "";
        String SS = "";
        OutputStream os;
        InputStream is;
        String []arrnamedata=namedata.split("#");
        String []arrdata=data.split("#");
        for(int i=0;i<arrnamedata.length;i++){
        addParameter(arrnamedata[i], arrdata[i]);
        }
        String urlStr = url;
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
//           if(SS.equals("found")){
//            JOptionPane.showMessageDialog(null,"update successfuly");
//           }
//           else{
//            JOptionPane.showMessageDialog(null,"the product not found","",JOptionPane.WARNING_MESSAGE);
//           }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return SS;
    }
    //******************************************************************************************************
    public static void main(String[] args) throws IOException {
        new SignIn().setVisible(true);        
    }
    
}
