<%-- 
    Document   : test
    Created on : Sep 29, 2014, 9:16:48 AM
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Styles.css" />
        <link href="css/bootstrap.css" type="text/css" rel="stylesheet"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>Instagrim</title>

    </head>
         <header class="bgimage"></header>
             <body>

    <div class="container" >
        <h1><center>Your world in Black and White<center></h1>
            
            <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2">
                <div class="jumbotron">

                    <h2>This is the test Page</h2>

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
                    
        

         <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-7 col-xs-offset-2 text-center " id="bar" >

            <ul class="list-inline" >
                <li><a href="/Instagrim/Images/majed">Sample Images</a></li
            </ul>
        </div>
                    
                    </div>
                 <script src="js/jquery-1.11.3.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.js"></script>

</body>

<footer class="bgfootimg">
</footer>

</html>