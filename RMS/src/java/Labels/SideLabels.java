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
@ManagedBean(name="side")
public class SideLabels {
	  
	  
	  private String email="Email";
	  private String password="Password";

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
	  
}
