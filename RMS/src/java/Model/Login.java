/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Security.SessionManagement;
import Services.LoginService;

import java.io.IOException;

import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


/**
 *
 * @author Shashwat
 */
@ManagedBean(name = "login")
public class Login {

	  private String email;
	  private String password;
	  private String message;
	  private LoginService login = LoginService.getInstance();
	  private final Logger log =  Logger.getLogger(this.getClass().getName());  
	  SessionManagement sm = new SessionManagement();


	  public String getEmail() {
			return email;
	  }

	  public void setEmail(String email) {
			this.email = email;
	  }

	  public String getPassword() {
			return password;
	  }

	  public void setPassword(String password) {
			this.password = password;
	  }

	  public String login() throws IOException {

			String result = "false";
		log.info("before login");	
			message = login.loginUser(email, password);
			log.info("after login "+message);	
			if (message.equalsIgnoreCase("sucess")) {
				  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Sucessful", message));
				  sm.createSession();
				  //FacesContext.getCurrentInstance().getExternalContext().dispatch("DashBord.xhtml");
				  result = "true";
			} else {
				  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Fails", message));
				  result = "false";
			}
			return result;
	  }

	  public void reset() {
			setEmail("");
			setPassword("");

	  }
}
