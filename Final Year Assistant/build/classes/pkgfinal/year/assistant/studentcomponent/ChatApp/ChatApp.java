package pkgfinal.year.assistant.studentcomponent.ChatApp;

import pkgfinal.year.assistant.studentcomponent.dashboard.*;
import pkgfinal.year.assistant.googledrive.*;
import pkgfinal.year.assistant.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pkgfinal.year.assistant.database.DatabaseHandler;

/**
 *
 * @author Nelly
 */
public class ChatApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ChatApp.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("CHAT APPLICATION PAGE");
        
        DatabaseHandler.getInstance();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
