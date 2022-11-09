<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 09/11/2022
  Time: 11:28 SA
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Management Application</title>
</head>
<body>
<center>
    <h1>Delete Student</h1>
</center>
<div align="center">
    <form method="post">
        <h3>Are you sure?</h3>
        <table>
            <caption>Student Information</caption>
            <tr>
                <th>ID: </th>
                <td>${student.getId()}</td>
            </tr>
            <tr>
                <th>Name: </th>
                <td>${student.getName()}</td>
            </tr>
            <tr>
                <th>Date Of Birth: </th>
                <td>${student.getDateOfBirth()}</td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>${student.getAddress()}</td>
            </tr>
            <tr>
                <th>Number Phone: </th>
                <td>${student.getPhoneNumber()}</td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>${student.getEmail()}</td>
            </tr>
            <tr>
                <th>Class Name: </th>
                <td>${student.classStudent.name}</td>
            </tr>
            <tr>
                <td><button type="submit" class="btn btn-danger">Delete</button></td>
                <td><button type="button" class="btn btn-secondary"><a href="/student" id="a-cancel">Cancel</a></button> </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
