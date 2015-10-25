<%-- 
    Document   : Home
    Created on : 05-Oct-2015, 12:52:01
    Author     : frank
--%>
<%@page import="java.util.*"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<link href="css/simple-sidebar.css" rel="stylesheet" type="text/css">

<div class="container-fluid" id="AA">
    <div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <h3> Hello <%=request.getSession().getAttribute("user")%></h3>
                </li>
                <li>
                    <!--                    <form id="form1" action="${pageContext.request.contextPath}/Home" method="GET"> 
                                             <input type="submit" value="uiserd" name="act"> Profile Info
                                            <a href="${pageContext.request.contextPath}/Home" name="act" value="userd" onclick="$(this).closest('form1').submit()">Profile Info</a>
                                           <input type="hidden" name="act" value="userd">
                                            <a href="${pageContext.request.contextPath}/Home" onclick="test();">Profile Info</a>
                                        </form>-->
                    <!--<a href="${pageContext.request.contextPath}/Home/<%=request.getSession().getAttribute("user")%>"> Profile Info</a></li>-->
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Search">Search users</a>
                </li>
                
                <li>
                    <a href="${pageContext.request.contextPath}/About/<%=request.getSession().getAttribute("user")%>">About</a>
                </li>
            </ul>
        </div>


        <div id="page-content-wrapper"> 
            <div class="container-fluid">
                <% String picid = (String) request.getAttribute("profilepic");
                                   if (picid == null) {%>
                <img src="media/defaultavatar.png">
                <% } else {%>  
                <img src="${pageContext.request.contextPath}/Thumb/<%=picid%>">
                <%}%>
                <br/><br/>
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">User Details
                        <div class="pull-right">
                            <form  action="${pageContext.request.contextPath}/Home/Edit" method="GET">
                                <input  type="submit" name="name" value="Edit">
                            </form>
                        </div>
                    </div>
                    <% ArrayList<String> array = (ArrayList<String>) request.getAttribute("userData");
                                    Set<String> followedUsers = (Set<String>) request.getAttribute("followedUsers");%>
                    <% if (array != null) {%>
                    <!-- Table -->
                    <table class="table">

                        <tr>
                            <td>User Name</td>
                            <td><%=array.get(0)%> </td>
                             <td></td>
                        </tr>
                        <tr>
                            <td>First Name</td>
                            <td><%=array.get(3)%> </td>
                             <td></td>
                        </tr>
                        <tr>
                            <td>Last Name</td>
                            <td><%=array.get(4)%> </td>
                             <td></td>
                        </tr>

                        <tr>
                            <td>Email</td>
                            <td><%=array.get(2)%> </td>
                             <td></td>
                        </tr>

                        <tr>
                            <td>Address</td>
                            <td><%=array.get(1)%> </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>About</td>
                            <td><%=array.get(5)%> </td>
                            <td><form  action="${pageContext.request.contextPath}/Home/EditAbout" method="GET">
                                    <input  type="submit" name="name" value="Edit">
                                </form></td>
                        </tr>
                        <%}%>

                        <% if (followedUsers.size() != 0) {%>  
                        <tr>
                            <td>Follow</td>
                            <td><form action="${pageContext.request.contextPath}/Unfollow" method="POST">
                                    <select name="follow"><% for (String s : followedUsers) {%>
                                        <option selected="selected"><%=s%></option> 
                                        <%}%></select>
                                    <input type="hidden" name="userN" value="<%=request.getParameter("follow")%>">
                                    <input type="submit" name="name" value="Unfollow">
                                </form>
                            </td>
                                 <td></td>
                        </tr>
                        <%}%>
                    </table>

                </div>
            </div>

        </div>

        <br/>
    </div>  
</div>
<footer>
    <div class="bottombar text-center" id="bar" >

        <ul class="list-inline" >
             <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/Upload">Upload</a></li>   
                    <li><a href="${pageContext.request.contextPath}/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>
                    <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
        </ul>
     </div>
        <div class="bgfootimg"></div>
</footer>
         </body>
<script src="js/jquery-1.11.3.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.js"></script>
<script src="js/myscript.js"></script>
</html>