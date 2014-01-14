/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationInterfaces;

import java.util.LinkedList;

/**
 *
 * @author Shashwat
 */
public interface GeneralMethods_Generals {

	   public  Boolean chkConfigRecords(int entityid, int recid, int orgid);
      public  Boolean chkDataRecords( int entityid, int recid, int orgid);
    public  String tableName(int entityid);
	public  String DisplaytableName(int entityid);
   public int getNextTable(int entityid,int orgid);
    public boolean chkSyncStatus(int mrid);
      public String decideNext(int mrid);
	  public int entityid(String tablename);
	  public String clauseKey(String tablename);
	  public int isRecordPresent(Object createddate,String tablename);
	   public String insertAndgetId(String sql,String tbalename);
	 public String getStructuredSql(String sql);
	 public LinkedList getCredentials(int id,String role);
	 public String setEmail(String email,String pass);
}
