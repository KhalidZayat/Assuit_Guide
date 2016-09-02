package com.android.khaled.assuit_guide.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.khaled.assuit_guide.Adapters.Departments_Adapter;
import com.android.khaled.assuit_guide.Json.Departments_Response;
import com.android.khaled.assuit_guide.Model.Department;
import com.android.khaled.assuit_guide.R;
import com.android.khaled.assuit_guide.rest.ApiInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class DepartmentsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Department> data =new ArrayList<>();
    private Departments_Adapter D_Adapter;
    private Call<Departments_Response> call;

    private View root;
    private Intent intent;
    ProgressBar progressBar;
    private TextView Title;
    int ID;
    Bundle bundle;

    public DepartmentsFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        root = inflater.inflate(R.layout.fragment_departments, container, false);
        intent = getActivity().getIntent();
        bundle = intent.getExtras();
        if(bundle == null)
        {
            bundle = getArguments();
        }
        ID = bundle.getInt("ID",0);
        Title = (TextView) root.findViewById(R.id.title2);
        Title.setText(bundle.getString("Title"));

        initViews();
        return root;
    }

    private void initViews(){
        progressBar =(ProgressBar)root.findViewById(R.id.progressBar2);
        recyclerView = (RecyclerView)root.findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        if(ID == 0)
        {
            data =new ArrayList<>();
            data.add(0,new Department(1,"المستشفيات العامة"));
            data.add(1,new Department(2,"المستشفيات الخاصة"));
            D_Adapter = new Departments_Adapter(getActivity(),data);
            recyclerView.setAdapter(D_Adapter);
            progressBar.setVisibility(View.GONE);
        }
        else
        {
            loadJSON();
        }
    }

    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://assuitguide.site88.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);
        call = request.getDepartments();

        call.enqueue(new Callback<Departments_Response>() {
            @Override
            public void onResponse(Call<Departments_Response> call, Response<Departments_Response> response) {

                Departments_Response depResponse = response.body();
                data = new ArrayList<>(Arrays.asList(depResponse.getData()));
                D_Adapter = new Departments_Adapter(getActivity(),data);
                recyclerView.setAdapter(D_Adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Departments_Response> call, Throwable t) {

                Toast.makeText(getContext(),"Can't Get Data",Toast.LENGTH_SHORT);
            }
        });
    }
}
