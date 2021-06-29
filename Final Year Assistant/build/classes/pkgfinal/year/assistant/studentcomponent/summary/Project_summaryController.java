/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.summary;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import pkgfinal.year.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Project_summaryController implements Initializable {

    @FXML
    private TextArea project_summary;
    @FXML
    private TextField reg_num;
    
    DatabaseHandler handler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler= DatabaseHandler.getInstance();
    }    

    @FXML
    private void Regnum_action(ActionEvent event) {
    loadsummary();
    }
   
    private void loadsummary() {
        String reg = reg_num.getText();
        String quer= "SELECT * FROM STUDENTRESOURCESTABLE WHERE Reg_num = '"+ reg +"'";
        ResultSet rs = handler.execQuery(quer);
        
        try {
            while(rs.next()){
            String obj = rs.getString("Project_Summary");
            String title = rs.getString("Project_Title");
            reg_num.setText(title);
            project_summary.setText(obj);
            JOptionPane.showMessageDialog(null, "Your project summary are \n"
                    + obj
                    + "!" , "OBJECTIVES", JOptionPane.INFORMATION_MESSAGE);
            project_summary.setText(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Project_summaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
