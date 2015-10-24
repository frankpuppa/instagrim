<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>

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
            <h1>INSTAgrim ! </h1> <h2 class="text-center">Your world in Black and White</h2>
            <div class="container-fluid">
                
                
            <div class="container">
                <h1 class="page-header">The Gallery</h1>
            </div>
                 
                    <%
                        java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
                        String username=(String)request.getSession().getAttribute("user");
                        ArrayList<ArrayList<String>> comments = (ArrayList<ArrayList<String>>)request.getAttribute("Comments");
                        if (lsPics == null) {
                    %>
                    <p>No Pictures found</p>
                    <%
                    } else {
                            int i=3;
                        Iterator<Pic> iterator;
                        iterator = lsPics.iterator();%>
                       
                       
                            
                        <% while (iterator.hasNext()) {
                            Pic p = (Pic) iterator.next();                               
                    %>
                  
                    <div class="container col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2">
                    <div class="container-fluid testaaa">
                        <br/>
                        <span style="font-weight:bold;"><%=p.getOwner()%> posted:</span><br/>   
                            <a href="${pageContext.request.contextPath}/Image/<%=p.getSUUID()%>">
                                <img class="center-block" src="${pageContext.request.contextPath}/Thumb/<%=p.getSUUID()%>"></a>
                                <br/>
                            <%if(p.getOwner().equals(username)){%>
                            <form  action="${pageContext.request.contextPath}/DeletePhoto" method="GET">
                                <input  type="hidden" name="delete" value="<%=p.getSUUID()%>">
                                <input  type="submit" name="name" value="Delete Photo">
                            </form>
                            <%}%>
                            <form  action="${pageContext.request.contextPath}/SetProfile" method="GET">
                                <input   type="hidden" name="setProfile" value="<%=p.getSUUID()%>">
                                <input   type="submit" name="name" value="Set as Profile">
                            </form>
                            <form  action="${pageContext.request.contextPath}/AddComment" method="GET">
                                <input   type="text" name="comment" value="">
                               <input    type="hidden" name="picid" value="<%=p.getSUUID()%>">
                                <input   type="submit" name="name" value="Submit">
                            </form>
                                <br/>
                            <!--<div class="container">-->
                            <% if(comments !=null){
                               for (int o=0; o<comments.size(); o++){
                            if(comments.get(o).get(0).equals(p.getSUUID())){%>
                            <!--<table style="width:100%">-->
                                <!--<tr>-->
                                    <span style="font-weight: bold"><%=comments.get(o).get(2)%> </span> 
                                        <span style="text-align: right"><%=comments.get(o).get(3)%> </span>
                                        <br/>
                                        <%=comments.get(o).get(1)%> 
                                        
                                        <% if(p.getOwner().equals(username)){%>
                                        
                                        <form  action="${pageContext.request.contextPath}/DelComment" method="GET">
                                         
                                        <input  class="right"  type="hidden" name="comment" value="<%=comments.get(o).get(4)%>">
                                        <input class="right"  type="submit" name="name" value="Delete Comment">
                                        </form>
                                        
                                        <%}%>
                                        <br>

                               <%}%>   
                               
                            <%}%>
                            <%}%>
                        
                    </div>
                            <br/>
                    </div>
                            <%}%>
                             <%}%>
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
