<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>

<script>
    
</script>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
<div class="container">
     
    <h2><center>Your world in Black and White<center></h2>
        
    <div class="col-md-4 col-md-offset-3 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2 text-center " id="bar" >

        <ul class="list-inline" >
            <li><a href="/Instagrim">Home</a></li>
            <li><a href="upload.jsp">Upload</a></li>
                <%

                    LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                    if (lg != null) {
                        String UserName = lg.getUsername();
                        if (lg.getlogedin()) {
                %>

            <li><a href="/Instagrim/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <%}
                } else {
                %>
            <li><a href="register.jsp">Register</a></li>
            <li><a href="login.jsp">Login</a></li>
                <%
                                            }%>



        </ul>
    </div>
    </div>           

