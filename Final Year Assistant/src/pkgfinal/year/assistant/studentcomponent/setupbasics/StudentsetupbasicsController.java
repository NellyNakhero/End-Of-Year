/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.setupbasics;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pkgfinal.year.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StudentsetupbasicsController implements Initializable {

    @FXML
    private Button button_save;
    @FXML
    private Button button_cancel;
    @FXML
    private TextField bsc_student_firstname;
    @FXML
    private TextField bsc_student_lastname;
    @FXML
    private TextField bsc_student_regnum;
    @FXML
    private TextField bsc_student_mobile;
    @FXML
    private TextField bsc_student_email;
    @FXML
    private TextField bsc_student_projectitle;
    @FXML
    private TextField bsc_student_projectbudget;
    @FXML
    private TextArea bsc_student_projectsummary;
    @FXML
    private TextArea bsc_student_projectobjectives;
    @FXML
    private AnchorPane rootpane;
    
    DatabaseHandler databaseHandler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler=DatabaseHandler.getInstance();
    }    

    @FXML
    private void strorage_details_clicked(MouseEvent event) {
    }

    @FXML
    private void button_save_action(ActionEvent event) {
        String student_username = bsc_student_firstname.getText();
        String supervisor_ID = bsc_student_lastname.getText();
        String student_regnum = bsc_student_regnum.getText();
        String student_mobile = bsc_student_mobile.getText();
        String student_email = bsc_student_email.getText();
        String student_projectitle = bsc_student_projectitle.getText();
        String student_projectbudget = bsc_student_projectbudget.getText();
        String student_projectsummary = bsc_student_projectsummary.getText();
        String student_projectobjectives = bsc_student_projectobjectives.getText();
        
        if(student_username.isEmpty()||supervisor_ID.isEmpty()||student_regnum.isEmpty()||student_mobile.isEmpty()||
            student_email.isEmpty()||student_projectitle.isEmpty()||student_projectbudget.isEmpty()||
                student_projectsummary.isEmpty()||student_projectobjectives.isEmpty()){
              JOptionPane.showMessageDialog(null, "Please Enter All Fields","Error Occurred",JOptionPane.ERROR_MESSAGE);
        }
        
       // String sm = spvisorr_id;
        String str= "INSERT INTO STUDENTRESOURCES VALUES(" +
                "'"+ student_regnum +"'," +
                "'"+ supervisor_ID +"'," +
                "'"+ student_projectsummary +"'," +
                "'"+ student_projectobjectives +"'," +
                "'"+ student_projectbudget +"'," +
                "'"+ student_projectitle +"'," +
                "'"+ student_username +"'," +
                "'"+ student_email +"'," +
                //"'"+ student_mobile +"'"  
                "'"+ student_mobile + "')";
                
        System.out.println(str);
          if( databaseHandler.execAction(str)){
          JOptionPane.showMessageDialog(null, "SUCCESSFULLY ADDED ------- Details --------INTO THE DATABASE" , "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
       }
        else//error 
       {
             JOptionPane.showMessageDialog(null, "FAILED:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
       }
        
    }

    @FXML
    private void button_cancel_action(ActionEvent event) {
        Stage stage = (Stage)rootpane.getScene().getWindow();
        stage.close();
    }
    
     private void checkData() {
       String qu = "SELECT Username FROM STUDENTRESOURCES";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            while (rs.next()) {
                String username = rs.getString("Username");
                System.out.println(username);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentsetupbasicsController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
