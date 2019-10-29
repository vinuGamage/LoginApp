<%-- 
    Document   : register
    Created on : Sep 10, 2019, 3:04:37 PM
    Author     : vinu_g
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register User</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container" style="font-family: century gothic;">

            <h1 class="display-1" >Register your account</h1>
            <br>
            <div class="container">


                <form name="register"  action="registerServlet" method="post">
                    <div class="form-group">
                        <label for="fullname">Full Name:</label>
                        <input type="text" class="form-control" name="name" pattern="^[A-Za-z -]+$" placeholder="Enter full name" required>
                    </div>
                    <div class="form-group">
                        <label for="pwd">Username:</label>
                        <input type="text" class="form-control" pattern="^[a-zA-Z0-9_]*$" placeholder="Enter username" name="user"   required>
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" pattern= "^[A-Za-z0-9 -]+$" placeholder="Enter password" name="pass" required>
                    </div>



                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>


    </body>
</html>

