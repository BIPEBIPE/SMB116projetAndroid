package com.example.projetandroid;

import android.app.Application;
import android.graphics.Bitmap;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Trending_Fragment extends Fragment {

    public Trending_Fragment() {
        // Required empty public constructor
    }

    public static Trending_Fragment newInstance() {
        Trending_Fragment fragment = new Trending_Fragment();
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

        View v=inflater.inflate(R.layout.fragment_trending, container, false);

        List<MovieAPI> movies= new ArrayList<MovieAPI>();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://api.themoviedb.org/3/trending/all/week?api_key=187d564fea6925a69feacfe93dcc5530";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject postObj = new JSONObject(response);
                    JSONArray postsArray = postObj.getJSONArray("results");
                    if(postsArray!=null){
                        for (int i = 0; i <= postsArray.length(); i++) {
                            JSONObject postObject = (JSONObject) postsArray.get(i);
                            String id = postObject.getString("id");
                            String title = postObject.getString("title");
                            String date = postObject.getString("release_date");
                            String desc = postObject.getString("overview");
                            String vote_average = postObject.getString("vote_average");
                            String img = postObject.getString("poster_path");
                            movies.add(new MovieAPI(id,title,date,desc,img,vote_average));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RecyclerView recyclerView = v.findViewById(R.id.recyclerview_trending);
                MovieAdapter_sup adapter = new MovieAdapter_sup(movies,Movie->{
                    NavController navController= Navigation.findNavController(v);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", Movie);//-> ce qui est envoyer au detail
                    navController.navigate(R.id.action_fragment_trending_to_fragment_detail_movie,bundle);
                });

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

        ImageButton button = (ImageButton) v.findViewById(R.id.button_disconnect);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.Login="";
                NavController navController= Navigation.findNavController(v);
                navController.navigate(R.id.action_fragment_trending_to_fragment_signing);
            }
        });

        ImageButton buttonSearch = (ImageButton) v.findViewById(R.id.button_search);
        TextView search = (TextView) v.findViewById(R.id.search);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerview_trending);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("e", search.getText().toString());
                if(search!=null) {
                    if (!search.getText().equals(null)) {
                        if (!search.getText().toString().equals("")) {
                            List<MovieAPI> movies = new ArrayList<MovieAPI>();
                            RequestQueue queue = Volley.newRequestQueue(getContext());
                            String url = "https://api.themoviedb.org/3/search/movie?query=" + search.getText().toString() + "&api_key=187d564fea6925a69feacfe93dcc5530";
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject postObj = new JSONObject(response);
                                        JSONArray postsArray = postObj.getJSONArray("results");
                                        if (postsArray != null) {
                                            for (int i = 0; i <= postsArray.length(); i++) {
                                                JSONObject postObject = (JSONObject) postsArray.get(i);
                                                String id = postObject.getString("id");
                                                String title = postObject.getString("title");
                                                String date = postObject.getString("release_date");
                                                String desc = postObject.getString("overview");
                                                String vote_average = postObject.getString("vote_average");
                                                String img = postObject.getString("poster_path");
                                                movies.add(new MovieAPI(id, title, date, desc, img, vote_average));
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    MovieAdapter_sup adapter = new MovieAdapter_sup(movies, Movie -> {
                                        NavController navController = Navigation.findNavController(v);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id", Movie);//-> ce qui est envoyer au detail
                                        navController.navigate(R.id.action_fragment_trending_to_fragment_detail_movie, bundle);
                                    });

                                    recyclerView.setAdapter(adapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    adapter.notifyDataSetChanged();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                            queue.add(stringRequest);
                        }
                    }
                }
            }
        });

        return v;
    }
}