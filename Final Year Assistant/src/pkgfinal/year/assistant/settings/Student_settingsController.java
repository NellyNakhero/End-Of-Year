/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Student_settingsController implements Initializable {

    @FXML
    private TextField c_username;
    @FXML
    private PasswordField c_password;
    @FXML
    private Button save_action;
    @FXML
    private Button cancel_action;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initDefaultValues();
    }    

    private void initDefaultValues() {
        PreferenceWrapper preferences= PreferenceWrapper.getPreferences();
        c_username.setText(String.valueOf(preferences.getUsername()));
        c_password.setText(String.valueOf(preferences.getPassword()));
    }
    
}
