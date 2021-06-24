<%--
  Created by IntelliJ IDEA.
  User: ghazalkazemi
  Date: 24/6/21
  Time: 9:35 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div id="container">

    <form:form action="saveCustomer" modelAttribute="customer" method="post" cssClass="col-md-6">
        <legend>Add Customer</legend>
    <div class="col">
        <div class="form-floating mb-3">
            <form:input path="firstName" cssClass="form-control"  id="floatingInput" placeholder="First name" />
            <label for="floatingInput">First name</label>
        </div>
        <div class="form-floating mb-3">
            <form:input path="lastName" cssClass="form-control"  id="floatingInput" placeholder="Last name" />
            <label for="floatingInput">Last name</label>
        </div>
        <div class="form-floating mb-3">
            <form:input path="email" cssClass="form-control"  id="floatingInput" placeholder="name@example.com" />
            <label for="floatingInput">Email</label>
        </div>
        <input value="Save" class="btn btn-outline-dark" type="submit" />
    </form:form>
</div>
        <div class="clearfix"></div>
        <a href="${pageContext.request.contextPath}/customer/list" class="link-dark">Back to list</a>
</body>
</html>
