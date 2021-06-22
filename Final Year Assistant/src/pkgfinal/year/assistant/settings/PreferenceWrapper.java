/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.settings;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PreferenceWrapper {
    public  static final String CONFIG_FILE="config.txt";
    
    String username;
    String password;
    
    PreferenceWrapper(){
        username="Student Doe";
        password="1234567";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static void initConfig(){
        Writer writer = null;
        try {
            writer = new FileWriter(CONFIG_FILE);
            PreferenceWrapper preference = new PreferenceWrapper();
            Gson gson= new Gson();
            gson.toJson(preference,writer);
        } catch (IOException ex) {
            Logger.getLogger(PreferenceWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(PreferenceWrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static PreferenceWrapper getPreferences(){
        Gson gson= new Gson();
         PreferenceWrapper preferences= new PreferenceWrapper();
        try {
            preferences = gson.fromJson(new FileReader(CONFIG_FILE), PreferenceWrapper.class);
        } catch (FileNotFoundException ex) {
            initConfig();
            Logger.getLogger(PreferenceWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preferences;
    }
}
