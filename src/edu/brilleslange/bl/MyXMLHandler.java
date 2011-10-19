package edu.brilleslange.bl;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MyXMLHandler extends DefaultHandler {

	Boolean currentElement = false;
	String currentValue = null;
	boolean impElement = false;
	boolean titleElement = false;
	boolean placementElement = false;
	boolean authorElement = false;
	public static Records records = null;
	Record currentRecord;
	PlacementO currentPO = null;
	

	public static Records getRecord() {
		return records;
	}

	public static void setRecord(Records record) {
		MyXMLHandler.records = record;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		currentElement = true;

		if(localName.equals("records") && uri.equals("http://www.loc.gov/zing/srw/")){
			records = new Records();

		}
		if (localName.equals("record") && uri.equals("info:lc/xmlns/marcxchange-v1"))
		{
			currentRecord=new Record();
		} 

		else if (localName.equals("datafield") && uri.equals("info:lc/xmlns/marcxchange-v1")) {
			if(attributes.getValue("tag").compareTo("245")==0){
				impElement=true;
			}

			else if(attributes.getValue("tag").compareTo("852")==0){
				placementElement=true;
				currentPO = new PlacementO();
			}

		}
		
		else if (localName.equals("subfield") && uri.equals("info:lc/xmlns/marcxchange-v1")) {
			if(attributes.getValue("code").compareTo("a")==0){
				titleElement=true;
			}

			else if(attributes.getValue("code").compareTo("c")==0){
				authorElement=true;
			}

		}
		



	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		currentElement = false;
		if (localName.equals("record") && uri.equals("info:lc/xmlns/marcxchange-v1"))
		{
			if(currentRecord.title!=null)
				records.addRecord(currentRecord);
		}
		/** set value */
		if (impElement==true)
		{
			if(localName.equals("subfield") && uri.equals("info:lc/xmlns/marcxchange-v1")){
				if(authorElement==true){
					currentRecord.author=currentValue;
					authorElement=false;
				}
				
				else if(titleElement==true){
					currentRecord.title=currentValue;
					titleElement=false;
				}
			}
			
			if(localName.equals("datafield") && uri.equals("info:lc/xmlns/marcxchange-v1")){
				impElement=false;
			}
		}
		
		
			else if (placementElement==true)
			{
				if(localName.equals("subfield") && uri.equals("info:lc/xmlns/marcxchange-v1")){
					currentPO.placements.add(currentValue);
				/*	if(currentRecord.placement!=null){
						currentRecord.placement=currentRecord.placement+" " + currentValue + "\n";
					}
					else{
						currentRecord.placement=currentValue;
					}*/
				}
				if(localName.equals("datafield") && uri.equals("info:lc/xmlns/marcxchange-v1")){
					placementElement=false;
					if(currentPO.testValue()){
						currentRecord.placement+=currentPO.placement + "\n\n";
					}
					
				}

			}

		


	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (currentElement) {
			currentValue = new String(ch, start, length);
			currentElement = false;
		}

	}

}
