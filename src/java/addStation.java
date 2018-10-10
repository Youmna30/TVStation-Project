/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author BEST WAY
 */
@WebServlet(urlPatterns = {"/addStation"})
public class addStation extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */  
           HttpSession session = request.getSession(true);
           String email=(String) session.getAttribute("UName");
           DBConnection connection = new DBConnection();
           String name=request.getParameter("name");
           String url=request.getParameter("url");
           String country=request.getParameter("country");
           final Pattern NAME_REGEX=Pattern.compile("^(?:[A-Za-z]+)(?:[A-Za-z0-9 ]*)$", Pattern.CASE_INSENSITIVE);
           final Pattern URL_REGEX=Pattern.compile("http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch\\?v=|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?‌​[\\w\\?‌​=]*)?", Pattern.CASE_INSENSITIVE);
           final Pattern COUNTRY_REGEX=Pattern.compile("^[a-zA-Z ]*", Pattern.CASE_INSENSITIVE);
           
           boolean check=false;
           Matcher m = NAME_REGEX.matcher(name);
           if(!m.find())
           {
               check=true;
               out.print("Enter a valid Name <br>");
               
           }
           m=URL_REGEX.matcher(url);
           if(!m.find())
           {
             check=true;
             out.print("Enter a valid URL <br>");
           }
           m=COUNTRY_REGEX.matcher(country);
           if(!m.find())
           {
               check=true;
               out.print("Enter a valid Country <br>");
           }
  
            if(check == false)
            {
              
                Statement stmt;
                String query;
                query="select * from station where url = '"+url+"'";
                
                try (Connection conn = connection.getConnection()) {
                    stmt = conn.createStatement();
                    ResultSet rs=stmt.executeQuery(query);
                    if(rs.next())
                    {
                        if(rs.getString("flag").equals("0"))
                        {
                          out.print("Wait for confirmation");
                        }
                        else
                        {
                          out.print("This station already existed");
                        }
                    }
                    else{
                    query="select * from user where email = '"+email+"'";
                    ResultSet rs1=stmt.executeQuery(query);
                    int userId;
                    if(rs1.next())
                    {
                    userId=rs1.getInt("id");
                    query ="insert into station(name,url,userId,country) values('"+name+"','"+url+"','"+userId+"','"+country+"')";

                    }
                    rs.close();
                    rs1.close();
                    stmt.executeUpdate(query);
                    conn.close();
                    }
                }
                
               stmt.close();
  
            }
            

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(addStation.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(addStation.class.getName()).log(Level.SEVERE, null, ex);
        }
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
