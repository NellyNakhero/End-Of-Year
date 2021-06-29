package pkgfinal.year.assistant.studentcomponent.setupbasics;

import pkgfinal.year.assistant.studentcomponent.dashboard.*;
import pkgfinal.year.assistant.googledrive.*;
import pkgfinal.year.assistant.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nelly
 */
public class setupbasics extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("studentsetupbasics.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("SET_UP BASICS PAGE");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
