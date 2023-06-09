import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import com.google.gson.Gson;
import java.util.Scanner;
import java.net.URL;
import java.io.*;

public class NewsGenerator
{
   public static Newsfeed feed;   // core newsfeed variable. all variables static because only 1 newsfeed at a time.
   public static int appid = 0;       // the appid field is the game's internal ID, as preferred by the user
   public static int count = 18;       // the count field is the number of entries as preferred by the user. 23 is maximum because Tekken 8 has only 18 entries.
   public static int maxlength = 820;   // the maxlength field is the maximum characters of the String contents within the JSON as preferred by the user
   
//    public static void main(String[] args)
//    {
//       createNewsfeed(appid, count, maxlength);
//    } // for debugging use
   
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
         System.out.println("Error attempting to call API and derive GSON");
      }
      
      //       !! ~ ALTERNATE API CALLS ~ !!
      // http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=2212330&count=30&maxlength=100&format=json   (YOMIH - 30 short entries)
      // http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=574980&count=3&maxlength=65536&format=json   (TFH - 3 verbose entries)
      // http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=1384160&count=9&maxlength=500&format=json    (GGST - 9 mid-length entries)
      
   } // createNewsfeed() closing brackets 
} // class closing brackets
