/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.listappointments;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pkgfinal.year.assistant.database.DatabaseHandler;
import pkgfinal.year.assistant.googledrive.StoragedriveController;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ListAppointmentsController implements Initializable {

    @FXML
    private TableView<Appointment> tableView;
    @FXML
    private TableColumn<Appointment, String> telphone_col;
    @FXML
    private TableColumn<Appointment, String> date_col;
    @FXML
    private TableColumn<Appointment, String> hours_col;
    @FXML
    private TableColumn<Appointment, String> minutes_col;

    DatabaseHandler handler;
    ObservableList<Appointment> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane rootpane;
    @FXML
    private TableColumn<Appointment, String> time_col1;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler= DatabaseHandler.getInstance();
        checkData();
        initCol();
        loaddata();
    }    

    private void initCol() {
        telphone_col.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        time_col1.setCellValueFactory(new PropertyValueFactory<>("time"));
        hours_col.setCellValueFactory(new PropertyValueFactory<>("hours"));
        minutes_col.setCellValueFactory(new PropertyValueFactory<>("minutes"));
    }

    private void loaddata() {
       DatabaseHandler handler= DatabaseHandler.getInstance();
       String qu = "SELECT * FROM APPOINTMENTSS";
       ResultSet rs = handler.execQuery(qu);
       
        try {
            while(rs.next()){
            String telephonex = rs.getString("supervisor_w_appointment_mobile");
            String datex = rs.getString("AppointmentDate");
            String timex = rs.getString("AppointmentTime");
            String hoursx = rs.getString("AppointmentHours");
            String minutesx = rs.getString("AppointmentMins");
            
            list.add(new Appointment(telephonex,datex,timex,hoursx,minutesx));
            System.out.println(datex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListAppointmentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.getItems().setAll(list);
    }
    
        private void checkData() {
       String qu = "SELECT AppointmentDate FROM APPOINTMENTSS";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs.next()) {
                String date_nmbr = rs.getString("AppointmentDate");
                System.out.println(date_nmbr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListAppointmentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    @FXML
    private void on_next(ActionEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/listmessages/listMessages.fxml", "Conversations");
    }
    
    
    void loadWindow(String loc,String title){   
      try {
               Parent parent = FXMLLoader.load(getClass().getResource(loc));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle(title);
               stage.setScene(new Scene(parent));
               stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListAppointmentsController.class.getName()).log(Level.SEVERE, null, ex);
        }

       }

    
    public static class Appointment{
        private final SimpleStringProperty mobile;
        private final SimpleStringProperty date;
        private final SimpleStringProperty time;
        private final SimpleStringProperty hours;
        private final SimpleStringProperty minutes;
        
        Appointment(String mobile, String date,String time, String hours, String minutes){
            this.mobile= new SimpleStringProperty(mobile);
            this.date = new SimpleStringProperty(date);
            this.time = new SimpleStringProperty(time);
            this.hours = new SimpleStringProperty(hours);
            this.minutes = new SimpleStringProperty(minutes);
        }

        public String getMobile() {
            return mobile.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getHours() {
            return hours.get();
        }

        public String getMinutes() {
            return minutes.get();
        }

        public String getTime() {
            return time.get();
        }
        
        
    }
    
    @FXML
    private void handleAppointmentDeletionOption(ActionEvent event) {
    }

    @FXML
    private void handleAppointmentEditOption(ActionEvent event) {
    }
    
}
