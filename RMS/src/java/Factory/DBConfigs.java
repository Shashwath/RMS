/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import ApplicationInterfaces.Factory_DBConfigs;
import GeneralMethods.Generalmethods;
import Security.ConstantIds;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shashwat
 */
public class DBConfigs implements Factory_DBConfigs {

	  private static DBConfigs dbc = new DBConfigs();
	  private final Logger log = Logger.getLogger(this.getClass().getName());
	  Generalmethods gm = new Generalmethods();
	  dbsquery dbsquery = new dbsquery();
	  String foreignfld;

	  private DBConfigs() {
	  }

	  public static DBConfigs getInstance() {
			return dbc;
	  }

	  public void clearResultSet(ResultSet rs) {
			rs = null;
	  }

	  @Override
	  public ArrayList<String> getConfigs(int entityid, String fieldname, boolean hidden, boolean pk, boolean autofill) {

			ArrayList<String> clm = new ArrayList<String>();
			String tablename = gm.tableName(entityid);
			String clause = "";
			try {
				  if (hidden) {
						clause = "where ishidden=1";
				  } else {
						clause = "where ishidden=0";
				  }

				  if (pk) {
						clause = clause + " and isprimarykey=1";
				  } else {
						clause = clause + " and isprimarykey=0";
				  }
				  if (autofill) {
						clause = clause + " and autofill=1";
				  } else {
						clause = clause + " and autofill=0";
				  }


				  ResultSet r1 = (ResultSet) dbsquery.exerting("select " + fieldname + " from configs_apptablefields " + clause + " and entityid=" + entityid);
				  while (r1.next()) {
						clm.add((String) r1.getString(fieldname));
				  }


			} catch (SQLException ex) {
				  Logger.getLogger(DBConfigs.class.getName()).log(Level.SEVERE, null, ex);
			}
			return clm;
	  }

	  @Override
	  public ArrayList<String> getFieldName(int entityid, boolean hidden) {
			ArrayList<String> clm = new ArrayList<String>();
			return clm;
	  }

	  @Override
	  public ArrayList<String> getDataType(int entityid, boolean hidden) {
			ArrayList<String> clm = new ArrayList<String>();
			return clm;
	  }

	  @Override
	  public ArrayList<String> getUIControl(int entityid, boolean hidden) {
			ArrayList<String> clm = new ArrayList<String>();



			return clm;
	  }

	  @Override
	  public ArrayList<String> getDefaultValue(int entityid, boolean hidden) {
			ArrayList<String> clm = new ArrayList<String>();
			return clm;
	  }

	  @Override
	  public LinkedList getTableContents(int entityid, String key, boolean hidden, boolean pk, boolean autofill, String fromdate, String todate, String status, String priority, int roleid, int memberid, String sortdatefield, boolean cancel) {
			throw new UnsupportedOperationException("Not supported yet.");
	  }

	  @Override
	  public int getValue(int entityid, String targetfld, String localfld, int svrid, boolean dist) {
			int value = (Integer) getObjectValue(entityid, targetfld, localfld, svrid, dist);

			return value;
	  }

	  @Override
	  public ArrayList<Object> getGeneralTableContents(int masterentity, int entityid, int prid, int empid, int grpid, boolean hidden, boolean pk, boolean autofill, boolean cancel, boolean dist) {
			ArrayList<Object> cont = new ArrayList<Object>();
			String tablename = gm.tableName(entityid);
			ArrayList<String> fld = getConfigs(entityid, "fieldname", hidden, pk, autofill);
			ArrayList<String> uic = getConfigs(entityid, "uicontrol", hidden, pk, autofill);
			ArrayList<String> ref = getConfigs(entityid, "referentityid", hidden, pk, autofill);
			String sort_field = getForeignfield(masterentity);
			String grpref = getForeignfield(ConstantIds.group);
			String empref = getForeignfield(ConstantIds.employee);
			String clause = "where ";
			String distinct = "";
			try {
				  //adding member clause
				  // clause=clause+" and "+sort_field+"="+memberid; 
				  if (masterentity > 0) {
						clause = clause + " " + sort_field + "=" + prid + " ";
				  }
				  if (grpid > 0) {
						clause = clause + " " + grpref + "=" + grpid + " ";
				  }
				  if (empid > 0) {
						clause = clause + " " + empref + "=" + empid + " ";
				  }
				  if (dist) {
						distinct = "distinct";
				  }
				  //clause=clause+" "+key+"="+memberid+" ";
				  clause = clause + "and  fld_cancel=" + cancel + " ";
				  //adding date range clause

				  ResultSet r1 = (ResultSet) dbsquery.exerting("select * from " + tablename + " " + clause);
				  while (r1.next()) {
						for (int i = 0; i < fld.size(); i++) {
							  String fldname = fld.get(i);
							  String uicontrol = uic.get(i);
							  int referentityid = Integer.parseInt(ref.get(i));
							  /*
							   * if the UICONTROL is reference then foreign key rule applies i.e we need to query from its parent rable 
							   * the code of which goes here
							   */
							  if (uicontrol.equalsIgnoreCase("reference")) {
									// int referentityid=Integer.parseInt(ref.get(i));
									String fldvalue = "";

									String tblname = gm.tableName(referentityid);
									String pkey = dbsquery.getPK(tblname);
									String queryflds = dbsquery.getQueryListingFields(referentityid);
									String[] qryfld = queryflds.split(",");

									//String sql="select "+queryflds+" from "+tblname+" where "+pkey+"="+(int)r1.getInt(fldname);
									ResultSet r2 = (ResultSet) dbsquery.exerting("select " + queryflds + " from " + tblname + " where " + pkey + "=" + (int) r1.getInt(fldname));
									while (r2.next()) {
										  for (int j = 0; j < qryfld.length; j++) {
												fldvalue = fldvalue + " " + (String) r2.getString(qryfld[j]);
										  }
									}

									cont.add(fldvalue);
							  } else {
									cont.add((String) r1.getString(fldname));
							  }

						}

				  }
			} catch (Exception e) {
			}
			return cont;
	  }

