import javafx.fxml.Initializable;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.ResourceBundle;
import javafx.application.Platform;
import java.util.prefs.Preferences;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;


public class SteamAppController implements Initializable
{
   @FXML
   private ChoiceBox<String> gameName;
    
    
   private String [] games = {
                              "Apex Legends",
                              "Call of duty: Mordern Warefare 2", 
                              "Counter Strike: Global Offensive",                              
                              "Destiny 2", 
                              "Dragon Ball Fighterz",                              
                              "Guilty Grear Strive",
                              "King Of Fighters XV", 
                              "Rainbow Six Siege", 
                              "Sea of Thieves",                             
                              "Tekken 8"
                              };
                              
   private Image [] gameImages = {
                                    new Image("file:STEAM_APP_IMAGES\\Apex_Legends.jpg"), 
                                    new Image("file:STEAM_APP_IMAGES\\Call_Of_Duty_ModernWarfare_2.jpg"), 
                                    new Image("file:STEAM_APP_IMAGES\\Counter_Strike_Global_Offensive.jpg"), 
                                    new Image("file:STEAM_APP_IMAGES\\Destiny_2.jpg"), 
                                    new Image("file:STEAM_APP_IMAGES\\Dragon_Ball_Fighterz.jpg"), 
                                    new Image("file:STEAM_APP_IMAGES\\Guilty_Gear_Strive.jpg"),
                                    new Image("file:STEAM_APP_IMAGES\\King_Of_Fighters_15.jpg"),
                                    new Image("file:STEAM_APP_IMAGES\\Rainbow_Six_Siege.jpg"),                                    
                                    new Image("file:STEAM_APP_IMAGES\\Sea_Of_Thieves.jpg"), 
                                    new Image("file:STEAM_APP_IMAGES\\Tekken_8.jpg")
                                    };
                                    
   private int [] gameIDs = {
                                 1172470,
                                 1938090,
                                 730,
                                 1085660,
                                 678950, 
                                 1384160,
                                 1498570,
                                 359550,
                                 1172620,
                                 1778820
                                 };
    
   private String [] developers = {
                                    "Respawn Entertainment",
                                    "Activision Shanghai & more",
                                    "Valve & Hidden Path Entertainment", 
                                    "Bungie Inc", 
                                    "Arc System Works",
                                    "Arc System Works", 
                                    "SNK Corporation", 
                                    "Ubisoft Montreal",
                                    "Rare Ltd", 
                                    "Bandai Namco Studios Inc."
                                    };
                          
   private String [] publishers = {
                                    "Electronic Arts",
                                    "Activision",
                                    "Valve", 
                                    "Bungie Inc. and Activison", 
                                    "Bandai Namco Entertainment",
                                    "Arc System Works", 
                                    "SNK Corporation", 
                                    "Ubisoft",
                                    "Microsoft Studios", 
                                    "Bandai Namco Entertainment"
                                    };
                          
   private String [] categories = {
                                    "First-Person Shooter",
                                    "First-Person Shooter",
                                    "First-Person Shooter", 
                                    "First-Person Shooter", 
                                    "Fighting",
                                    "Fighting", 
                                    "Fighting", 
                                    "First-Person Shooter",
                                    "Action Advenutre", 
                                    "Fighting"
                                    };
   @FXML
   private Button getUpdates;
   @FXML
   private Button markFave;
   
   @FXML
   private ImageView steamImage;
   
   @FXML
   private Label developerName;
   @FXML
   private Label publisherName;
   @FXML
   private Label categoryName;
   
   @FXML
   private Label updateTitle;
   @FXML
   private Label updateDate;
   @FXML
   private Hyperlink updateLink;
   @FXML
   private Label updateContents;
   
