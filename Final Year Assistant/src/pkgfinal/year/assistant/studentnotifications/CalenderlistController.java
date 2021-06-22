/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentnotifications;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pkgfinal.year.assistant.database.DatabaseHandler;


/**
 * FXML Controller class
 *
 * @author User
 */
public class CalenderlistController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private TableView<Appointment> tableView;
    @FXML
    private TableColumn<Appointment, String> phone_col;
    @FXML
    private TableColumn<Appointment, String> date_col;
    @FXML
    private TableColumn<Appointment, String> hours_col;
    @FXML
    private TableColumn<Appointment, String> minutes_col;
    
    ObservableList <Appointment> list= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }    
    
      private void initCol() {
       phone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));
       date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
       hours_col.setCellValueFactory(new PropertyValueFactory<>("hours"));
       minutes_col.setCellValueFactory(new PropertyValueFactory<>("minutes"));
    }
      
       private void loadData() {
        DatabaseHandler databaseHandler= DatabaseHandler.getInstance();
   
        String qu = "SELECT * FROM APPOINTMENTS";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            while (rs.next()) {
                String telphone = rs.getString("supervisor_w_appointment_mobile");
                String appdate = rs.getString("AppointmentDate");
                String sethrs = rs.getString("AppointmentHours");
                String setmins = rs.getString("AppointmentMins");
                System.out.println(appdate);
              
              list.add(new Appointment(telphone, appdate, sethrs, setmins));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CalenderlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(list);
       }  

    @FXML
    private void handleAppointmentDeletionOption(ActionEvent event) {
    }

    @FXML
    private void handleAppointmentEditOption(ActionEvent event) {
    }
       
       public static class Appointment{
           private final SimpleStringProperty set_phonenumber;
           private final SimpleStringProperty meeting_date;
           private final SimpleStringProperty meet_hrs;
           private final SimpleStringProperty meet_minutes;
           
           public Appointment(String set_phonenumber,String meeting_date,String meet_hrs, String meet_minutes){
            this.set_phonenumber= new SimpleStringProperty(set_phonenumber);
            this.meeting_date= new SimpleStringProperty(meeting_date);
            this.meet_hrs= new SimpleStringProperty(meet_hrs);
            this.meet_minutes= new SimpleStringProperty(meet_minutes);
           }

        public String getSet_phonenumber() {
            return set_phonenumber.get();
        }

        public String getMeeting_date() {
            return meeting_date.get();
        }

        public String getMeet_hrs() {
            return meet_hrs.get();
        }

        public String getMeet_minutes() {
            return meet_minutes.get();
        }
           
       }

    
}
