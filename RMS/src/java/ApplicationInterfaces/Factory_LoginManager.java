/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationInterfaces;

import java.util.ArrayList;

/**
 *
 * @author Shashwat
 */
public interface Factory_LoginManager {
	  
	  public int isExist(String email,String password);
	  public boolean isBlocked(int webuserid);
	  public ArrayList<Integer> getPersonelIds(int webuserid);
	  
}
