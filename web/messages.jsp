<%@page import="beans.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%
    System.out.println("Outside postings.jsp... value of inputUser:  " + request.getParameter("inputUser"));
    System.out.println("Printing username session attribute from postings: " + session.getAttribute("username"));
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
        <script type="text/javascript">        
        window.setTimeout(function() {
            $("#alert_message_success").fadeTo(500, 0).slideUp(500, function(){
                $(this).remove(); 
            });
        }, 3000);
        </script>
        <title>Tutors@JMU - Postings</title>
    </head>
    <body>
        <%@include file="header/login_header.jsp" %> 
        <s:if test="hasActionMessages()">
            <div class="alert alert-success message" id="alert_message_success">
                 <s:actionmessage/>
            </div>
        </s:if>
        <h1 class="text-center">Welcome Back, 
            <span id="welcome" style="text-transform: capitalize !important;">
                <%=session.getAttribute("username")%>!</span></h1> 
                <br>
                
                <form class="form-horizontal col-md-offset-1" action="send_message" method="post">

                    <div class="form-group">
                        <label for="inputUser" class="col-sm-2 control-label">User:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputUser" name="inputUser" placeholder="User to send to" >
                            <%--<s:property value="fieldErrors['user']"></s:property>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="body" class="col-sm-2 control-label">Message Title:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="message-body" name="messageTitle" placeholder="Message Title" >
                            <%--<s:property value="fieldErrors['user']"></s:property>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="body" class="col-sm-2 control-label">Message Body:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="message-body" name="messageBody" placeholder="Say something" >
                            <%--<s:property value="fieldErrors['user']"></s:property>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" class="btn btn-default" onClick="history.go(-1);
                                    return true;">Go Back</button>
                            <button type="submit" class="btn btn-default">Send Message</button>
                        </div>
                    </div>
            </div>
        </form>        
                        
                            <%
                                String user = request.getSession().getAttribute("username").toString();
                                UserDao ud = new UserDao();
                                
                                
                            %>
                
        <%@include file="footer/footer.jsp" %> 
    </body>
</html>