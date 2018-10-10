/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author BEST WAY
 */
@WebServlet(urlPatterns = {"/search"})
public class search extends HttpServlet {

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
            throws ServletException, IOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String searchQuery=request.getParameter("search");
            String recv;
            String recvbuff="";
            String query="https://www.googleapis.com/youtube/v3/search?q="+searchQuery+"&key=AIzaSyCRJqo_zdv1gDIsSkczJOFTnKcm2coSWEA&maxResults=20&part=snippet&type=video";
            URL jsonpage = new URL(query);
            URLConnection urlcon = jsonpage.openConnection();
            BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
            
            while ((recv = buffread.readLine()) != null)
            {
               //System.out.println(buffread.readLine());

                recvbuff += recv;
            }
            System.out.println(recvbuff);
            buffread.close();

            JSONObject js=new JSONObject(recvbuff);
            //JSONObject j=js.getJSONObject("kind");
           JSONArray items = js.getJSONArray("items"); // this is the "items: [ ] part
            /*for (int i = 0; i < items.length(); i++) {
                JSONObject videoObject = items.getJSONObject(i);
                JSONObject obj = videoObject.getJSONObject("id");

               // String title = videoObject.getString("title");
                String videoId = obj.getString("videoId");
                System.out.println("id is"+videoId);
            }*/
         /*  if(js.length() == 0){
               System.out.println("emptyy");
           }
           else{
           System.out.println(js.get("etag"));videoIdkind


           }*/
            //System.out.println("kiiiiiiiind"+j.getString("kind"));
            request.setAttribute("json", recvbuff);
        RequestDispatcher rd1= request.getRequestDispatcher("resultSearch.jsp");
        rd1.forward(request, response);
            out.print("heeeeey");
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
        } catch (JSONException ex) {
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (JSONException ex) {
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
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
