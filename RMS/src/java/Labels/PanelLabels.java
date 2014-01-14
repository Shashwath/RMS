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
@ManagedBean(name="panel")
public class PanelLabels {
	  
	  private String login="Login";

	  public String getLogin() {
			return login;
	  }

	  public void setLogin(String login) {
			this.login = login;
	  }
	  
	  
	  
}
