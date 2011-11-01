package edu.brilleslange.activities;


import java.util.ArrayList;

import edu.brilleslange.widget.MultiDirectionSlidingDrawer;
import edu.brilleslange.R;
import edu.brilleslange.bl.BibsysBridge;
import edu.brilleslange.bl.Record;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.Spinner;


public class SearchScreenActivity extends Activity {


	ImageButton mCloseButton;
	ImageButton mOpenButton;
	MultiDirectionSlidingDrawer mDrawer;


	BibsysBridge bibsys = new BibsysBridge();
	ArrayList<String> l = new ArrayList<String>();
	ListView list;
	ArrayAdapter<String> adapt;
	ArrayList<Record> results;
	ProgressBar mProgress;
	Handler mHandler = new Handler();
	String s;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchscreen);
		ImageButton searchb = (ImageButton) findViewById(R.id.searchbutton);
		mProgress = (ProgressBar) findViewById(R.id.progressbar);
		mProgress.setVisibility(View.INVISIBLE);

		adapt = new ArrayAdapter<String>(this,R.layout.searchresultsitem,l);
		list = (ListView)findViewById(R.id.searchresultslist);
		list.setAdapter(adapt);
		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(new MyItemClickListener(this));

		searchb.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				keyBoardBendOver();
			}
		});
		
		EditText edittxt = (EditText) findViewById(R.id.entry);
		edittxt.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent kEvent) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					keyBoardBendOver();
					return true;
				}
				return false;
			}
			
		});
		
		



		// SLIDING DRAWER:


		mCloseButton.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick( View v )
			{
				EditText searchEditText = (EditText)findViewById(R.id.entry);
				EditText advancedSearchEditText = (EditText)findViewById(R.id.advancedTextField);
				String searchS = advancedSearchEditText.getEditableText().toString();
				searchEditText.setText(searchS);
				mDrawer.animateClose();
			}
		});

		mOpenButton.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick( View v )
			{
				if( !mDrawer.isOpened() ){
					mDrawer.animateOpen();
					EditText searchEditText = (EditText)findViewById(R.id.entry);
					EditText advancedSearchEditText = (EditText)findViewById(R.id.advancedTextField);
					String searchS = searchEditText.getEditableText().toString();
					advancedSearchEditText.setText(searchS);
				}
			}
		});

		Spinner sortingSpinner = (Spinner) findViewById(R.id.sortingSpinner);

		ArrayAdapter<String> sortingSpinnerAd = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, searchByWords);
		sortingSpinnerAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sortingSpinner.setAdapter(sortingSpinnerAd);
		
		Spinner languageSpinner = (Spinner) findViewById(R.id.languageSpinner);
		ArrayAdapter<String> languageSpinnerAd = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
		languageSpinnerAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		languageSpinner.setAdapter(languageSpinnerAd);		
		
		Spinner searchChoiceSpinner = (Spinner) findViewById(R.id.searchChoice);
		ArrayAdapter<String> searchChoiceSpinnerAd = new ArrayAdapter<String>(this, R.layout.spinner_item, searchChoice);
		searchChoiceSpinnerAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		searchChoiceSpinner.setAdapter(searchChoiceSpinnerAd);
		
		ImageButton advancedSearchButton = (ImageButton)findViewById(R.id.searchButton);
		advancedSearchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick( View v )
			{
				Spinner searchChoiceSpinner = (Spinner) findViewById(R.id.searchChoice);
				Spinner sortingSpinner = (Spinner) findViewById(R.id.sortingSpinner);
				Spinner languageSpinner = (Spinner) findViewById(R.id.languageSpinner);
				EditText advancedTextField = (EditText)findViewById(R.id.advancedTextField);
				String searchString = searchChoiceCodes[searchChoiceSpinner.getSelectedItemPosition()] + "=";
				searchString += advancedTextField.getText().toString();
				
				int langPos = languageSpinner.getSelectedItemPosition();
				if(langPos!=0){
					searchString += " AND language=" + languageCodes[langPos];
				}
				
				searchString += " sortby " + searchByWordsCodes[sortingSpinner.getSelectedItemPosition()];
				updateSearchResults(searchString);
				
				EditText searchEditText = (EditText)findViewById(R.id.entry);
				EditText advancedSearchEditText = (EditText)findViewById(R.id.advancedTextField);
				String searchS = advancedSearchEditText.getEditableText().toString();
				searchEditText.setText(searchS);
				
				mDrawer.animateClose();
				
				
				
			}
		});	

	}
	
	public void keyBoardBendOver() {
		EditText edittxt = (EditText) findViewById(R.id.entry);
		String searchValue = edittxt.getText().toString();
		mProgress.setVisibility(View.VISIBLE);
		updateSearchResults("title="+searchValue);
		InputMethodManager imm = (InputMethodManager) getSystemService(
				INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(edittxt.getWindowToken(),0);
	}
	
	@Override
	public void onContentChanged()
	{
		super.onContentChanged();
		mCloseButton = (ImageButton) findViewById( R.id.button_close );
		mOpenButton = (ImageButton) findViewById( R.id.button_open );
		mDrawer = (MultiDirectionSlidingDrawer) findViewById( R.id.drawer );
	}


	// SLIDING DRAWER SLUTT

	void updateSearchResults(String s){
		this.s = s;
		new GetBibsys(this,s).start();
		mProgress.setProgress(30);
	}

	synchronized void callback(ArrayList<Record> results){
		l.clear();
		this.results = results;
		for(Record r : results){ 
			l.add(r.title);
		}
		handler.sendEmptyMessage(0);
	}

	public void dialogShowYourSelf() {
		if (results.size() == 0) {
			String noRezultz = "Ingen resultater";
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle(noRezultz);
			dialog.setMessage(":'(");
			dialog.show();
		} 
	}
	
	private Handler handler = new Handler() {
		@Override
		public void  handleMessage(Message msg) {
			adapt.notifyDataSetChanged();
			mProgress.setVisibility(View.GONE);
			dialogShowYourSelf();
		}};

		// Variable
		final String[] searchChoice = {"Tittel","Forfatter","ISBN"};
		final String[] searchChoiceCodes = {"tittel","forfatter","isbn"};
		final String[] searchByWords = {"Tittel","Forfatter","Nyeste først","Eldste først"};
		final String[] searchByWordsCodes = {"tittel","forfatter","sortdate","sortdate-"};
		final String[] languages = {"Alle","Norsk bokmål", "Norsk nynorsk", "Engelsk", "Dansk", "Finsk", "Fransk", "Færøysk", "Tysk", "Islandsk", "Italiensk", "Spansk", "Svensk", "Tegnspråk", "Afrikaans", "Albansk", "Amharisk", "Angelsaksisk", "Arabisk", "Armensk", "Aserbajdsjansk", "Bengali", "Bulgarsk", "Egyptisk", "Engelsk (mellomengelsk)", "Esperanto", "Estisk", "Flerspråklig", "Fransk (mellomfransk)", "Frisisk", "Fulfulde", "Georgisk", "Gresk (etter 1453)", "Gresk (fram til 1453)", "Hebraisk", "Hindi", "Indonesisk", "Irsk", "Japansk", "Jiddish", "Kalaalisut (Grønland)", "Katalansk", "Kinesisk", "Koptisk", "Koreansk", "Kroatisk", "Kurdisk", "Latin", "Latvisk", "Litauisk", "Makedonsk", "Nederlandsk (inkl. Flamsk)", "Norsk, gammelnorsk", "Norsk, mellomnorsk", "Panjabi", "Persisk (Farsi)", "Polsk", "Portugisisk", "Pushto", "Romani", "Rumensk", "Russisk", "Samisk, Inarisamisk", "Samisk, Lulesamisk", "Samisk, Nordsamisk", "Samisk, Skoltesamisk", "Samisk, Sørsamisk", "Samisk, andre", "Sanskrit", "Serbisk", "Slovakisk", "Slovensk", "Somali", "Swahili", "Tamil", "Thai", "Tibetansk", "Tsjekkisk", "Tyrkisk", "Ukrainsk", "Ungarsk", "Urdu", "Vietnamesisk", "Zulu"};
		final String[] languageCodes = {"all","nob", "nno", "eng", "dan", "fin", "fre", "fao", "ger", "ice", "ita", "spa", "swe", "sgn", "afr", "alb", "amh", "ang", "ara", "arm", "aze", "ben", "bul", "egy", "enm", "epo", "est", "mul", "frm", "fry", "ful", "geo", "gre", "grc", "heb", "hin", "ind", "gle", "jpn", "yid", "kal", "cat", "chi", "cop", "kor", "scr", "kur", "lat", "lav", "lit", "mac", "dut", "non", "nom", "pan", "per", "pol", "por", "pus", "rom", "rum", "rus", "smn" , "smj", "sme", "sms", "sma", "smi", "san", "scc", "slo", "slv", "som", "swa", "tam", "tha", "tib", "cze", "tur", "ukr", "hun", "urd", "vie", "zul"} ;
		
}

class GetBibsys extends Thread{
	SearchScreenActivity ssa;
	Handler mHandler;
	String s;
	GetBibsys(SearchScreenActivity ssa, String s){
		this.ssa = ssa;
		mHandler = ssa.mHandler;
		this.s = s;
	}

	public void run() {
		mHandler.post(new Runnable() {
			public void run() {
				ssa.mProgress.setProgress(30);
			}
		});

		ArrayList<Record> results = new BibsysBridge().search(s);
		ssa.callback(results);
	}
}

class MyItemClickListener implements OnItemClickListener{
	SearchScreenActivity ssa;

	MyItemClickListener(SearchScreenActivity ssa){
		this.ssa = ssa;
	}
	@Override
	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
		Intent myIntent = new Intent(v.getContext(), BookResultActivity.class);
		myIntent.putExtra("Record", ssa.results.get(position));
		ssa.startActivityForResult(myIntent, 0);
	}
}