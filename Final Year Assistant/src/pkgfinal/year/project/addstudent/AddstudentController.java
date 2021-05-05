/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.project.addstudent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pkgfinal.year.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author Nelly
 */
public class AddstudentController implements Initializable {

    @FXML
    private AnchorPane spvr_anchor;
    @FXML
    private TextField reg_num;
    @FXML
    private TextField name_stdnt;
    @FXML
    private TextField project_title;
    @FXML
    private TextField email_stdnt;
    @FXML
    private TextField mobile_stdnt;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label label_student_details;
    
    DatabaseHandler databaseHandler;
    
    @FXML
    private AnchorPane rootpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler=DatabaseHandler.getInstance();
        checkData();
    }    

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)rootpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveDetails(ActionEvent event) {
        String regNum = reg_num.getText();
        String nameStdnt = name_stdnt.getText();
        String projectTitle = project_title.getText();
        String emailStdnt = email_stdnt.getText();
        String mobileStdnt = mobile_stdnt.getText();
        
 if(regNum.isEmpty()||nameStdnt.isEmpty()||projectTitle.isEmpty()||emailStdnt.isEmpty()||mobileStdnt.isEmpty()){
JOptionPane.showMessageDialog(null, "Please Enter All Fields","Error Occurred",JOptionPane.ERROR_MESSAGE);
 }
 else{
  String qu= "INSERT INTO STUDENTS VALUES(" +
                "'"+ regNum +"'," +
                "'"+ nameStdnt +"'," +
                "'"+ projectTitle +"'," +
                "'"+ emailStdnt +"'," +
                "'"+ mobileStdnt +"'," +
                ""+ "true" + "" +
                ")";
        System.out.println(qu);
       if( databaseHandler.execAction(qu)){
          JOptionPane.showMessageDialog(null, "SUCCESSFULLY ADDED -------" + nameStdnt + " --------INTO THE DATABASE" , "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
       }
        else//error 
       {
             JOptionPane.showMessageDialog(null, "FAILED:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
       }
    }
    }
    private void checkData() {
       String qu = "SELECT reg_num FROM STUDENTS";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            while (rs.next()) {
                String reg_nmbr = rs.getString("reg_num");
                System.out.println(reg_nmbr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddstudentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
