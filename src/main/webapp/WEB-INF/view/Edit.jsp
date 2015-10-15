<%-- 
    Document   : Edit
    Created on : 15-Oct-2015, 17:34:37
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

            <h3>Edit Profile Values</h3>
            <form method="POST"  action="Home">
<!--                <div class="form-group">
                    <label for="Name">User Name: </label> 
<input type="text" name="username" size="20" class="form-control" value="<%--<%=array.get(0)%>--%>">
                </div>-->
                
                <div class="form-group">
                    <label for="first_name"> First Name: </label>  
                    <input type="text" name="first_name" size="25" class="form-control" value="<%=array.get(3)%>">
                </div>
                <div class="form-group">
                    <label for="last_name"> Last Name: </label>  
                    <input type="text" name="last_name" size="25" class="form-control" value="<%=array.get(4)%>">
                </div>
                <div class="form-group">
                    <label for="email"> Email: </label>  
                    <input type="text" name="email" size="25" class="form-control" value="<%=array.get(2)%>">
                </div>
                <div class="form-group">
                    <label for="address"> Address: </label>  
                    <textarea rows="2" cols="20" name="address" maxlength="100" class="form-control" style="resize:vertical"><%=array.get(1)%></textarea>
                </div>
                <div class="form-group">
                    <label for="Password"> Password: </label>  
                    <input type="password" name="password" size="20" class="form-control">
                </div>

                <button type="submit" class="btn btn-default">Edit Values</button>
            </form>
        </div>
    </div>
                <%}%>
    <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-6  col-xs-offset-3 text-center bottombar" id="bar" >

        <ul class="list-inline" >
            <li><a href="/Instagrim">Index</a></li>
            <li><a href="/Instagrim/Upload">Upload</a></li>   
            <li><a href="/Instagrim/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>
            <li><a href="Logout">Logout</a></li>
        </ul>
    </div>
</div>