/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shashwat
 */
public class SessionManagement {
	  
	  
	  HttpSession session ;
	  boolean status=false;
	  public HttpSession createSession()
	  {
	  session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	  return session;
	  }
	  public HttpSession getSession()
	  {
	  return session;
	  }
	  
	  public Boolean isAlive()
	  { 
	  if((session.getLastAccessedTime()+session.getMaxInactiveInterval())<System.currentTimeMillis())
	  status=true;
	  else
	  status=false;
	  
	  return status;
	  }
	  
	  public String DestroySession()
	  {
			session.invalidate();
			return "Session has been invalidated";
	  }
	  
}
