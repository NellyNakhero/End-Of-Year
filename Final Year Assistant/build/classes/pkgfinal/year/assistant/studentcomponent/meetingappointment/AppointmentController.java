/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.meetingappointment;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pkgfinal.year.assistant.addstudent.AddstudentController;
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
    private TextField HRS;
    @FXML
    private TextField MIN;
    @FXML
    private TextField appointee_phone_number;
    @FXML
    private Pane Hrs_Entered_Pane;
    
    DatabaseHandler handler;

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
        String appointee_mobile= appointee_phone_number.getText();
        String appointment_hrs=HRS.getText();
        String appointment_mins=MIN.getText();
        String Supervsr_Name;
        LocalDate appointment_date= new_appontment_date.getValue();
        String ddate=appointment_date.toString();
        
        if(appointee_mobile.isEmpty()||appointment_hrs.isEmpty()||appointment_mins.isEmpty()||ddate.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Enter All Fields","Error Occurred",JOptionPane.ERROR_MESSAGE);
        }
        checkData();
        String quer= "SELECT * FROM SUPERVISORS WHERE mobile_s = '"+ appointee_mobile +"'";
        ResultSet rs = handler.execQuery(quer);
        try {
             while(rs.next()){
            String supvir_mobile=rs.getString("mobile_s");
            Supervsr_Name = rs.getString("name_s");
             }  
              JOptionPane.showMessageDialog(null, "Successfully Accessed The Database... \n"
                      + "Please wait as we set up your appointment:" , "Success Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String appointdate=appointment_date + "";
        int riset_count=0;
        riset_count++;
        int choice=JOptionPane.showConfirmDialog(null, "Are you sure you want to set_up new appointment.","Confirm Appointment Operation", JOptionPane.INFORMATION_MESSAGE);
        if(choice==JOptionPane.YES_OPTION){
           String str= "INSERT INTO APPOINTMENT(supervisor_w_appointment_mobile,AppointmentDate,"
                      + "AppointmentHours,AppointmentMins,reset_count) VALUES(" +
                "'"+ appointee_mobile +"'," +
                "'"+ appointdate +"'," +
                "'"+ appointment_hrs +"'," +
                "'"+ appointment_mins +"'," +
                ""+ riset_count + ")";
            System.out.println(str);
         if(handler.execAction(str)){
               JOptionPane.showMessageDialog(null, "Congratulations ...Student-Supervisor Appointment set Successfully Completed!"
                       + "\n New Ameeting date is "+ appointdate+" for "+ appointment_hrs+"hrs and "+ appointment_mins+" minutes", "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Issue Operation failed:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
            }
        } else { // if no option is selected
           JOptionPane.showMessageDialog(null, "Issue Operation Cancelled!" , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
        }
          
    }
 

}
