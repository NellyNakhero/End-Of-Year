/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.dashboard;

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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pkgfinal.year.assistant.database.DatabaseHandler;
import pkgfinal.year.assistant.googledrive.StoragedriveController;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StudentdashboardController implements Initializable {

    @FXML
    private Text name_student;
    @FXML
    private Text projecttitle_student;
    @FXML
    private Button close;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button register;

    DatabaseHandler databaseHandler;
    @FXML
    private Button appointments;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler =DatabaseHandler.getInstance();
    }    

    @FXML
    private void Close(ActionEvent event) {
        Stage stage = (Stage)rootpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void chat_action(MouseEvent event) {
      String link ="https://wa.me/+25479035839";
      loadWindow("/pkgfinal/year/assistant/studentcomponent/ChatApp/ChatApp.fxml", "PROJECT CONVERSATION PAGE");
    }

    @FXML
    private void register_details(ActionEvent event) {
      loadWindow("/pkgfinal/year/assistant/studentcomponent/setupbasics/studentsetupbasics.fxml", "PROJECT SET_UP BASICS PAGE");
    }

    @FXML
    private void homebutton_clicked(MouseEvent event) {
    }

    @FXML
    private void objectives_clicked(MouseEvent event) {
      loadWindow("/pkgfinal/year/assistant/studentcomponent/objectives/project_objectives.fxml", "PROJECT OBJECTIVES PAGE");
    }

    @FXML
    private void calender_clicked(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/calendercomponent/toggle_calender.fxml", "Calender View");
    }

    @FXML
    private void settings_clicked(MouseEvent event) {
    }

    @FXML
    private void drive_clicked(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/googledrive/storagedrive.fxml", "Storage Drive");
    }
    
     void loadWindow(String loc,String title){   
      try {
               Parent parent = FXMLLoader.load(getClass().getResource(loc));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle(title);
               stage.setScene(new Scene(parent));
               stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudentdashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

    @FXML
    private void appointment_details(ActionEvent event) {
        //loadWindow("/pkgfinal/year/assistant/meetingappointment/appointment.fxml", "RESCHEDULE APPOINTMENTS");
    }
}
