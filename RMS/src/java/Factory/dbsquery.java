/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import java.sql.*;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Dr.Nagabhushan
 */
public class dbsquery {

	  private final Logger log =  Logger.getLogger(this.getClass().getName());  
	 DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy HH-mm-ss-ms");
	 Date created = new Date();
	 ResultSet rs;
	 int num;
	 //private dbsquery dbsquery=new dbsquery();
	static Connection con = null;
	private  final String url = "jdbc:mysql://localhost:3306/RMS";
	private  final String uname = "triya";
	private  final String password = "Triya@123";

	
	// Variable declaration
	
	private ArrayList<Integer> personal; 
	public dbsquery() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, uname, password);
			// 	con = DriverManager.getConnection(url, uname, password);
		} catch (Exception cnfe) {

			cnfe.getMessage();
		}
}


	
	public  ResultSet exerting(String query) throws SQLException {

		/*	  Connection con = null;
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		} catch (ClassNotFoundException cnfe) {
		
		cnfe.getMessage();
		}
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mrs_server", "root", "admin");
		 */
		PreparedStatement prst = con.prepareStatement(query);
		ResultSet rs1 = prst.executeQuery();


		return rs1;


	}

	public  int inserting(String query) throws SQLException {

		/*	Connection con = null;
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		} catch (ClassNotFoundException cnfe) {
		
		cnfe.getMessage();
		}
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mrs_server", "root", "admin");
		 */
		PreparedStatement prst = con.prepareStatement(query);
		num = prst.executeUpdate();


		return num;


	}

	public  String insertingWS(String query) throws SQLException {
		String state = "";
		/*	Connection con = null;
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		} catch (ClassNotFoundException cnfe) {
		
		cnfe.getMessage();
		}
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mrs_server", "root", "admin");
		 */
		try {
			PreparedStatement prst = con.prepareStatement(query);
			num = prst.executeUpdate();
			state = "1,NULL";
		} catch (Exception ex) {
			state = "0," + ex.getMessage();
		}

		return state;


	}

	public  ArrayList<Integer> logincheck(String name, String pass) throws SQLException {
		personal=new ArrayList<Integer>();
		PreparedStatement prst = con.prepareStatement("select * from tbl_webusers where fld_email='" + name + "' and fld_password='" + pass + "' and fld_isblocked=false");
		rs = prst.executeQuery();
		while (rs.next()) {
			personal.add((int) rs.getInt("fld_employeeref"));
			personal.add((int) rs.getInt("fld_groupref"));
			personal.add((int) rs.getInt("fld_companyref"));
			personal.add((int) rs.getInt("fld_isblocked"));
                       }
		return personal;
		}

   public  boolean emailCheck(String email,int orgid,int roleid) throws SQLException
	{
		boolean status=false;
	ResultSet r1=(ResultSet)exerting("select fld_svrrowid from tbl_webusers where fld_email='"+email+"' and fld_roleid="+roleid+" and fld_orgid="+orgid);
	while(r1.next())
	{
	status=true;
	}
	return status;
	}
   public  boolean admin_logincheck(String name, String pass, String email, String table1) throws SQLException {
		/*	Connection con = null;
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		} catch (ClassNotFoundException cnfe) {
		
		cnfe.getMessage();
		}
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mrs_server", "root", "admin");
		 */

		PreparedStatement prst = con.prepareStatement("select email,username,password from " + table1 + "");
		rs = prst.executeQuery();
		while (rs.next()) {
			String uemail = (String) rs.getString("email");
			String upass = (String) rs.getString("password");
			String uname = (String) rs.getString("username");
			if (uname.equalsIgnoreCase(name) && upass.equals(pass) && uemail.equalsIgnoreCase(email)) {
				return true;
			}
		}


		return false;
	}

	public  boolean emailcheck(String email) throws SQLException {

		/*		Connection con;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cn) {
		cn.getMessage();
		}
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mrs_server", "root", "admin");
		 */
		PreparedStatement prst = con.prepareStatement("select emails from customersinfo");
		rs = prst.executeQuery();
		while (rs.next()) {
			String uname = (String) rs.getString("emails");


			if (uname.equalsIgnoreCase(email)) {
				return false;
			}

		}

		return true;

	}

	public  int org_updatesync(String sql, int orgid, String operation) throws SQLException {


		ResultSet r1 = (ResultSet) exerting("select fld_svrrowid,fld_locid from tbl_medrep where fld_orgid=" + orgid);
		while (r1.next()) {

			int i = (int) inserting("insert into db_sync (mrid,operations,sqls,isexecuted,locid,createddate) values(" + (int)r1.getInt("fld_svrrowid") + ",'" + operation + "','" + sql + "',false," + (int)r1.getInt("fld_locid") + ",'" + dateFormat.format(created) + "')");
			//System.out.println(r1.getInt("fld_mrid"));
		}


		return 0;
	}
	
	public  int mr_updatesync(String sql, int orgid, String operation,int mrid,int locid) throws SQLException {


		

			int i = (int) inserting("insert into db_sync (mrid,operations,sqls,isexecuted,locid,createddate) values(" + mrid + ",'" + operation + "','" + sql + "',false," + locid + ",'" + dateFormat.format(created) + "')");
			
		


		return 0;
	}

	public  int loc_updatesync(String sql, int orgid, String operation, int locid) throws SQLException {

		try {
			ResultSet r1 = (ResultSet) exerting("select fld_svrrowid from tbl_medrep where fld_locid=" + locid);
			while (r1.next()) {

				num = (int) inserting("insert into db_sync (mrid,operations,sqls,isexecuted,locid,createddate) values(" + (int) r1.getInt("fld_svrrowid") + ",'" + operation + "','" + sql + "',false," + locid + ",'" + dateFormat.format(created) + "')");
			}
		} catch (Exception ex) {
		}

		return num;
	}

	public  int mr_updatesync(String sql, int orgid, String operation) throws SQLException {


		ResultSet r1 = (ResultSet) exerting("select fld_svrrowid,fld_locid from tbl_medrep where fld_orgid=" + orgid);
		while (r1.next()) {

			int i = (int) inserting("insert into db_sync (mrid,operations,sqls,isexecuted,locid,createddate) values(" + (int) r1.getInt("fld_svrrowid") + ",'" + operation + "','" + sql + "',false," + (int) r1.getInt("fld_locid") + ",'" + dateFormat.format(created) + "')");
		}


		return 0;
	}
	
	public  int mr_updatetosync(String sql, int mrid, String operation) throws SQLException {


		ResultSet r1 = (ResultSet) exerting("select fld_svrrowid,fld_locid from tbl_medrep where fld_svrrowid=" + mrid);
		while (r1.next()) {

			int i = (int) inserting("insert into db_sync (mrid,operations,sqls,isexecuted,locid,createddate) values(" + (int) r1.getInt("fld_svrrowid") + ",'" + operation + "','" + sql + "',false," + (int) r1.getInt("fld_locid") + ",'" + dateFormat.format(created) + "')");
		}


		return 0;
	}

	public  Boolean isclient(int entityid) throws SQLException {
		Boolean flag = false;


		ResultSet r1 = (ResultSet) exerting("select isclient from configs_apptables where entityid=" + entityid);
		while (r1.next()) {
			flag = (Boolean) r1.getBoolean("isclient");

		}


		return flag;
	}

	public  int getLastId(String tblname) throws SQLException {
		int rowid = 0;
		String prkey = getPK(tblname);

		ResultSet r1 = (ResultSet) exerting("select * from " + tblname);
		while (r1.next()) {
			rowid = (int) r1.getInt(prkey);

		}


		return rowid;
	}

	public  int org_getLastId(String tblname, int fld_orgid) throws SQLException {
		int rowid = 0;

		String prkey = getPK(tblname);

		ResultSet r1 = (ResultSet) exerting("select " + prkey + " from " + tblname + " where fld_orgid=" + fld_orgid + " or fld_orgid=1 and isclient=1");
		while (r1.next()) {
			rowid = (int) r1.getInt(prkey);

		}


		return rowid;
	}

	public  int def_getLastId(String tblname, int fld_orgid) throws SQLException {
		int rowid = 0;

		String prkey = getPK(tblname);

		ResultSet r1 = (ResultSet) exerting("select " + prkey + " from " + tblname + " where fld_orgid=" + fld_orgid + " or fld_orgid=1");
		while (r1.next()) {
			rowid = (int) r1.getInt(prkey);

		}


		return rowid;
	}

	public  int conf_getLastId(String tblname, int fld_orgid) throws SQLException {
		int rowid = 0;
		String pk = getPK(tblname);
		System.out.print("primary key  " + pk);
		int entityid = 0;

		LinkedList entity_list = getClientEntities(fld_orgid);

		System.out.print("entity list  " + entity_list);

		ResultSet r1 = (ResultSet) exerting("select " + pk + ",entityid from " + tblname + " where fld_orgid=" + fld_orgid + " or fld_orgid=1");
		while (r1.next()) {
			entityid = (int) r1.getInt("entityid");
			if (entity_list.contains(entityid)) {
				rowid = (int) r1.getInt(pk);
			}

		}

		System.out.print(rowid);

		return rowid;
	}

	public  LinkedList getClientEntities(int fld_orgid) {
		LinkedList entity_list = new LinkedList();
		try {

			ResultSet r2 = (ResultSet) exerting("select * from configs_apptables where fld_orgid=" + fld_orgid + " or fld_orgid=1 and isclient=1");
			while (r2.next()) {
				entity_list.add((int) r2.getInt("entityid"));

			}

		} catch (SQLException ex) {
			Logger.getLogger(dbsquery.class.getName()).log(Level.SEVERE, null, ex);
		}
		return entity_list;
	}

	public  String getPK(String tblname) throws SQLException {
		String pk = "nothing";


		ResultSet r5 = (ResultSet) exerting("select COLUMN_NAME from information_schema.KEY_COLUMN_USAGE where CONSTRAINT_NAME='PRIMARY' AND TABLE_NAME='" + tblname + "'  AND TABLE_SCHEMA='mr_newdb'");
		while (r5.next()) {
			pk = (String) r5.getString("COLUMN_NAME");
		}

		return pk;
	}

	public  LinkedList isColumnPresent(String table_name) throws SQLException {
		LinkedList column_name = new LinkedList();

		ResultSet r6 = (ResultSet)exerting("SELECT column_name FROM information_schema.columns WHERE table_schema = 'mrs_server' AND table_name = '" + table_name + "'");
		while (r6.next()) {

			column_name.add((String) r6.getString("column_name"));
		}

		return column_name;
	}

	public  int isRecPresent(Object createddate, String tablename) throws SQLException {
		int recid = 0;


		ResultSet r1 = (ResultSet) exerting("select fld_svrrowid,fld_createddate from " + tablename + " where fld_createddate='" + createddate + "'");
		while (r1.next()) {
//System.out.println((String)r1.getString("fld_createddate"));
			recid = (int) r1.getInt("fld_svrrowid");

		}
		System.out.println("End of string " + recid);

		return recid;
	}

	public  String insertAndgetId(String sql, String tablename) throws SQLException {
		String recid = "0";
		String result = "0";

		/*	Connection con = null;
		try {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		} catch (ClassNotFoundException cnfe) {
		
		cnfe.getMessage();
		}
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mrs_server", "root", "admin");
		 */

		con.setAutoCommit(false);
		try {
			int i = (int) inserting(sql);
			System.out.println("1");
			ResultSet r1 = (ResultSet) exerting("select MAX(fld_svrrowid) from " + tablename);
			while (r1.next()) {
				recid = "" + (int) r1.getInt("MAX(fld_svrrowid)");

			}

			con.commit();
			result = "1";
		} catch (Exception e) {
			result = "0";
			System.out.print(e.getMessage());
			con.rollback();
			con.setAutoCommit(true);
		}

		con.setAutoCommit(true);


		return result + "," + recid;
	}

	public  int insertAndgetIdOnly(String sql, String tablename) throws SQLException {
		int recid = 0;



		con.setAutoCommit(false);
		try {
			int i = (int) inserting(sql);
			System.out.println("1");
			ResultSet r1 = (ResultSet)exerting("select MAX(fld_svrrowid) from " + tablename);
			while (r1.next()) {
				recid = (int) r1.getInt("MAX(fld_svrrowid)");

			}

			con.commit();

		} catch (Exception e) {

			System.out.print(e.getMessage());
			con.rollback();
			con.setAutoCommit(true);
		}


		con.setAutoCommit(true);

		return recid;
	}

	public  int loc_updatesyncWS(int mrid, String sql, int orgid, String operation, int locid) throws SQLException {

		try {
			ResultSet r1 = (ResultSet) exerting("select fld_svrrowid from tbl_medrep where fld_locid=" + locid + " and fld_svrrowid!=" + mrid);
			while (r1.next()) {

				num = (int) inserting("insert into db_sync (mrid,operations,sqls,isexecuted,locid,createddate) values(" + (int) r1.getInt("fld_svrrowid") + ",'" + operation + "','" + sql + "',false," + locid + ",'" + dateFormat.format(created) + "')");
			}
		} catch (Exception ex) {
		}

		return num;
	}

	public  String updatedandBroadCast(String cancel_sql, String sql, int mrid, int locid, int orgid, String operation, String tablename) throws SQLException {
		String i = "";
		try {
			con.setAutoCommit(false);
			i = (String) insertingWS(cancel_sql);
			if(tablename.equalsIgnoreCase("tbl_accounts") || tablename.equalsIgnoreCase("tbl_contacts"))
			{
			int j = (int) loc_updatesyncWS(mrid, sql, orgid, operation, locid);
			}
			con.commit();
		} catch (Exception ex) {
			con.rollback();
			i = ex.getMessage();
			con.setAutoCommit(true);
		}
		con.setAutoCommit(true);
		return i;
	}

	public  String getChildTables(String tablename,String prkey) throws SQLException
	{
	//LinkedList l1=new LinkedList();
	String tablecolumn ="";
	ResultSet r1=(ResultSet)exerting("SELECT * FROM information_schema.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_NAME = '"+tablename+"' AND REFERENCED_COLUMN_NAME = '"+prkey+"' AND TABLE_SCHEMA='mr_newdb'");
	while(r1.next())
	{
//	System.out.println("table name "+(String)r1.getString("table_name"));
//	System.out.println("column name "+(String)r1.getString("column_name"));
	//l1.add((String)r1.getString("table_name"));
	//l1.add((String)r1.getString("column_name"));
    	tablecolumn=tablecolumn+","+(String)r1.getString("table_name")+","+(String)r1.getString("column_name");
	}
	  
	return tablecolumn;
	
	}
   public  ArrayList<String> getChildTableNames(String tablename,String prkey) throws SQLException
	{
	//LinkedList l1=new LinkedList();
	ArrayList<String> tablecolumn =new ArrayList<String>();
	ResultSet r1=(ResultSet)exerting("SELECT * FROM information_schema.KEY_COLUMN_USAGE WHERE REFERENCED_TABLE_NAME = '"+tablename+"' AND REFERENCED_COLUMN_NAME = '"+prkey+"' AND TABLE_SCHEMA='mr_newdb'");
	while(r1.next())
	{
//	System.out.println("table name "+(String)r1.getString("table_name"));
//	System.out.println("column name "+(String)r1.getString("column_name"));
	//l1.add((String)r1.getString("table_name"));
	//l1.add((String)r1.getString("column_name"));
    	tablecolumn.add((String)r1.getString("table_name"));
	}
	  
	return tablecolumn;
	
	}
	 
   public  int org_AssessmentStatus(int assessmentref,String status, int orgid,int planid) throws SQLException {

String sql="";
		ResultSet r1 = (ResultSet) exerting("select fld_svrrowid,fld_locid from tbl_medrep where fld_orgid=" + orgid);
		while (r1.next()) 
		{

			int i = (int) inserting("insert into tbl_assessmentsstatus (fld_assessmentid,fld_planref,fld_assessmentstatus,fld_currentquestion,fld_mrid,fld_locid,fld_orgid,fld_createddate,fld_modifieddate) values(" + assessmentref + "," + planid + ",'" + status + "',0," + (int) r1.getInt("fld_svrrowid") + ","+(int) r1.getInt("fld_locid")+"," + orgid+ ",'" + dateFormat.format(created) + "','" + dateFormat.format(created) + "')");
		    sql="insert into tbl_assessmentsstatus (fld_assessmentid,fld_planref,fld_assessmentstatus,fld_currentquestion,fld_mrid,fld_locid,fld_orgid,fld_createddate,fld_modifieddate) values(" + assessmentref + "," + planid + ",''" + status + "'',0," + (int) r1.getInt("fld_svrrowid") + ","+(int) r1.getInt("fld_locid")+"," + orgid+ ",''" + dateFormat.format(created) + "'',''" + dateFormat.format(created) + "'')";
			
			System.out.println(r1.getInt("fld_svrrowid"));
		}

     int j=(int) org_updatesync(sql,orgid,"insert");
		return 0;
	}

   public  String getQueryListingFields(int entityid) throws SQLException
	{
	//LinkedList l1=new LinkedList();
	String queryref="";
	ResultSet r1=(ResultSet)exerting("select queryreffields from configs_apptables where entityid="+entityid);
	while(r1.next())
	{
//	System.out.println("table name "+(String)r1.getString("table_name"));
//	System.out.println("column name "+(String)r1.getString("column_name"));
	//l1.add((String)r1.getString("table_name"));
	//l1.add((String)r1.getString("column_name"));
    	queryref=(String)r1.getString("queryreffields");
	}
	  
	return queryref;
	
	}
}
