package edu.brilleslange.adapters;

import java.util.ArrayList;

import edu.brilleslange.R;
import android.app.Activity;
import android.content.Context;
import edu.brilleslange.bl.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchResultsAdapter extends BaseAdapter {
    
    private Activity activity;
    private static LayoutInflater inflater=null;
    private ArrayList<Record> data;
    
    
    public SearchResultsAdapter(Activity a, ArrayList<Record> data) {
        activity = a;
        this.data = data;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.searchresultsitem, null);

        TextView text=(TextView)vi.findViewById(R.id.searchresultitemtext);
     
        text.setText(data.get(position).title);
   
        return vi;
    }
}
