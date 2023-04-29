
This app serves as a tracker to recent updates of a particular Steam game.
Currently, it only supports a select handful of games, but more may be added at a later date. Maybe.
Select a game from the top and press "get update". Press "mark fav" to make the app open up to the currently selected game.

Feel free to open up the source .java files. Each one is commented along the way in an attempt to explain how it works.



Here is a map of the newsfeed object used, if you're curious.

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
