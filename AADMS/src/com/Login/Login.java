package com.Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "User login", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/World", "3228", "root");
			Statement stmt = con.createStatement();
			String sql_stud = "select password from users where email = '" + username + "'";
			String sql_teach = "select password from teachers where email = '" + username + "'";
			ResultSet rs = stmt.executeQuery(sql_stud);
			rs.beforeFirst();
			if (!rs.isBeforeFirst()) {

				rs = stmt.executeQuery(sql_teach);
				rs.beforeFirst();
				if (!rs.isBeforeFirst()) {
					out.println("User not registered");
					return;
				} else
					rs.next();
				if (rs.getString("password").equals(password))
					response.sendRedirect("schedule_notice_teach.html");
			} else
				rs.next();

			if (rs.getString("password").equals(password))
				response.sendRedirect("schedule_notice_stud.html");
			else
				out.println("Invalid password");

			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
