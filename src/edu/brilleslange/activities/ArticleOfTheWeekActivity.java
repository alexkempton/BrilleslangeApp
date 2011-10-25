package edu.brilleslange.activities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import edu.brilleslange.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
