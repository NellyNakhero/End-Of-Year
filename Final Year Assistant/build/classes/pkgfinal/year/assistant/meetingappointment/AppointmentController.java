/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.meetingappointment;

import com.jfoenix.effects.JFXDepthManager;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import pkgfinal.year.assistant.database.DatabaseHandler;

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
    
    String Appointment_Time ="";
    DatabaseHandler handler;
    @FXML
    private Label reschedule_appointmnet_heading;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXDepthManager.setDepth(leftpane, 1);
        handler= DatabaseHandler.getInstance();
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
    
     private void checkData() {
       String qu = "SELECT mobile_s FROM SUPERVISORS";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()) {
                String reg_nmbr = rs.getString("mobile_s");
                System.out.println(reg_nmbr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void reschedule_action(ActionEvent event) {
        //onclick reschedule
         String apointee_phone = appointee_phone_number.getText();
         LocalDate appointment_date= new_appontment_date.getValue();
        String appontee_date = appointment_date.toString();
        String appointee_hrs = HRS.getText();
        String appointee_min = MIN.getText();
        
        if(apointee_phone.isEmpty()||appontee_date.isEmpty()||appointee_hrs.isEmpty()||appointee_min.isEmpty()){
          JOptionPane.showMessageDialog(null, "Please Enter All Fields","Error Occurred",JOptionPane.ERROR_MESSAGE);
       }else{
         String quer= "SELECT * FROM SUPERVISORS WHERE mobile_s = '"+ apointee_phone +"'";
        ResultSet rs = handler.execQuery(quer);
        try {
            String supvir_mobile;
            String Supervsr_Name;
            
             while(rs.next()){
            supvir_mobile=rs.getString("mobile_s");
            Supervsr_Name = rs.getString("name_s");
             }  
          // JOptionPane.showMessageDialog(null, "Successfully Accessed The Database... \n"
          //          + "Please wait as we set up your appointment:" , "Success Message", JOptionPane.INFORMATION_MESSAGE);
        
     if(apointee_phone.equals(apointee_phone)){
          JOptionPane.showMessageDialog(null, "Successfully Accessed The Database... \n"
            + "Please wait as we set up your appointment:" , "Success Message", JOptionPane.INFORMATION_MESSAGE);
        
         int riset_count=0;
        riset_count++;
        int choice=JOptionPane.showConfirmDialog(null, "Are you sure you want to set_up new appointment.","Confirm Appointment Operation", JOptionPane.INFORMATION_MESSAGE);
        if(choice==JOptionPane.YES_OPTION){
           String str= "INSERT INTO APPOINTMENTSS VALUES(" +
                "'"+ apointee_phone +"'," +
                "'"+ appontee_date +"'," +
                "'"+ Appointment_Time +"'," +
                "'"+ appointee_hrs +"'," +
                "'"+ appointee_min +"'," +
                ""+ riset_count + ")";
            System.out.println(str);
         if(handler.execAction(str)){
               JOptionPane.showMessageDialog(null, "Congratulations ...Student-Supervisor Appointment set Successfully Completed!"
                       + "\n New Ameeting date is "+ appontee_date+" at "+Appointment_Time+" for "+ appointee_hrs+"hrs and "+ appointee_min+" minutes", "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Issue Operation failed:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
            }
        } else { // if no option is selected
           JOptionPane.showMessageDialog(null, "Issue Operation Cancelled!" , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
        }
        }else{
         JOptionPane.showMessageDialog(null, "Error Accessing The Database... \n"
                 + "The database entry doesnt exist:" , "Success Message", JOptionPane.INFORMATION_MESSAGE);
         
        }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
     int time=0;
    @FXML
    private void AM_7_box_cliked(MouseEvent event) {
        Appointment_Time= "7 AM";
       JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void AM_8_box_cliked(MouseEvent event) {
        Appointment_Time= "8 AM";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void AM_9_box_cliked(MouseEvent event) {
        Appointment_Time= "9 AM";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void AM_10_box_cliked(MouseEvent event) {
        Appointment_Time= "10 AM";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void AM_11_box_cloked(MouseEvent event) {
        Appointment_Time= "11 AM";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void AM_12_box_cloked(MouseEvent event) {
        Appointment_Time= "12 Noon";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void PM_1_box_cliked(MouseEvent event) {
        Appointment_Time= "1 PM";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void PM_2_box_cliked(MouseEvent event) {
        Appointment_Time= "2 PM";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void PM_3_box_cliked(MouseEvent event) {
        Appointment_Time= "3 PM";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void PM_4_box_cliked(MouseEvent event) {
        Appointment_Time= "4 PM";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void PM_5_box_cliked(MouseEvent event) {
        Appointment_Time= "5 PM";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void PM_6_box_cliked(MouseEvent event) {
        Appointment_Time= "6 PM";
        JOptionPane.showMessageDialog(null, "Selected appointment time at!" + Appointment_Time , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
     }

    @FXML
    private void reschedule_mouse_click(MouseEvent event) {
    }
    
}
