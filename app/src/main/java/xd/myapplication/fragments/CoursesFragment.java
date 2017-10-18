package xd.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xd.myapplication.R;
import xd.myapplication.adapters.CourseAdapter;
import xd.myapplication.api.Api;
import xd.myapplication.models.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoursesFragment extends Fragment {

    private RecyclerView recyclerView;


    public CoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        // Call init method
        init(view);

        return view;
    }

    /**
     * This method instance variables
     * @param view
     */
    private void init(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String[] titles = new String[3];
        titles[0] = "Pepito";
        titles[1] = "xD";
        titles[2] = "LOL";

        String[] urls = new String[3];
        urls[0] = "Pepito";
        urls[1] = "xD";
        urls[2] = "LOL";

        CourseAdapter courseAdapter = new CourseAdapter(titles, urls);
        recyclerView.setAdapter(courseAdapter);

        // Call from request

        Call<List<Category>> call = Api.instance().getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.body() != null) {
                    for(Category category : response.body()) {
                        Log.i("Name:", category.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });











    }

}



