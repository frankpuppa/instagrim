<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>

<script>
    
</script>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
<div class="container">
     
    <h2> <center>Your world in Black and White</center></h2>
     <!--col-md-offset-3 col-sm-4 col-sm-offset-3 col-xs-7 col-xs-offset-2 text-center-->    
    <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-6  col-xs-offset-3 text-center bottombar" id="bar" >

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
            <li><a href="/Instagrim/Register">Register</a></li>
            <li><a href="/Instagrim/Login">Login</a></li>
                <%
                                            }%>



        </ul>
    </div>
    </div>           

