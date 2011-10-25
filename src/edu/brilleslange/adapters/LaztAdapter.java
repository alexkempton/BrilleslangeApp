package edu.brilleslange.adapters;

import edu.brilleslange.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LaztAdapter extends BaseAdapter {
    
    private Activity activity;
    private static LayoutInflater inflater=null;
    private int[] data = new int[5];
    private String[] menuTxt = new String[5];
    
    public LaztAdapter(Activity a) {
        activity = a;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        data[0] = R.drawable.bbk;
        data[1] = R.drawable.document;
        data[2] = R.drawable.facebookv2;
        //data[3] = R.drawable.map;
        data[3] = R.drawable.search;
        data[4] = R.drawable.twitter;
        menuTxt[0] = "Bestill en bibliotekar";
        menuTxt[1] = "Ukens artikkel";
        menuTxt[2] = "Hva skjer på biblioteket?";
        //menuTxt[3] = "Finn i bygget";
        menuTxt[3] = "Søk etter litteratur";
        menuTxt[4] = "#Realfagsbiblioteket";
    }

    public int getCount() {
        return data.length;
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
            vi = inflater.inflate(R.layout.item, null);

        TextView text=(TextView)vi.findViewById(R.id.text);;
        ImageView image=(ImageView)vi.findViewById(R.id.image);
        text.setText(menuTxt[position]);
        image.setImageResource(data[position]);
        return vi;
    }
}
