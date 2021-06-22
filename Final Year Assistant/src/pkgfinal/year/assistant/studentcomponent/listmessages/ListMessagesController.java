/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.listmessages;

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
public class ListMessagesController implements Initializable {

    @FXML
    private TableColumn<Messages, String> fromcol;
    @FXML
    private TableColumn<Messages, String> messagecol;

    ObservableList <Messages> list= FXCollections.observableArrayList();
    
    @FXML
    private TableView<Messages> tableview;
    @FXML
    private TableColumn<Messages, String> tocol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }    

    private void initCol() {
        fromcol.setCellValueFactory(new PropertyValueFactory<>("from"));
        messagecol.setCellValueFactory(new PropertyValueFactory<>("message"));
        tocol.setCellValueFactory(new PropertyValueFactory<>("toselect"));
    }

    private void loadData() {
        DatabaseHandler databaseHandler= DatabaseHandler.getInstance();
        String qu = "SELECT * FROM MESSAGESS";
        ResultSet rs = databaseHandler.execQuery(qu);
        
        try {
            while(rs.next()){
                String from = rs.getString("From_contacts");
                String Message = rs.getString("Message");
                String toselect = rs.getString("To_contacts");
                System.out.println(from);
                System.out.println(Message);
                
                list.add(new Messages(from,Message,toselect));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListMessagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(list);
        tableview.setItems(list);
        tableview.setItems(list);
    }
    
    public static class Messages{
        private final SimpleStringProperty from;
        private final SimpleStringProperty message;
        private final SimpleStringProperty toselect;

        public Messages(String from, String message,String toselect) {
            this.from = new SimpleStringProperty(from);
            this.message = new SimpleStringProperty(message);
            this.toselect = new SimpleStringProperty(toselect);
        }

        public String getFrom() {
            return from.get();
        }

        public String getMessage() {
            return message.get();
        }

        public String getToselect() {
            return toselect.get();
        }
        
        
        
    }
    
}
