<%--
  Created by IntelliJ IDEA.
  User: ghazalkazemi
  Date: 23/6/21
  Time: 1:07 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Customers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <h2>CRM - Customer Relationship Manager</h2>
    <input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd'; return false;"
           class="btn btn-outline-dark"/>
<table class="table table-hover">
    <thead class="table-dark">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="customer" items="${customers}">
        <c:url var="updateLink" value="/customer/showFormForUpdate">
            <c:param name="customerId" value="${customer.id}"/>
        </c:url>
        <tr>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>${customer.email}</td>
            <td>
                <a href="${updateLink}">Update</a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</div>
</body>
</html>
