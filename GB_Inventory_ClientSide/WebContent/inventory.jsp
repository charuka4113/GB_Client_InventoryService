<%@page import="com.Inventory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inventory Management Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/inventory.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Inventory Management Service</h1>
<form id="formInventory" name="formInventory">
 Software Product Name: 
 <input id="productname" name="productname" type="text" 
 class="form-control form-control-sm">
 <br> Software Product description: 
 <input id="description" name="description" type="text" 
 class="form-control form-control-sm">
 <br> Software Product Size: 
 <input id="size" name="size" type="text" 
 class="form-control form-control-sm">
 <br> Software Product Price: 
 <input id="price" name="price" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidproductidSave" 
 name="hidproductidSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divInventoryGrid">
	<%
	Inventory itemObj = new Inventory(); 
 		out.print(itemObj.readInventory()); 
 	%>
</div>
</div> </div> </div> 
</body>
</html>