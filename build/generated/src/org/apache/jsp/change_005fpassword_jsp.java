package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class change_005fpassword_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("    ");

        String token = request.getParameter("token");
        System.out.println("The token is: " + token);
        session.setAttribute("token", token);
        session.setAttribute("activation-time", System.currentTimeMillis());
        System.out.println("Sending the token to servlet...");
    
      out.write("\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/tutorstyle.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheets/bootstrap.css\">\n");
      out.write("        <script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.js\"></script>\n");
      out.write("<!--        <script type=\"text/javascript\">\n");
      out.write("            window.setTimeout(function () {\n");
      out.write("                $(\"#alert_message\").fadeTo(500, 0).slideUp(500, function () {\n");
      out.write("                    $(this).remove();\n");
      out.write("                });\n");
      out.write("            }, 3000);\n");
      out.write("        </script>-->\n");
      out.write("        <title>Change Password</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("<!--    <s:if test=\"hasActionErrors()\">\n");
      out.write("        <div class=\"alert alert-danger\" id=\"alert_message\">\n");
      out.write("            <s:actionerror/>\n");
      out.write("        </div>\n");
      out.write("    </s:if>-->\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header/header.jsp", out, false);
      out.write("\n");
      out.write("    <div class=\"jumbotron\">\n");
      out.write("        <div class=\"col-md-offset-3\">\n");
      out.write("            <h1 class=\"col-md-offset-2\">Change Password</h1>\n");
      out.write("            <hr>\n");
      out.write("            <p class=\"col-md-offset-1\">Please make sure that the new typed password matches the confirmation.</p>\n");
      out.write("            <p class=\"col-md-offset-3\">This is a one time submission.</p>\n");
      out.write("            <hr>\n");
      out.write("            <form class=\"form-horizontal\" action=\"change\" method=\"post\">  \n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"password\" class=\"col-sm-2 control-label\">Password:</label>\n");
      out.write("                    <div class=\"col-sm-10\">\n");
      out.write("                        <input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\" placeholder=\"New Password\" >\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"passwordConf\" class=\"col-sm-2 control-label\">Confirm:</label>\n");
      out.write("                    <div class=\"col-sm-10\">\n");
      out.write("                        <input type=\"password\" class=\"form-control\" id=\"passwordConf\" name=\"passwordConf\" placeholder=\"Confirm Password\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <div class=\"col-sm-offset-2 col-sm-10\">\n");
      out.write("                        <button type=\"button\" class=\"btn btn-default\" onClick=\"history.go(-1);\n");
      out.write("                                return true;\">Go Back</button>\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-default\">Sign in</button>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </form>        \n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("  ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/footer/footer.jsp", out, false);
      out.write("\n");
      out.write("</body>\n");
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
