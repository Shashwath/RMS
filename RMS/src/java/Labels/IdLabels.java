/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Labels;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Shashwat
 */
@ManagedBean(name="ids")
public class IdLabels {
	  
	  private String login="login_login";
	  private String reset="login_reset";
	  private String email="login_email";
	  private String password="login_password";

	  public void setEmail(String email) {
			this.email = email;
	  }

	  public void setPassword(String password) {
			this.password = password;
	  }

	  public void setReset(String reset) {
			this.reset = reset;
	  }
	  

	  
	  public String getLogin() {
			return login;
	  }

	  public void setLogin(String login) {
			this.login = login;
	  }
	  
}
