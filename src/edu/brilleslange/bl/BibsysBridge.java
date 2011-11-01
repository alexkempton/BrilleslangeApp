package edu.brilleslange.bl;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import java.net.URLEncoder;

import edu.brilleslange.bl.MyXMLHandler;

public class BibsysBridge {
	public ArrayList<Record> search(String searchquery){
	searchquery = searchquery.replaceAll(" ","%20");
		
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			//NY versjoin
			
			URL sourceUrl = new URL("http://" + "sru.bibsys.no" + "/search/biblio?version=1.2&operation=searchRetrieve&startRecord=1&maximumRecords=10&query=avdeling=UREAL+and+" + searchquery + "&recordSchema=marcxchange");
				   
			
			
			
			
			MyXMLHandler myXMLHandler = new MyXMLHandler();
			xr.setContentHandler(myXMLHandler);
			xr.parse(new InputSource(sourceUrl.openStream()));

		} catch (Exception e) {
			@SuppressWarnings("unused")
			String ex = e.getMessage();
		}

		ArrayList<Record> records = MyXMLHandler.records.getRecord();
		

		return records;
	}
}




