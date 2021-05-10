/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.setupbasics;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void strorage_details_clicked(MouseEvent event) {
    }

    @FXML
    private void button_save_action(ActionEvent event) {
    }

    @FXML
    private void button_cancel_action(ActionEvent event) {
    }
    
}
