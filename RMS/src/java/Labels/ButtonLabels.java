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
@ManagedBean(name="buttons")
public class ButtonLabels {
	  
	  
	 public String login="Login";
	 public String reset="Reset";

	  public String getReset() {
			return reset;
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
