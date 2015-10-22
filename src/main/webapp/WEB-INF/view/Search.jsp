<%-- 
    Document   : Search
    Created on : 12-Oct-2015, 15:57:02
    Author     : frank
--%>



<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<div class="container">
    <h1>INSTAgrim ! </h1> <h2 class="text-center">Your world in Black and White</h2>
    <div class="row">
        <form action="${pageContext.request.contextPath}/Search" method="POST">
            <h3 class=>Search User: </h3> <input type="text" name="fname">

            <input type="submit" value="Submit">
        </form> 
        <br><br>
        <div class="container-fluid">
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading">Results</div>
                <%  ArrayList<ArrayList<String>> array = (ArrayList<ArrayList<String>>)request.getAttribute("usersV");
                    ArrayList<Set<String>>followedUsers = (ArrayList<Set<String>>)request.getAttribute("followedUsers");%>
                                
            <!-- Table -->
            <table class="table">
                <tr>
                    <td>#</td>
                    <td>Username</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Email</td>
                    <td>Following</td>
                    <td>Add Follow</td>
                    <td>About</td>
                </tr>
                <% if (array != null) {
                        for (int i = 0; i < array.size(); i++) {%>
                        <tr>
                
                    <td> <%= i + 1%></td>
                    <% for (int j = 0; j < array.get(i).size(); j++) {%>    
                    <td> <%=array.get(i).get(j)%></td>
                    <%}%>
                    <% if(followedUsers.get(i)!=null) {%>
                   <td><select>
                           <% Set<String> set =followedUsers.get(i);
                            for(String s: set){ %>
                       
                            <option value="<%=s %>" ><%=s %></option>
                            <%}%>
                       </select></td>
                       
                    <%}else{%>
                    <td></td>
                        <%}%>    
                        <td>
                            <form action="${pageContext.request.contextPath}/Follow" method="POST">
                                <input type="hidden" name="userN" value="<%= array.get(i).get(0) %>">
                            <input type="submit" name="name" value="Follow">
                            </form>
                        </td>
                        <td><a href="${pageContext.request.contextPath}/About/<%=array.get(i).get(0)%>"><%=array.get(i).get(0)%>'s Page</a></td>
                        </tr>
                        <%}%>
                     <%}%>
                        
            </table>
            
        </div>
    </div>
    </div>
  






    <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-6  col-xs-offset-3 text-center bottombar" id="bar" >

                    <ul class="list-inline" >
                        <li><a href="${pageContext.request.contextPath}">Index</a></li>
                        <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/Upload">Upload</a></li>   
                            <li><a href="${pageContext.request.contextPath}/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>
                            <li><a href="Logout">Logout</a></li>
                    </ul>
    </div>
</div>