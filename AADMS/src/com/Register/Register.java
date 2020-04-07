package com.Register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet(description = "Register user", urlPatterns = { "/Register" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String teacher = request.getParameter("teacher");

		String name = request.getParameter("name");
		String roll = request.getParameter("roll");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("pass1");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/World", "3228", "root");
			Statement stmt = con.createStatement();
			String sql_stud = "insert into users values('" + email + "','" + name + "','" + roll + "','" + phone + "','"
					+ password + "')";
			String sql_teach = "insert into teachers values('" + email + "','" + name + "','" + roll + "','" + phone
					+ "','" + password + "')";
			if (teacher == null)
				stmt.execute(sql_stud);
			else
				stmt.execute(sql_teach);
			stmt.close();
			con.close();
			out.println("Data inserted successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
