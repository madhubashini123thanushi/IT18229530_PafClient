$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 	$("#alertSuccess").text("");
 	$("#alertSuccess").hide();
 	$("#alertError").text("");
 	$("#alertError").hide();
 	
 	var status = validateUserForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "UsersAPI",
 type : type,
 data : $("#formUser").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onUserSaveComplete(response.responseText, status);
 }
 });
});



function onUserSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
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
$("#hidUserIDSave").val("");
$("#formUser")[0].reset();
}


$(document).on("click", ".btnUpdate", function(event)
		{
		$("#hidUserIDSave").val($(this).data("userid"));
		 $("#userName").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#Email").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#Age").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#PhoneNumber").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#Password").val($(this).closest("tr").find('td:eq(4)').text());
		});


$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "UsersAPI",
		 type : "DELETE",
		 data : "userID=" + $(this).data("userid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onUserDeleteComplete(response.responseText, status);
		 }
		 });
		});



function onUserDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUsersGrid").html(resultSet.data);
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




//CLIENT-MODEL================================================================
function validateUserForm()
{
// NAME
if ($("#userName").val().trim() == "")
 {
 return "Insert User Name.";
 }
// EMAIL
if ($("#Email").val().trim() == "")
 {
 return "Insert Email.";
 }
// AGE
if ($("#Age").val().trim() == "")
 {
 return "Insert Age.";
 }
//PHONE NUMBER
if ($("#PhoneNumber").val().trim() == "")
 {
 return "Insert Phone Number.";
 }
// PASSWORD------------------------
if ($("#Password").val().trim() == "")
 {
 return "Insert Password.";
 }
return true;
}
 	