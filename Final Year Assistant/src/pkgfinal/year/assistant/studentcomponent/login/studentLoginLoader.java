/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.studentcomponent.login;

import pkgfinal.year.assistant.dashboard.*;
import pkgfinal.year.assistant.liststudents.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nelly
 */
public class studentLoginLoader extends Application{

    public static void main(String[] args) {
          launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("student_login.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("STUDENT LOGIN");
      stage.show();
    }
    
}
