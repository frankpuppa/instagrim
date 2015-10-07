<%-- 
    Document   : Home
    Created on : 05-Oct-2015, 12:52:01
    Author     : frank
--%>
<%@page import="java.util.*"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>

<div class="container">
        <h2>Hello <%=request.getSession().getAttribute("user")%></h2>
        
        <article>
            <h1>Your Pics</h1>
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
        

        <a href="Logout">Logout</a>

        
        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-6  col-xs-offset-3 text-center bottombar" id="bar" >

        <ul class="list-inline" >
            <li><a href="/Instagrim">Home</a></li>
            <li><a href="/Upload">Upload</a></li>   
            <li><a href="/Instagrim/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>

        </ul>
    </div>
</div>
        