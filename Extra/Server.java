/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Momen
 */
public class Server extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
//            out.println(request.getParameter("EmpID"));
//            out.println(request.getParameter("Password"));
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorysystem?useSSL=false","root","");
            Statement stmt = con.createStatement();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("***");
            if(request.getParameter("EmpID")!=null && request.getParameter("Password") != null)
                {
                int id=Integer.parseInt(request.getParameter("EmpID"));
                int pass=Integer.parseInt(request.getParameter("Password"));
                String sqlstr="SELECT * FROM `employess` WHERE `EmpID`='"+ id+ "' and `Password`='"+ pass+ "';";
                ResultSet rs=stmt.executeQuery(sqlstr);
                if(rs.next())
                {
                    out.print("true!"+rs.getString("LAT"));
                }
                else
                {
                    out.print("false! ");
                } 
                }
            else if(request.getParameter("ProdID")!=null && request.getParameter("Ammount") != null&& request.getParameter("status") != null)
                {
                    String sqlstr="SELECT * FROM `products` WHERE `ProdID`='"+request.getParameter("ProdID")+"';";
                    ResultSet rs=stmt.executeQuery(sqlstr);
                    if(rs.next())
                    {
                    
//                    out.print(request.getParameter("status"));
                    if(request.getParameter("status").equals("add"))
                    {
                    out.print("found");
                    int amm = Integer.parseInt(request.getParameter("Ammount"))+Integer.parseInt(rs.getString("Ammount"));
                    stmt = con.createStatement();
                    String sqlstr1="UPDATE `products` SET `Ammount`='"+ String.valueOf(amm)+ "' WHERE `ProdID`='"+ request.getParameter("ProdID")+"';";
                    stmt.executeUpdate(sqlstr1);
//                    con.commit();
                    }
                    else if(request.getParameter("status").equals("delete"))
                    {
                    if(Integer.parseInt(rs.getString("Ammount")) >= Integer.parseInt(request.getParameter("Ammount")))
                    {
                     out.print("found");
                    int amm1 = Integer.parseInt(rs.getString("Ammount")) - Integer.parseInt(request.getParameter("Ammount"));
                    String sqlstr1="UPDATE `products` SET `Ammount`='"+String.valueOf(amm1)+ "' WHERE `ProdID`='"+ request.getParameter("ProdID")+"';";
                    stmt.executeUpdate(sqlstr1);
                    }
                    else
                    {
                        out.print("this ammount not avilable in inventory");
                    }
//                    con.commit(); 
                    }                    
                                        
                    }
                    else
                    {
                        out.print("notfound");
                    }    
                }
            else if(request.getParameter("ProdIDSearch")!=null )
            {
                String sqlstr="SELECT * FROM `products` WHERE `ProdID`='"+request.getParameter("ProdIDSearch")+"';";
                    ResultSet rs=stmt.executeQuery(sqlstr);
                    
                    if(rs.next())
                    {
                    String z= rs.getString("ProdID")+":"+rs.getString("Name")+":"+rs.getString("Ammount")+":"+rs.getString("PriceItem");
                    out.print("found!"+z);//rs.getString("Ammount")
                    
                    }
                    else
                    {
                    out.print("notfound! ");
                    }
            }
            else if(request.getParameter("show")!=null )
            {
                String sqlstr="SELECT * FROM `products`;";
                ResultSet rs=stmt.executeQuery(sqlstr);
                String z="";
                while(rs.next())
                {
                z+= rs.getString("ProdID")+":"+rs.getString("Name")+":"+rs.getString("Ammount")+":"+rs.getString("PriceItem")+"@";
                }
                out.print(z);
            }
            else if(request.getParameter("Empselid")!=null )
            {
                stmt = con.createStatement();
                Date x =new Date(); 
                int y =x.getYear()+1900; 
                int m= x.getMonth()+1; 
                int d = x.getDate(); 
                int hr=x.getHours()-1;
                int min=x.getMinutes();
                int sec=x.getSeconds(); 
                String sqlstr1="UPDATE `employess` SET `LAT`='"
                        + y
                        + "-"
                        + m
                        + "-"
                        + d
                        + " "
                        + hr
                        + ":"
                        + min
                        + ":"
                        + sec
                        + "' WHERE `EmpID`='"+request.getParameter("Empselid")+"';";
                stmt.executeUpdate(sqlstr1);
//                System.err.println(new Date());
            }
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
