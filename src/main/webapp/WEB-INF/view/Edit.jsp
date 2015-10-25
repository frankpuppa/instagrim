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
            <form method="POST"  action="">
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
                    <label for="address"> About: </label>  
                    <textarea rows="2" cols="20" name="about" class="form-control" style="resize:vertical"><%=array.get(5)%></textarea>
                </div>
                <div class="form-group">
                    <label for="Password"> Password: </label>  
                    <input type="password" name="password" size="20" class="form-control">
                </div>

                <button type="submit" value="edit" name="submit" class="btn btn-default">Edit Values</button>
            </form>
        </div>
    </div>
                <%}%>
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