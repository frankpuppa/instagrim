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
                                <div class="panel-heading">User Details</div>
                                
                                <% String[] array = (String[]) request.getAttribute("userData");%>
                                <% if (array != null) {%>
                                <!-- Table -->
                                <table class="table">

                                    <tr>
                                        <td>Login Name</td>
                                        <td><%=array[0]%> </td>
                                        <td><a href="#">Edit</a></td>
                                    </tr>
                                     <tr>
                                        <td>First Name</td>
                                        <td><%=array[3]%> </td>
                                        <td><a href="#">Edit</a></td>
                                    </tr>
                                    <tr>
                                        <td>Last Name</td>
                                        <td><%=array[4]%> </td>
                                        <td><a href="#">Edit</a></td>
                                    </tr>
                                    
                                    <tr>
                                        <td>Email</td>
                                        <td><%=array[2]%> </td>
                                        <td><a href="#">Edit</a></td>
                                    </tr>
                                    
                                     <tr>
                                        <td>Address</td>
                                        <td><%=array[1]%> </td>
                                        <td><a href="#">Edit</a></td>
                                    </tr>

                                </table>
                                <%}%>
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
