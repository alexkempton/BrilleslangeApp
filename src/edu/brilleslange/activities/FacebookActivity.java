package edu.brilleslange.activities;





import edu.brilleslange.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class FacebookActivity extends Activity {

	WebView mWebView;
	String url = "http://m.facebook.com/pages/Det-nye-realfagsbiblioteket-i-Vilhelm-Bjerknes-hus/114911458561913";
	ProgressBar fProgress;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facebook);
		fProgress = (ProgressBar) findViewById(R.id.fprogressbar);
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.setVisibility(View.INVISIBLE);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl(url);
		mWebView.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url) {
				fProgress.setVisibility(View.GONE);
				mWebView.setVisibility(View.VISIBLE);
			}
		});
	}
	
	private Handler handler = new Handler() {
		@Override
		public void  handleMessage(Message msg) {
			fProgress.setVisibility(View.GONE);
		}};
}