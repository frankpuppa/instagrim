
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/Styles.css" />
        <link href="../css/bootstrap.css" type="text/css" rel="stylesheet"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Instagrim</title>
    </head>
    <header class="bgimage"></header>
    <body>
        <% String uservisited = (String)request.getAttribute("uservisited");
           String picid = (String) request.getAttribute("profilepic");
           String about =(String) request.getAttribute("about");%>

        <div class="container">
            <h1>INSTAgrim ! </h1> <h2 class="text-center"><%=uservisited%>'s world in Black and White</h2>
            <div class="container-fluid">
                <div class="paragraph">
                    <div class="row">
                        <div class="span">
                            <div class="clearfix content-heading">
                                <% if (picid == null) {%>
                                <img src="../media/defaultavatar.png">
                                <% } else {%>  
                                <img src="${pageContext.request.contextPath}/Thumb/<%=picid%>">
                                <%}%>
                                <h3>Experience &nbsp </h3>
                            </div>
                            <p><%=about%></p>
                        </div>
                    </div>
                </div>
            </div>







            <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-6  col-xs-offset-3 text-center bottombar" id="bar" >

                <ul class="list-inline" >
                    <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/Upload">Upload</a></li>   
                    <li><a href="${pageContext.request.contextPath}/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>

                </ul>
            </div>  
        </div>
    </body>
    <footer class="bgfootimg"></footer>
    <script src="../js/jquery-1.11.3.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.js"></script>
    <script src="../js/myscript.js"></script>
</html>