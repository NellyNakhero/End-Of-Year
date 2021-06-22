/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.objectives;

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
public class Project_objectivesController implements Initializable {

    @FXML
    private TextArea project_objectives;
    @FXML
    private TextField reg_num;
    
    DatabaseHandler handler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler= DatabaseHandler.getInstance();
      //  checkData();
       // loadobjectives();
    }    

    private void loadobjectives() {
        String reg = reg_num.getText();
        String quer= "SELECT * FROM STUDENTRESOURCESTABLE WHERE Reg_num = '"+ reg +"'";
        ResultSet rs = handler.execQuery(quer);
        
        try {
            while(rs.next()){
            String obj = rs.getString("Project_Objectives");
            String title = rs.getString("Project_Title");
            reg_num.setText(title);
            project_objectives.setText(obj);
            JOptionPane.showMessageDialog(null, "Your project objectives are \n"
                    + obj
                    + "!" , "OBJECTIVES", JOptionPane.INFORMATION_MESSAGE);
            project_objectives.setText(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Project_objectivesController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
     private void checkData() {
       String qu = "SELECT Project_Objectives FROM STUDENTRESOURCESTABLE";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()) {
                String objectives = rs.getString("Project_Objectives");
                System.out.println(objectives);
                project_objectives.setText(objectives);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Project_objectivesController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    @FXML
    private void Regnum_action(ActionEvent event) {
        loadobjectives();
    }
    
}
