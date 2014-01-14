/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralMethods;

import ApplicationInterfaces.GeneralMethods_Generals;
import Factory.dbsquery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;


import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Shashwat
 */
public class Generalmethods implements GeneralMethods_Generals{

   // Generalmethods gm=new Generalmethods();
    dbsquery dbsquery=new dbsquery();
    @Override
    public Boolean chkConfigRecords(int entityid, int recid, int orgid) {
        
        
          Boolean status=false; int increment=0; String tablename="";
       try{
        if(entityid==11111){tablename="configs_apptables";}
        
        if(entityid==22222){tablename="configs_apptablefields";}
        
        if(entityid==33333){tablename="configs_lovs";}
        
        if(entityid==44444){tablename="configs_entitychilditems";}
        
        if(entityid==55555){tablename="tbl_sync";}
        // end
      
       System.out.print("hi  "+entityid+" , "+tablename+" ,"+recid);
           // ResultSet r2=(ResultSet)dbsquery.exerting("select count(*) from "+tablename+" where orgid="+orgid+" ");
        if(entityid==11111)
        {
        increment=dbsquery.org_getLastId(tablename, orgid);
        }
        else
        {
       increment=dbsquery.conf_getLastId(tablename, orgid);
        }
        
        System.out.print("increment is "+increment);
        if(recid==increment)
        {status=true;}
        else{status=false;}
    
        
    }
    catch(Exception ex)
    {  Logger.getLogger(Generalmethods.class.getName()).log(Level.SEVERE, null, ex);
    
    System.out.println(ex.getMessage());
    }
         return status;
        
        
        
    }

    

    @Override
    public String tableName(int entityid) {
      
         String tblname=null;
       try{
         ResultSet r1=(ResultSet)dbsquery.exerting("select tablename from configs_apptables where entityid="+entityid+"");
        while(r1.next())
        {
           tblname=(String)r1.getString("tablename");
        }
       }
       catch(Exception ex)
       {}
        return tblname;
        
    }

    @Override
    public int getNextTable(int entityid, int orgid) {
       
         String tblname=null;
      try {  ResultSet r1=(ResultSet)dbsquery.exerting("select entityid from configs_apptables where entityid >"+entityid+" and fld_orgid=1 and fld_orgid="+orgid+" and isclient=true");
        while(r1.next())
        {
           return (int)r1.getInt("entityid");
        }
      }
      catch(Exception ex)
      {}
        return 0;
   
        
    }

