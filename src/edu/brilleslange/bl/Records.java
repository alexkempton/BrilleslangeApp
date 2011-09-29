package edu.brilleslange.bl;
import java.util.ArrayList;

public class Records {
	private ArrayList<Record> record = new ArrayList<Record>();

	public ArrayList<Record> getRecord() {
		return record;
	}

	public void addRecord(Record record) {
		this.record.add(record);
	}
}

class Record{
	public String title;
}
