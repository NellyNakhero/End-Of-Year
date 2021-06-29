/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.listcoordinatormessages;

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
public class CoordinatorMessagesController implements Initializable {

    @FXML
    private TableView<MessagesCoordinator> tableView;
    @FXML
    private TableColumn<MessagesCoordinator, String> from_col;
    @FXML
    private TableColumn<MessagesCoordinator, String> message_col;

    ObservableList <MessagesCoordinator> list= FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initcol();
        loaddata();
    }    

    private void initcol() {
        from_col.setCellValueFactory(new PropertyValueFactory<>("from"));
        message_col.setCellValueFactory(new PropertyValueFactory<>("message"));
    }

    private void loaddata() {
        DatabaseHandler databaseHandler= DatabaseHandler.getInstance();
        String code="Coordinator_P100";
        String qu = "SELECT * FROM MESSAGESS WHERE From_contacts = '"+ code +"'";
        ResultSet rs = databaseHandler.execQuery(qu);
        
        try {
            while(rs.next()){
                String from = rs.getString("From_contacts");
                String Message = rs.getString("Message");
                System.out.println(from);
                System.out.println(Message);
                
                list.add(new MessagesCoordinator(from,Message));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoordinatorMessagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list);
        tableView.setItems(list);
        tableView.setItems(list);
    }
    public static class MessagesCoordinator{
        private final SimpleStringProperty from;
        private final SimpleStringProperty message;

        public MessagesCoordinator(String from, String message) {
            this.from = new SimpleStringProperty(from);
            this.message = new SimpleStringProperty(message);
        }

        public String getFrom() {
            return from.get();
        }

        public String getMessage() {
            return message.get();
        }
        
        
        
    }
}
