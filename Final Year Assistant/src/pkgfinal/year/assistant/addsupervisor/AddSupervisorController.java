package pkgfinal.year.assistant.addsupervisor;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pkgfinal.year.assistant.database.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author Nelly A.
 */

public class AddSupervisorController implements Initializable {

    @FXML
    private AnchorPane spvr_anchor;
    @FXML
    private TextField id_number_s;
    @FXML
    private TextField name_s;
    @FXML
    private TextField email_s;
    @FXML
    private TextField mobile_s;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private AnchorPane rootpane;
    
    DatabaseHandler dbHandler;
    
    public final String spvisorr_id= id_number_s.getText();
      
    //call return pvrid
    public String get_supervisor_ID(){
        return spvisorr_id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbHandler=DatabaseHandler.getInstance();
        checkSupervisorData();
    }    


    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage)rootpane.getScene().getWindow();
        stage.close();
    }

 //  String spvr_id= id_number_s.getText();
    @FXML
    private void saveSupervisor(ActionEvent event) {
        
    String spvr_id= id_number_s.getText();
    String spvr_name=name_s.getText();
    String spvr_email=email_s.getText();
    String spvr_mobile=mobile_s.getText();
    
    if( spvr_id.isEmpty()|| spvr_name.isEmpty() || spvr_email.isEmpty() || spvr_mobile.isEmpty() ){
        JOptionPane.showMessageDialog(null, "Please Enter All Fields","Error Occurred",JOptionPane.ERROR_MESSAGE);
    }
    
        System.out.println("we were young");
    
        String qu= "INSERT INTO SUPERVISORS VALUES(" +
                "'"+ spvr_id +"'," +
                "'"+ spvr_name +"'," +
                "'"+ spvr_email +"'," +
                "'"+ spvr_mobile +"'" +
                ")";
        System.out.println(qu);
       if( dbHandler.execAction(qu)){
          JOptionPane.showMessageDialog(null, "SUCCESSFULLY ADDED -------" + spvr_name + " --------INTO THE DATABASE" , "SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
       }
        
    }
    
    
    
     private void checkSupervisorData() {
       String spvr_qu = "SELECT id_number_s FROM SUPERVISORS";
        ResultSet rs = dbHandler.execQuery(spvr_qu);
        try {
            while (rs.next()) {
                String id_nmbr = rs.getString("id_number_s");
                System.out.println(id_nmbr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddSupervisorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
