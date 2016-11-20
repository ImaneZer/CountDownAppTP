
/*
 * @author Imane ZEROUALI
 */
package imane.WebServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Add extends HttpServlet {
	
  
  public void doGet( HttpServletRequest rq, HttpServletResponse rs )
	 throws ServletException, IOException {
	  this.getServletContext().getRequestDispatcher( "/WEB-INF/Add.jsp" ).forward( rq, rs );
  }

  
  public void doPost(HttpServletRequest rq, HttpServletResponse rs)
   throws ServletException, IOException {
     doGet(rq, rs);
 }


}