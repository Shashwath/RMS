/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Shashwat
 */

@ManagedBean(name = "forgot")
public class ForgotPassword {
	  
	  public String forgotPassword()
	  {
	  
	  return "";
	  }
	  
	  private int value;
String test="<p:inputText >";

	  public String getTest() {
			return test;
	  }

	  public void setTest(String test) {
			this.test = test;
	  }

	  public int getValue() {
			return value;
	  }
	  public void setValue(int value) {
			this.value = value;
	  }
	  
	  
}
