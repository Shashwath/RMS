/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ApplicationInterfaces.Services_DashBord;
import java.util.logging.Logger;
import org.primefaces.component.menubar.Menubar;

/**
 *
 * @author Shashwat
 */
public class DashBordService implements Services_DashBord{
private final Logger log =  Logger.getLogger(this.getClass().getName());  
	  private DynamicUIService dui= DynamicUIService.getInstance();
	  static DashBordService dashbord=new DashBordService();
	  
	  private DashBordService()
	  {}
	  
	  public static DashBordService getInstance()
	  {
	  return dashbord;
	  }
	  
	  @Override
	  public Menubar getAcessibilityMenbar(int empid, int grpid, int cmpid) {
			log.info("get menu bars");
		return dui.getAcessibilityBar(empid, grpid, cmpid);
	  }

	  
	  
	  
	  
	  
}
