import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.util.*;


public class SteamAppMain extends Application
{
   /**
   The main() method takes arguments (if applicable) and opens the application.
   */
   public static void main(String[] args)
   {
      launch(args);
   }
   
   /**
   The start() method tages the stage and opens it up to the viewer.
   */
   @Override
   public void start(Stage stage) throws Exception 
   {
      Parent root = FXMLLoader.load(getClass().getResource("SteamApp.fxml"));    
      Scene scene = new Scene(root);
      stage.setTitle("Steam Game Update Tracker");
      
      stage.setScene(scene);
      stage.show();
   }
}
