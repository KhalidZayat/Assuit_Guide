package com.android.khaled.assuit_guide.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.khaled.assuit_guide.R;

/**
 * Created by khaled on 27/08/16.
 */
public class GridAdapter extends BaseAdapter{

    private Context context;
    private String[] names={"مستشفيات","أطباء وعيادات","صيدليات","معامل تحاليل","فنادق","سكن طلبة","المفضلة","مطاعم"};
    private int[]  posters = {R.drawable.hospital,R.drawable.doctor,R.drawable.pills,R.drawable.labs,R.drawable.hotels,R.drawable.school,R.drawable.favorites,R.drawable.res};


    public GridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return posters.length;
    }

    @Override
    public Object getItem(int position) {
        return posters[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gridview_item,parent,false);
            holder = new ViewHolder();

            holder.image = (ImageView) convertView.findViewById(R.id.poster_main);
            holder.text = (TextView) convertView.findViewById(R.id.Dep);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.image.setImageResource(posters[position]);
        holder.text.setText(names[position]);

        return convertView;
    }

    public class ViewHolder
    {
        ImageView image;
        TextView text;
    }

}
