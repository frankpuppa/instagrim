<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>
<div class="container">
        <h2 class="text-center">Your world in Black and White</h2>
        <div class="container">
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>
        </article>
        </div>
     
</div> 

<footer>
    <div class="bottombar text-center" id="bar" >

        <ul class="list-inline" >
            <li><a href="${pageContext.request.contextPath}">Index</a></li>
                <li><a href="${pageContext.request.contextPath}/Home">Home</a></li> 
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