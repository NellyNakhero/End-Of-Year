/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.year.assistant.admin.settings;

import pkgfinal.year.assistant.studentcomponent.settings.*;
import pkgfinal.year.assistant.settings.*;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author User
 */
public class PreferenceWrapper {
    public  static final String CONFIG_FILE="coordinator_config.txt";
    
    String username;
    String password;
    String title;
    
    PreferenceWrapper(){
        username="Coordinator Doe";
        password="1234567";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {  
        this.password = password;
        //this.password = DigestUtils.sha1Hex(password);
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
    
    public static void writePreferenceToFile(PreferenceWrapper preference){
         Writer writer = null;         
        try {
            writer = new FileWriter(CONFIG_FILE);
            Gson gson= new Gson();
            gson.toJson(preference,writer);
             JOptionPane.showMessageDialog(null, "SETTINGS SUCCESSFULLY UPDATED!","SUCCESS",JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            Logger.getLogger(PreferenceWrapper.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "CAN'T SAVE CONFIGURATION FILE!","FAILED",JOptionPane.ERROR_MESSAGE);

        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(PreferenceWrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
