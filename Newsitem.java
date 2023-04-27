public class Newsitem {
   public String title;
   public String url;
   public String contents;
   public long date;
   public int feed_type;
   public String[] tags; 
}


/* MAP

Newsfeed feed
	Appnews appnews
		Newsitem[] newsitems
			Newsitems[0]
				String title
				String url
				String contents
				long date
            int feed_type
				String[] tags
         Newsitem[1]
				String title
				String url
				String contents
				long date
            int feed_type
				String[] tags
         Newsitem[2]
				String title
				String url
				String contents
				long date
            int feed_type
				String[] tags

*/
