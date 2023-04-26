import java.util.Scanner;
import java.net.URL;
// import java.text.SimpleDateFormat;     (we'll need this import statement later down the line so keep it on speed dial)
import com.google.gson.Gson;
import java.io.*;

public class Driver
{
   public static void main(String[] args)
   {
   
   System.out.println("\nAccessing update data from server, please wait a moment.");
   System.out.println();
   
   int appid = 2212330; // the appid field is the game's internal ID
   int count = 30;      // the count field is the number of entries
   int maxlength = 100; // the maxlength field is the maximum characters of the String contents within the JSON
   
   String linkToAPI = "http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=" + appid + "&count=" + count + "&maxlength=" + maxlength + "&format=json";
   
   try {
      URL rec = new URL(linkToAPI);
      Scanner server = new Scanner(rec.openStream());
      String serverFeedback = server.nextLine();
      Gson gson = new Gson();
      Newsfeed feed = gson.fromJson(serverFeedback, Newsfeed.class); 
      System.out.println("Processing titles of 30 most recent updates to YOMI Hustle, please wait...");
      
      for (int index = 0; index < 29; index++) {   // this for loop displays each update. you can turn it off if you want, its only a temp fix.
         System.out.println(feed.appnews.newsitems[index].title);
      }
      
   } catch (IOException i) {
      System.out.println("An internal error has occurred. Please alert the developers.");
   }
   
   //       !! ~ ALTERNATE API CALLS ~ !!
   // http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=2212330&count=30&maxlength=100&format=json   (YOMIH - 30 short entries)
   // http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=574980&count=3&maxlength=65536&format=json   (TFH - 3 verbose entries)
   // http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=1384160&count=9&maxlength=500&format=json    (GGST - 9 mid-length entries)
   
   } // main method closing brackets 
   
} // class closing brackets