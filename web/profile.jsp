<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%
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
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheets/tutorstyle.css">
        <link rel="stylesheet" type="text/css" href="stylesheets/bootstrap.css">
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.js"></script>
        <title>Tutors@JMU - Profile</title>
    </head>
    <body>
        <%@include file="header/login_header.jsp" %> 
        
        <h1 class="text-center" id="welcome" style="text-transform: capitalize !important;"><%=session.getAttribute("username")%>'s Profile</h1>
        <br>
        <p class="text-center"><span>Would you like to change your password?<a href="pass_reset"> Reset Now</a></span></p>
        <br>
        <button class="col-md-offset-5" type="button" class="btn btn-default" onClick="history.go(-1);
                return true;">Go Back</button>
                <%@include file="footer/footer.jsp" %> 
    </body>
</html>
