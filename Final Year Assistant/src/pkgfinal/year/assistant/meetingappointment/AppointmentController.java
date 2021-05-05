/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.meetingappointment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AppointmentController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private Pane leftpane;
    @FXML
    private DatePicker new_appontment_date;
    @FXML
    private Pane Hrs_Entered_Pane;
    @FXML
    private TextField HRS;
    @FXML
    private TextField MIN;
    @FXML
    private TextField appointee_phone_number;
    @FXML
    private Pane AM_7_box;
    @FXML
    private Label AM_7;
    @FXML
    private Pane AM_8_box;
    @FXML
    private Label AM_8;
    @FXML
    private Pane AM_9_box;
    @FXML
    private Pane AM_10_box;
    @FXML
    private Pane AM_11_box;
    @FXML
    private Pane AM_12_box;
    @FXML
    private Pane PM_1_box;
    @FXML
    private Pane PM_2_box;
    @FXML
    private Pane PM_3_box;
    @FXML
    private Pane PM_4_box;
    @FXML
    private Pane PM_5_box;
    @FXML
    private Pane PM_6_box;
    
    String Appointment_Time;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HRS_onaction(ActionEvent event) {
    }

    @FXML
    private void MIN_onaction(ActionEvent event) {
    }

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage)rootpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void reschedule_action(ActionEvent event) {
        //onclick reschedule
         String apointee_phone = appointee_phone_number.getText();
        String appontee_date = new_appontment_date.toString();
        String appointee_hrs = HRS.getText();
        String appointee_min = MIN.getText();
        
        if(apointee_phone.isEmpty()||appontee_date.isEmpty()||appointee_hrs.isEmpty()||appointee_min.isEmpty()){
JOptionPane.showMessageDialog(null, "Please Enter All Fields","Error Occurred",JOptionPane.ERROR_MESSAGE);
 }
    }

    @FXML
    private void AM_7_box_cliked(MouseEvent event) {
        Appointment_Time= "7 AM";
    }

    @FXML
    private void AM_8_box_cliked(MouseEvent event) {
        Appointment_Time= "8 AM";
    }

    @FXML
    private void AM_9_box_cliked(MouseEvent event) {
        Appointment_Time= "9 AM";
    }

    @FXML
    private void AM_10_box_cliked(MouseEvent event) {
        Appointment_Time= "10 AM";
    }

    @FXML
    private void AM_11_box_cloked(MouseEvent event) {
        Appointment_Time= "11 AM";
    }

    @FXML
    private void AM_12_box_cloked(MouseEvent event) {
        Appointment_Time= "12 Noon";
    }

    @FXML
    private void PM_1_box_cliked(MouseEvent event) {
        Appointment_Time= "1 PM";
    }

    @FXML
    private void PM_2_box_cliked(MouseEvent event) {
        Appointment_Time= "2 PM";
    }

    @FXML
    private void PM_3_box_cliked(MouseEvent event) {
        Appointment_Time= "3 PM";
    }

    @FXML
    private void PM_4_box_cliked(MouseEvent event) {
        Appointment_Time= "4 PM";
    }

    @FXML
    private void PM_5_box_cliked(MouseEvent event) {
        Appointment_Time= "5 PM";
    }

    @FXML
    private void PM_6_box_cliked(MouseEvent event) {
        Appointment_Time= "6 PM";
    }
    
}
