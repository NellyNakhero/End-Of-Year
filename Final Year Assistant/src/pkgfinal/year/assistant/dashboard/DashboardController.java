/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.dashboard;

import com.jfoenix.effects.JFXDepthManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pkgfinal.year.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DashboardController implements Initializable {

    @FXML
    private VBox students_vbox;
    @FXML
    private VBox supervisors_vbox;
    @FXML
    private VBox coordinators_vbox;

    /**
     * Initializes the controller class.
     */
    DatabaseHandler handler;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXDepthManager.setDepth(students_vbox, 1);
        JFXDepthManager.setDepth(coordinators_vbox, 1);
    }    

    @FXML
    private void student_login(ActionEvent event) {
    loadWindow("/pkgfinal/year/assistant/studentcomponent/login/student_login.fxml","STUDENT LOGIN");    
    }

    @FXML
    private void supervisor_login(ActionEvent event) {
    loadWindow("/pkgfinal/year/assistant/supervisorcomponent/login/login.fxml","SUPERVISOR LOGIN");    
    }

    @FXML
    private void coordinator_login(ActionEvent event) {
    loadWindow("/pkgfinal/year/assistant/admin/LOGIN/login.fxml","COORDINATOR LOGIN");    
    }

    @FXML
    private void FAQs_Action(MouseEvent event) {
    }
    
    void loadWindow(String loc,String title){   
      try {
               Parent parent = FXMLLoader.load(getClass().getResource(loc));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle(title);
               stage.setScene(new Scene(parent));
               stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
