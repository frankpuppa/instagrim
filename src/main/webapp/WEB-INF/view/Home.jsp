<%-- 
    Document   : Home
    Created on : 05-Oct-2015, 12:52:01
    Author     : frank
--%>
<%@page import="java.util.*"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<link href="css/simple-sidebar.css" rel="stylesheet" type="text/css">


<div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <h3> Hello <%=request.getSession().getAttribute("user")%></h3>
                </li>
                <li>
<!--                    <form id="form1" action="/Instagrim/Home" method="GET"> 
                         <input type="submit" value="uiserd" name="act"> Profile Info
                        <a href="/Instagrim/Home" name="act" value="userd" onclick="$(this).closest('form1').submit()">Profile Info</a>
                       <input type="hidden" name="act" value="userd">
                        <a href="/Instagrim/Home" onclick="test();">Profile Info</a>
                    </form>-->
                       <a href="/Instagrim/Home/<%=request.getSession().getAttribute("user")%>"> Profile Info</a></li>
                </li>
                <li>
                    <a href="/Instagrim/Search">Search users</a>
                </li>
                <li>
                    <a href="#"></a>
                </li>
                <li>
                    <a href="#">Events</a>
                </li>
                <li>
                    <a href="#">About</a>
                </li>
                <li>
                    <a href="#">Services</a>
                </li>
                <li>
                    <a href="#">Contact</a>
                </li>
            </ul>
        </div>
        

                    <div id="page-content-wrapper">
                        <div class="container-fluid">
                            <div class="panel panel-default">
                                <!-- Default panel contents -->
                                <div class="panel-heading">User Details
                                    <div class="pull-right">
                                <form  action="/Instagrim/Home" method="GET">
                                            <input type="hidden" name="param" value="edit">
                                            <input  type="submit" name="name" value="Edit">
                                            </form>
                                    </div>
                                </div>
                                
                                <% ArrayList<String> array = (ArrayList<String>) request.getAttribute("userData");
                                   Set<String>followedUsers = (Set<String>)request.getAttribute("followedUsers");%>
                                <% if (array != null) {%>
                                <!-- Table -->
                                <table class="table">

                                    <tr>
                                        <td>User Name</td>
                                        <td><%=array.get(0)%> </td>
                                    </tr>
                                     <tr>
                                        <td>First Name</td>
                                        <td><%=array.get(3)%> </td>
                                    </tr>
                                    <tr>
                                        <td>Last Name</td>
                                        <td><%=array.get(4)%> </td>
                                    </tr>
                                    
                                    <tr>
                                        <td>Email</td>
                                        <td><%=array.get(2)%> </td>
                                    </tr>
                                    
                                     <tr>
                                        <td>Address</td>
                                        <td><%=array.get(1)%> </td>
                                    </tr>
                                     <%}%>
                                     
                                       <% if(followedUsers.size()!=0) {%>  
                                    <tr>
                                        <td>Follow</td>
                                        <td><form action="/Instagrim/Unfollow" method="POST">
                                            <select name="follow"><% for (String s : followedUsers){%>
                                                <option selected="selected"><%=s%></option> 
                                                    <%}%></select>
                                                    <input type="hidden" name="userN" value="<%=request.getParameter("follow") %>">
                                                <input type="submit" name="name" value="Unfollow">
                                            </form>
                                        </td>
                                       
                                    </tr>
                                        <%}%>
                                </table>
                                
                                </div>
                            </div>
                        </div> 
                                
                    <div class="col-md-4 col-md-offset-5 col-sm-6 col-sm-offset-3 col-xs-6  col-xs-offset-3 text-center bottombar" id="bar" >

                        <ul class="list-inline" >
                            <li><a href="/Instagrim">Index</a></li>
                            <li><a href="/Instagrim/Upload">Upload</a></li>   
                            <li><a href="/Instagrim/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>
                            <li><a href="Logout">Logout</a></li>
                        </ul>
                    </div>
 </div>
