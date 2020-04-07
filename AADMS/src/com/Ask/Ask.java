package com.Ask;

import java.io.*; 
import java.sql.*; 
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet(urlPatterns = { "/Ask" })
public class Ask extends HttpServlet 
{ 
	private static final long serialVersionUID = 1L;
  
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException 
    { 
        try { 
  				Connection con = null;
     			Class.forName("com.mysql.cj.jdbc.Driver");  // Needed for JDK9/Tomcat9
     			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/World", "root", "root");  // <<== Check

    		    PreparedStatement st = con.prepareStatement("insert into doubts(roll,subject,teacher,doubt,diificulty) values(?,?,?,?,?)"); 
    		    st.setInt(1, Integer.valueOf(request.getParameter("rollno")));
        		st.setString(2, request.getParameter("field4"));  
        		st.setString(3, request.getParameter("field5"));  
        		st.setString(4, request.getParameter("field6")); 
        		st.setString(5, request.getParameter("difficulty")); 
        		st.executeUpdate(); 

        // Close all the connections 
    		    st.close(); 
        		con.close(); 

        // Get a writer pointer  
        // to display the succesfull result  
				response.sendRedirect("doubts.html");
    		} catch (Exception e) 
    		{ 
        		e.printStackTrace(); 
    		} 
   	} 
}
 


