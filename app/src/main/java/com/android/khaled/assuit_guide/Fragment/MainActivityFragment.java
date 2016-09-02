package com.android.khaled.assuit_guide.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.khaled.assuit_guide.Activity.Departments;
import com.android.khaled.assuit_guide.Activity.DetailsActivity;
import com.android.khaled.assuit_guide.Activity.MainActivity;
import com.android.khaled.assuit_guide.Adapters.GridAdapter;
import com.android.khaled.assuit_guide.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private GridView grid;
    private GridAdapter adapter;
    private Intent intent =null;
    Bundle bundle;
    DetailsActivityFragment detailsActivityFragment = new DetailsActivityFragment();
    DepartmentsFragment departmentsFragment = new DepartmentsFragment();

    public MainActivityFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_description) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View Root =  inflater.inflate(R.layout.fragment_main, container, false);
        grid = (GridView) Root.findViewById(R.id.grid);
        adapter = new GridAdapter(getContext());
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0 :
                    {
                        bundle = new Bundle();
                        bundle.putInt("ID", 0);
                        bundle.putString("Title","إختر نوع المستشفيات");

                        if(MainActivity.CheckTablet == true)
                        {
                            departmentsFragment = new DepartmentsFragment();
                            departmentsFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, departmentsFragment).commit();
                        }
                        else
                        {
                            intent = new Intent(getActivity(), Departments.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                    }
                    break;

                    case 1:
                    {
                        bundle = new Bundle();
                        bundle.putInt("ID", 1);
                        bundle.putString("Title", "إختر التخصص");

                        if(MainActivity.CheckTablet == true)
                        {
                            departmentsFragment = new DepartmentsFragment();
                            departmentsFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, departmentsFragment).commit();
                        }
                        else
                        {
                            intent = new Intent(getActivity(), Departments.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    break;


                    case 2:
                    {
                        bundle = new Bundle();
                        bundle.putInt("ID", 2);
                        bundle.putString("Title","قائمة الصيدليات");

                        if(MainActivity.CheckTablet == true)
                        {
                            detailsActivityFragment = new DetailsActivityFragment();
                            detailsActivityFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailsActivityFragment).commit();
                        }
                        else
                        {
                            intent = new Intent(getActivity(), DetailsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    break;

                    case 3:
                    {
                        bundle = new Bundle();
                        bundle.putInt("ID", 3);
                        bundle.putString("Title","قائمة معامل التحاليل");

                        if(MainActivity.CheckTablet == true)
                        {
                            detailsActivityFragment = new DetailsActivityFragment();
                            detailsActivityFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailsActivityFragment).commit();
                        }
                        else
                        {
                            intent = new Intent(getActivity(), DetailsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    break;

                    case 4:
                    {
                        bundle = new Bundle();
                        bundle.putInt("ID", 4);
                        bundle.putString("Title","قائمة  الفنادق");

                        if(MainActivity.CheckTablet == true)
                        {
                            detailsActivityFragment = new DetailsActivityFragment();
                            detailsActivityFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailsActivityFragment).commit();
                        }
                        else
                        {
                            intent = new Intent(getActivity(), DetailsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    break;

                    case 5:
                    {
                        bundle = new Bundle();
                        bundle.putInt("ID", 5);
                        bundle.putString("Title","قائمة  السماسرة");

                        if(MainActivity.CheckTablet == true)
                        {
                            detailsActivityFragment = new DetailsActivityFragment();
                            detailsActivityFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailsActivityFragment).commit();
                        }
                        else
                        {
                            intent = new Intent(getActivity(), DetailsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    break;

                    case 6:
                    {
                        bundle = new Bundle();
                        bundle.putInt("ID", 6);
                        bundle.putString("Title","قائمة المفضلة");

                        if(MainActivity.CheckTablet == true)
                        {
                            detailsActivityFragment = new DetailsActivityFragment();
                            detailsActivityFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailsActivityFragment).commit();
                        }
                        else
                        {
                            intent = new Intent(getActivity(), DetailsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    break;

                    case 7:
                    {
                        bundle = new Bundle();
                        bundle.putInt("ID", 7);
                        bundle.putString("Title","قائمة  المطاعم");

                        if(MainActivity.CheckTablet == true)
                        {
                            detailsActivityFragment = new DetailsActivityFragment();
                            detailsActivityFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailsActivityFragment).commit();
                        }
                        else
                        {
                            intent = new Intent(getActivity(), DetailsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                    break;
                }
            }
        });
        return  Root;

    }
}
