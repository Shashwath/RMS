/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import ApplicationInterfaces.Factory_DynamicUI;
import Security.ConstantIds;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Shashwat
 */
public class DynamicUIManager implements Factory_DynamicUI{

	  private static DynamicUIManager dui=new DynamicUIManager();
	  private final Logger log =  Logger.getLogger(this.getClass().getName());  
	  private DBConfigs db=DBConfigs.getInstance();
	  private DynamicUIManager()
	  {}
	  public static DynamicUIManager getInstance()
	  {
	  return dui;
	  }
	  
	  @Override
	   public ArrayList<Object> getBusinessModules(int empid, int grpid, int cmpid) {
			
		ArrayList<Object>	targrtflds=new ArrayList<Object>();
		ArrayList<Object>	localflds=new ArrayList<Object>();
		ArrayList<Object>	localvalue=new ArrayList<Object>();
		targrtflds.add(ConstantIds.businessmodule);//add table id 
		log.info("start uimanager");
		localflds.add(ConstantIds.company);
		localflds.add(ConstantIds.group);
		localflds.add(ConstantIds.employee);
		
		localvalue.add(cmpid);
		localvalue.add(grpid);
		localvalue.add(empid);
		log.info("add to arraylist");
		log.info("table ids "+ConstantIds.employeeacess);
			ArrayList<Object> businessmodule=DBConfigs.getInstance().getEntityValues(ConstantIds.employeeacess, targrtflds, localflds, localvalue,true,false);
	  log.info("business arraylist size "+businessmodule.size());
				return businessmodule;
				}

	  @Override
	  public ArrayList<Object> getBussinessEntity(int empid,int grpid,int cmpid,int bsid) {
		ArrayList<Object>	targrtflds=new ArrayList<Object>();
		ArrayList<Object>	localflds=new ArrayList<Object>();
		ArrayList<Object>	localvalue=new ArrayList<Object>();
		targrtflds.add(ConstantIds.apptables);//add table id 
		
		localflds.add(ConstantIds.company);
		localflds.add(ConstantIds.group);
		localflds.add(ConstantIds.employee);
		localflds.add(ConstantIds.businessmodule);
		
		localvalue.add(cmpid);
		localvalue.add(grpid);
		localvalue.add(empid);
		localvalue.add(bsid);
			
			ArrayList<Object> businessentity=DBConfigs.getInstance().getEntityValues(ConstantIds.employeeacess, targrtflds, localflds, localvalue,true,false);
	  
				return businessentity;
		
	  }

	  @Override
	  public ArrayList<String> getFieldName(int empid, int grpid, int cmpid, int entityid, boolean hidden, boolean pk, boolean autofill) {
		ArrayList<String> val=db.getConfigs(entityid, "fieldname", hidden, pk, autofill);
		
		
		return val;
	  }

	  @Override
	  public ArrayList<String> getDisplayName(int empid, int grpid, int cmpid, int entityid, boolean hidden, boolean pk, boolean autofill) {
		ArrayList<String> val=db.getConfigs(entityid, "displaylabel", hidden, pk, autofill);
		
		
		return val;
	  }

	  @Override
	  public ArrayList<String> getFieldType(int empid, int grpid, int cmpid, int entityid, boolean hidden, boolean pk, boolean autofill) {
		ArrayList<String> val=db.getConfigs(entityid, "fieldtype", hidden, pk, autofill);
		
		
		return val;
	  }

	  @Override
	  public ArrayList<String> getDataType(int entityid, boolean hidden, boolean pk, boolean autofill) {
			throw new UnsupportedOperationException("Not supported yet.");
	  }

	  @Override
	  public ArrayList<String> getUIControl(int entityid, boolean hidden, boolean pk, boolean autofill) {
		ArrayList<String> val=db.getConfigs(entityid, "uicontrol", hidden, pk, autofill);
		
		
		return val;
	  }

	  @Override
	  public ArrayList<String> getDefaultValue(int entityid, boolean hidden, boolean pk, boolean autofill) {
		ArrayList<String> val=db.getConfigs(entityid, "defaultvalue", hidden, pk, autofill);
		
		
		return val;
	  }

	  @Override
	  public ArrayList<String> getIsRequired(int entityid, boolean hidden, boolean pk, boolean autofill) {
		ArrayList<String> val=db.getConfigs(entityid, "isrequired", hidden, pk, autofill);
		
		
		return val;
	  }

	  @Override
	  public ArrayList<String> getLov(int entityid, boolean hidden, boolean pk, boolean autofill) {
			ArrayList<String> val=db.getConfigs(entityid, "lovreference", hidden, pk, autofill);
		
		
		return val;
	  }

	  @Override
	  public ArrayList<String> getUIConfigs(int entityid, boolean hidden, boolean pk, boolean autofill) {
			throw new UnsupportedOperationException("Not supported yet.");
	  }

	  @Override
	  public ArrayList<String> getFldLength(int entityid, boolean hidden, boolean pk, boolean autofill) {
		ArrayList<String> val=db.getConfigs(entityid, "fieldlength", hidden, pk, autofill);
		
		
		return val;
	  }

	  @Override
	  public ArrayList<String> getReferenceId(int entityid, boolean hidden, boolean pk, boolean autofill) {
		ArrayList<String> val=db.getConfigs(entityid, "referentityid", hidden, pk, autofill);
		
		
		return val;
	  }

	  
	  
	  
}