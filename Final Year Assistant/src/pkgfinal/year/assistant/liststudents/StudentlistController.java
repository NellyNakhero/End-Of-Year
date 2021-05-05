/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.liststudents;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import pkgfinal.year.assistant.database.DatabaseHandler;
import pkgfinal.year.assistant.addstudent.AddstudentController;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StudentlistController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, String> student_name_col;
    @FXML
    private TableColumn<Student, String> stdnt_regnum_col;
    @FXML
    private TableColumn<Student, String> project_title_col;
    @FXML
    private TableColumn<Student, String> stdnt_email_col;
    @FXML
    private TableColumn<Student, String> stdnt_tel_col;
    @FXML
    private TableColumn<Student, String> stdnt_availability_col;

    ObservableList <Student> list= FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }   

    private void initCol() {
       student_name_col.setCellValueFactory(new PropertyValueFactory<>("student_name"));
       stdnt_regnum_col.setCellValueFactory(new PropertyValueFactory<>("stdnt_regnum"));
       project_title_col.setCellValueFactory(new PropertyValueFactory<>("project_title"));
       stdnt_email_col.setCellValueFactory(new PropertyValueFactory<>("stdnt_email"));
       stdnt_tel_col.setCellValueFactory(new PropertyValueFactory<>("stdnt_tel"));
       stdnt_availability_col.setCellValueFactory(new PropertyValueFactory<>("Availability"));
       
    }

    private void loadData() {
   DatabaseHandler databaseHandler= DatabaseHandler.getInstance();
   
     String qu = "SELECT * FROM STUDENTS";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            while (rs.next()) {
                String reg_nmbr = rs.getString("reg_num");
                System.out.println(reg_nmbr);
                String name_student = rs.getString("name_stdnt");
                System.out.println(name_student);
                String prjct_title = rs.getString("project_title");
                System.out.println(prjct_title);
                String email_student = rs.getString("email_stdnt");
                System.out.println(email_student);
                String mobile_student = rs.getString("mobile_stdnt");
                System.out.println(mobile_student);
                Boolean isAvailable = rs.getBoolean("isAvail");
                System.out.println(isAvailable);
              
                list.add(new Student(reg_nmbr,name_student,prjct_title,email_student,mobile_student,isAvailable ));
            }
        } catch (SQLException ex) { 
            Logger.getLogger(StudentlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(list);

    }
    
      public static class Student{
        private final SimpleStringProperty student_name;
        private final SimpleStringProperty stdnt_regnum;
        private final SimpleStringProperty project_title;
        private final SimpleStringProperty stdnt_email;
        private final SimpleStringProperty stdnt_tel;
        private final SimpleBooleanProperty Availability;
        
    public Student(String student_name,String stdnt_regnum,String project_title,String stdnt_email,String stdnt_tel, Boolean Availability){
            this.student_name= new SimpleStringProperty(student_name);
            this.stdnt_regnum= new SimpleStringProperty(stdnt_regnum);
            this.project_title= new SimpleStringProperty(project_title);
            this.stdnt_email= new SimpleStringProperty(stdnt_email);
            this.stdnt_tel= new SimpleStringProperty(stdnt_tel);
            this.Availability= new SimpleBooleanProperty(Availability);
            
            }

        public String getStudent_name() {
            return student_name.get();
        }

        public String getStdnt_regnum() {
            return stdnt_regnum.get();
        }

        public String getProject_title() {
            return project_title.get();
        }

        public String getStdnt_email() {
            return stdnt_email.get();
        }

        public String getStdnt_tel() {
            return stdnt_tel.get();
        }

        public Boolean getAvailability() {
            return Availability.get();
        }
    
    
      }


    @FXML
    private void handleStudentDeletionOption(ActionEvent event) {
//         Student student_to_be_deleted = tableView.getSelectionModel().getSelectedItem();
//        if(student_to_be_deleted == null){
//            JOptionPane.showMessageDialog(null, "Please select the student details that you want deleted first", "No Student Deleted", JOptionPane.QUESTION_MESSAGE);
//            return;
//        }
//        
//       int choice=JOptionPane.showConfirmDialog(null, "Are you sure you want to delete student details  " +student_to_be_deleted.getStudent_name() + " of registration number "+ student_to_be_deleted.getStdnt_regnum()
//               ,"Confirm Issue Operation", JOptionPane.INFORMATION_MESSAGE);
//       if(choice==JOptionPane.YES_OPTION){
// //          Boolean confm= DatabaseHandler.getInstance().isRoomAlreadyIssued(student_to_be_deleted);
//           if(confm){
//                   JOptionPane.showMessageDialog(null, " The student is already issued and cannot be deleted","Cannot Delete Already assigned student",JOptionPane.ERROR_MESSAGE);
//           }
// //          Boolean result = DatabaseHandler.getInstance().deleteRoom(student_to_be_deleted);
//           if(result){
//               JOptionPane.showMessageDialog(null, "Student "+student_to_be_deleted.getStudent_name()+ "Successfully deleted from Supervison Database");
//           list.remove(student_to_be_deleted);
//           }
//           else{
//               JOptionPane.showMessageDialog(null, "Student "+student_to_be_deleted.getStudent_name()+" Deletion was unsuccessfull!!!", "Error Occurred",JOptionPane.ERROR_MESSAGE);
//           }
//           
//       } else{
//          JOptionPane.showMessageDialog(null, "Delete Operation Cancelled!","Cancel Delete Operation",JOptionPane.PLAIN_MESSAGE);
//       }
    }

    @FXML
    private void handleStudentEditOption(ActionEvent event) {
        
         Student student_to_be_edited = tableView.getSelectionModel().getSelectedItem();
        if(student_to_be_edited == null){
            JOptionPane.showMessageDialog(null, "Please select the student entry that you want Edited first", "No Student Entry Deleted", JOptionPane.QUESTION_MESSAGE);
            return;
        }
        
          try {
              //getting the scene
              FXMLLoader loader= new FXMLLoader(getClass().getResource("/pkgfinal/year/assistant/addstudent/addstudent.fxml"));
               Parent parent = loader.load();
               //inflating the loaded scene
               AddstudentController add_student_controller= (AddstudentController)loader.getController();
  //             student_to_be_edited.inflateUI(student_to_be_edited);
               
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle("Edit Student Entry Information");
               stage.setScene(new Scene(parent));
               stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddstudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
