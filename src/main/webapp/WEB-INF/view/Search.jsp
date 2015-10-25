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
            <p> '*' to search all users</p>
            <br/>
            <div class="container">
                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Results</div>
                    <%  ArrayList<ArrayList<String>> array = (ArrayList<ArrayList<String>>) request.getAttribute("usersV");
                    ArrayList<Set<String>> followedUsers = (ArrayList<Set<String>>) request.getAttribute("followedUsers");%>

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
                            <% if (followedUsers.get(i) != null) {%>
                            <td><select>
                                    <% Set<String> set = followedUsers.get(i);
                               for (String s : set) {%>

                                    <option value="<%=s%>" ><%=s%></option>
                                    <%}%>
                                </select></td>

                            <%} else {%>
                            <td></td>
                            <%}%>    
                            <td>
                                <form action="${pageContext.request.contextPath}/Follow" method="POST">
                                    <input type="hidden" name="userN" value="<%= array.get(i).get(0)%>">
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
</div>
  
                        <footer>
                            <div class="bottombar text-center" id="bar" >

                                <ul class="list-inline" >
                                    <li><a href="${pageContext.request.contextPath}">Index</a></li>
                                    <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
                                    <li><a href="${pageContext.request.contextPath}/Upload">Upload</a></li>   
                                    <li><a href="${pageContext.request.contextPath}/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>
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

