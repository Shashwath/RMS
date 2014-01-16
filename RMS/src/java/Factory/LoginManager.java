/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import ApplicationInterfaces.Factory_LoginManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Shashwat
 */
public class LoginManager implements Factory_LoginManager {

	  private final Logger log =  Logger.getLogger(this.getClass().getName());  
	  private static LoginManager loginmanager=new LoginManager();
	  private dbsquery dbs = new dbsquery();
	  private DBConfigs dbc = DBConfigs.getInstance();
	  private ResultSet rs;
	  private boolean status;
	  private int webuserid;
	  private ArrayList<Integer> personal;

	  private LoginManager() {
	  }

	  public static LoginManager getInstance() {
			return loginmanager;
	  }

	  @Override
	  public int isExist(String email, String password) {

			webuserid = 0;
			try {
				  rs = dbs.exerting("select fld_prid from tbl_webusers where fld_contactmail='" + email + "' and fld_password='" + password + "'");
				  while (rs.next()) {
						webuserid = (int) rs.getInt("fld_prid");
				  }
			} catch (Exception e) {
			} finally {
				  dbc.clearResultSet(rs);
			}

			return webuserid;
	  }

	  @Override
	  public boolean isBlocked(int webuserid) {
			status = false;
			try {
				  rs = dbs.exerting("select fld_isblocked from tbl_webusers where fld_prid=" + webuserid);
				  while (rs.next()) {
						status = (Boolean) rs.getBoolean("fld_isblocked");
				  }
			} catch (Exception e) {
			} finally {
				  		  dbc.clearResultSet(rs);
			}

			return status;
	  }

	  @Override
	  public ArrayList<Integer> getPersonelIds(int webuserid) {
			personal = new ArrayList<Integer>();
			try {
				  rs = dbs.exerting("select * from tbl_webusers where fld_prid=" + webuserid);
				  while (rs.next()) {
						personal.add((int) rs.getInt("fld_employeeref"));
						personal.add((int) rs.getInt("fld_groupref"));
						personal.add((int) rs.getInt("fld_companyref"));
				  }

			} catch (Exception e) {
			}
			finally
			{}


			return personal;
	  }

	  
}
