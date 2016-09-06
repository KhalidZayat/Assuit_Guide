package com.android.khaled.assuit_guide.Fragment;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.khaled.assuit_guide.Activity.DetailsActivity;
import com.android.khaled.assuit_guide.Adapters.Recycle_Adapter;
import com.android.khaled.assuit_guide.DataBase.Favorite_Adapter;
import com.android.khaled.assuit_guide.Json.Json_Response;
import com.android.khaled.assuit_guide.Model.Model_Item;
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
public class DetailsActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Model_Item> data =new ArrayList<>();
    private Recycle_Adapter R_Adapter;
    private Call<Json_Response> call;
    private TextView Title;
    private View root;
    private Intent intent;
    private ProgressBar progressBar;
    private int ID,Dep_ID;
    private Bundle bundle;
    private char type;
    private Favorite_Adapter db;
    public DetailsActivityFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
//                if(R_Adapter != null) {
//                    final ArrayList<Model_Item> filteredModelList = filter(data, query);
//                    R_Adapter.setFilter(filteredModelList);
//                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if(R_Adapter != null) {
                    final ArrayList<Model_Item> filteredModelList = filter(data, query);
                    R_Adapter.setFilter(filteredModelList);
                }
                return false;
            }
        });


        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(getContext(), DetailsActivity.class)));
        searchView.setIconifiedByDefault(false);

        MenuItemCompat.setOnActionExpandListener(searchItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        if(R_Adapter != null)
                        {
                            R_Adapter.setFilter(data);
                        }
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_details, container, false);
        intent = getActivity().getIntent();
        bundle = intent.getExtras();
        if(bundle == null)
        {
            bundle = getArguments();
        }
        ID = bundle.getInt("ID",0);
        Title = (TextView) root.findViewById(R.id.title);
        Title.setText(bundle.getString("Title"));
        Dep_ID = bundle.getInt("Dep_ID",0);
        type = bundle.getChar("type");
        initViews();
        return root;
    }

    private void initViews(){
        progressBar =(ProgressBar)root.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView)root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://assuitguide.site88.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);

        ////////////////////////////////////////////////////////////
        if(Dep_ID > 0 )
        {
            if(type == 'C')
            {
                call = request.getClinics(Dep_ID);
            }
            else
            {
                if(Dep_ID == 1)
                    call = request.getHospitals();
                else
                    call = request.getS_Hospitals();
            }
        }
        ////////////////////////////////////////////////////////////
        else if(ID == 2)
            call = request.getPharmacy();
        else if(ID == 3)
            call = request.getLabs();
        else if(ID == 4)
            call = request.getHotels();
        else if(ID == 5)
            call = request.getSemsars();
        else if(ID == 6)
        {
            db = new Favorite_Adapter(getContext(),"Model");
            data = db.getModels();
            R_Adapter = new Recycle_Adapter(getContext(), data,true);
            recyclerView.setAdapter(R_Adapter);
            progressBar.setVisibility(View.GONE);
        }
        else if(ID == 7)
            call = request.getResturants();
        ////////////////////////////////////////////////////////////
        if(call != null) {
            call.enqueue(new Callback<Json_Response>() {
                @Override
                public void onResponse(Call<Json_Response> call, Response<Json_Response> response) {
                    Json_Response jsonResponse = response.body();
                    data = new ArrayList<>(Arrays.asList(jsonResponse.getData()));
                    R_Adapter = new Recycle_Adapter(getContext(), data,false);
                    recyclerView.setAdapter(R_Adapter);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Json_Response> call, Throwable t) {

                    Toast.makeText(getContext(), "Can't Get Data", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private ArrayList<Model_Item> filter(ArrayList<Model_Item> models, String query) {

        final ArrayList<Model_Item> filteredModelList = new ArrayList<>();
        for (Model_Item model : models) {
            final String text = model.getName();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
