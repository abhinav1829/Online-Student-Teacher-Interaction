package com.Upload;
 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = { "/Upload" })
@MultipartConfig
public class Upload extends HttpServlet {
 
    /**
     *
     */
    private static final long serialVersionUID = 1L;
 
    protected void doPost(HttpServletRequest request,  HttpServletResponse response)       throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
 
        final Part filePart = request.getPart("pdf");
        String roll = "3228" ;
        String subject = request.getParameter("subject");
        String assignno = request.getParameter("assignno");
 
        InputStream pdfFileBytes = null;
        final PrintWriter writer = response.getWriter();
 
        try {
 
          if (!filePart.getContentType().equals("application/pdf"))
            {
                       writer.println("<br/> Invalid File");
                       return;
            }
 
           else if (filePart.getSize()>5242880 ) { //5mb
               {
              writer.println("<br/> File size too big");
              return;
               }
           }
 
            pdfFileBytes = filePart.getInputStream();  // to get the body of the request as binary data
 
            final byte[] bytes = new byte[pdfFileBytes.available()];
             pdfFileBytes.read(bytes);  //Storing the binary data in bytes array.
 
            Connection  con=null;
             Statement stmt=null;
 
               try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/World","3228","root");
                  } catch (Exception e) {
                        System.out.println(e);
                        System.exit(0);
                              }
 
                int success=0;
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO assign VALUES(?,?,?,?)");
                pstmt.setString(1, roll);
                pstmt.setBytes(2,bytes); 
                pstmt.setString(3, subject);
                pstmt.setString(4, assignno);//Storing binary data in blob field.
                success = pstmt.executeUpdate();
                if(success>=1)  System.out.println("File uploaded");
                 con.close(); 
 
                 writer.println("<br/> File uploaded successfully");

         		response.sendRedirect("schedule_notice_stud.html");
 
        } catch (FileNotFoundException fnf) {
            writer.println("You  did not specify a file to upload");
            writer.println("<br/> ERROR: " + fnf.getMessage());
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
 
            if (pdfFileBytes != null) {
                pdfFileBytes.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
 
    }
 
}