/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.settings;

import pkgfinal.year.assistant.supervisorcomponent.dashboard.*;
import pkgfinal.year.assistant.studentcomponent.summary.*;
import pkgfinal.year.assistant.studentcomponent.objectives.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nelly
 */
public class studentSettingsLoader extends Application{

    public static void main(String[] args) {
          launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("student_settings.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
      stage.setTitle("SETTINGS WINDOW");
      
      PreferenceWrapper.initConfig();
    }
    
}