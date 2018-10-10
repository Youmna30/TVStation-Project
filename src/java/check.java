/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 *
 * @author BEST WAY
 */
@WebServlet(urlPatterns = {"/check"})
public class check extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DBConnection connection = new DBConnection();
    
            String name =request.getParameter("name");
            String email=request.getParameter("email");
            String mobile=request.getParameter("mobile");
            String password=request.getParameter("password");
            final Pattern EMAIL_REGEX=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            final Pattern MOBILE_REGEX=Pattern.compile("^01[0-2]{1}[0-9]{8}", Pattern.CASE_INSENSITIVE);
            final Pattern NAME_REGEX=Pattern.compile("^[a-zA-Z]+(([a-zA-Z ])?[a-zA-Z]*)*$", Pattern.CASE_INSENSITIVE);

                                                        


            Matcher m = EMAIL_REGEX.matcher(email);
            boolean check = false;
            if(!m.find())
            {
                check=true;
                out.print("Enter a valid Email <br>");
               
            }
            m=MOBILE_REGEX.matcher(mobile);
            if(!m.find())
            {
                check=true;
              out.print("Enter a valid Mobile Number <br>");
            }
            m= NAME_REGEX.matcher(name);
            if(!m.find())
            {
                check=true;
               out.print("Enter a valid Name");
            }
            
            if(check == false)
            {
              
                Statement stmt;
                String query;
                query="select * from user where email = '"+email+"'";
                
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
                          out.print("You are already a user");
                        }
                        conn.close();
                    }
                    else{
                    query ="insert into user(name,email,mobile,password) values('"+name+"','"+email+"','"+mobile+"','"+password+"')";
                    stmt = conn.createStatement();
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(check.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(check.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(check.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(check.class.getName()).log(Level.SEVERE, null, ex);
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
