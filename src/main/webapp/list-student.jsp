<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <head>
        <title>Student Tracker App</title>
	<link rel="stylesheet" href="css/style.css" type="text/css"/>
    </head>

    <body>
	<div id="wrapper">
	    <div id="header">
		<h2>Foobar University</h2>		
	    </div>
	</div>

	<div id="container">
	    <div id="content">
		<!-- put new button: Add Student -->
		<input type="submit" value="Add Student"
		       onclick="window.location.href='add-student-form.jsp';return false;"
		       class="add-student-button"		       
		       >
		<table>
		    <tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Action</th>
		    </tr>
		    <c:forEach var="tempStu" items="${student_list}">
			<!-- Set up a link for each student -->
			<c:url var="tempLink" value="student" >
			    <c:param name="command" value="LOAD"></c:param>
			    <c:param name="studentId" value="${tempStu.id}"></c:param>
			</c:url>
			
			<!-- Set up delete link  -->
			<c:url var="deleteLink" value="student" >
			    <c:param name="command" value="DELETE"></c:param>
			    <c:param name="studentId" value="${tempStu.id}"></c:param>
			</c:url>
		    <tr>
			<td>${tempStu.firstName}</td>
			<td>${tempStu.lastName}</td>
			<td>${tempStu.email}</td>
			<td>
			    <a href="${tempLink}">Update</a>
			     | 
			     <a href="${deleteLink}" 
				onclick="if(!(confirm('Are you sure you want to delete this student?'))) 
				    return false; ">Delete</a>
			</td>
		    </tr>
		   </c:forEach>
		</table>
	    </div>
	</div>
    </body>
</html>

