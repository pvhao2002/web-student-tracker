<%-- 
    Document   : add-student-form
    Created on : Oct 8, 2022, 11:31:10 PM
    Author     : haodeptrai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Student</title>
	<link rel="stylesheet" href="css/style.css" type="text/css"/>
	<link rel="stylesheet" href="css/add-student-style.css" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
	    <div id="header">
		<h2>Foobar University</h2>
	    </div>
	</div>

	<div id="container">
	    <h3>Update Student</h3>


	    <form action="student" method="GET">
		<input type="hidden" name="command" value="UPDATE">
		<input type="hidden" name="studentId" value="${the_student.id}">
		<table>
		    <tbody>
			<tr>
			    <td><label>First name:</label></td>
			    <td><input type="text" name="firstName" value="${the_student.firstName}"></td>   
			</tr>

			<tr>
			    <td><label>Last name:</label></td>
			    <td><input type="text" name="lastName" value="${the_student.lastName}"></td>   
			</tr>

			<tr>
			    <td><label>Email:</label></td>
			    <td><input type="email" name="email" value="${the_student.email}"></td>   
			</tr>

			<tr>
			    <td><label></label></td>
			    <td><input type="submit" value="Save" class="save"></td>   
			</tr>
		    </tbody>
		</table>
	    </form>
	    <div style="clear: both;" >
		<p>
		    <a href="student">Back to list</a>		    
		</p>
	    </div>
	</div>
    </body>
</html>
