/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.supervisorcomponent.login;

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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pkgfinal.year.assistant.supervisorcomponent.settings.PreferenceWrapper;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LoginController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button save;

    /**
     * Initializes the controller class.
     */
    PreferenceWrapper preference;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    preference = PreferenceWrapper.getPreferences();
    }    

    @FXML
    private void login_action(ActionEvent event) {
        title.setText("Student Login!");
        String sname=username.getText();
        String spswd = password.getText();
        //String spswd=DigestUtils.sha1Hex(password.getText());
        
        if(sname.equalsIgnoreCase(preference.getUsername())&&spswd.equals(preference.getPassword())){
        loadWindow("/pkgfinal/year/assistant/supervisorcomponent/dashboard/dashboard.fxml", "SUPERVISOR DASHBOARD");
        ((Stage)username.getScene().getWindow()).close();
            
        } else{
            title.setText("INVALID CREDENTIALS!");
            title.setStyle("-fx-background-color:#d32f2f; -fx-text-fill:white");
        }
       
    }
    
     void loadWindow(String loc,String title){   
      try {
               Parent parent = FXMLLoader.load(getClass().getResource(loc));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle(title);
               stage.setScene(new Scene(parent));
               stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
