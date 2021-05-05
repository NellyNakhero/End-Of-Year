/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.listsupervisors;

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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pkgfinal.year.assistant.database.DatabaseHandler;
//import pkgfinal.year.assistant.liststudents.StudentlistController;


/**
 * FXML Controller class
 *
 * @author Nelly A.
 */
public class SupervisorsListController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private TableView<Supervisor> tableView;
    @FXML
    private TableColumn<Supervisor, String> supervisors_ID_col;
    @FXML
    private TableColumn<Supervisor, String> Supervisors_Name_col;
    @FXML
    private TableColumn<Supervisor, String> Supervisors_Email_col;
    @FXML
    private TableColumn<Supervisor, String> Supervisors_Telephone_col;

    /**
     * Initializes the controller class.
     */
    ObservableList <Supervisor> list= FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }    
    
     private void initCol() {
       supervisors_ID_col.setCellValueFactory(new PropertyValueFactory<>("supervisorID_nmbr"));
       Supervisors_Name_col.setCellValueFactory(new PropertyValueFactory<>("name_supervisor"));
       Supervisors_Email_col.setCellValueFactory(new PropertyValueFactory<>("email_supervisor"));
       Supervisors_Telephone_col.setCellValueFactory(new PropertyValueFactory<>("mobile_supervisor"));    
    }
     
     
   private void loadData() {
   DatabaseHandler databaseHandler= DatabaseHandler.getInstance();
   
     String qu = "SELECT * FROM SUPERVISORS";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            while (rs.next()) {
                String supervisorID_nmbr = rs.getString("id_number_s");
                System.out.println(supervisorID_nmbr);
                String name_supervisor = rs.getString("name_s");
                System.out.println(name_supervisor);
                String email_supervisor = rs.getString("email_s");
                System.out.println(email_supervisor);
                String mobile_supervisor = rs.getString("mobile_s");
                System.out.println(mobile_supervisor);
              
               list.add(new Supervisor(supervisorID_nmbr,name_supervisor,email_supervisor,mobile_supervisor));
            }
           } catch (SQLException ex) {
            Logger.getLogger(SupervisorsListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.setItems(list);
    }

    @FXML
    private void handleStudentDeletionOption(ActionEvent event) {
    }

    @FXML
    private void handleStudentEditOption(ActionEvent event) {
    }

    public static class Supervisor {
        private final SimpleStringProperty supervisorID_nmbr;
        private final SimpleStringProperty name_supervisor;
        private final SimpleStringProperty email_supervisor;
        private final SimpleStringProperty mobile_supervisor;
        
        public Supervisor(String supervisorID_nmbr, String name_supervisor, String email_supervisor, String mobile_supervisor) {
        this.supervisorID_nmbr= new SimpleStringProperty(supervisorID_nmbr);
        this.name_supervisor= new SimpleStringProperty(name_supervisor);
        this.email_supervisor= new SimpleStringProperty(email_supervisor);
        this.mobile_supervisor= new SimpleStringProperty(mobile_supervisor);
        }

        public String getSupervisorID_nmbr() {
            return supervisorID_nmbr.get();
        }

        public String getName_supervisor() {
            return name_supervisor.get();
        }

        public String getEmail_supervisor() {
            return email_supervisor.get();
        }

        public String getMobile_supervisor() {
            return mobile_supervisor.get();
        }
    }
    
}           
