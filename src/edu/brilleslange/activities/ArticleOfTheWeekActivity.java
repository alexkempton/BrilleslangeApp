package edu.brilleslange.activities;



import edu.brilleslange.R;
import android.app.Activity;
import android.os.Bundle;

import android.webkit.WebView;


public class ArticleOfTheWeekActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.articleoftheweek);		
		WebView webview = (WebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true); 
		String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
		webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdf);

	}
	
	
	
}
