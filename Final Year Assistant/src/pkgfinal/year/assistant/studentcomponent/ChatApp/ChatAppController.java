package pkgfinal.year.assistant.studentcomponent.ChatApp;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import pkgfinal.year.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ChatAppController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button Send;
    @FXML
    private TextField From_ID;
    @FXML
    private TextField To_ID;
    @FXML
    private TextArea Message;

    DatabaseHandler databaseHandler;
    int count = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler =DatabaseHandler.getInstance();
    }    

    @FXML
    private void logout_event(MouseEvent event) {
        Stage stage = (Stage)rootpane.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void Send_action(ActionEvent event) {
        String sender = From_ID.getText();
        String messages = Message.getText();
        String receiver = To_ID.getText();
        count=2;
        if(sender.isEmpty()||messages.isEmpty()||receiver.isEmpty()){
          JOptionPane.showMessageDialog(null, "Please Enter All Fields","Error Occurred",JOptionPane.ERROR_MESSAGE);
        }
        
        count++;
        
        int choice=JOptionPane.showConfirmDialog(null, "Are you sure you want to send the Message.","Confirm Messaging Operation", JOptionPane.INFORMATION_MESSAGE);
        if(choice==JOptionPane.YES_OPTION){
           String str= "INSERT INTO MESSAGESS(renew_count,From_contacts,Message,To_contacts) VALUES(" +
                  "" + count +"," +
                  "'" + sender +"'," +
                  "'" + messages +"'," +
                  "'" + receiver + "'" +
                  ")";
            System.out.println(str);
         if(databaseHandler.execAction(str)){
            JOptionPane.showMessageDialog(null, "Message Sent!", "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Message Operation failed:" , "Error Occured", JOptionPane.ERROR_MESSAGE);
            }
        } else { // if no option is selected
           JOptionPane.showMessageDialog(null, "Message Operation Cancelled!" , "CANCELLED", JOptionPane.INFORMATION_MESSAGE);
        }
//          String quer= "INSERT INTO MESSAGES (renew_count,From_contacts,Message,To_contacts) VALUES(" +
//                "" + count +"," +
//                "'" + sender +"'," +
//                "'" + messages +"'," +
//                "'" + receiver + "'" +
//                ")";
//        System.out.println(quer);
//       if( databaseHandler.execAction(quer)){
//          JOptionPane.showMessageDialog(null, "------- SUCCESSFULLY SENT -------", "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
//       }
//        else//error 
//       {
//             JOptionPane.showMessageDialog(null, "FAILED: TRY AGAIN" , "Error Occured", JOptionPane.ERROR_MESSAGE);
//       }
    }

    
     private void checkData() {
       String qu = "SELECT Message FROM MESSAGES";
        ResultSet rs = databaseHandler.execQuery(qu);
        try {
            while (rs.next()) {
                String message = rs.getString("Message");
                System.out.println(message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChatAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    @FXML
    private void profille_clicked(MouseEvent event) {
    }

    @FXML
    private void chat_clicked(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/listmessages/listMessages.fxml", "Your Conversations");
    }

    @FXML
    private void calender_clicked(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/calendercomponent/toggle_calender.fxml", "Calender View");
    }

    @FXML
    private void drive_clicked(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/googledrive/storagedrive.fxml", "Storage Drive");
    }

    @FXML
    private void loadCoordinatorMessages(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/listcoordinatormessages/coordinatorMessages.fxml", "Your Conversations");

    }

    @FXML
    private void loadSupervisorMessage(MouseEvent event) {
    }

    @FXML
    private void From_action(ActionEvent event) {
    }
    void loadWindow(String loc,String title){   
      try {
               Parent parent = FXMLLoader.load(getClass().getResource(loc));
               Stage stage = new Stage(StageStyle.DECORATED);
               stage.setTitle(title);
               stage.setScene(new Scene(parent));
               stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ChatAppController.class.getName()).log(Level.SEVERE, null, ex);
        }

       } 

    @FXML
    private void Settings_clicked(MouseEvent event) {
        loadWindow("/pkgfinal/year/assistant/studentcomponent/settings/settings.fxml", "SETTINGS WINDOW");
    }
}
