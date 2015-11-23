package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class profile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/header/login_header.jsp");
    _jspx_dependants.add("/footer/footer.jsp");
  }

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
      out.write("<!DOCTYPE html>\n");

    System.out.println("Printing username session attribute from profile " + session.getAttribute("username"));
        try {
        Boolean isLoggedInNow
                = ((Boolean) (session.getAttribute("loggedIn")));
        if (!isLoggedInNow.booleanValue() || isLoggedInNow == null) {
            String url = "http://" + request.getServerName() + ":8080" + request.getContextPath() + "/login.jsp";
            response.sendRedirect(response.encodeRedirectURL(url));
        } else {
            System.out.println("Inside postings.jsp else statement. The user is logged in!");
        }
    } catch (NullPointerException e) {
        String url = "http://" + request.getServerName() + ":8080" + request.getContextPath() + "/login.jsp";
        response.sendRedirect(response.encodeRedirectURL(url));
    }

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/tutorstyle.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/bootstrap.css\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/bootstrap.css\">\n");
      out.write("        <title>Tutors@JMU - Header</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <nav class=\"navbar navbar-default navbar-static-top\">\n");
      out.write("            <div class=\"container-fluid\">\n");
      out.write("                <!-- Brand and toggle get grouped for better mobile display -->\n");
      out.write("                <div class=\"navbar-header\">\n");
      out.write("                    <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">\n");
      out.write("                        <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                    </button>\n");
      out.write("                    <a class=\"navbar-brand\" href=\"postings.jsp\">Tutors@JMULogo</a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <!-- Collect the nav links, forms, and other content for toggling -->\n");
      out.write("                <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n");
      out.write("                    <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                        <li><p style=\"bottom:-11pt; position: relative;\"><span style=\"text-transform: capitalize !important;\">");
      out.print(session.getAttribute("username"));
      out.write("</span> is signed in.</p></li>\n");
      out.write("                        <li><a href=\"profile.jsp\" >My Account </a></li>\n");
      out.write("                        <li><a href=\"logout\">Sign Out</a></li>\n");
      out.write("                    </ul>\n");
      out.write("                </div><!-- /.navbar-collapse -->\n");
      out.write("            </div><!-- /.container-fluid -->\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write(" \n");
      out.write("        \n");
      out.write("        <h1 class=\"text-center\" id=\"welcome\" style=\"text-transform: capitalize !important;\">");
      out.print(session.getAttribute("username"));
      out.write("'s Profile</h1>\n");
      out.write("        <p class=\"text-center\"><span>Would you like to change your password?<a href=\"pass_reset\"> Reset Now</a></span></p>    \n");
      out.write("        <button type=\"button\" class=\"btn btn-default\" onClick=\"history.go(-1);\n");
      out.write("                return true;\">Go Back</button>\n");
      out.write("                ");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Tutors@JMU - Footer</title>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/tutorstyle.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/bootstrap.css\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body> \r\n");
      out.write("        <footer class=\"footer navbar navbar-inverse navbar-fixed-bottom\">\r\n");
      out.write("            <div class=\"container-fluid col-md-offset-8 \">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("               <p class=\"copyright\">&copy; Copyright 2015 All Rights Reserved Tutors@JMU</p>\r\n");
      out.write("                </div>\r\n");
      out.write("                <ul class=\"footer-links\">\r\n");
      out.write("                    <li class=\"footer-link\"><a href=\"about_us\">About</a></li>\r\n");
      out.write("                    <li class=\"footer-link\"><a href=\"contact\">Contact</a></li>\r\n");
      out.write("                    <li class=\"footer-link\"><a href=\"help\">Help</a></li>\r\n");
      out.write("                </ul>\r\n");
      out.write("            </div>\r\n");
      out.write("        </footer>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
      out.write(" \n");
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
