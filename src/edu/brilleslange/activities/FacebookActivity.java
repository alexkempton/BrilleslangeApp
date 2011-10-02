package edu.brilleslange.activities;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import edu.brilleslange.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class FacebookActivity extends ListActivity {
	Facebook facebook = new Facebook("217362831661151");
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs3;
	ArrayList<String> postList;
	FacebookActivity t = this;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.facebook);

		logOnToFacebookAndGetFeed();
	}

	synchronized void populateList(ArrayList<String> postList){
		this.postList = postList;
		handler.sendEmptyMessage(0);
	}

	private Handler handler = new Handler() {
		@Override
		public void  handleMessage(Message msg) {
			setListAdapter((ListAdapter) new ArrayAdapter<String>(t, R.layout.list_item, postList));
			ListView lv = getListView();
			lv.setTextFilterEnabled(true);
		}};







		private void logOnToFacebookAndGetFeed() {

			mPrefs3 = getPreferences(MODE_PRIVATE);
			String access_token4 = mPrefs3.getString("access_token4", null);
			long expires = mPrefs3.getLong("access_expires", 0);
			if(access_token4 != null) {
				facebook.setAccessToken(access_token4);
			}
			if(expires != 0) {
				facebook.setAccessExpires(expires);
			}

			/*
			 * Only call authorize if the access_token4 has expired.
			 */
			if(!facebook.isSessionValid()) {

				facebook.authorize(this, new String[] {"user_about_me"}, -1, new DialogListener() {
					@Override
					public void onComplete(Bundle values) {
						SharedPreferences.Editor editor = mPrefs3.edit();
						editor.putString("access_token4", facebook.getAccessToken());
						editor.putLong("access_expires", facebook.getAccessExpires());
						editor.commit();
						getFeed();
					}

					@Override
					public void onFacebookError(FacebookError error) {}

					@Override
					public void onError(DialogError e) {}

					@Override
					public void onCancel() {}
				});
			}
			else{
				getFeed();
			}


		}

		private void getFeed(){
			AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(facebook);
			mAsyncRunner.request("114911458561913/feed", new FeedRequestListener(this));
		}

		/*
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		facebook.authorizeCallback(requestCode, resultCode, data);
		AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(facebook);
        mAsyncRunner.request("platform/feed", new FeedRequestListener());
	}*/
}

class FeedRequestListener implements RequestListener{
	ArrayList<String >postList = new ArrayList<String>();
	FacebookActivity fa;

	FeedRequestListener(FacebookActivity fa){
		this.fa = fa;
	}

	@Override
	public void onComplete(String response, Object state) {
		try {
			JSONObject json = new JSONObject(response);
			JSONArray data = json.getJSONArray("data");
			for(int i = 0;i<data.length();i++){
				JSONObject post = data.getJSONObject(i);
				String result = post.getString("description") != null ? post.getString("description") : "no description"; 
				postList.add(result);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fa.populateList(postList);

	}

	@Override
	public void onIOException(IOException e, Object state) {
	}

	@Override
	public void onFileNotFoundException(FileNotFoundException e,
			Object state) {
	}

	@Override
	public void onMalformedURLException(MalformedURLException e,
			Object state) {
	}

	@Override
	public void onFacebookError(FacebookError e, Object state) {
	}		
}