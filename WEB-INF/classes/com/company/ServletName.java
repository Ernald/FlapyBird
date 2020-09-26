/**
 * 
 */
package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WriteTest")
public class ServletName extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setContentType("text/html");
		    PrintWriter out = resp.getWriter();
		    ArrayList<Person> list = PersonDAO.fillIn();	
		    
		    for (int i = 0; i < list.size(); i++) {
		    	Person tmp = list.get(i);
		    	out.println("<p>"+ tmp.getName()+" "+tmp.getLastname()+" "+tmp.getAge()+"</p>");
		    }
		} 
		
		catch (Throwable theException)
        {
            System.out.println("error on WriteTest "+ theException);
        }
	
	}

}
