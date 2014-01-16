/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ApplicationInterfaces.Services_DynamicUI;
import Factory.DBConfigs;
import Factory.DynamicUIManager;
import Model.PersonalIds;
import Security.ConstantIds;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.menubar.Menubar;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.submenu.UISubmenu;
import org.primefaces.model.menu.MenuItem;


/**
 *
 * @author Shashwat
 */
public class DynamicUIService implements Services_DynamicUI {

	  private final Logger log = Logger.getLogger(this.getClass().getName());
	  static DynamicUIService dynamicui = new DynamicUIService();
	  private DynamicUIManager dui = DynamicUIManager.getInstance();
	  private DBConfigs dbc = DBConfigs.getInstance();
	  private Menubar menubars = new Menubar();
	  private UISubmenu submenu = new UISubmenu();
	  private UIMenuItem menuitem;
	  private Object name = "";
	  private Object subname = "";
	  Application application = FacesContext.getCurrentInstance().getApplication();

	  private DynamicUIService() {
	  }

	  public static DynamicUIService getInstance() {
			return dynamicui;
	  }

	  @Override
	  public Menubar getAcessibilityBar(int empid, int grpid, int cmpid) {
			menubars.clearInitialState();
			submenu.clearInitialState();
			submenu.getChildren().clear();
			menubars.getChildren().clear();
			log.info("start building menubar");
			ArrayList<Object> businessmodule = dui.getBusinessModules(empid, grpid, cmpid);
			log.info("got business module arraylist");
			log.info("size is " + businessmodule.size());
			ArrayList<Object> businessentity;
			for (int i = 0; i < businessmodule.size(); i++) {
				  log.info("entered businee for loop");
				  log.info("ids are " + ConstantIds.businessmodule + " , " + (Integer) businessmodule.get(i));
				  name = dbc.getObjectValue(ConstantIds.businessmodule, "fld_name", "fld_prid", (Integer) businessmodule.get(i), true);
				  log.info("businessmodulename " + name);
				  submenu = (UISubmenu) application.createComponent(UISubmenu.COMPONENT_TYPE);
				  submenu.setLabel((String) name);

				  businessentity = dui.getBussinessEntity(empid, grpid, cmpid, (Integer) businessmodule.get(i));
				  for (int j = 0; j < businessentity.size(); j++) {
						subname = dbc.getObjectValue(ConstantIds.apptables, "tablename", "entityid", (Integer) businessentity.get(j), true);
						log.info((Integer) businessentity.get(j) + " entityname " + subname);
						menuitem = (UIMenuItem) application.createComponent(UIMenuItem.COMPONENT_TYPE);
						menuitem.setValue(subname);
						menuitem.setAjax(true);
						menuitem.setUrl("Businessentity.xhtml");
					//	menuitem.setId((Integer) businessentity.get(j)+"");
						//menuitem.addActionListener(new Testing((Integer) businessmodule.get(i) ,(Integer) businessentity.get(j)));
						
						menuitem.addActionListener(new ActionListener() {

							  @Override
							  public void processAction(ActionEvent event) throws AbortProcessingException {
									PersonalIds.setEntityid(45);
									log.info("action event executed ");
							  }
						});
						//menuitem.setId(String.valueOf(businessentity.get(j)));
						submenu.getChildren().add(menuitem);
				  }
				  menubars.getChildren().add(submenu);
			}


			return menubars;
	  }

	  @Override
	  public HtmlPanelGrid createFormElements(int empid, int grpid, int cmpid, int entityid) {
			throw new UnsupportedOperationException("Not supported yet.");
	  }

	  @Override
	  public HtmlPanelGrid editFormElements(int empid, int grpid, int cmpid, int entityid, int recordid) {
			throw new UnsupportedOperationException("Not supported yet.");
	  }

/*
	  private MethodBinding setValue(int entityid) {
			PersonalIds.setEntityid(entityid);
			return "s"; 
	  }
	   * */
	   
}



class Testing implements ActionListener
{
	  private final Logger log = Logger.getLogger(this.getClass().getName());
private int moduleid,entityid;
	  public Testing(int module,int entity)
	  {
			log.info("constructor "+entity);
	  moduleid=module;
	  entityid=entity;
	  }
	  @Override
	  public void processAction(ActionEvent event) throws AbortProcessingException {
		
			PersonalIds.setEntityid(entityid);
			log.info("added actionlistner "+entityid);
	  }
}