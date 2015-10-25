<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<div class="container-fluid" >
    <h1><center>Your world in Black and White<center></h1>

                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2">
                    <div class="jumbotron">

                        <h2>Login</h2>

                        <form role="form" method="POST"  action="Login">
                            <div class="form-group">
                                <label for="Name">User Name: </label> 
                                <input type="text" name="username" size="20" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="Password"> Password: </label>  
                                <input type="password" name="password" size="20" class="form-control">
                            </div>
                            <br/>
                            <button type="submit" class="btn btn-default">Login</button>
                        </form>
                    </div>

                </div>
                </div>
<footer>
    <div class="bottombar text-center" id="bar" >

        <ul class="list-inline" >
            <li><a href="${pageContext.request.contextPath}">Index</a></li>
            <li><a href="${pageContext.request.contextPath}/Register">Register</a></li>
                    
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