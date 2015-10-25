
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/Styles.css" />
        <link href="../css/bootstrap.css" type="text/css" rel="stylesheet"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Instagrim</title>
    </head>
    <header class="bgimage"></header>
    <body>
        <% String username =(String)request.getSession().getAttribute("user");
           String uservisited = (String)request.getAttribute("uservisited");
           String picid = (String) request.getAttribute("profilepic");
           String about =(String) request.getAttribute("about");
           ArrayList<ArrayList<String>> guestbook = (ArrayList<ArrayList<String>>) request.getAttribute("guestbook");%>

        <div class="container">
            <h1>INSTAgrim ! </h1> <h2 class="text-center"><%=uservisited%>'s world in Black and White</h2>
            <div class="container-fluid">
                <div class="paragraph">
                    <div class="row">
                        <div class="span">
                            <div class="clearfix content-heading">
                                <% if (picid == null) {%>
                                <img src="../media/defaultavatar.png">
                                <% } else {%>  
                                <img src="${pageContext.request.contextPath}/Thumb/<%=picid%>">
                                <%}%>
                                <h3>Experience &nbsp </h3>
                            </div>
                            <p><%=about%></p>
                        </div>
                    <br/>
                               <br/>
                        <div class="container" >
                             
                            <div class="jumbotron">
                            <h3>Leave a message on <%=uservisited%>'s Guestbook</h3>
                            <form method="POST"  action=""> 
                            <div class="form-group">
                             <label for="guestbook"> Leave a Comment: </label>  
                          <textarea rows="1" cols="40" name="guestbook" class="form-control" style="resize:vertical"></textarea>
                          </div>
                                <button type="submit" name="submit" value="guestbook" class="btn btn-default">Submit</button>
                            </form>
                            </div>
 
                  
                             <div class="panel panel-default">
                            <% if(guestbook!=null){
                                 for (int i = 0; i < guestbook.size(); i++) {%>
                                 <div class="panel-heading"> 
                                    <%if(username.equals(uservisited)){%>
                                     <div class="pull-right">
                                         <form  action="${pageContext.request.contextPath}/About/Del" method="POST">
                                             <input  type="hidden" name="delete" value="<%=guestbook.get(i).get(3)%>">
                                             <input  type="submit" name="submit" value="Delete">
                                         </form>
                                     </div>
                                             <%}%>
                                     <p>Left by: <span style="font-weight: bold"><%=guestbook.get(i).get(0)%></span>&nbsp; 
                                         <span style="text-align: right"><%=guestbook.get(i).get(2)%></span></p>
                               
                                 </div>
                                 <table class="table">

                                     <tr><td><p><%=guestbook.get(i).get(1)%></p><td><tr>
                                 </table>
                            <%} 
                            }%>
                            </div>
                                   </div>
                    </div>
                </div>
            </div>







            <div class="col-xs-4 col-xs-offset-4 bottombar text-center" id="bar" >

                <ul class="list-inline" >
                    <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/Upload">Upload</a></li>   
                    <li><a href="${pageContext.request.contextPath}/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>

                </ul>
            </div>  
        </div>
    </body>
    <footer class="bgfootimg"></footer>
    <script src="../js/jquery-1.11.3.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.js"></script>
    <script src="../js/myscript.js"></script>
</html>