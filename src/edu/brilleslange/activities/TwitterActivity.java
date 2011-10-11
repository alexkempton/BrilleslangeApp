package edu.brilleslange.activities;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.brilleslange.R;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TwitterActivity  extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twitter);
		
		ListView lv = (ListView)findViewById(R.id.twitterlist);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, getTweets()));
		lv.setTextFilterEnabled(true);
	}

	private String[] getTweets(){
		HttpClient httpclient = new DefaultHttpClient();
		String[] tweets = null;
		try {
			HttpGet httpget = new HttpGet("http://search.twitter.com/search.json?q=realfagsbibliotek&result_type=mixed&count=5");
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);

			JSONArray json = new JSONArray(new JSONObject(responseBody).getString("results"));
			tweets = new String[json.length()];
			for(int i = 0;i<json.length();i++){
				JSONObject t = json.getJSONObject(i);
				tweets[i] = t.getString("from_user") + ": " + t.getString("text");
			}


		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			httpclient.getConnectionManager().shutdown();
		}

		return tweets;
	}


}
