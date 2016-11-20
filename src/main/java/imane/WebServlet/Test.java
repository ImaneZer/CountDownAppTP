/*
 * @author Imane ZEROUALI
 */
package imane.WebServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {

	  public void doGet( HttpServletRequest request, HttpServletResponse response )
	  	throws ServletException, IOException {

	      boolean refresh = false; 


	      if( (request.getParameter("nom") != null)|| (request.getParameter("date") != null) || (request.getParameter("Langues") != null))
	      {
	        if(request.getParameter("modifier")!= null)
	        {

	          Cookie[] cookies = request.getCookies();
	          for(Cookie c: cookies)
	          {
	            if(c.getName().equals(request.getParameter("modifier")))
	            {
	              modifierCookie(response, c,request.getParameter("nom"),request.getParameter("date"), request.getParameter("Langues"));
	            }
	          }
	        }else {


	          CountDown newC = AjouterCountDown(request.getParameter("nom"),request.getParameter("date"), request.getParameter("Langues"));

	          if(!CookieExiste(request.getCookies(), newC))
	          {
	            AddNewCookie(response, newC);
	            refresh = true;
	          }
	        }
	         request.setAttribute( "newC", request.getCookies());

	      }else if(request.getParameter("supprimer")!= null){


	        removeCookie(request, response, request.getParameter("supprimer") );
	        request.setAttribute( "newC", request.getCookies());

	      } else{
	        if(request.getCookies() == null)
	        {
	          request.setAttribute( "newC","false" );
	        }else{

	          request.setAttribute( "newC",request.getCookies());
	        }
	      }

	      if(!refresh)
	      {
	        this.getServletContext().getRequestDispatcher( "/WEB-INF/CountDown.jsp" ).forward( request, response );
	      }else {
	        response.sendRedirect("/CountDownApp/Test");
	      }
	  }

	  
	  public void doPost(HttpServletRequest request, HttpServletResponse response)
	 	  throws ServletException, IOException {
	 	    doGet(request, response);
	   }



	   private void modifierCookie(HttpServletResponse response, Cookie cookie, String nom, String date, String langue )
	   {
	    cookie.setValue(nom+" "+date+" "+langue);
	    response.addCookie(cookie);
	   }

	        private void removeCookie(HttpServletRequest request,HttpServletResponse response, String nom)
	    {
	      Cookie[] cookies = request.getCookies();

	      for ( Cookie c : cookies)
	      {
	        if(c.getName().equals(nom))
	        {
	          c.setMaxAge(0);
	          c.setValue("");
	          response.addCookie(c);
	        }
	      }
	    }

	    private boolean CookieExiste(Cookie[] cookies, CountDown CountDown )
	    {
	      String chaine = CountDown.getnom()+" "+CountDown.getdate()+" "+CountDown.getlangue();
	      for ( Cookie c : cookies)
	      {
	        if(c.getValue() == chaine)
	        {
	          return true;
	        }
	      }
	      return false;
	    }

	    private void AddNewCookie(HttpServletResponse response, CountDown CountDown)
	    {
	      response.addCookie( new Cookie(CountDown.getid()+"", CountDown.getnom()+" "+CountDown.getdate()+" "+CountDown.getlangue()) );
	    }

	    private CountDown AjouterCountDown(String nom, String date, String langue) {
	      CountDown c1 = new CountDown();
	      c1.setnom(nom);
	      c1.setdate(date);
	      c1.setlangue(langue);
	      return c1;
	    }

	}