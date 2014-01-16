/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Labels;

/**
 *
 * @author Shashwat
 */
public class ErrorsandWarnings {
	  
	  private static ErrorsandWarnings error=new ErrorsandWarnings();
	 public String noaccount="Account Doesn't Exist";
	 public String blocked="Your Account has been blocked";
	 public String sucess="sucess";
	  
	  private ErrorsandWarnings()
	  {}
	  
	  public static ErrorsandWarnings getInstance()
	  {
	  return error;
	  }
	  
	  

	  
}
