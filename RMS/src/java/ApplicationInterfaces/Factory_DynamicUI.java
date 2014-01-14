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
public interface Factory_DynamicUI {

	  public ArrayList<Object> getBusinessModules(int empid,int grpid,int cmpid);
	  public ArrayList<Object> getBussinessEntity(int empid,int grpid,int cmpid,int bsid);
	  public ArrayList<String> getFieldName(int empid,int grpid,int cmpid,int entityid,boolean hidden,boolean pk,boolean autofill);
	  public ArrayList<String> getDisplayName(int empid,int grpid,int cmpid,int entityid,boolean hidden,boolean pk,boolean autofill);
	  public ArrayList<String> getFieldType(int empid,int grpid,int cmpid,int entityid,boolean hidden,boolean pk,boolean autofill);
	  
	  
	  public ArrayList<String> getDataType(int entityid,boolean hidden,boolean pk,boolean autofill);
	 
	  public ArrayList<String> getUIControl(int entityid,boolean hidden,boolean pk,boolean autofill);
	 
	  public ArrayList<String> getDefaultValue(int entityid,boolean hidden,boolean pk,boolean autofill);
	 
	  public ArrayList<String> getIsRequired(int entityid,boolean hidden,boolean pk,boolean autofill);
	 
	  
	  public ArrayList<String> getLov(int entityid,boolean hidden,boolean pk,boolean autofill) ;
	 
	  
	  public ArrayList<String> getUIConfigs(int entityid,boolean hidden,boolean pk,boolean autofill) ;
	 
	  
	  public ArrayList<String> getFldLength(int entityid,boolean hidden,boolean pk,boolean autofill);
	 
	  
	    public ArrayList<String> getReferenceId(int entityid,boolean hidden,boolean pk,boolean autofill) ;
	 
}
