<%-- 
    Document   : EditAbout
    Created on : 20-Oct-2015, 23:41:01
    Author     : frank
--%>
<%@page import="java.util.*"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>

<div class="container-fluid">
    <h1>INSTAgrim ! </h1> <h2 class="text-center">Your world in Black and White</h2>


    <% ArrayList<String> array = (ArrayList<String>) request.getAttribute("userData");%>
    <% if(array!=null){%>

    <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2">
        <div class="jumbotron">

            <h3>Edit About</h3>
            <form method="POST"  action="">            
              
                <div class="form-group">
                    <label for="address"> About: </label>  
                    <textarea rows="4" cols="50" name="about" class="form-control" style="resize:vertical"><%=array.get(5)%></textarea>
                </div>
                

                <button type="submit" name="submit" value="about" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
                <%}%>
    <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-6  col-xs-offset-3 text-center bottombar" id="bar" >

        <ul class="list-inline" >
            <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/Upload">Upload</a></li>   
            <li><a href="${pageContext.request.contextPath}/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>
            <li><a href="Logout">Logout</a></li>
        </ul>
    </div>
</div>