package com.SDoubts;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet(urlPatterns = { "/SDoubts" })
public class SDoubts extends HttpServlet 
{  
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
static String doubt_response = "<!DOCTYPE html>\r\n" + 
   		"<html>\r\n" + 
   		"\r\n" + 
   		"<head>\r\n" + 
   		"	<link rel=\"icon\" href=\"Resources/book.png\">\r\n" + 
   		"	<title>View Doubts</title>\r\n" + 
   		"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
   		"	<link rel=\"stylesheet\" href=\"CSS/vd_style.css\" />\r\n" + 
   		"	<script type=\"text/javascript\" src=\"JS/check.js\"></script>\r\n" + 
   		"	<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css\">\r\n" + 
   		"	<link href=\"http://fonts.googleapis.com/css?family=Cookie\" rel=\"stylesheet\" type=\"text/css\">\r\n" + 
   		"</head>\r\n" + 
   		"\r\n" + 
   		"<body>\r\n" + 
   		"\r\n" + 
   		"	<header>\r\n" + 
   		"		<div class=\"wrapper\">\r\n" + 
   		"			<div id=\"menu_button_div\">\r\n" + 
   		"				<button id=\"menu_button\" onclick=\"openNav()\">&equiv;</button>\r\n" + 
   		"			</div>\r\n" + 
   		"			<div class=\"topnav\">\r\n" + 
   		"				<a href=\"page.html\"><strong><em>Assignment And Doubt Management</em></strong></a>\r\n" + 
   		"				<input type=\"text\" id=\"search\" placeholder=\"Search...\" style=\"width:100px\"></input>\r\n" + 
   		"				<input onclick=\"Search()\" type=\"Submit\" name=\"submit\" value=\"Search\"></input>\r\n" + 
   		"			</div>\r\n" + 
   		"		</div>\r\n" + 
   		"	</header>\r\n" + 
   		"\r\n" + 
   		"	<nav>\r\n" + 
   		"		<a href=\"schedule_notice_stud.html\">Notice and Schedule</a>\r\n" + 
   		"		<a href=\"uploadass.html\">Upload assignment</a>\r\n" + 
   		"		<a href=\"assign_marks.html\">Assignment marks</a>\r\n" + 
   		"		<a href=\"askDoubts.html\">Ask a Doubt</a>\r\n" + 
   		"		<a href=\"doubts.html\">View All Doubts</a>\r\n" + 
   		"		<a href=\"quiz.html\">Quiz</a>\r\n" + 
   		"		<a href=\"Login.html\">Logout</a>\r\n" + 
   		"	</nav>\r\n" + 
   		"\r\n" + 
   		"	<div class=\"heading\">\r\n" + 
   		"		<div class=\"text-box\">\r\n" + 
   		"			<h1 class=\"heading-primary\">\r\n" + 
   		"				<span class=\"heading-primary-main\">Solve Doubts</span>\r\n" + 
   		"				<span class=\"heading-primary-sub\">Help the students by answering their doubts.</span>\r\n" + 
   		"			</h1>\r\n" +  
   		"		</div>\r\n" + 
   		"	</div><br><br>"+
   		"   <div class=\"form\">";
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
                     throws ServletException, IOException 
   {
   
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
 
      Connection conn = null;
      Statement stmt = null;
      try 
      {
         Class.forName("com.mysql.cj.jdbc.Driver");  // Needed for JDK9/Tomcat9
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/World", "root", "root");  // <<== Check
 
         stmt = conn.createStatement();
 
         String sqlStr = "SELECT * FROM doubts";
 
         out.println(doubt_response);
         out.println("<table>");
         out.println("<thead class = \"header-table\"><tr><th>Question No.</th><th>Roll No.</th><th>Subject</th><th>Teacher</th><th>Question</th><th>Difficulty</th><th>Answer</th></tr></thead>");
         ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
        

         while(rs.next()) {
            
            out.println("<tr><td>" + rs.getInt(1) + "</td>");
            out.println("<td>" + rs.getInt(2) + "</td>");
            out.println("<td>" + rs.getString(3) + "</td>");
            out.println("<td>" + rs.getString(4) + "</td>");
            out.println("<td>" + rs.getString(5) + "</td>");
            out.println("<td>" + rs.getInt(6) + "</td>");
            out.println("<td>" + rs.getString(7) + "</td></tr>");
         }
         out.println("</table>");
         out.println("</div>");
         out.println("<footer class=\"footer-distributed\">\r\n" + 
         		"		<div class=\"footer-left\">\r\n" + 
         		"			<h3>\r\n" + 
         		"				Assignment And <span>Doubt Management</span>\r\n" + 
         		"			</h3>\r\n" + 
         		"			<br> <br>\r\n" + 
         		"			<p class=\"footer-company-name\">PICT &copy; 2019</p>\r\n" + 
         		"		</div>\r\n" + 
         		"\r\n" + 
         		"		<div class=\"footer-center\">\r\n" + 
         		"			<div>\r\n" + 
         		"				<i class=\"fa fa-map-marker\"></i>\r\n" + 
         		"				<p>\r\n" + 
         		"					<span>Pict, Katraj</span> Pune, India\r\n" + 
         		"				</p>\r\n" + 
         		"			</div>\r\n" + 
         		"			<div>\r\n" + 
         		"				<i class=\"fa fa-phone\"></i>\r\n" + 
         		"				<p>020 1111 2222</p>\r\n" + 
         		"			</div>\r\n" + 
         		"			<div>\r\n" + 
         		"				<i class=\"fa fa-envelope\"></i>\r\n" + 
         		"				<p>\r\n" + 
         		"					<a href=\"mailto:support@company.com\">support@pict.com</a>\r\n" + 
         		"				</p>\r\n" + 
         		"			</div>\r\n" + 
         		"		</div>\r\n" + 
         		"\r\n" + 
         		"		<div class=\"footer-right\">\r\n" + 
         		"			<p class=\"footer-company-about\">\r\n" + 
         		"				<span>About the company</span> This is the place to verify all your\r\n" + 
         		"				doubts, receive assignments and upload answers as well as stay\r\n" + 
         		"				up-to-date with your college ctivities through news and schedule.\r\n" + 
         		"			</p>\r\n" + 
         		"			<div class=\"footer-icons\">\r\n" + 
         		"				<a href=\"#\"><i class=\"fa fa-facebook\"></i></a> <a href=\"#\"><i class=\"fa fa-twitter\"></i></a> <a\r\n" + 
         		"					href=\"#\"><i class=\"fa fa-linkedin\"></i></a> <a href=\"#\"><i class=\"fa fa-github\"></i></a>\r\n" + 
         		"			</div>\r\n" + 
         		"		</div>\r\n" + 
         		"\r\n" + 
         		"	</footer>");
         out.println("</body></html>");
      } catch (SQLException ex) {
         ex.printStackTrace();
     } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
     } finally {
         out.close();
         try {
            // Step 5: Close the Statement and Connection
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
      }
   }
}
