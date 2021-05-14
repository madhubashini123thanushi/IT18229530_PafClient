<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/users.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Users Management V10.1</h1>
<form id="formUser" name="formUser">
 User Name:
 <input id="userName" name="userName" type="text"
 class="form-control form-control-sm">
 <br> Email:
 <input id="Email" name="Email" type="text"
 class="form-control form-control-sm">
 <br> Age:
 <input id="Age" name="Age" type="text"
 class="form-control form-control-sm">
 <br> Phone Number:
 <input id="PhoneNumber" name="PhoneNumber" type="text"
 class="form-control form-control-sm">
 <br> Password:
 <input id="Password" name="Password" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidUserIDSave"
 name="hidUserIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divUsersGrid">
 <%
 User userObj = new User();
 out.print(userObj.readUsers());
 %>
</div>
</div> </div> </div>
</body>
</html>