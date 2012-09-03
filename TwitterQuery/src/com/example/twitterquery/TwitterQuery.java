package com.example.twitterquery;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TwitterQuery {

	public String query = null;
	public ArrayList<Bitmap> imageList;
	public ArrayList<String> imageURLList;
	public ArrayList<String> userNameList;
	public ArrayList<String> dateTimeList;
	public ArrayList<String> tweetList;
	
	public TwitterQuery (String _query) {
		query = _query;
		imageList = new ArrayList<Bitmap>();
		imageURLList = new ArrayList<String>();
		userNameList = new ArrayList<String>();
		dateTimeList = new ArrayList<String>();
		tweetList = new ArrayList<String>();
	}
	
	public void LoadTweets() {
		if(query != null) {
			JSONParser jParser = new JSONParser();
			
			try{
				
				jParser.parse(query);
				JSONObject jQuery = (JSONObject) jParser.parse(query);
				JSONArray jResult = (JSONArray) jQuery.get("results");
				Iterator<JSONObject> jIterator = jResult.iterator();
				
				while (jIterator.hasNext()) {
					JSONObject currentTweet = jIterator.next();
					userNameList.add((String) currentTweet.get("from_user_name"));
					imageURLList.add((String) currentTweet.get("profile_image_url"));
					dateTimeList.add((String) currentTweet.get("created_at"));
					tweetList.add((String) currentTweet.get("text"));
				}
				
			} catch (Exception e) {
				
			}
			
		} else {
			ClearTweets();
		}
	}
	
	public void LoadProfilePictures() {
		for(String s : imageURLList) {
			try {
				Bitmap tempImage;
				tempImage = BitmapFactory.decodeStream(new FlushedInputStream((InputStream) new URL(s).getContent()));
				imageList.add(tempImage);
			} catch (Exception e) {
				
			}
		}
	}
	
	public void ClearTweets() {
		imageList.clear();
		userNameList.clear();
		dateTimeList.clear();
		tweetList.clear();
	}
	
	/**
	 * The class that will provide the correct implementation of the skip method
	 * this class extends the FilterInputStream
	 *
	 * @author Deep Shah
	 * 
	 * @see <a href="http://www.gitshah.com/2011/05/fixing-skia-decoder-decode-returned.html">http://www.gitshah.com/2011/05/fixing-skia-decoder-decode-returned.html</a>
	 * @see <a href="http://code.google.com/p/android/issues/detail?id=6066">http://code.google.com/p/android/issues/detail?id=6066</a>
	 */
	private class FlushedInputStream extends FilterInputStream {
	  public FlushedInputStream(final InputStream inputStream) {
	    super(inputStream);
	  }

	  /**
	   * Overriding the skip method to actually skip n bytes.
	   * This implementation makes sure that we actually skip
	   * the n bytes no matter what.
	   */
	  @Override
	  public long skip(final long n) throws IOException {
	    long totalBytesSkipped = 0L;

	    while (totalBytesSkipped < n) {
	      long bytesSkipped = in.skip(n - totalBytesSkipped);

	      if (bytesSkipped == 0L) {
	        int bytesRead = read();

	        if (bytesRead < 0) { // we reached EOF
	          break;
	        }

	        bytesSkipped = 1;
	      }

	      totalBytesSkipped += bytesSkipped;
	    }

	    return totalBytesSkipped;
	  }
	} 
	
}
