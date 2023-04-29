import java.util.Scanner;
import java.net.URL;
// import java.text.SimpleDateFormat;     (we'll need this import statement later down the line so keep it on speed dial)
import com.google.gson.Gson;
import java.io.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class NewsGenerator
{
   public static Newsfeed feed;   // core newsfeed variable. all variables static because only 1 newsfeed at a time.
   public static int appid = 0;       // the appid field is the game's internal ID, as preferred by the user
   public static int count = 10;       // the count field is the number of entries as preferred by the user
   public static int maxlength = 100;   // the maxlength field is the maximum characters of the String contents within the JSON as preferred by the user
   
//    public static void main(String[] args)
//    {
//       createNewsfeed(appid, count, maxlength);
//    }
   
   
   
   public static void createNewsfeed(int appid, int count, int maxlength)
   {
      String linkToAPI = "http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=" + appid + "&count=" + count + "&maxlength=" + maxlength + "&format=json";
      
      try {
         URL rec = new URL(linkToAPI);                               // make the url using the string made a few lines ago
         Scanner server = new Scanner(rec.openStream());             // make a scanner for the incoming json
         String serverFeedback = server.nextLine();                  // get the json
         Gson gson = new Gson();                                     // make a gson object which will contain json later
         feed = gson.fromJson(serverFeedback, Newsfeed.class);       // feed the json into the gson to output our news
      } catch (IOException i) {
         // we should put something here lol
      }
      
      //       !! ~ ALTERNATE API CALLS ~ !!
      // http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=2212330&count=30&maxlength=100&format=json   (YOMIH - 30 short entries)
      // http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=574980&count=3&maxlength=65536&format=json   (TFH - 3 verbose entries)
      // http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=1384160&count=9&maxlength=500&format=json    (GGST - 9 mid-length entries)
      
   } // createNewsfeed() closing brackets 

   
} // class closing brackets
