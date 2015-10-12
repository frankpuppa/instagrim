<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

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
        
        <div class="container">
            <h2>Your world in Black and White</h2>
        </header>

        <article>
            <h3>Your Pics</h3>
            <%
                java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
                if (lsPics == null) {
            %>
            <p>No Pictures found</p>
            <%
            } else {
                Iterator<Pic> iterator;
                iterator = lsPics.iterator();
                while (iterator.hasNext()) {
                    Pic p = (Pic) iterator.next();
            %>
            <a href="/Instagrim/Image/<%=p.getSUUID()%>" ><img src="/Instagrim/Thumb/<%=p.getSUUID()%>"></a><br/><%
                    }
                }
                %>
        </article>



        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-6  col-xs-offset-3 text-center bottombar" id="bar" >

            <ul class="list-inline" >
                <li><a href="/Instagrim/Home">Home</a></li>
                <li><a href="/Instagrim/Upload">Upload</a></li>   
                <li><a href="/Instagrim/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>

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