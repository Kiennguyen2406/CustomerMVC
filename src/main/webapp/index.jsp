<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<h2>Customer List</h2>
<c:if test="${not empty message}">
    <p>${message}</p>
</c:if>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.age}</td>
            <td>${customer.email}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Form to perform actions (Create, Edit, Delete) -->
<h3>Manage Customer</h3>
<form action="c" method="post">
    <label>Action:
        <select name="action">
            <option value="create">Create</option>
            <option value="edit">Edit</option>
            <option value="delete">Delete</option>
        </select>
    </label><br>
    <label>ID: <input type="text" name="id" placeholder="ID" required></label><br>
    <label>Name: <input type="text" name="name" placeholder="Name"></label><br>
    <label>Age: <input type="text" name="age" placeholder="Age"></label><br>
    <label>Email: <input type="text" name="email" placeholder="Email"></label><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>
