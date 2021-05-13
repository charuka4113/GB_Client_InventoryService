$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateInventoryForm(); 

if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidproductidSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
url : "InventoryAPI", 
type : type, 
data : $("#formInventory").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onInventorySaveComplete(response.responseText, status); 
} 
}); 
}); 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	$("#hidproductidSave").val($(this).data("productid")); 
 $("#productname").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#description").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#size").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#price").val($(this).closest("tr").find('td:eq(3)').text()); 
});


$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "InventoryAPI", 
		 type : "DELETE", 
		 data : "productid=" + $(this).data("productid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onInventoryDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});


//CLIENT-MODEL================================================================
function validateInventoryForm() 
{ 
// CODE
if ($("#productname").val().trim() == "") 
 { 
 return "Insert Software Product Name."; 
 } 
// NAME
if ($("#description").val().trim() == "") 
 { 
 return "Insert Software Product Description."; 
 } 
//PRICE-------------------------------
if ($("#size").val().trim() == "") 
 { 
 return "Insert Software product size."; 
 } 
// is numerical value
var tmpPrice = $("#price").val().trim(); 
if (!$.isNumeric(tmpPrice)) 
 { 
 return "Insert a numerical value for Item Price."; 
 } 
// convert to decimal price
 //$("#price").val(parseFloat(tmpPrice).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#description").val().trim() == "") 
 { 
 return "Insert Item Description."; 
 } 
return true; 
}

function onInventorySaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divInventoryGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
$("#hidproductidSave").val(""); 
$("#formInventory")[0].reset(); 
}


function onInventoryDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divInventoryGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
