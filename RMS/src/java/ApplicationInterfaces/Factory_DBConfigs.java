/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationInterfaces;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Shashwat
 */
public interface Factory_DBConfigs {

	  public ArrayList<String> getConfigs(int entityid, String fieldname, boolean hidden, boolean pk, boolean autofill);

	  public ArrayList<String> getFieldName(int entityid, boolean hidden);

	  public ArrayList<String> getDataType(int entityid, boolean hidden);

	  public ArrayList<String> getUIControl(int entityid, boolean hidden);

	  public ArrayList<String> getDefaultValue(int entityid, boolean hidden);

	  public String getForeignfield(int entityid);

	  public LinkedList getTableContents(int entityid, String key, boolean hidden, boolean pk, boolean autofill, String fromdate, String todate, String status, String priority, int roleid, int memberid, String sortdatefield, boolean cancel);

	  public int getValue(int entityid, String targetfld, String localfld, int svrid, boolean dist);

	  public Object getObjectValue(int entityid, String targetfld, String localfld, int svrid, boolean dist);

	  public int drillUp(int entityid, int target, int local, int svrid, boolean dist);

	  public Object drillUpObject(int entityid, int target, int local, int svrid, boolean dist);

	  public ArrayList<Object> getTableValues(int entityid, ArrayList<Object> targetfld, ArrayList<Object> localfld, ArrayList<Object> svrid, boolean dist, boolean cancel);

	  public Object getEntityValues(int entityid, ArrayList<Object> targetentity, ArrayList<Object> localentity, ArrayList<Object> svrid, boolean dist, boolean cancel);

	  public ArrayList<Object> getGeneralTableContents(int masterentity, int entityid, int prid, int empid, int grpid, boolean hidden, boolean pk, boolean autofill, boolean cancel, boolean dist);
}
