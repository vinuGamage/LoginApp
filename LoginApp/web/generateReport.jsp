<%-- 
    Document   : generateReport
    Created on : Oct 2, 2019, 4:14:44 PM
    Author     : vinu_g
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generate Report</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    </head>
    <body>
          <div class="container" style="font-family: century gothic;">
            <h1 class="display-1">Generate Report</h1>
            <br><br>
         <form  name="login" action = "GenerateReport" method="POST" >

        <div class="form-group">
            <label for="filename">File Name :</label>
            <input type="text" class="form-control"  placeholder="Enter filename" pattern="^[a-zA-Z0-9_]*$" name="fileName" required>
        </div>
        <button type="submit" class="btn btn-primary">Generate Report</button>
         </div>
    </form>
    </body>
</html>
