<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>

<!--<nav role="navigation" class="navbar navbar-default">

        <div class="collapse navbar-collapse">

            <ul class="nav navbar-nav navbar-left">

                <li><a href="#"  >Home</a></li>
            </ul>


        </div>

    </nav>-->


<div class="container">
     
    <h2><center>Your world in Black and White<center></h2>
                </div>
      <!--          <div class="row">
                    <div class="col-md-4">
                        <div class="container-fluid">
                            <nav>
                                <ul>
                                    <li><a href="upload.jsp">Upload</a></li>
                                       <%-- <%

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
                    }%>--%>

                                    <li class="footer"><a href="/Instagrim">Home</a></li>

                                </ul>
                        </div>
                    </div>
                    </nav>
                </div>-->
                <br/>
                <br/>


    <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2 text-center " id="bar" >

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
</div>



