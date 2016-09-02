package com.android.khaled.assuit_guide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.khaled.assuit_guide.Model.Model_Item;
import com.android.khaled.assuit_guide.R;

import java.util.ArrayList;

/**
 * Created by khaled on 28/08/16.
 */
public class Recycle_Adapter extends RecyclerView.Adapter<Recycle_Adapter.ViewHolder>
{
    private ArrayList<Model_Item> models;
    Context context;
    String Phone_Number;
    boolean fav = false;

    public Recycle_Adapter(Context context , ArrayList<Model_Item> Data) {
        this.models = Data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_viewer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Recycle_Adapter.ViewHolder viewHolder, final int position) {

        viewHolder.Name.setText(models.get(position).getName());
        viewHolder.Phone.setText(models.get(position).getPhone());
        viewHolder.Address.setText(models.get(position).getAddress());

        viewHolder.favorite_button.setClickable(true);
        viewHolder.call_button.setClickable(true);
        viewHolder.send_button.setClickable(true);

        if(fav == false)
        {
            viewHolder.favorite_button.setImageResource(R.drawable.heart_outline);
            fav = false;
        }

        else
        {
            viewHolder.favorite_button.setImageResource(R.drawable.heart);
            fav = true;
        }

        viewHolder.favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fav == false) {
                    viewHolder.favorite_button.setImageResource(R.drawable.heart);
                    Add_Item(position);
                    fav = true;
                    Toast.makeText(context, "Movi Added To Favorite List", Toast.LENGTH_SHORT).show();
                } else {
                    viewHolder.favorite_button.setImageResource(R.drawable.heart_outline);
                    fav = false;
                    Delete_Item(position);
                    Toast.makeText(context, "Movi Removed From Favorite List", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewHolder.call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhoneNumber(position);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+Phone_Number));
                context.startActivity(intent);
            }
        });

        viewHolder.send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTextUrl(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder    {
        private TextView Phone,Name,Address;
        private ImageView favorite_button,call_button,send_button;

        public ViewHolder(View view)
        {
            super(view);

            Phone = (TextView)view.findViewById(R.id.phone);
            Name = (TextView)view.findViewById(R.id.name);
            Address = (TextView)view.findViewById(R.id.address);
            favorite_button = (ImageView)view.findViewById(R.id.star);
            call_button = (ImageView)view.findViewById(R.id.call);
            send_button = (ImageView)view.findViewById(R.id.sms);

        }
    }

    public void getPhoneNumber(int position)    {
        String [] nums =models.get(position).getPhone().split("/");
        Phone_Number = nums[0];
    }

    private void shareTextUrl(int position) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "دليلك فى أسيوط");
        String data = "الاسم // " +models.get(position).getName()+"\n\nالعنوان // "+ models.get(position).getAddress()+"\n\n رقم الهاتف // "+ models.get(position).getPhone();
        share.putExtra(Intent.EXTRA_TEXT, data);
        context.startActivity(Intent.createChooser(share, "Share link!"));
    }

    private void Add_Item(int position)    {

    }

    private void Delete_Item(int position)    {

    }

    //provide new dataset belong to search query
    public void setFilter(ArrayList<Model_Item> newModels) {
        this.models = new ArrayList<>();
        this.models.addAll(newModels);
        notifyDataSetChanged();
    }
}