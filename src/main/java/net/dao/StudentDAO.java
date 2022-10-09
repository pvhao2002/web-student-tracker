/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import net.context.*;
import net.entity.*;

/**
 *
 * @author haodeptrai
 */
public class StudentDAO {

    public void addStudent(Student theStudent) throws Exception {
	Connection conn = null;
	PreparedStatement myStmt = null;
	try {
	    // get db connection
	    conn = new DBContext().getConnection();
	    // create sql for insert
	    String sql = "insert into student (id, first_name, last_name, email) values (?, ?, ?, ?)";
	    myStmt = conn.prepareStatement(sql);
	    // set the param values for the student
	    myStmt.setInt(1, theStudent.getId());
	    myStmt.setString(2, theStudent.getFirstName());
	    myStmt.setString(3, theStudent.getLastName());
	    myStmt.setString(4, theStudent.getEmail());
	    // execute sql insert
	    myStmt.execute();
	} catch (Exception e) {

	} finally {
	    // clean up JDBC objects
	    close(conn, null, myStmt);
	}

    }

    public StudentDAO() {
    }

    public List<Student> getStudents() throws Exception {
	List<Student> listStu = new ArrayList<>();
	Connection conn = null;
	Statement myStm = null;
	ResultSet myRs = null;
	try {
	    // Get a connection
	    conn = new DBContext().getConnection();

	    // create sql statement
	    String sql = "select * from student order by last_name";
	    myStm = conn.createStatement();
	    // execute query

	    myRs = myStm.executeQuery(sql);
	    // process result set
	    while (myRs.next()) {
		// retrieve data from result set row
		int id = myRs.getInt("id");
		String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
		String email = myRs.getString("email");
		// create new student object
		Student tempStu = new Student(id, firstName, lastName, email);

		// add it to the list of students
		listStu.add(tempStu);

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    // close JDBC objects
	    close(conn, myRs, myStm);
	}

	return listStu;
    }

    private void close(Connection conn, ResultSet myRs, Statement myStm) {
	try {
	    if (myRs != null) {
		myRs.close();
	    }

	    if (myStm != null) {
		myStm.close();
	    }

	    if (conn != null) {
		conn.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Student getStudents(String theStudentId) throws Exception {
	Student theStudent = null;
	Connection conn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	int idStudent;
	try {
	    // convert student id to int
	    idStudent = Integer.parseInt(theStudentId);

	    // getconnection to database
	    conn = new DBContext().getConnection();

	    // create sql to get selected student
	    String sql = "select * from student where id=?";

	    // create prepared statement
	    myStmt = conn.prepareStatement(sql);

	    // set params
	    myStmt.setInt(1, idStudent);

	    // execute statement
	    myRs = myStmt.executeQuery();

	    // retrieve data form result set row
	    if (myRs.next()) {
		String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
		String email = myRs.getString("email");
		// use the studentID during construction
		theStudent = new Student(idStudent, firstName, lastName, email);
	    } else {
		throw new Exception("Could not find student id: " + idStudent);
	    }

	} catch (Exception e) {
	} finally {
	    close(conn, myRs, myStmt);
	}
	return theStudent;
    }

    public void updateStudent(Student theStudent) throws Exception {

	Connection conn = null;
	PreparedStatement myStmt = null;
	try {
	    // get db connection
	    conn = new DBContext().getConnection();
	    // create sql update statement 
	    String sql = "update student set first_name = ?, last_name = ?, email = ? where id = ?";
	    // prepare statement
	    myStmt = conn.prepareStatement(sql);
	    // set params
	    myStmt.setString(1, theStudent.getFirstName());
	    myStmt.setString(2, theStudent.getLastName());
	    myStmt.setString(3, theStudent.getEmail());
	    myStmt.setInt(4, theStudent.getId());
	    // execute sql statement
	    myStmt.execute();
	} catch (Exception e) {
	}
	finally{
	    close(conn, null, myStmt);
	}

    }

    public void deleteStudent(String theStudentId) throws Exception{
	Connection conn = null;
	PreparedStatement myStmt = null;
	int id;
	try {
	    // convert student id to int
	   id = Integer.parseInt(theStudentId);
	   // get connection to database
	   conn = new DBContext().getConnection();
	   // create sql to delete student 
	   String sql = "delete from student where id = ?";
	   // prepare statement
	   myStmt = conn.prepareStatement(sql);
	   // set params
	   myStmt.setInt(1, id);
	   // execute sql statements
	   myStmt.execute();
	    
	} catch (Exception e) {
	}
	finally{
	    close(conn, null, myStmt);
	}
    
    }

}
