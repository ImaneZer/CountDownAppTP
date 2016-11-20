/*
 * @author Imane ZEROUALI
 */
package imane.WebServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

public class Modif extends HttpServlet {

  
  public void doGet( HttpServletRequest rq, HttpServletResponse rs )
	 throws ServletException, IOException {

     Cookie[] cookies = rq.getCookies();
     Cookie MCookie = new Cookie("1"," imane") ;
      for ( Cookie c : cookies )
      {
        if(c.getName().equals(rq.getParameter("Modif")))
        {
          MCookie = c;
        }
      }
    rq.setAttribute("Modifie",MCookie);
	  this.getServletContext().getRequestDispatcher( "/WEB-INF/Modif.jsp" ).forward( rq, rs );
  }

  
  public void doPost(HttpServletRequest rq, HttpServletResponse rs)
   throws ServletException, IOException {
     doGet(rq, rs);
 }

}
