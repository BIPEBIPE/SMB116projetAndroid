package com.example.projetandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WatchListFragment extends Fragment {

    public WatchListFragment() {
        // Required empty public constructor
    }

    public static WatchListFragment newInstance() {
        WatchListFragment fragment = new WatchListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(MainActivity.Login==""){
            return null;
        }

        View v=inflater.inflate(R.layout.fragment_watchlist, container, false);

        MovieRepository mr = new MovieRepository(getContext());
        List<Movie> lm = mr.getAllMoviesbyLogin(MainActivity.Login);
        List<MovieAPI> movies= new ArrayList<MovieAPI>();
        RequestQueue queue = Volley.newRequestQueue(getContext());

        RecyclerView recyclerView = v.findViewById(R.id.recyclerview_watchlist);
        MovieAdapter adapter = new MovieAdapter(movies,Movie->{
            NavController navController= Navigation.findNavController(v);
            Bundle bundle = new Bundle();
            bundle.putString("id", Movie);//-> ce qui est envoyer au detail
            navController.navigate(R.id.action_fragment_watchlist_to_fragment_detailwatchlist_movie,bundle);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        for (Movie m: lm) {
            String url = "https://api.themoviedb.org/3/movie/"+m.getMovie_id()+"?api_key=187d564fea6925a69feacfe93dcc5530";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject postObj = new JSONObject(response);
                        if(postObj!=null){
                            String id = postObj.getString("id");
                            String title = postObj.getString("original_title");
                            String date = postObj.getString("release_date");
                            String desc = postObj.getString("overview");
                            String vote_average = postObj.getString("vote_average");
                            String img = postObj.getString("poster_path");
                            movies.add(new MovieAPI(id,title,date,desc,img,vote_average));
                            for(int i=0;i<movies.size();i++){
                                Log.e("error",movies.get(i).toString());
                            }
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }}, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(stringRequest);
        }

        ImageButton Imgbutton = (ImageButton) v.findViewById(R.id.button_disconnect);
        Imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.Login="";
                NavController navController= Navigation.findNavController(v);
                navController.navigate(R.id.action_fragment_watchlist_to_fragment_signing);
            }
        });

        return v;
    }
}