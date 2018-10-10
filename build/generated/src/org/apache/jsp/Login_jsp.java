package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Login</title>\n");
      out.write("         <script type=\"text/javascript\">\n");
      out.write("           function sendreq()\n");
      out.write("            {              \n");
      out.write("            var email=document.getElementById(\"email\").value;\n");
      out.write("            var password=document.getElementById(\"password\").value;\n");
      out.write("            if(email==\"\" || password==\"\")\n");
      out.write("            {\n");
      out.write("                 document.getElementById(\"show_div\").innerHTML=\"Please, fill out all the fields\";\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            else\n");
      out.write("            {\n");
      out.write("            var xmlhttp=new XMLHttpRequest(); \n");
      out.write("            xmlhttp.open(\"POST\",\"checkLogin\",true);\n");
      out.write("            xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');\n");
      out.write("            xmlhttp.send(\"&email=\"+ email+\"&password=\"+password);\n");
      out.write("            xmlhttp.onreadystatechange=function(){\n");
      out.write("                if(this.readyState===4&&this.status===200){\n");
      out.write("                    var x=xmlhttp.responseText;\n");
      out.write("                    if(x==\"Done\")\n");
      out.write("                    {\n");
      out.write("                        document.location.href=(\"Home.jsp?email=\"+email\");\n");
      out.write("                    }\n");
      out.write("                    else if(x==\"Admin\"){\n");
      out.write("                        document.location.href=(\"HomeAdmin.jsp\");\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                    else\n");
      out.write("                    {\n");
      out.write("                    document.getElementById(\"show_div\").innerHTML=x;\n");
      out.write("                     }\n");
      out.write("                 }\n");
      out.write("            };    \n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    \n");
      out.write("            \n");
      out.write("            \n");
      out.write("            </script>\n");
      out.write("\n");
      out.write("            <style>\n");
      out.write("                *{\n");
      out.write("                    margin: 0;\n");
      out.write("                    padding: 0;\n");
      out.write("                }\n");
      out.write("                body{\n");
      out.write("                    background-color: #4d4d4d;\n");
      out.write("                }\n");
      out.write("                span{\n");
      out.write("                     color: white;\n");
      out.write("                     font-style: italic;\n");
      out.write("                     font-size: 100px;\n");
      out.write("                }\n");
      out.write("                \n");
      out.write("            .name{\n");
      out.write("                margin-top: 20px;\n");
      out.write("                margin-left: 10px\n");
      out.write("            }\n");
      out.write("              h1{\n");
      out.write("                    color: black;\n");
      out.write("                    margin-left: 36.5%;\n");
      out.write("                    margin-top:10%;\n");
      out.write("                    margin-bottom: 10%;\n");
      out.write("                    font-weight:bold\n");
      out.write("              }\n");
      out.write("                .form{\n");
      out.write("                    width:20%;\n");
      out.write("                    height: 75%;\n");
      out.write("                    padding: 1.5%;\n");
      out.write("                    margin-left: 35%;\n");
      out.write("                    margin-top: 7%;\n");
      out.write("                    background-color:#b3b3b3;\n");
      out.write("                    border-radius: 10px;\n");
      out.write("                }\n");
      out.write("                label,#show_div{\n");
      out.write("                    color: black;\n");
      out.write("                    font-size:30px;\n");
      out.write("                    margin: 4%\n");
      out.write("                    \n");
      out.write("                }\n");
      out.write("                input{\n");
      out.write("                    margin: 4%;\n");
      out.write("                    width: 80%;\n");
      out.write("                    padding: 2%;\n");
      out.write("                    border-radius: 5px;\n");
      out.write("                    font-size: 20px\n");
      out.write("                    \n");
      out.write("                }\n");
      out.write("                input[type=\"submit\"]\n");
      out.write("                {\n");
      out.write("                    width: 30%;\n");
      out.write("                    padding: 2%;\n");
      out.write("                    font-size: 25px;\n");
      out.write("                    background-color: #4d4d4d;\n");
      out.write("                    color: white;\n");
      out.write("                    border:none;\n");
      out.write("                    margin-left: 35%\n");
      out.write("\n");
      out.write("                }\n");
      out.write("            </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("         <div class=\"name\">\n");
      out.write("            <span> TV STATIONS</span>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"form\">\n");
      out.write("            <h1>Welcome</h1>\n");
      out.write("        <label>Email</label><br>\n");
      out.write("        <input type=\"text\" name=\"email\" id=\"email\" required><br>\n");
      out.write("        <label>Password</label><br>\n");
      out.write("        <input type=\"password\" name=\"passowrd\" id=\"password\" required><br>\n");
      out.write("        <input type=\"submit\" onclick=\"sendreq()\" value=\"Login\"><br> \n");
      out.write("        <div id=\"show_div\"></div>        \n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
