/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.googledrive;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import pkgfinal.year.assistant.database.DatabaseHandler;
import pkgfinal.year.assistant.studentcomponent.ChatApp.ChatAppController;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StoragedriveController implements Initializable {

    @FXML
    private Button storage_details;
    @FXML
    private Label upload_new_file;
    @FXML
    private HBox box_my_drive;
    @FXML
    private HBox box_computer;
    @FXML
    private HBox box_shared_w_me;
    @FXML
    private HBox box_contributors;
    @FXML
    private HBox box_notifications;
    @FXML
    private HBox box_settings;
    @FXML
    private AnchorPane project_summary;
    @FXML
    private AnchorPane project_objectives;
    @FXML
    private AnchorPane project_calender;
    @FXML
    private AnchorPane project_budget;
    @FXML
    private AnchorPane project_todo_items;
    @FXML
    private ListView<String> listview_files;

    DatabaseHandler handler;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler= DatabaseHandler.getInstance();
    }    

    @FXML
    private void upload_new_file_clicked(MouseEvent event) {
        FileChooser fc= new FileChooser();
        File selected_file = fc.showOpenDialog(null);
        
        if(selected_file != null){
            listview_files.getItems().add(selected_file.getName());
        }else{
            
          JOptionPane.showMessageDialog(null, " -------Cancelled Uploading The File --------" , "Error", JOptionPane.ERROR_MESSAGE);
       
        }
       
    }

    @FXML
    private void box_my_drive_clicked(MouseEvent event) {
         
        FileChooser fc= new FileChooser();
        File selected_file = fc.showOpenDialog(null);
        
        if(selected_file != null){
            listview_files.getItems().add(selected_file.getName());
        }else{
            
          JOptionPane.showMessageDialog(null, " -------Cancelled Uploading The File --------" , "Error", JOptionPane.ERROR_MESSAGE);
       
        }
    }

    @FXML
    private void box_computer_clicked(MouseEvent event) {
            FileChooser fc= new FileChooser();
        File selected_file = fc.showOpenDialog(null);
        
        if(selected_file != null){
            listview_files.getItems().add(selected_file.getName());
        }else{
            
          JOptionPane.showMessageDialog(null, " -------Cancelled Uploading The File --------" , "Error", JOptionPane.ERROR_MESSAGE);
       
        }
    }

    @FXML
    private void box_shared_w_me_clicked(MouseEvent event) {
            FileChooser fc= new FileChooser();
        File selected_file = fc.showOpenDialog(null);
        
        if(selected_file != null){
            listview_files.getItems().add(selected_file.getName());
        }else{
            
          JOptionPane.showMessageDialog(null, " -------Cancelled Uploading The File --------" , "Error", JOptionPane.ERROR_MESSAGE);
       
        }
    }

    @FXML
    private void box_contributors_clicked(MouseEvent event) {
     
     JOptionPane.showMessageDialog(null, " No Contributors Set Yet--------" , "Contributors Settings", JOptionPane.INFORMATION_MESSAGE);
       
    }

    @FXML
    private void box_notifications_clicked(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/listappointments/listAppointments.fxml", "Notifications View");
    }

    @FXML
    private void box_settings_clicked(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/settings/settings.fxml", "SETTINGS WINDOW");
    }

    @FXML
    private void strorage_details_clicked(MouseEvent event) {
    }

    @FXML
    private void project_summary_clicked(MouseEvent event) {
        int choice=JOptionPane.showConfirmDialog(null, "Have You Already Set Your Project Summary Yet?"
                + "\n .","Confirm Operation", JOptionPane.INFORMATION_MESSAGE);
        
        if(choice==JOptionPane.YES_OPTION){
         loadWindow("/pkgfinal/year/assistant/studentcomponent/summary/project_summary.fxml", "Calender View");
//            String qu = "SELECT Project_Summary FROM STUDENTRESOURCES";
//            ResultSet rs = handler.execQuery(qu);
//            try {
//               while (rs.next()) {
//                String prj_summary = rs.getString("Project_Summary");
//                System.out.println(prj_summary);
//                JOptionPane.showMessageDialog(null,"PROJECT SUMMARY:- \n"+ prj_summary , "You Already Set Your Project Summary", JOptionPane.INFORMATION_MESSAGE);
//            
//               }
//        }   catch (SQLException ex) {
//                Logger.getLogger(StoragedriveController.class.getName()).log(Level.SEVERE, null, ex);
//            }
       
            
        }
        else if(choice==JOptionPane.NO_OPTION){
          String input_summary=JOptionPane.showInputDialog(null, "Enter Your Project Summary:-");
          if(input_summary.equals(null)){
              JOptionPane.showMessageDialog(null, "Please Enter The Field","Error Occurred",JOptionPane.ERROR_MESSAGE);
            }
          else{
              String str= "INSERT INTO STUDENTRESOURCES(Project_Summary) VALUES(" +
                      "'"+ input_summary +"'," + ")";
              System.out.println(str);
              
              if(handler.execAction(str)){
               JOptionPane.showMessageDialog(null, "Congratulations ...Project Summary Successfully Added!"
                      , "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Issue Operation failed:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
        else{
           JOptionPane.showMessageDialog(null, "Issue Cancelled!" , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    void loadWindow(String loc,String title){   
      try {
               Parent parent = FXMLLoader.load(getClass().getResource(loc));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle(title);
               stage.setScene(new Scene(parent));
               stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StoragedriveController.class.getName()).log(Level.SEVERE, null, ex);
        }

       }

    @FXML
    private void project_objectives_clicked(MouseEvent event) {  
      loadWindow("/pkgfinal/year/assistant/studentcomponent/objectives/project_objectives.fxml", "PROJECT OBJECTIVES PAGE");
    }

    @FXML
    private void project_calender_clicked(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/calendercomponent/toggle_calender.fxml", "Calender View");
    }

    @FXML
    private void project_budget_clicked(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/budget/project_budget.fxml", "Calender View");
    }

    @FXML
    private void project_todo_items_clicked(MouseEvent event) {
    }
    
}
