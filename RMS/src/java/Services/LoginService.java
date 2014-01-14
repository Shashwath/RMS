/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ApplicationInterfaces.Services_Login;
import Factory.LoginManager;
import Factory.dbsquery;
import Labels.ErrorsandWarnings;
import Model.PersonalIds;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Shashwat
 */
public class LoginService implements Services_Login{
	 private final Logger log =  Logger.getLogger(this.getClass().getName());  
	 private  ErrorsandWarnings error=ErrorsandWarnings.getInstance();
	 private LoginManager loginmanager=LoginManager.getInstance();
	 public static LoginService loginservice =new LoginService();
	 private boolean status=false;
	 private int webuserid=0;
	 private String message;
	 private ArrayList<Integer>  peronalid=new ArrayList<Integer>();

	 private LoginService()
	 {}
	 
	 public static LoginService getInstance()
	 {
	 return loginservice;
	 }
	 
	  @Override
	  public String loginUser(String email, String password) {
			
			//Check for the availability of user
			log.info("email and pass are "+email+" "+password);
			webuserid=loginmanager.isExist(email, password);
			if(webuserid>0)
			{
			//Ckech is it blocked
			if(! loginmanager.isBlocked(webuserid))
			{
				  //Get all personal Ids and login the person
				  
				  peronalid=loginmanager.getPersonelIds(webuserid);
				  
				  //Store in personal bean class
				  PersonalIds.setEmpid(1);
				  PersonalIds.setCmpid(1);
				  PersonalIds.setGrpid(1);
				  message=error.sucess;
				  //Lodge the login details in a lodgeevent table
			
			} 
			else
			{
			message=error.blocked;
			}
			}
			else
			{
			message=error.noaccount;
			}
			
				  
			
			
			return message;
	  }
	  
	  
	  
}
