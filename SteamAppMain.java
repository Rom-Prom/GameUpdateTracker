import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.*;


public class SteamAppMain extends Application
{
   
   
   
   
   public static void main(String[] args)
   {
      launch(args);
   }
   
   @Override
   public void start(Stage stage) throws Exception // GET RID OF THE throws Exception CLAUSE IN RELEASE BUILD
   {
      Parent root = FXMLLoader.load(getClass().getResource("SteamApp.fxml"));    
      Scene scene = new Scene(root);
      stage.setTitle("Steam Game Update Tracker");
      
      stage.setScene(scene);
      stage.show();
   }
   
}
