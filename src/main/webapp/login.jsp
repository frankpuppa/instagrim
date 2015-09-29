<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Styles.css" />
        <link href="css/bootstrap.css" type="text/css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>Instagrim</title>

    </head>
    <body>
         <header class="bgimage"></header>
               
    <body>
    <div class="container">
        <h1><center>Your world in Black and White<center></h1>
    </div>
        <nav>
            <ul>
                
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
        <div class="container">
            <div class="col-xs-6" align="center">
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
                <button type="submit" class="btn btn-default">Login</button>
                

            </form>
        </div>
            </div>
        </div>
                

        
        <footer>
            <ul>
                <li class="footer"><a href="/Instagrim">Home</a></li>
            </ul>
        </footer>
        <script src="js/jquery-1.11.3.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
            <script src="js/bootstrap.js"></script>
    </body>
</html>
