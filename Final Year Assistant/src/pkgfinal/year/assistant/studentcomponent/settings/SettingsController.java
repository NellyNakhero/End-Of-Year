/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SettingsController implements Initializable {

    @FXML
    private TextField c_username;
    @FXML
    private PasswordField c_password;
    @FXML
    private TextField title;
    @FXML
    private Button save;
    @FXML
    private Button cance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         initDefaultValues();
    }    

    @FXML
    private void save_action(ActionEvent event) {
        String uname= c_username.getText();
        String upassword = c_password.getText();
        String utittle = title.getText();
        
        int choice=JOptionPane.showConfirmDialog(null, "Make Changes To The File?"+uname
                + "\n .","Confirm Operation", JOptionPane.INFORMATION_MESSAGE);
        
        if(choice==JOptionPane.YES_OPTION){
        PreferenceWrapper preferences = PreferenceWrapper.getPreferences();
        preferences.setUsername(uname);
        preferences.setPassword(upassword);
        preferences.setTitle(utittle);
        
        PreferenceWrapper.writePreferenceToFile(preferences);
        }
    }

    @FXML
    private void cancel_action(ActionEvent event) {
        ((Stage)c_username.getScene().getWindow()).close();
    }

    private void initDefaultValues() {
        PreferenceWrapper preferences= PreferenceWrapper.getPreferences();
        c_username.setText(String.valueOf(preferences.getUsername()));
        c_password.setText(String.valueOf(preferences.getPassword()));
        title.setText(String.valueOf(preferences.getTitle()));
    }
    
}
