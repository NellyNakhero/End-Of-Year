/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.admin.mainwidow;

import com.jfoenix.effects.JFXDepthManager;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import pkgfinal.year.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MainController implements Initializable {

    @FXML
    private StackPane rootpane;
    @FXML
    private TextField Reg_num_Input;
    @FXML
    private Text stdnt_reg_num;
    @FXML
    private Text stdnt_names;
    @FXML
    private Text Availabilty;
    @FXML
    private TextField spvr_id_Input;
    @FXML
    private Text spvr_Name;
    @FXML
    private Text spvr_Mobile;
    @FXML
    private HBox Book_Info;
    @FXML
    private ListView<String> Issue_Data_List;
    @FXML
    private Button settingsButton;
    @FXML
    private HBox student_info;
    @FXML
    private HBox Supervisor_Info;

    DatabaseHandler handler;
    Boolean isreadyforunassign=false;
    @FXML
    private TextField Student_Info_ID;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXDepthManager.setDepth(student_info, 1);
        JFXDepthManager.setDepth(Supervisor_Info, 1);
        handler= DatabaseHandler.getInstance();
    }    

    @FXML
    private void handleMenuClose(ActionEvent event) {
        ((Stage)rootpane.getScene().getWindow()).close();

    }

    @FXML
    private void AddMenuAddStudent(ActionEvent event) {
    loadWindow("/pkgfinal/year/assistant/addstudent/addstudent.fxml","Add Student");
    }

    @FXML
    private void AddMenuAddSupervisor(ActionEvent event) {
    loadWindow("/pkgfinal/year/assistant/addsupervisor/addsupervisor.fxml","Add Supervisor");
    }

    @FXML
    private void LoadViewStudents(ActionEvent event) {
    loadWindow("/pkgfinal/year/assistant/liststudents/studentlist.fxml","View All Students");
    }

    @FXML
    private void LoadViewSupervisors(ActionEvent event) {
        loadWindow("/pkgfinal/year/assistant/listsupervisors/SupervisorsList.fxml","View All Supervisors");
    }

    @FXML
    private void Load_Student_Info(ActionEvent event) {
        clearStudentCache();
        
        String RegNum = Reg_num_Input.getText();
        String quer= "SELECT * FROM STUDENTS WHERE reg_num = '"+ RegNum +"'";
        ResultSet rs = handler.execQuery(quer);
        Boolean flag = false;
        
           try {
            while(rs.next()){
                String regstr_num = rs.getString("reg_num");
                String studnt_name = rs.getString("name_stdnt");
                Boolean Status = rs.getBoolean("isAvail");
                
                stdnt_reg_num.setText(regstr_num);
                stdnt_names.setText(studnt_name);
                String status= (Status)? "Available":"Not Available";
                Availabilty.setText(status);
                
                flag = true;
            }
            if(!flag){
                stdnt_reg_num.setText("No Such Student In The Database!");
                stdnt_names.setText("");
                Availabilty.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadSupervisorInfo(ActionEvent event) {
        clearSupervisorCache();
        
        String NationalID = spvr_id_Input.getText();
        String quer= "SELECT * FROM SUPERVISORS WHERE id_number_s = '"+ NationalID +"'";
        ResultSet rs = handler.execQuery(quer);
        Boolean flag = false;
        
        try {
            while(rs.next()){
                String Supervsr_Name = rs.getString("name_s");
                String Supervsr_Contact = rs.getString("mobile_s");
                
                spvr_Name.setText(Supervsr_Name);
                spvr_Mobile.setText(Supervsr_Contact);
                
                flag = true;
            }
            if(!flag){
               spvr_Name.setText("No Such Supervisor In The Database");
                spvr_Mobile.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Load_Isuue_Operation(ActionEvent event) {
      String student_num = Reg_num_Input.getText();
      String supervisor_ID = spvr_id_Input.getText();
      
       int choice=JOptionPane.showConfirmDialog(null, "Are you sure you want to assign student "+ stdnt_names.getText()+
                " to "+spvr_Name.getText(),"Confirm Issue Operation", JOptionPane.INFORMATION_MESSAGE);
        if(choice==JOptionPane.YES_OPTION){
              String str= "INSERT INTO ISSUE(Reg_num,ID_number_s) VALUES(" +
                "'"+ student_num +"'," +
                "'"+ supervisor_ID + "')";
        System.out.println(str);
        
        String str2= "UPDATE STUDENTS SET isAvail = false WHERE reg_num = '" + student_num + "'";
            System.out.println(str2);
            if(handler.execAction(str)&& handler.execAction(str2)){
               JOptionPane.showMessageDialog(null, "Congratulations ...Student-Supervisor Issued Successfully Completed!" , "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Issue Operation failed:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
            }
        } else { // if no option is selected
           JOptionPane.showMessageDialog(null, "Issue Operation Cancelled!" , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    @FXML
    private void LoadRenewOperation(ActionEvent event) {
        //TO INCREASE THE NUMBER STIDENT HAS CONSULTED COUNT
          if( !isreadyforunassign){
           JOptionPane.showMessageDialog(null, "Please Select athe Student To Whom You Are Renewing Consultation Count:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
            return;
        }
      int choice=JOptionPane.showConfirmDialog(null, "Are you sure you want to renew consultation count for the student? "
               ,"Confirm Issue Operation", JOptionPane.INFORMATION_MESSAGE);
       if(choice==JOptionPane.YES_OPTION){
           
        String action= "UPDATE ISSUE SET IssueTime= CURRENT_TIMESTAMP, renew_count= renew_count + 1 WHERE Reg_num = '"+ Student_Info_ID.getText() +"'";
        
          if(handler.execAction(action)){
            JOptionPane.showMessageDialog(null, "The Student's Consulatation Count Has Been Renewed succesfully!" , "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);   
           }else{
            JOptionPane.showMessageDialog(null, "Updating Of Consultation Count Operation Failed:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
          }
       }else{
            JOptionPane.showMessageDialog(null, "Operation Cancelled!" , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);            
        }
    
    }

    @FXML
    private void Load_Submission_Operation(ActionEvent event) {
        //removing student from being assigned to the supervisor
         if( !isreadyforunassign){
           JOptionPane.showMessageDialog(null, "Please Select A Student To Be Unassigned:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int choice=JOptionPane.showConfirmDialog(null, "Are you sure you want to unassign the student from the supervisor? "
               ,"Confirm Delete Operation", JOptionPane.INFORMATION_MESSAGE);
        if(choice==JOptionPane.YES_OPTION){
        String S_ID= Student_Info_ID.getText();
        String query1= "DELETE FROM ISSUE WHERE Reg_num = '"+ S_ID +"'";
        String query2= "UPDATE STUDENTS SET isAvail= true WHERE reg_num = '"+ S_ID +"'";
        
        if(handler.execAction(query1)&& handler.execAction(query2)){
           JOptionPane.showMessageDialog(null, "The Student has been succesfully unassigned from the supervisor!" , "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Student-Supervisor Unassignment Operation failed:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Operation Cancelled!" , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);            
        }
        
        
    }

    @FXML
    private void loadAddStudent(ActionEvent event) {
    loadWindow("/pkgfinal/year/assistant/addstudent/addstudent.fxml","Add Student");
    }

    @FXML
    private void loadAddSupervisor(ActionEvent event) {
     loadWindow("/pkgfinal/year/assistant/addsupervisor/addsupervisor.fxml","Add Supervisor");
    }

    @FXML
    private void loadStudentsTable(ActionEvent event) {
    loadWindow("/pkgfinal/year/assistant/liststudents/studentlist.fxml","View All Students");
    }

    @FXML
    private void loadSupervisorsTable(ActionEvent event) {
        loadWindow("/pkgfinal/year/assistant/listsupervisors/SupervisorsList.fxml","View All Supervisors");
    }

    @FXML
    private void Load_Settings_Operation(ActionEvent event) {
        loadWindow("/pkgfinal/year/assistant/admin/settings/settings.fxml","Revise Settings");    
    }
   
    void loadWindow(String loc,String title){   
      try {
               Parent parent = FXMLLoader.load(getClass().getResource(loc));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle(title);
               stage.setScene(new Scene(parent));
               stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

       } 
       
       void clearStudentCache() {
        stdnt_reg_num.setText("");
        stdnt_names.setText("");
        Availabilty.setText("");
    }

    void clearSupervisorCache() {
        spvr_Name.setText("");
        spvr_Mobile.setText("");
    }

    @FXML
    private void LoadAllStudentInfo(ActionEvent event) {
         ObservableList <String> issuedata= FXCollections.observableArrayList();
        isreadyforunassign = false;
        
        String S_ID= Student_Info_ID.getText();
        String quer= "SELECT * FROM ISSUE WHERE reg_num = '"+ S_ID +"'";
        ResultSet rs= handler.execQuery(quer);
        
        try {
            while(rs.next()){
               String STDT_ID=  S_ID;
               String SupNatID= rs.getString("id_number_s");
               Timestamp G_issue_time = rs.getTimestamp("IssueTime");
               int G_renewtimes = rs.getInt("renew_count");
               
               issuedata.add("Issue Date and Time: "+ G_issue_time.toString());
               issuedata.add("Number Of Times Consulted: "+ G_renewtimes);
               
               issuedata.add("\n");
               issuedata.add("STUDENT INFORMATION :-");
                String que= "SELECT * FROM STUDENTS WHERE reg_num = '"+ STDT_ID +"'";
                ResultSet rsl= handler.execQuery(que);
                while(rsl.next()){
                    issuedata.add("REGISTRATION NUMBER : " + rsl.getString("reg_num"));
                    issuedata.add("STUDENT NAME : " + rsl.getString("name_stdnt"));
                    issuedata.add("PROJECT TITLE : " + rsl.getString("project_title"));
                    issuedata.add("EMAIL ADDRESS : " + rsl.getString("email_stdnt")); 
                    issuedata.add("MOBILE NUMBER : " + rsl.getString("mobile_stdnt")); 
                }
                
                issuedata.add("\n");
                issuedata.add("SUPERVISING LECTURER INFORMATION :-");
                String qu= "SELECT * FROM SUPERVISORS WHERE id_number_s = '"+ SupNatID +"'";
                ResultSet rslt= handler.execQuery(qu);
                while(rslt.next()){
                    issuedata.add("SUPERVISORS NAME : " + rslt.getString("name_s"));
                    issuedata.add("SUPERVISORS NATIONAL ID : " + rslt.getString("id_number_s"));
                    issuedata.add("SUPERVISORS EMAIL: " + rslt.getString("email_s"));
                    issuedata.add("SUPERVISORS TELEPHONE : " + rslt.getString("mobile_s"));
                                   }
          isreadyforunassign=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Issue_Data_List.getItems().setAll(issuedata);
    }

    @FXML
    private void search_student(ActionEvent event) {
     JOptionPane.showMessageDialog(null, "This feature is still under development.\n Sorry for any inconviniences" , "SYSTEM UPDATE", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void search_supervisor(ActionEvent event) {
     JOptionPane.showMessageDialog(null, "This feature is still under development.\n Sorry for any inconviniences" , "SYSTEM UPDATE", JOptionPane.INFORMATION_MESSAGE);
    }

       }
