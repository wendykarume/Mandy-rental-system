// Class package
package rental.system.controllers;

// Custom UI package imports
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

// Imports for exception handling
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// JavaFX imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

// Database imports
import rental.system.models.Provider;
import rental.system.models.User;
import rental.system.sources.BCrypt;

/*
    Class facilitating signup as user or provider
*/
public class Signup{
    
    // FXML Objects to be used
    @FXML private AnchorPane signup;
    @FXML private JFXTextField first_name, last_name, email;
    @FXML private JFXPasswordField password, confirm;
    @FXML private JFXRadioButton userbutton, providerbutton;
    @FXML private Label radio_check, fname, lname, mail, pass, pass_confirm, 
            sign_up;
    
    // Database objects
    User user = new User();
    Provider provider = new Provider();
    
    // Setting up for password hashing
    private String hashed;
    
    // Private method to dashboard after signup
    @FXML private void toDash(ActionEvent event) throws SQLException{
        
        // Clearing fields
        radio_check.setText("");
        fname.setText("");
        lname.setText("");
        mail.setText("");
        pass.setText("");
        pass_confirm.setText("");
        sign_up.setText("");
        
        try{
            // Checking user input
            
            // Making sure a radiobutton is selected
            if (!userbutton.isSelected() && !providerbutton.isSelected()){
                
                // Setting messge for user
                radio_check.setText("Select to proceed");
                
            // Making sure the first name is not empty upon submission
            } else if (first_name.getLength() == 0){
                
                // Setting error message
                fname.setText("Required field");
                
            // Making sure the last name field is not empty upon submission
            }else if (last_name.getLength() == 0){
                
                // Outputting error message
                lname.setText("Required field");                

            // Checking email
            } else if (email.getLength() == 0){
                
                // Outputting message
                mail.setText("Required field");
                
            // Making sure email has the @ symbol
            } else if (!email.getText().contains("@")){
                
                // Outputting text
                mail.setText("Invalid email");
                
            // Checking length of password
            }else if(password.getLength() < 8){
                
                // Outputting message
                pass.setText("Invalid, input 8 or more characters");
                
            // Checking length of confirm
            } else if (confirm.getLength() < 8){
                
                // Outputting message
                pass_confirm.setText("Invalid, input 8 or more characters");
                
            }else if (!password.getText().equals(confirm.getText())){
                
                pass.setText("Passwords do not match");
                pass_confirm.setText("Passwords do not match");
                
                // Clearing fields
                password.clear();
                confirm.clear();
                
            }else{
                // Validating from and inserting into database                
                
                // Getting data from database
                ResultSet provider_rs = provider.fetch(email.getText());
                ResultSet user_rs = user.fetch(email.getText());
                
                if ((user_rs != null) || (provider_rs != null)){
                        
                    // Alerting the user
                    mail.setText("Email elready exists, use another");
                    sign_up.setText("You should Login");

                    // Closing connection
                    user.closeConnection();
                }
                
                // If user button is selected 
                if (userbutton.isSelected()){
                    
                    if (user_rs != null){
                        
                        // Alerting the user
                        mail.setText("Email elready exists, use another");
                        sign_up.setText("You should Login");
                        
                        // Closing connection
                        user.closeConnection();    
                    }else{
                        // Creating database tables
                        user.create();
                        
                        // Hashing the password input
                        hashed = BCrypt.hashpw(password.getText(), 
                                BCrypt.gensalt(12)); 
                                
                        // inserting userdata
                        user.insert(first_name.getText(), last_name.getText(), 
                                email.getText(), hashed);

                        // Closing connection
                        user.closeConnection();
                        
                        // Afterwards displaying UserDash
                        AnchorPane pane = FXMLLoader.load(getClass().
                            getResource("/rental/system/views/userdash.fxml"));
                        signup.getChildren().setAll(pane);
                        
                    }
                        
                }else if(providerbutton.isSelected()){
                    
                    if (provider_rs != null){
                        
                        // Alerting the user
                        mail.setText("Email already exists, use another");
                        sign_up.setText("You should Login");
                        
                        // Closing connection
                        provider.closeConnection();
                        
                    }else{
                        // Creating tables
                        provider.create();
                        
                        // Hashing the password input
                        hashed = BCrypt.hashpw(password.getText(), 
                                BCrypt.gensalt());
                        
                        // Inserting data
                        provider.insert(first_name.getText(), 
                                last_name.getText(), email.getText(), 
                                hashed);

                        // Closing connection
                        provider.closeConnection();
                        
                        // Catch exception if present
                        AnchorPane pane = FXMLLoader.load(getClass().
                                getResource
                                ("/rental/system/views/providerdash.fxml"));
                        signup.getChildren().setAll(pane);
                        
                    }
                        
                }else{
                    // Loading Alert Window for miscellaneous error
                    AnchorPane pane = FXMLLoader.load(getClass().
                            getResource("/rental/system/views/alert.fxml"));
                    signup.getChildren().setAll(pane);
                    
                }
                
            }
                
        }catch (IOException e){
            // Output exception
            Logger.getLogger(Signup.class.getName()).
                    log(Level.SEVERE, null, e);

                }
                
            }
    
    // private method to login if logged in
    @FXML private void login(ActionEvent event){
        
        try{
            // Loading window and catching exception if present
            AnchorPane pane = FXMLLoader.load(getClass().
                    getResource("/rental/system/views/login.fxml"));
            signup.getChildren().setAll(pane);
            
        }catch (IOException e) {
            // Output exception
            Logger.getLogger(Signup.class.getName()).
                            log(Level.SEVERE, null, e);
            
        }
        
    }
    
    // Exit function
    @FXML private void exit(ActionEvent event){
        
        System.exit(0);
        
    }
    
}
