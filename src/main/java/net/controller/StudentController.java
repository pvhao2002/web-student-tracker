/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package net.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.dao.*;
import net.entity.Student;

/**
 *
 * @author haodeptrai
 */
@WebServlet("/student")
public class StudentController extends HttpServlet {

    private StudentDAO studentDAO;
    @Override
    public void init()
	    throws ServletException {
	super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

	try {
	    studentDAO = new StudentDAO();
	} catch (Exception e) {
	    throw new ServletException(e);
	}

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
	    // read the "command" parameter
	    String theCommand = request.getParameter("command");
	    // if the command is missing, then default to listing students
	    if (theCommand == null) {
		theCommand = "LIST";
	    }
	    // rout to the appropriate method
	    switch (theCommand) {
		case "LIST":
		    listStudents(request, response);
		    break;
		case "ADD":
		    addStudent(request, response);
		    break;
		case "LOAD":
		    loadStudent(request, response);
		    break;
		case "UPDATE":
		    updateStudent(request,response);
		    break;
		case "DELETE":
		    deleteStudent(request,response);
		    break;
		default:
		    listStudents(request, response);
		    break;
	    }
	} catch (Exception e) {
	    throw new ServletException(e);
	}

    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
	// get students from db dao
	List<Student> students = studentDAO.getStudents();
	// add student to the request
	request.setAttribute("student_list", students);
	// send to jsp page (view)	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/list-student.jsp");
	dispatcher.forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// read student info from form data
	String idd = request.getParameter("id");
	int id = Integer.parseInt(idd);
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	// create a new student object
	Student theStudent = new Student(id, firstName, lastName, email);
	System.out.println(theStudent);
	log(theStudent.toString());
	// add the student to the database
	studentDAO.addStudent(theStudent);
	// send back to main page(the student list)
	listStudents(request, response);
    }

    private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

	// read student id from form data
	String theStudentId = request.getParameter("studentId");

	// get student from database (dbultil)
	Student theStudent = studentDAO.getStudents(theStudentId);
	// place student in request attribute
	request.setAttribute("the_student", theStudent);

	// send to jsp page: update-student-form.jsp
	RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
	dispatcher.forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	// read student info from form data
	int id = Integer.parseInt(request.getParameter("studentId"));
	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	// create a new  student object
	Student theStudent = new Student(id, firstName, lastName, email);
	// perform  update on database
	studentDAO.updateStudent(theStudent);
	// send them back  to "list students" page
	listStudents(request, response);	
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
	// read student id from form data
	String theStudentId = request.getParameter("studentId");
	// delete student from database
	studentDAO.deleteStudent(theStudentId);
	// send them back to "list student" page
	listStudents(request, response);	
    }
}
