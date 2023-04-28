import javafx.fxml.Initializable;
import com.google.gson.Gson;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.ResourceBundle;
import javafx.application.Platform;
import java.net.URL;


public class SteamAppController implements Initializable
{

    @FXML
    private ChoiceBox<String> GameName;
    
    private String [] Games = {"Apex Legends","Call of duty- Mordern Warefare 2",
                               "Counter Strike: Global Offensive", "Destiny 2", "Dragon Ball Fighterz","King Of Fighters XV", 
                               "League Of Legends", "Rainbow Six Siege","Tekken 7", "Tekken 8"};
                              
    private String [] GameImages = {"Apex_Legends.jpg", "Call_Of_Duty_ModernWare_2.jpg", "Counter_Strike_Global_Offensive.jpg", 
                                    "Destiny_2.jpg", "Dragon_Ball_Fighterz", "King_Of_Fighters_15.jpg", "League_Of_Legends.jpg", 
                                    "Rainbox_Six_Siege.jpg","Tekken_7.jpg", "Tekken_8.jpg"};
                                    
    private String [] GameIDs = {"1172470","1938090","730","1085660","678950", "1498570","20590", "359550", "389730", "1778820"};

    @FXML
    private Button GetUpdates;

    @FXML
    private ImageView SteamImage;
    
    @FXML
    public void showImage()
    {
    
    }
    
    @FXML
    public void Update(ActionEvent event)
   {
      
   } 
   
   
   @Override
    public void initialize (URL location, ResourceBundle resources) 
    {
      GameName.getItems().addAll(Games);
    }

}