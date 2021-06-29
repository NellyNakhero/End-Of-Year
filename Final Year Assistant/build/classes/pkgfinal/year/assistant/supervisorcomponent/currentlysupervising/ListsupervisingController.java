/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.supervisorcomponent.currentlysupervising;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pkgfinal.year.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ListsupervisingController implements Initializable {

    @FXML
    private TableColumn<MyStudentReg, String> Reg_num;

    @FXML
    private TableView<MyStudentReg> tableview;
    @FXML
    private TableColumn<MyStudentReg, String> consult_col;
    
    ObservableList<MyStudentReg> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }    

    private void initCol() {
        Reg_num.setCellValueFactory(new PropertyValueFactory<>("reg_nu"));
        consult_col.setCellValueFactory(new PropertyValueFactory<>("consult"));
    }

    private void loadData() {
       DatabaseHandler handler= DatabaseHandler.getInstance();
       
       String lectID = "11111111";
       String quer= "SELECT * FROM ISSUE WHERE ID_number_s = '"+ lectID +"'";
        ResultSet rs = handler.execQuery(quer);
        String registration;
      
        try {
            while(rs.next()){
              registration = rs.getString("Reg_num");  
              String count = rs.getString("renew_count");
                  list.add(new MyStudentReg(registration,count));  
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListsupervisingController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
//        try {
//            while(rs.next()){
//            String registration = rs.getString("Reg_num");
//            
//            String count = rs.getString("renew_count");
//            
//            String regX=registration;
//            String qu= "SELECT * FROM STUDENTS WHERE reg_num = '"+ regX +"'";
//            
//            while(rs.next()){
//                String nameX  = rs.getString("name_stdnt");
//                String phoneX  = rs.getString("mobile_stdnt");
//                String emailX  = rs.getString("email_stdnt");
//                String titleX  = rs.getString("project_title");
//                
//                list.add(new MyStudents(registration,nameX,phoneX,emailX,titleX,count));
//                System.out.println(titleX);
//            }
//            
//            }
//        } catch (SQLException ex) { 
//            Logger.getLogger(ListsupervisingController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        tableview.getItems().setAll(list);
       }
        public static class MyStudentReg{
            private final SimpleStringProperty reg_nu;
            private final SimpleStringProperty consult;
            
        public MyStudentReg(String reg_nu,String consult) {
            this.reg_nu = new SimpleStringProperty(reg_nu);
            this.consult = new SimpleStringProperty(consult);
        }

        public String getReg_nu() {
            return reg_nu.get();
        }

        public String getConsult() {
            return consult.get();
        }
        
        
        }
        public static class MyStudents{
        private final SimpleStringProperty reg_num;
        private final SimpleStringProperty name;
        private final SimpleStringProperty phone;
        private final SimpleStringProperty email;
        private final SimpleStringProperty title;
        private final SimpleStringProperty consult;

        public MyStudents(String reg_num, String name, String phone, String email, String title,String consult) {
            this.reg_num = new SimpleStringProperty(reg_num);
            this.name = new SimpleStringProperty(name);
            this.phone = new SimpleStringProperty(phone);
            this.email = new SimpleStringProperty(email);
            this.title = new SimpleStringProperty(title);
            this.consult = new SimpleStringProperty(consult);
        }

        public String getReg_num() {
            return reg_num.get();
        }

        public String getName() {
            return name.get();
        }

        public String getPhone() {
            return phone.get();
        }

        public String getEmail() {
            return email.get();
        }

        public String getTitle() {
            return title.get();
        }

        public String getConsult() {
            return consult.get();
        }
        
        
        
        }
}
