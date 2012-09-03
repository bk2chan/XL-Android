package com.example.twitterquery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	public boolean isLoading = false;
	public int resultsPerPage = 6;
	public int pageNumber = 1;
	public View currentView;

	EditText searchInput;
	
	TextView userName1;
	TextView userName2;
	TextView userName3;
	TextView userName4;
	TextView userName5;
	TextView userName6;
	
	TextView tweet1;
	TextView tweet2;
	TextView tweet3;
	TextView tweet4;
	TextView tweet5;
	TextView tweet6;
	
	ImageView profilePicture1;
	ImageView profilePicture2;
	ImageView profilePicture3;
	ImageView profilePicture4;
	ImageView profilePicture5;
	ImageView profilePicture6;
	
	Button prevButton;
	Button nextButton;
	Button loadButton;
	
	LinearLayout searchLayout1;
	LinearLayout searchLayout2;
	LinearLayout searchLayout3;
	LinearLayout searchLayout4;
	LinearLayout searchLayout5;
	LinearLayout searchLayout6;
	
	LinearLayout searchLayoutButtons;
	
	Boolean searched = false;
	
	Boolean expandedLayout1 = false;
	Boolean expandedLayout2 = false;
	Boolean expandedLayout3 = false;
	Boolean expandedLayout4 = false;
	Boolean expandedLayout5 = false;
	Boolean expandedLayout6 = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = (EditText) this.findViewById(R.id.search_Text);
        
        userName1 = (TextView) this.findViewById(R.id.result_Tweet_User_1);
        userName2 = (TextView) this.findViewById(R.id.result_Tweet_User_2);
        userName3 = (TextView) this.findViewById(R.id.result_Tweet_User_3);
        userName4 = (TextView) this.findViewById(R.id.result_Tweet_User_4);
        userName5 = (TextView) this.findViewById(R.id.result_Tweet_User_5);
        userName6 = (TextView) this.findViewById(R.id.result_Tweet_User_6);
        
        tweet1 = (TextView) this.findViewById(R.id.result_Tweet_Message_1);
        tweet2 = (TextView) this.findViewById(R.id.result_Tweet_Message_2);
        tweet3 = (TextView) this.findViewById(R.id.result_Tweet_Message_3);
        tweet4 = (TextView) this.findViewById(R.id.result_Tweet_Message_4);
        tweet5 = (TextView) this.findViewById(R.id.result_Tweet_Message_5);
        tweet6 = (TextView) this.findViewById(R.id.result_Tweet_Message_6);
        
        profilePicture1 = (ImageView) this.findViewById(R.id.result_Pic_1);
        profilePicture2 = (ImageView) this.findViewById(R.id.result_Pic_2);
        profilePicture3 = (ImageView) this.findViewById(R.id.result_Pic_3);
        profilePicture4 = (ImageView) this.findViewById(R.id.result_Pic_4);
        profilePicture5 = (ImageView) this.findViewById(R.id.result_Pic_5);
        profilePicture6 = (ImageView) this.findViewById(R.id.result_Pic_6);
        
        nextButton = (Button) this.findViewById(R.id.search_Next);
        prevButton = (Button) this.findViewById(R.id.search_Prev);
        loadButton = (Button) this.findViewById(R.id.load_Button);
        
        prevButton.setEnabled(false);
        nextButton.setEnabled(true);
        
        searchLayout1 = (LinearLayout) this.findViewById(R.id.result_Layout_1);
        searchLayout2 = (LinearLayout) this.findViewById(R.id.result_Layout_2);
        searchLayout3 = (LinearLayout) this.findViewById(R.id.result_Layout_3);
        searchLayout4 = (LinearLayout) this.findViewById(R.id.result_Layout_4);
        searchLayout5 = (LinearLayout) this.findViewById(R.id.result_Layout_5);
        searchLayout6 = (LinearLayout) this.findViewById(R.id.result_Layout_6);
        
        searchLayoutButtons = (LinearLayout) this.findViewById(R.id.extra_Layout);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void ClickResultLayout1(View view) {
    	if(!isLoading) 
    		expandedLayout1 = !expandedLayout1;
    	if(expandedLayout1) {
    		tweet1.setMaxLines(5);
    	} else {
    		tweet1.setMaxLines(2);
    	}
    }
	public void ClickResultLayout2(View view) {
		if(!isLoading)
			expandedLayout2 = !expandedLayout2;
		if(expandedLayout2) {
    		tweet2.setMaxLines(5);
    	} else {
    		tweet2.setMaxLines(2);
    	}
    }
	public void ClickResultLayout3(View view) {
		if(!isLoading)
			expandedLayout3 = !expandedLayout3;
		if(expandedLayout3) {
    		tweet3.setMaxLines(5);
    	} else {
    		tweet3.setMaxLines(2);
    	}
	}
	public void ClickResultLayout4(View view) {
		if(!isLoading)
			expandedLayout4 = !expandedLayout4;
		if(expandedLayout4) {
    		tweet4.setMaxLines(5);
    	} else {
    		tweet4.setMaxLines(2);
    	}
	}
	public void ClickResultLayout5(View view) {
		if(!isLoading)
			expandedLayout5 = !expandedLayout5;
		if(expandedLayout5) {
    		tweet5.setMaxLines(5);
    	} else {
    		tweet5.setMaxLines(2);
    	}
	}
	public void ClickResultLayout6(View view) {
		if(!isLoading)
			expandedLayout6 = !expandedLayout6;
		if(expandedLayout6) {
    		tweet6.setMaxLines(5);
    	} else {
    		tweet6.setMaxLines(2);
    	}
	}
    
    public void loadTweet(View view) {
    	if(!isLoading) {
    		pageNumber = 1;
    		
    		
    		
    		String searchParam = "bieber";
    		
    		if(searchInput.getText().toString().length() > 0) {
    			searchParam = searchInput.getText().toString();
    		}
    		
    		SearchOption options = new SearchOption(searchParam,pageNumber,resultsPerPage);
    		
    		new TwitterThread().execute(options);
    		
    		isLoading = true;
    		prevButton.setEnabled(false);
    		nextButton.setEnabled(true);
    		
    	}
    }
    
    public void prevTweet(View view) {
    	if(!isLoading) {
    		if(pageNumber > 1) {
    			pageNumber--;
    			
    			if(pageNumber == 1) {
    				prevButton.setEnabled(false);
    			}
    			
    			String searchParam = "bieber";
        		
    			if(searchInput.getText().toString().length() > 0) {
        			searchParam = searchInput.getText().toString();
        		}
        		
        		SearchOption options = new SearchOption(searchParam,pageNumber,resultsPerPage);
    			
    			isLoading = true;
    			
    			new TwitterThread().execute(options);
    		}
    	} else {
    		prevButton.setEnabled(false);
    	}
    }
    
    public void nextTweet(View view) {
    	if(!isLoading) {
    		pageNumber++;
    		
    		String searchParam = "bieber";
    		
    		if(searchInput.getText().toString().length() > 0) {
    			searchParam = searchInput.getText().toString();
    		}
    		
    		SearchOption options = new SearchOption(searchParam,pageNumber,resultsPerPage);
    		
    		new TwitterThread().execute(options);
    		
    		isLoading = true;
    		prevButton.setEnabled(true);
    	}
    }
    
    private class TwitterThread extends AsyncTask<SearchOption, Void, TwitterQuery> {

    	protected TwitterQuery doInBackground(SearchOption...options) {
    		
    		TwitterQuery returnQuery;
    		String entityContents = "Error:  Could not load from Twitter";
    		String searchURL = "http://search.twitter.com/search.json?q=";
    		String query = "%23" + options[0].search;
    		String pageNumber = "&page=" + String.valueOf(options[0].pageNumber);
    		String resultPerPage = "&rpp=" + String.valueOf(options[0].results);
    		
    		searchURL = searchURL + query + pageNumber + resultPerPage;
    		returnQuery = new TwitterQuery(entityContents);
    		
    		try {
    			HttpClient httpclient = new DefaultHttpClient();
    		    HttpGet httpget = new HttpGet(searchURL);
    		    HttpResponse response = httpclient.execute(httpget);
    		    HttpEntity entity = response.getEntity();
    		    if (entity != null) {
    		        entityContents = EntityUtils.toString(entity);
    		        returnQuery.query = entityContents;
    		        returnQuery.ClearTweets();
    		        returnQuery.LoadTweets();
    		        returnQuery.LoadProfilePictures();
    		    }
    		} catch (Exception e) {
    			returnQuery.query = e.getMessage();
    		}

    		return returnQuery;
    	}
    	
    	protected void onPostExecute(TwitterQuery _tweets) {
    		
    		DateFormat  formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
    		
    		if(!searched) {
    			searchLayout1.setVisibility(1);
    			searchLayout2.setVisibility(1);
    			searchLayout3.setVisibility(1);
    			searchLayout4.setVisibility(1);
    			searchLayout5.setVisibility(1);
    			searchLayout6.setVisibility(1);
    			searchLayoutButtons.setVisibility(1);
    		}
    		
    		userName1.setText(_tweets.userNameList.get(0).trim());
    		userName2.setText(_tweets.userNameList.get(1).trim());
    		userName3.setText(_tweets.userNameList.get(2).trim());
    		userName4.setText(_tweets.userNameList.get(3).trim());
    		userName5.setText(_tweets.userNameList.get(4).trim());
    		userName6.setText(_tweets.userNameList.get(5).trim());
    		
    		try {
    			Date date = (Date) formatter.parse(_tweets.dateTimeList.get(0));
    		
	    		tweet1.setText(date.toLocaleString() + " - " +  _tweets.tweetList.get(0));
	    		date = (Date) formatter.parse(_tweets.dateTimeList.get(1));
	    		tweet2.setText(date.toLocaleString() + " - " +  _tweets.tweetList.get(1));
	    		date = (Date) formatter.parse(_tweets.dateTimeList.get(2));
	    		tweet3.setText(date.toLocaleString() + " - " +  _tweets.tweetList.get(2));
	    		date = (Date) formatter.parse(_tweets.dateTimeList.get(3));
	    		tweet4.setText(date.toLocaleString() + " - " +  _tweets.tweetList.get(3));
	    		date = (Date) formatter.parse(_tweets.dateTimeList.get(4));
	    		tweet5.setText(date.toLocaleString() + " - " +  _tweets.tweetList.get(4));
	    		date = (Date) formatter.parse(_tweets.dateTimeList.get(5));
	    		tweet6.setText(date.toLocaleString() + " - " +  _tweets.tweetList.get(5));
    		} catch (Exception e) {
    			
    		}
    		
    		profilePicture1.setImageBitmap(_tweets.imageList.get(0));
    		profilePicture2.setImageBitmap(_tweets.imageList.get(1));
    		profilePicture3.setImageBitmap(_tweets.imageList.get(2));
    		profilePicture4.setImageBitmap(_tweets.imageList.get(3));
    		profilePicture5.setImageBitmap(_tweets.imageList.get(4));
    		profilePicture6.setImageBitmap(_tweets.imageList.get(5));
    		
    		isLoading = false;
    		searched = true;
    		
    		expandedLayout1 = false;
    		expandedLayout2 = false;
    		expandedLayout3 = false;
    		expandedLayout4 = false;
    		expandedLayout5 = false;
    		expandedLayout6 = false;
    		
    		return;
    	}

    }
}
