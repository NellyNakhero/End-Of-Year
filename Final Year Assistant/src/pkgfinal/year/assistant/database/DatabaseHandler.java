/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Nelly
 */
public class DatabaseHandler {
    
    private final static Logger LOGGER = LogManager.getLogger(DatabaseHandler.class.getName());
    public static String student_reg_number_glbl="PXXX/XXXX/XX";
    public static String student_mobile_number_glbl="+2547XXXXXXXXX";
    public static String supervisor_staff_id_glbl="PXXX/XXXX/XX";
    
    
//...............................................................................................................................................
    private static DatabaseHandler handler = null;
    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;
//...............................................................................................................................................
    
    public DatabaseHandler(){
        createConnection();
        setUpStudentTable();
        setUpSupervisorTable();
        setUpIssueTable();
        setUpAppointmentTable();
        setUpStudentResoucesTable();
    }
//...............................................................................................  
    public static DatabaseHandler getInstance(){
          if(handler == null){
              handler= new DatabaseHandler();
          }
          return handler;
      }
//............................................................................................................................................    
    private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cant load database", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
 //...........................................................................................................//
  void setUpStudentTable(){ 
       String  TABLE_NAME = "STUDENTS";
        try {
            
            stmt= (Statement) conn.createStatement();
            
           DatabaseMetaData dbm = conn.getMetaData();
           
           ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(),null);
           
           if(tables.next()){
               System.out.println("Table "+ TABLE_NAME+" for STUDENTS already exists. Ready to go!");
           }
           else{
               stmt.execute("CREATE TABLE "+ TABLE_NAME + "("
               +" reg_num varchar(200) primary key, \n"
               +" name_stdnt varchar(200), \n"
               + "project_title varchar(200), \n"
               +"email_stdnt varchar(100), \n"
               +"mobile_stdnt varchar(100), \n"
               +"isAvail boolean default true"
               +")");
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()+"............set up database");
        } finally {       
        }
    }

     
 //...........................................................................................................//
  void setUpSupervisorTable(){ 
       String  TABLE_NAME = "SUPERVISORS";
        try {
            
            stmt= (Statement) conn.createStatement();
            
           DatabaseMetaData dbm = conn.getMetaData();
           
           ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(),null);
           
           if(tables.next()){
               System.out.println("Table "+ TABLE_NAME+" for SUPERVISORS already exists. Ready to go!");
           }
           else{
               stmt.execute("CREATE TABLE "+ TABLE_NAME + "("
               +" id_number_s varchar(200) primary key, \n"
               +" name_s varchar(200), \n"
               + "email_s varchar(200), \n"
               +"mobile_s varchar(100)"
               +")");
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()+"............set up database");
        } finally {       
        }
    } 
//...............................................................................................................
    void setUpAppointmentTable(){
        String  TABLE_NAME = "APPOINTMENT";
         try {
            
            stmt= (Statement) conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
           
           ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(),null);
           
           if(tables.next()){
               System.out.println("Table "+ TABLE_NAME+" for Appointments already exists. Ready to go!");
           }
           else{
               stmt.execute("CREATE TABLE "+ TABLE_NAME + "("
               +"supervisor_w_appointment_mobile varchar(200) primary key, \n"
               + "AppointmentDate varchar(200), \n"
               + "AppointmentTime varchar(200), \n"
               + "AppointmentHours varchar(200), \n"
               + "AppointmentMins varchar(200), \n"
               + "reset_count integer default 0 \n"
               +")");
           }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//................................................................................................................
    void setUpIssueTable(){
        String  TABLE_NAME = "ISSUE";
         try {
            
            stmt= (Statement) conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
           
           ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(),null);
           
           if(tables.next()){
               System.out.println("Table "+ TABLE_NAME+" for Assigning already exists. Ready to go!");
           }
           else{
               stmt.execute("CREATE TABLE "+ TABLE_NAME + "("
               +"Reg_num varchar(200) primary key, \n"
               + "ID_number_s varchar(200), \n"
               + "IssueTime timestamp default CURRENT_TIMESTAMP, \n"
               + "renew_count integer default 0, \n"
               +"FOREIGN KEY (Reg_num) REFERENCES STUDENTS(reg_num), \n"
               +"FOREIGN KEY (ID_number_s) REFERENCES SUPERVISORS(id_number_s)"
               +")");
           }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//................................................................................................................
    void setUpStudentResoucesTable(){
        String  TABLE_NAME = "STUDENTRESOURCES";
         try {
            
            stmt= (Statement) conn.createStatement();
            
            DatabaseMetaData dbm = conn.getMetaData();
           
           ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(),null);
           
           if(tables.next()){
               System.out.println("Table "+ TABLE_NAME+" for chats, images and docs for student already exists. Ready to go!");
           }
           else{
               stmt.execute("CREATE TABLE "+ TABLE_NAME + "("
               +"Reg_num varchar(200) primary key, \n"
               + "ID_number_s varchar(200), \n"
               + "Project_Summary varchar (500), \n"
               + "Project_Objectives varchar (500), \n"
               + "Project_Budget varchar (500), \n"
               + "Project_ToDo varchar (500), \n"
               +"FOREIGN KEY (Reg_num) REFERENCES STUDENTS(reg_num), \n"
               +"FOREIGN KEY (ID_number_s) REFERENCES SUPERVISORS(id_number_s)"
               +")");
           }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//................................................................................................  
    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        }
        catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally {
        }
        return result;
    }
//................................................................................................
    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        }
        finally {
        }
    }
    
}
