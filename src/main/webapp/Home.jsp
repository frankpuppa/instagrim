<%-- 
    Document   : Home
    Created on : 05-Oct-2015, 12:52:01
    Author     : frank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Hello <%=request.getSession().getAttribute("user")%></h2>
        <a href="Logout">Logout</a>
    </body>
</html>
