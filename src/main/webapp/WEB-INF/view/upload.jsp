<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

    <body>
        <div class="container">
        <h2>Your world in Black and White</h2>
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>

        </article>
     <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-6  col-xs-offset-3 text-center bottombar" id="bar" >

            <ul class="list-inline" >
                <li><a href="/Instagrim">Index</a></li>
                <li><a href="/Instagrim/Home">Home</a></li> 
                <li><a href="/Instagrim/Images/<%=request.getSession().getAttribute("user")%>"> Your Pics</a></li>
            </ul>
        </div>  
        </div>