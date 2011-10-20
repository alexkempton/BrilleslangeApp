package edu.brilleslange.activities;

import edu.brilleslange.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;


public class ArticleOfTheWeekActivity extends Activity {

	WebView webview;
	String url = "https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B_uoc_LQsxsaZTVlMDhiZGQtMTI2ZS00MGQ2LTk4ZDgtZjlmMzI0Nzg3MDg3&hl=en_US";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.articleoftheweek);
		webview = (WebView) findViewById(R.id.webview);
		webview.loadUrl(url);
		
	}
}
