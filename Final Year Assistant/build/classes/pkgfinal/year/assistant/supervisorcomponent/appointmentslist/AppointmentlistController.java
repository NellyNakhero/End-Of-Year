/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.supervisorcomponent.appointmentslist;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AppointmentlistController implements Initializable {

    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<?, ?> Reg_num;
    @FXML
    private TableColumn<?, ?> consult_col;
    @FXML
    private TableColumn<?, ?> Reg_num1;
    @FXML
    private TableColumn<?, ?> consult_col1;
    @FXML
    private TableColumn<?, ?> consult_col11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