   private String updateURL = "";
   public static final String FAVE_MARK = "fave_mark_key";
   
   
   @FXML
   public void updateDisplay()    {
      System.out.println("Button pressed");
      if(gameName.getSelectionModel().getSelectedIndex() >= 0)
      {
      System.out.println("Index of item selected: " + gameName.getSelectionModel().getSelectedIndex() + 
                         " Game ID selected: " + gameIDs[gameName.getSelectionModel().getSelectedIndex()]);
      }
      NewsGenerator.createNewsfeed(gameIDs[gameName.getSelectionModel().getSelectedIndex()], NewsGenerator.count, 100);
      System.out.println(NewsGenerator.feed);
      
      developerName.setText(developers[gameName.getSelectionModel().getSelectedIndex()]);
      publisherName.setText(publishers[gameName.getSelectionModel().getSelectedIndex()]);
      categoryName.setText(categories[gameName.getSelectionModel().getSelectedIndex()]);
      steamImage.setImage(gameImages[gameName.getSelectionModel().getSelectedIndex()]);
      
      for (int index = NewsGenerator.count - 1; index >= 0; index--) {
         try {
            if (NewsGenerator.feed.appnews.newsitems[index].tags[0].equalsIgnoreCase("patchnotes") || NewsGenerator.feed.appnews.newsitems[index].tags[0].equalsIgnoreCase("mod_reviewed")) {
               updateTitle.setText(NewsGenerator.feed.appnews.newsitems[index].title);
               updateLink.setText(NewsGenerator.feed.appnews.newsitems[index].url);
               updateURL = NewsGenerator.feed.appnews.newsitems[index].url;
               updateContents.setText(NewsGenerator.feed.appnews.newsitems[index].contents);
            } else {
               updateTitle.setText("NO UPDATE FOUND FOR THIS GAME!");
               updateLink.setText("");
               updateContents.setText("No recent Steam news entries have contained any patchnotes or in-game updates. Either an internal error has occurred, or this game has not received a proper update in recent times.");
            }
         } catch (NullPointerException n) {
            if (NewsGenerator.feed.appnews.newsitems[0].feed_type > 0) {
               updateTitle.setText(NewsGenerator.feed.appnews.newsitems[index].title);
               updateLink.setText(NewsGenerator.feed.appnews.newsitems[index].url);
               updateURL = NewsGenerator.feed.appnews.newsitems[index].url;
               updateContents.setText(NewsGenerator.feed.appnews.newsitems[index].contents);
            } else {
               updateTitle.setText("NO UPDATE FOUND FOR THIS GAME!");
               updateLink.setText("");
               updateContents.setText("No recent Steam news entries have contained any patchnotes or in-game updates. Either an internal error has occurred, or this game has not received a proper update in recent time.");
            }
         }
      }
   }
   
   @FXML
   public void openLink() throws Exception // since I studied the web for this code, ill explain it while im here
   {
      if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) { // checks if user's hardware + software support the class used to open a hyperlink here
         Desktop.getDesktop().browse(new URI(updateURL)); // if hyperlink opening is supported, opens a hyperlink, using link found in newsfeed
      }
   }
   
   @FXML
   public void markFave() throws Exception
   {
      Preferences pref = Preferences.userNodeForPackage(SteamAppController.class);
      pref.put(FAVE_MARK, String.valueOf(gameIDs[gameName.getSelectionModel().getSelectedIndex()]));
   }
   
   @Override
   public void initialize (URL location, ResourceBundle resources)
   {
      gameName.getItems().addAll(games);
      
      Preferences pref = Preferences.userNodeForPackage(SteamAppController.class);
      NewsGenerator.appid = Integer.parseInt(pref.get(FAVE_MARK,"1778820"));
      
      switch(NewsGenerator.appid) {
         case 1172470: gameName.getSelectionModel().select(0); break;
         case 1938090: gameName.getSelectionModel().select(1); break;
         case 730: gameName.getSelectionModel().select(2); break;
         case 1085660: gameName.getSelectionModel().select(3); break;
         case 678950: gameName.getSelectionModel().select(4); break;
         case 1498570: gameName.getSelectionModel().select(5); break;
         case 1172620: gameName.getSelectionModel().select(6); break;
         case 359550: gameName.getSelectionModel().select(7); break;
         case 1384160: gameName.getSelectionModel().select(8); break;
         case 1778820: gameName.getSelectionModel().select(9); break;
         default: gameName.getSelectionModel().select(0); break;
      }
      
      try {
         updateDisplay();
      } catch (Exception u) {
         System.out.println("Error on startup.");
      }
   }
   
   
   
   
}
