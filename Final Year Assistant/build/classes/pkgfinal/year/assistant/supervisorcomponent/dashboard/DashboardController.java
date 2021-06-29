/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.supervisorcomponent.dashboard;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pkgfinal.year.assistant.supervisorcomponent.settings.PreferenceWrapper;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DashboardController implements Initializable {

    @FXML
    private HBox TITLE;
    @FXML
    private ImageView messages;
    @FXML
    private ImageView notifications;
    @FXML
    private Text name;

    /**
     * Initializes the controller class.
     */
    
    PreferenceWrapper preference;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    preference = PreferenceWrapper.getPreferences();
    String sName = preference.getUsername();
    name.setText(sName);
    }    

    @FXML
    private void messages_clicked(MouseEvent event) {
    loadWindow("/pkgfinal/year/assistant/studentcomponent/listmessages/listMessages.fxml", "Conversations");
    }

    @FXML
    private void notifications_clicked(MouseEvent event) {
    loadWindow("/pkgfinal/year/assistant/studentcomponent/listappointments/listAppointments.fxml", "APPOINTMENTS PAGE");
    }

    @FXML
    private void currently_Supervised_list_click(MouseEvent event) {
    loadWindow("/pkgfinal/year/assistant/supervisorcomponent/currentlysupervising/listsupervising.fxml", "LIST SUPERVISED STUDENTS DASHBOARD");
    }


    @FXML
    private void admin_clicked(MouseEvent event) {
    loadWindow("/pkgfinal/year/assistant/admin/mainwidow/main.fxml", "ADMIN PRIVILEDGES DASHBOARD");
    }

    @FXML
    private void appointments_click(MouseEvent event) {
      loadWindow("/pkgfinal/year/assistant/studentcomponent/listappointments/listAppointments.fxml", "APPOINTMENTS PAGE");
    }

    @FXML
    private void calender_clicked(MouseEvent event) {
    loadWindow("/pkgfinal/year/assistant/studentcomponent/calendercomponent/toggle_calender.fxml", "Calender View");
    }

    @FXML
    private void settings_clicked(MouseEvent event) {
loadWindow("/pkgfinal/year/assistant/supervisorcomponent/settings/settings.fxml", "SETTINGS PAGE");
    }

    @FXML
    private void logout_clicked(MouseEvent event) {
        Stage stage = (Stage)TITLE.getScene().getWindow();
        stage.close();
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

    @FXML
    private void chat_Clicked(MouseEvent event) {
     loadWindow("/pkgfinal/year/assistant/studentcomponent/ChatApp/ChatApp.fxml", "PROJECT CONVERSATION PAGE");

    }

    private void currently_Supervised_list_click(ActionEvent event) {
    loadWindow("/pkgfinal/year/assistant/supervisorcomponent/currentlysupervising/listsupervising.fxml", "LIST SUPERVISED STUDENTS DASHBOARD");

    }

    private void settings_clicked(ActionEvent event) {
loadWindow("/pkgfinal/year/assistant/supervisorcomponent/settings/settings.fxml", "SETTINGS PAGE");
    }

    private void appointments_click(ActionEvent event) {
    loadWindow("/pkgfinal/year/assistant/studentcomponent/listappointments/listAppointments.fxml", "APPOINTMENTS PAGE");
    }

    @FXML
    private void currently_Super_list_click(ActionEvent event) {
        loadWindow("/pkgfinal/year/assistant/supervisorcomponent/currentlysupervising/listsupervising.fxml", "LIST SUPERVISED STUDENTS DASHBOARD");
    }

    @FXML
    private void reset_clicked(ActionEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/listappointments/listAppointments.fxml", "APPOINTMENTS PAGE");
    loadWindow("/pkgfinal/year/assistant/supervisorcomponent/settings/settings.fxml", "SETTINGS PAGE");
    }

    @FXML
    private void appointments_list_click(ActionEvent event) {
    }
    
}
