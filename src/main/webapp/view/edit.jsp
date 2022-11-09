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
<h1>Edit Student Information</h1>
<p>
    <c:if test="${message} !=null">
        <span class="message">${message}</span>
    </c:if>
</p>
<p>
    <a href="/students">Back to student list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Student information</legend>
        <table>
            <c:if test="${student != null}">
                <input type="hidden" name="id" value="${requestScope["student"].id}' />"/>
            </c:if>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope["student"].name}"></td>
            </tr>
            <tr>
                <td>Date Of Birth: </td>
                <td><input type="date" name="dateOfBirth" id="dateOfBirth" value="${requestScope["student"].dateOfBirth}"></td>
            </tr>
            <tr>
                <td>Address: </td>
                <td><input type="text" name="address" id="address" value="${requestScope["student"].address}"></td>
            </tr>
            <tr>
                <td>Phone Number: </td>
                <td><input type="text" name="phoneNumber" id="phoneNumber" value="${requestScope["student"].phoneNumber}"></td>
            </tr>
            <tr>
                <td>Email Address: </td>
                <td><input ype="email" name="email" id="email" value="${requestScope["student"].email}"></td>
            </tr>
            <tr>
                <td>Class: </td>
                <td><input type="text" name="classStudent" id="classStudent" value="${requestScope["student"].classStudent.name}"></td>
            </tr>

            <tr>
                <td></td>
                <td><input type="submit" value="Update information"></td>
            </tr>

        </table>
    </fieldset>
</form>
</body>
</html>