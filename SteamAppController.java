import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import java.util.GregorianCalendar;
import java.util.prefs.Preferences;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;


public class SteamAppController implements Initializable
{
   // this is the choicebox used with all the games.
   @FXML
   private ChoiceBox<String> gameName;
    
    
   private String [] games = { // this array is the one every other array works off of. it is the list of games our app is functional with. we can add more as time goes on.
                              "Apex Legends",
                              "Call of duty: Mordern Warefare 2", 
                              "Counter Strike: Global Offensive", 
                              "Destiny 2", 
                              "Dragon Ball Fighterz", 
                              "Guilty Gear Strive",
                              "King Of Fighters XV", 
                              "Rainbow Six Siege",
                              "Sea of Thieves",                             
                              "Tekken 8"
                              };
                              
   private Image [] gameImages = { // this is the list of images used for each game.
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
                                    
   private int [] gameIDs = { // this array is one of the most important -- it gives the appid of the selected game
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
    
   private String [] developers = { // this array contains the developer
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
                          
   private String [] publishers = { // this array contains the publisher
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
                          
   private String [] categories = { // this array contains the category each game is defined under. 
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
                                    
   // buttons! one gets updates for game, other marks selected game as fave.
   @FXML
   private Button getUpdates;
   @FXML
   private Button markFave;
   
   // this is the image we show for the game
   @FXML
   private ImageView steamImage;
   
   // labels that get overwritten with the game's information
   @FXML
   private Label developerName;
   @FXML
   private Label publisherName;
   @FXML
   private Label categoryName;
   
   // labels that are overwritten with the latest update's information
   @FXML
   private Label updateTitle;
   @FXML
   private Label updateDate;
   @FXML
   private Hyperlink updateLink; // this one technically works the same way as a button, but its in the same visual area thing so i put it here
   @FXML
   private Label updateContents;
   
   // these variables are used internally
   private String updateURL = "";   // gets used for the hyperlink to the news post
   public static final String FAVE_MARK = "fave_mark_key"; // used in preference setting and getting
   private GregorianCalendar postTimestamp = new GregorianCalendar(); // used in formatting the timestamp later
   
   
   @FXML
   public void updateDisplay()    {
//       System.out.println("Button pressed"); 
//       if(gameName.getSelectionModel().getSelectedIndex() >= 0) {
//          System.out.println("Index of item selected: " + gameName.getSelectionModel().getSelectedIndex() + 
//                             " Game ID selected: " + gameIDs[gameName.getSelectionModel().getSelectedIndex()]);
//       } // this is all debugging code
      
      // this line makes the actual newsfeed
      NewsGenerator.createNewsfeed(gameIDs[gameName.getSelectionModel().getSelectedIndex()], NewsGenerator.count, 200);
      // System.out.println(NewsGenerator.feed); // debugging code
      
      // sets the game information and image
      developerName.setText(developers[gameName.getSelectionModel().getSelectedIndex()]);
      publisherName.setText(publishers[gameName.getSelectionModel().getSelectedIndex()]);
      categoryName.setText(categories[gameName.getSelectionModel().getSelectedIndex()]);
      steamImage.setImage(gameImages[gameName.getSelectionModel().getSelectedIndex()]);
      
      // these next few lines give a default value to the UI. 
      // if the try-catch after this block still fails somehow, this error is presented, with a link to the steam game's steam page so the user can manually check for updates.
      updateTitle.setText("NO UPDATE FOUND FOR THIS GAME!");
      SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yy hh:mm a");
      String formattedDate = "";
      updateDate.setText("");
      updateLink.setText("");
      updateURL = "https://store.steampowered.com/news/app/" + gameIDs[gameName.getSelectionModel().getSelectedIndex()] + "?updates=true";
      updateContents.setText("No recent Steam news entries have contained any patchnotes or in-game updates. Either an internal error has occurred, or this game has not received a proper update in recent times.");
      
      // this next try-catch block attempts to fill the labels with info on the latest update
      try {
         for (int index = NewsGenerator.count - 1; index >= 0; index--) { // this for-loop is used thrice.
            // first, the for-loop initializes the index at the end of the list of updates.
            // second, it checks the entry if it has the patchnotes tag. later, itll check instead for the mod_reviewed tag. if the game has no tags, it checks feed_type instead, as a final fallback.
            // if the entry has the desired variable, it writes the entry's data to the display
            // it then repeats this process
            if (NewsGenerator.feed.appnews.newsitems[index].tags[0].equalsIgnoreCase("patchnotes")) { // this if statement is why the for-loop is not in a method.
               updateTitle.setText(NewsGenerator.feed.appnews.newsitems[index].title);
               postTimestamp.setTimeInMillis(NewsGenerator.feed.appnews.newsitems[index].date * 1000); // writes timestamp according to the date variable in the newsfeed
               formattedDate = fmt.format(this.postTimestamp.getTime());   // writes up the gregorian calendar object via the postTimestamp
               updateDate.setText(formattedDate);
               updateLink.setText(NewsGenerator.feed.appnews.newsitems[index].url);
               updateURL = NewsGenerator.feed.appnews.newsitems[index].url;
               updateContents.setText(NewsGenerator.feed.appnews.newsitems[index].contents);
            } 
         } // this if statement indirectly checks if the prior loop executed properly. if the update URL is as specified here, that means no entries had the patchnotes tag. thus, it should check for the mod_reviewed tag instead.
         if (updateURL.equalsIgnoreCase("https://store.steampowered.com/news/app/" + gameIDs[gameName.getSelectionModel().getSelectedIndex()] + "?updates=true"))
            for (int index = NewsGenerator.count - 1; index >= 0; index--) {
               if (NewsGenerator.feed.appnews.newsitems[index].tags[0].equalsIgnoreCase("mod_reviewed")) {
                  updateTitle.setText(NewsGenerator.feed.appnews.newsitems[index].title);
                  postTimestamp.setTimeInMillis(NewsGenerator.feed.appnews.newsitems[index].date * 1000);
                  formattedDate = fmt.format(this.postTimestamp.getTime());
                  updateDate.setText(formattedDate);
                  updateLink.setText(NewsGenerator.feed.appnews.newsitems[index].url);
                  updateURL = NewsGenerator.feed.appnews.newsitems[index].url;
                  updateContents.setText(NewsGenerator.feed.appnews.newsitems[index].contents);
               }
            }
      } catch (NullPointerException n) { // null pointer exception is given when there are no tags in the json. thus, neither patchnotes nor mod_reviewed are possible to check for.
         for (int index = NewsGenerator.count - 1; index >= 0; index--) {
            if (NewsGenerator.feed.appnews.newsitems[index].feed_type > 0) { // feed type is the least consistent of the three variables, but is present on every entry regardless of dev or publisher or game.
               updateTitle.setText(NewsGenerator.feed.appnews.newsitems[index].title);
               postTimestamp.setTimeInMillis(NewsGenerator.feed.appnews.newsitems[index].date * 1000);
               formattedDate = fmt.format(this.postTimestamp.getTime());
               updateDate.setText(formattedDate);
               updateLink.setText(NewsGenerator.feed.appnews.newsitems[index].url);
               updateURL = NewsGenerator.feed.appnews.newsitems[index].url;
               updateContents.setText(NewsGenerator.feed.appnews.newsitems[index].contents);
            }
         }
      }
   }
   
   /**
   The openLink() method opens hyperlinks in the default browser of the user. It only works on particular platforms (mostly just windows). This method is called when a hyperlink is pressed.
   */
   @FXML
   public void openLink() throws Exception // since I studied the web for this code, ill explain it while im here
   {
      if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) { // checks if user's hardware + software support the class used to open a hyperlink here
         Desktop.getDesktop().browse(new URI(updateURL)); // if hyperlink opening is supported, opens a hyperlink, using link found in newsfeed
      }
   }
   
   /**
   The markFave() method is called when the user hits the MARK FAV. button. It takes the ID of the currently selected game and places it in preferences.
   */
   @FXML
   public void markFave() throws Exception
   {
      Preferences pref = Preferences.userNodeForPackage(SteamAppController.class); // make a preference variable for this class
      // System.out.println("Marked ID of " + String.valueOf(gameIDs[gameName.getSelectionModel().getSelectedIndex()])); // debugging line
      pref.put(FAVE_MARK, String.valueOf(gameIDs[gameName.getSelectionModel().getSelectedIndex()])); // mark current selected game as preference
   }
   
   /**
   The initialize() method is called on startup of the application. It checks preferences, and then automatically calls the API for the preferred game. Or, if no preference is made yet, it defaults to the first entry.
   */
   @Override
   public void initialize (URL location, ResourceBundle resources)
   {
      gameName.getItems().addAll(games); // list all games in the choice box
      
      Preferences pref = Preferences.userNodeForPackage(SteamAppController.class); // check preferences
      NewsGenerator.appid = Integer.parseInt(pref.get(FAVE_MARK,"1172470")); // if no preference, use first entry
      
      switch(NewsGenerator.appid) { // select choicebox entry based on preference
         case 1172470: gameName.getSelectionModel().select(0); break;
         case 1938090: gameName.getSelectionModel().select(1); break;
         case 730: gameName.getSelectionModel().select(2); break;
         case 1085660: gameName.getSelectionModel().select(3); break;
         case 678950: gameName.getSelectionModel().select(4); break;
         case 1384160: gameName.getSelectionModel().select(5); break;
         case 1498570: gameName.getSelectionModel().select(6); break;
         case 359550: gameName.getSelectionModel().select(7); break;
         case 1172620: gameName.getSelectionModel().select(8); break;
         case 1778820: gameName.getSelectionModel().select(9); break;
         default: gameName.getSelectionModel().select(0); break;
      }
      
      try { // attempt an API call
         updateDisplay();
      } catch (Exception u) {
         System.out.println("Error on startup.");
      }
   }
}