    @Override
    public boolean chkSyncStatus(int mrid) {
       Boolean status=false;
        try {
            
               ResultSet r1=(ResultSet)dbsquery.exerting("select * from db_syncstatus where mrid="+mrid+"");
               while(r1.next())
               { int mr=(int)r1.getInt("mrid");
                   status=true;
               }
              
        } catch (Exception ex) {
            Logger.getLogger(Generalmethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("status is "+status);
         return status;
        
    }

  
	@Override
	public int entityid(String tablename) {
		
		   int entityid=0;
       try{
         ResultSet r1=(ResultSet)dbsquery.exerting("select entityid from configs_apptables where tablename='"+tablename+"'");
        while(r1.next())
        {
           entityid=(int)r1.getInt("entityid");
        }
       }
       catch(Exception ex)
       {}
        return entityid;
       
		
	}

	@Override
	public String clauseKey(String tablename) {
		LinkedList columnlist;
		String clausekey="";
		try {
			columnlist=(LinkedList)dbsquery.isColumnPresent(tablename);
			
			if(columnlist.contains("fld_mrid"))
			{
			clausekey="fld_mrid";
			return "fld_mrid";
			}
			else if(columnlist.contains("fld_locid"))
			{
			clausekey="fld_locid";
			return "fld_locid";
			}
			else if(columnlist.contains("fld_orgid"))
			{
			clausekey="fld_orgid";
			return "fld_orgid";
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(Generalmethods.class.getName()).log(Level.SEVERE, null, ex);
		}
	
	
	return clausekey;
	}


	@Override
	public String insertAndgetId(String sql,String tablename) {
		 String result="0";
		 String res="0";
			try {
				  result = dbsquery.insertAndgetId(sql, tablename);
			res="0";
			} catch (SQLException ex) {
				  Logger.getLogger(Generalmethods.class.getName()).log(Level.SEVERE, null, ex);
			res="0";
			}
		
		return result;
	}

	@Override
	public int isRecordPresent(Object createddate, String tablename) {
		int result=0;
			try {
				  result = dbsquery.isRecPresent(createddate, tablename);
			} catch (SQLException ex) {
				  Logger.getLogger(Generalmethods.class.getName()).log(Level.SEVERE, null, ex);
			}
		System.out.print("result ="+result);
		return result;
	}

	  @Override
	  public String getStructuredSql(String sql) {
	
			String new_sql="";
			for(int i=0;i<sql.length();i++)
			{
			
				  if((""+sql.charAt(i)).equalsIgnoreCase("'"))
				  {
				  new_sql=new_sql+""+sql.charAt(i)+"'";
				  }
				  else
				  {
						new_sql=new_sql+""+sql.charAt(i)+"";
				  }
				  
			}
			
			return new_sql;
	  }

	@Override
	public LinkedList getCredentials(int id, String role) {
		
		LinkedList l=new LinkedList();
		try{
		
		ResultSet r1=(ResultSet)dbsquery.exerting("select fld_email,fld_password from tbl_webusers where fld_memberid="+id);
        while(r1.next())
        {
           l.add((String)r1.getString("fld_email"));
		   l.add((String)r1.getString("fld_password"));
        }
		
		
		
		}
		catch(Exception e)
		{}
		return l;
	}

	@Override
	public String setEmail(String email, String password) {
	
		final String user="shashwat.triya@gmail.com";//change accordingly
final String pass="shashwatsm";
String result="";
//1st step) Get the session object	
Properties props = new Properties();
props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly
props.put("mail.smtp.auth", "true");
//props.put("mail.mime.charset", "UTF-8");
//Session session = request.getSession();
Session session = Session.getInstance(props,
 new javax.mail.Authenticator() {
				  @Override
  protected PasswordAuthentication getPasswordAuthentication() {
   return new PasswordAuthentication(user,pass);
   }
});
//2nd step)compose message
String subject="Hello Please find your Credentilas ";
String msg="Dear User your Email Id="+email+"  and  Password="+password;
try {
 MimeMessage message = new MimeMessage(session);
 message.setFrom(new InternetAddress(user));
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
 message.setSubject(subject);


 message.setText(msg);
 
 //3rd step)send message
 Transport.send(message);

 System.out.println("Done");
result="1";
 }			 catch (MessagingException e) {
result="0";		
 }
return result;
	}

	  @Override
	  public String DisplaytableName(int entityid) {
			  String tblname=null;
       try{
         ResultSet r1=(ResultSet)dbsquery.exerting("select displaylabel from configs_apptables where entityid="+entityid+"");
        while(r1.next())
        {
           tblname=(String)r1.getString("displaylabel");
        }
       }
       catch(Exception ex)
       {}
        return tblname;
	  }

 public String fldDisplaytableName(int entityid,String fldname) {
			  String tblname=null;
       try{
         ResultSet r1=(ResultSet)dbsquery.exerting("select displaylabel from configs_apptablefields where entityid="+entityid+" and fieldname='"+fldname+"'");
        while(r1.next())
        {
           tblname=(String)r1.getString("displaylabel");
        }
       }
       catch(Exception ex)
       {}
        return tblname;
	  }

	  @Override
	  public Boolean chkDataRecords(int entityid, int recid, int orgid) {
			throw new UnsupportedOperationException("Not supported yet.");
	  }

	  @Override
	  public String decideNext(int mrid) {
			throw new UnsupportedOperationException("Not supported yet.");
	  }
    
}
