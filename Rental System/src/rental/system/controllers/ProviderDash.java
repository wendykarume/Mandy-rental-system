// CLass package
package rental.system.controllers;

// Exception handling
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

// JavaFX imports
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/*
    Provider Dashboard with various controls
*/
public class ProviderDash{    
    
    // Objects to be used
    @FXML private AnchorPane provider_dash;
    
    // Private method to view houses as provider
    @FXML private void viewHouses(ActionEvent event){
        
        try{
            AnchorPane pane = FXMLLoader.load(getClass().
                        getResource("/rental/system/views/viewhouseprovider.fxml"));
            provider_dash.getChildren().setAll(pane);
            
        }catch (IOException e){
            // Output exception
            Logger.getLogger(ProviderDash.class.getName()).
                    log(Level.SEVERE, null, e);
            
        }
        
    }
    
    @FXML private void addHouses(ActionEvent event){
        
        //code
        
    }
    
    @FXML private void stats(ActionEvent event){
        
        try{
            // Run code and catch exception if there
            AnchorPane pane = FXMLLoader.load(getClass().
                    getResource("/rental/system/views/stats.fxml"));
            provider_dash.getChildren().setAll(pane);

        }catch (IOException e){
            // Output the exception
            Logger.getLogger(ProviderDash.class.getName()).
                    log(Level.SEVERE, null, e);

        }
        
    }
    
}
