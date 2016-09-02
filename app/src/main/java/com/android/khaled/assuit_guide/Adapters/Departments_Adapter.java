package com.android.khaled.assuit_guide.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.khaled.assuit_guide.Activity.DetailsActivity;
import com.android.khaled.assuit_guide.Activity.MainActivity;
import com.android.khaled.assuit_guide.Fragment.DetailsActivityFragment;
import com.android.khaled.assuit_guide.Model.Department;
import com.android.khaled.assuit_guide.R;

import java.util.ArrayList;

/**
 * Created by khaled on 02/09/16.
 */
public class Departments_Adapter extends RecyclerView.Adapter<Departments_Adapter.ViewHolder>
{
    private ArrayList<Department> models;
    Activity context;

    public Departments_Adapter(Activity context , ArrayList<Department> Data) {
        this.models = Data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deparments_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Departments_Adapter.ViewHolder holder, int position) {
        holder.Name.setText(models.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView Name;


        public ViewHolder(View view)
        {
            super(view);

            Name = (TextView)view.findViewById(R.id.department);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(models.size() == 2)
            {
                Bundle bundle = new Bundle();
                bundle.putInt("Dep_ID", models.get(getAdapterPosition()).getID());
                bundle.putString("Title", "قائمة  -  " + Name.getText());
                bundle.putChar("type",'H');

                if (MainActivity.CheckTablet == true) {
                    DetailsActivityFragment detailsActivityFragment = new DetailsActivityFragment();
                    detailsActivityFragment.setArguments(bundle);
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailsActivityFragment).commit();
                } else {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }

            else
            {
                Bundle bundle = new Bundle();
                bundle.putInt("Dep_ID", models.get(getAdapterPosition()).getID());
                bundle.putString("Title", "قائمة الأطباء  -  " + Name.getText());
                bundle.putChar("type",'C');

                if (MainActivity.CheckTablet == true) {
                    DetailsActivityFragment detailsActivityFragment = new DetailsActivityFragment();
                    detailsActivityFragment.setArguments(bundle);
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailsActivityFragment).commit();
                } else {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        }
    }

}