package rental.system.uicontrollers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rental.system.Controllers;

public class Welcome{

@FXML
private void signup(ActionEvent event){
    
    try {
            Login log = new Login();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(); // obj to load fxml
            loader.setLocation(getClass().getResource(""
                    + "/rental/system/ui/signup.fxml"));
            // getting the location
            Parent root = loader.load(); // defining root as the Parent
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);// setting the scene
            primaryStage.show(); // displaying the window
            primaryStage.setTitle("Signup | Rental Management System");
            // setting the title
        } catch (IOException ex) {
            Logger.getLogger(Controllers.class.getName()).log(Level.SEVERE, null, ex);
            // catching exception if fxml not found
        }
    
}

@FXML
private void login(ActionEvent event){
    
    try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(); // obj to load fxml
            loader.setLocation(getClass().getResource(""
                    + "/rental/system/ui/login.fxml"));
            // getting the location
            Parent root = loader.load(); // defining root as the Parent
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);// setting the scene
            primaryStage.show(); // displaying the window
            primaryStage.setTitle("Signup | Rental Management System");
            // setting the title
        } catch (IOException ex) {
            Logger.getLogger(Controllers.class.getName()).log(Level.SEVERE, null, ex);
            // catching exception if fxml not found
        }
    
}

}