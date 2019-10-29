<%-- 
    Document   : AllUsers
    Created on : Sep 18, 2019, 11:51:11 AM
    Author     : vinu_g
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Available Users</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
        <h1 class="display-1">All Users Available</h1>

        <table class="table table-striped">
            <tr>

                <th>Emp Name</th>
                <th>Employee Username</th>
            </tr>

            <c:forEach var="employee" items="${employeeList}">
                <tr>

                    <td>${employee.fullname}</td>
                    <td>${employee.username}</td>

                </tr>
            </c:forEach>
        </table>

        <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${currentPage != 1}">
        <td><a href="loginServlet?page=${currentPage - 1}">Previous</a></td>
    </c:if>

    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="loginServlet?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
        </tr>
    </table>
    <br><br>


    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="loginServlet?page=${currentPage + 1}">Next</a></td>
    </c:if>
    <br><br>

</table>
<br><br>

    <form  name="reportpdf" action = "GenerateReport" method="POST" >

        <div class="form-group">
            <label for="filename">File Name :</label>
            <input type="text" class="form-control"  placeholder="Enter filename" pattern="^[a-zA-Z0-9_]*$" name="fileName" >
        </div>
        <button type="submit" class="btn btn-primary">Generate PDF Report</button>

    </form>
    <br><br><br>

    <form  name="reportexcel" action = "GenerateReportExcel" method="POST" >

        <div class="form-group">
            <label for="filename">File Name :</label>
            <input type="text" class="form-control"  placeholder="Enter filename" pattern="^[a-zA-Z0-9_]*$" name="fileName" >
        </div>
        <button type="submit" class="btn btn-primary">Generate EXCEL Report</button>

    </form>
</div>
</body>
</html>
