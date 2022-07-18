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
    public static void main(String[] args) throws IOException {
        new SignIn().setVisible(true);        
    }
    
}
