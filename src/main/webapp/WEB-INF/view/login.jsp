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



                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-6  col-xs-offset-3 text-center bottombar" id="bar" >

                    <ul class="list-inline" >
                        <li><a href="/Instagrim">Index</a></li>
                    </ul>
                </div>
                </div>