	  @Override
	  public String getForeignfield(int entityid) {
			foreignfld = "";
			try {


				  ResultSet r1 = dbsquery.exerting("select fld_foreignfield from configs_apptables where entityid=" + entityid);
				  while (r1.next()) {
						foreignfld = (String) r1.getString("fld_foreignfield");
				  }


			} catch (SQLException ex) {
				  Logger.getLogger(DBConfigs.class.getName()).log(Level.SEVERE, null, ex);
			}
			return foreignfld;
	  }

	  public String getTargetfld(ArrayList<Object> targetfld) {
			String targetflds = "";


			for (int i = 0; i < targetfld.size(); i++) {
				  targetflds = targetflds + "," + targetfld.get(i);
			}

			return targetflds.substring(1, targetflds.length());
	  }

	  public String getInputfld(ArrayList<Object> localfld, ArrayList<Object> svrid) {
			String clause = "";
			for (int i = 0; i < localfld.size(); i++) {
				  clause = clause + " and " + localfld.get(i) + "=" + svrid.get(i) + "";
			}
			return clause.substring(4, clause.length());
	  }

	  @Override
	  public ArrayList<Object> getTableValues(int entityid, ArrayList<Object> targetfld, ArrayList<Object> localfld, ArrayList<Object> svrid, boolean dist, boolean cancel) {
			log.info("start getTablevalues");	
			String tblname = gm.tableName(entityid);
			log.info("tablename is "+tblname);	
			String targetclause = getTargetfld(targetfld);
			log.info("target clause "+targetclause);	
			String localfldclause = "";
			String distinct = "";
			if (localfld.size() > 0) {
				  localfldclause = "where " + getInputfld(localfld, svrid);
			}
			// String prkey=dbsquery.getPK(tblname);
	log.info("where clause "+localfldclause);	
			if (dist) {
				  distinct = "distinct";
			}
			ArrayList<Object> values = new ArrayList<Object>();
			try {
				  log.info("sql query "+"select " + distinct + " " + targetclause + " from " + tblname + "  " + localfldclause + " and fld_cancel=" + cancel);
				  ResultSet r1 = (ResultSet) dbsquery.exerting("select " + distinct + " " + targetclause + " from " + tblname + "  " + localfldclause + " and fld_cancel=" + cancel);
				  while (r1.next()) {
						for (int i = 0; i < targetfld.size(); i++) {
							  values.add((Object) r1.getObject(targetfld.get(i).toString()));
						log.info("db values are  "+targetfld.get(i).toString());	
						}
				  }
			} catch (Exception e) {
				  log.info("exeption   "+e.getMessage());	
			}
			return values;
	  }

	  public ArrayList<Object> getEntityForeignFlds(ArrayList<Object> targetentity) {
			ArrayList<Object> values = new ArrayList<Object>();
			for (int i = 0; i < targetentity.size(); i++) {
				  values.add(getForeignfield((Integer) targetentity.get(i)));
			}


			return values;
	  }

	  @Override
	  public ArrayList<Object> getEntityValues(int entityid, ArrayList<Object> targetentity, ArrayList<Object> localentity, ArrayList<Object> svrid, boolean dist, boolean cancel) {
			log.info("start entityvalues");	
			ArrayList<Object> values = getTableValues(entityid, getEntityForeignFlds(targetentity), getEntityForeignFlds(localentity), svrid, dist, cancel);

log.info("entityvalues size is "+values.size());	
			return values;

	  }

	  @Override
	  public Object getObjectValue(int entityid, String targetfld, String localfld, int svrid, boolean dist) {
			Object value = "";
			String tblname = gm.tableName(entityid);
			// String prkey=dbsquery.getPK(tblname);
			try {
				  ResultSet r1 = (ResultSet) dbsquery.exerting("select " + targetfld + " from " + tblname + " where " + localfld + "=" + svrid);
				  while (r1.next()) {
						value = (Object) r1.getObject(targetfld);
				  }
			} catch (Exception e) {
			}

			return value;
	  }

	  @Override
	  public int drillUp(int entityid, int target, int local, int svrid, boolean dist) {
			throw new UnsupportedOperationException("Not supported yet.");
	  }

	  @Override
	  public Object drillUpObject(int entityid, int target, int local, int svrid, boolean dist) {
			throw new UnsupportedOperationException("Not supported yet.");
	  }
}
