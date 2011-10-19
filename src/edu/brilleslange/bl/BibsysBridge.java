package edu.brilleslange.bl;

import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import java.net.URLEncoder;

import edu.brilleslange.bl.MyXMLHandler;

public class BibsysBridge {
	public ArrayList<Record> search(String title){
		title = URLEncoder.encode(title);
		
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			URL sourceUrl = new URL(
					"http://sru.bibsys.no/search/biblio?version=1.2&operation=searchRetrieve&startRecord=1&maximumRecords=10&query=avdeling=UREAL+and+title=" + title + "&recordSchema=marcxchange");

			MyXMLHandler myXMLHandler = new MyXMLHandler();
			xr.setContentHandler(myXMLHandler);
			xr.parse(new InputSource(sourceUrl.openStream()));

		} catch (Exception e) {
			String ex = e.getMessage();
		}

		ArrayList<Record> records = MyXMLHandler.records.getRecord();
		

		return records;
	}
}




