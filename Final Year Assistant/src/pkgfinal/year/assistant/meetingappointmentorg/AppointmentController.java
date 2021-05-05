package pkgfinal.year.assistant.meetingappointmentorg;

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
import pkgfinal.year.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AppointmentController implements Initializable {

    @FXML
    private DatePicker new_appontment_date;
    @FXML
    private TextField HRS;
    @FXML
    private TextField MIN;
    @FXML
    private TextField appointee_phone_number;
//
//    
//      String appointment_hrs=HRS.getText();
//      String appointment_mins=MIN.getText();
//      LocalDate appointment_date= new_appontment_date.getValue();
//      String appointee_mobile= appointee_phone_number.getText();
//      DatabaseHandler handler;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Pane leftpane;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXDepthManager.setDepth(leftpane, 1);
     //   handler= DatabaseHandler.getInstance();
    }    

    @FXML
    private void HRS_onaction(ActionEvent event) {
        
        
    }

    @FXML
    private void MIN_onaction(ActionEvent event) {
    }

    @FXML
    private void reschedule_action(ActionEvent event) {
//        String quer= "SELECT * FROM SUPERVISORS WHERE mobile_s = '"+ appointee_mobile +"'";
//        ResultSet rs = handler.execQuery(quer);
//        try {
//            String supvir_mobile=rs.getString("mobile_s");
//        } catch (SQLException ex) {
//            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        String appointdate=appointment_date + "";
//        int riset_count=0;
//        int choice=JOptionPane.showConfirmDialog(null, "Are you sure you want to set_up new appointment ","Confirm Appointment Operation", JOptionPane.INFORMATION_MESSAGE);
//        if(choice==JOptionPane.YES_OPTION){
//              String str= "INSERT INTO APPOINTMENT(supervisor_w_appointment_mobile,AppointmentDate,"
//                      + "AppointmentHours,AppointmentMins,reset_count) VALUES(" +
//                "'"+ appointee_mobile +"'," +
//                "'"+ appointdate +"'," +
//                "'"+ appointment_hrs +"'," +
//                "'"+ appointment_mins +"'," +
//                "'"+ riset_count + "')";
//        System.out.println(str);
//         if(handler.execAction(str)){
//               JOptionPane.showMessageDialog(null, "Congratulations ...Student-Supervisor Appointment set Successfully Completed!" , "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
//            }
//            else{
//                JOptionPane.showMessageDialog(null, "Issue Operation failed:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
//            }
//        } else { // if no option is selected
//           JOptionPane.showMessageDialog(null, "Issue Operation Cancelled!" , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
//        }
    }

    @FXML
    private void Cancel(ActionEvent event) {
        Stage stage = (Stage)rootpane.getScene().getWindow();
        stage.close();
    }
    
    
}
