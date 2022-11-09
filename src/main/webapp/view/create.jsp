<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 09/11/2022
  Time: 11:28 SA
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Student Management Application</title>
</head>
<body>
<center>
  <h1>Student Management</h1>
  <h2>
    <a href="students?action=students">List All Students</a>
  </h2>
</center>
<div align="center">
  <form method="post">
    <table border="1" cellpadding="5">
      <caption>
        <tr>
          <th>StudentName:</th>
          <td>
            <input type="text" name="name" id="name" size="45" placeholder="Enter name"/>
          </td>
        </tr>
        <tr>
          <th>Date Of Birth:</th>
          <td>
            <input type="text" name="dateOfBirth" id="dateOfBirth" size="45" placeholder="Enter date of birth as YYYY-MM-DD"/>
          </td>
        </tr>
        <tr>
          <th>Address:</th>
          <td>
            <input type="text" name="address" id="address" size="45" placeholder="Enter address"/>
          </td>
        </tr>
        <tr>
          <th>Phone Number:</th>
          <td>
            <input type="text" name="phoneNumber" id="phoneNumber" size="45" placeholder="Enter phone number"/>
          </td>
        </tr>
        <tr>
          <th>Email Address:</th>
          <td>
            <input type="email" name="email" id="email" size="45" placeholder="Enter email"/>
          </td>
        </tr>
        <tr>
          <th>Class:</th>
          <td>
            <select name="classStudent" id="class">
              <option value="" disabled selected>Open this select menu</option>
              <c:forEach var="c" items="${classStudent}">
                <option value="${c.id}"><c:out value="${c.name}"/></option>
              </c:forEach>
            </select>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <input type="submit" value="Create"/>
          </td>
        </tr>

      </caption>
    </table>
  </form>


</div>
</body>
</html>