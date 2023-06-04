package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DetailMovieFragment extends Fragment {

    private String Titre;
    private String Description;
    private String Id;

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    public static DetailMovieFragment newInstance(String param1) {
        DetailMovieFragment fragment = new DetailMovieFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Id = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_detail_movie, container, false);
        TextView tv = v.findViewById(R.id.header);
        if (Id != null) {
            ImageView affiche = (ImageView) v.findViewById(R.id.afficheDetails);
            RequestQueue queue = Volley.newRequestQueue(v.getContext());
            String url = "https://api.themoviedb.org/3/movie/"+Id+"?api_key=187d564fea6925a69feacfe93dcc5530";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject postObj = new JSONObject(response);
                        String titre = postObj.getString("original_title");
                        String date = postObj.getString("release_date");
                        TextView tv1 = v.findViewById(R.id.date);
                        tv1.setText("date de sortie: "+date);
                        String note = postObj.getString("vote_average");
                        TextView tv2 = v.findViewById(R.id.note);
                        tv2.setText("note des spectateurs: "+note);
                        String resume = postObj.getString("overview");
                        TextView tv3 = v.findViewById(R.id.description);
                        tv3.setText(resume);

                        Description=resume;
                        Titre=titre;
                        tv.setText(titre);
                        JSONObject postjson = postObj.getJSONObject("belongs_to_collection");
                        String src= postObj.getString("poster_path");//postjson.getString("poster_path");
                        src="https://image.tmdb.org/t/p/w500/"+src;
                        Log.e("e",src);
                        Picasso.with(v.getContext()).load(src).into(affiche);
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

        FloatingActionButton button = (FloatingActionButton) v.findViewById(R.id.add_btn);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Movie m = new Movie(Titre);
                m.setMovie_id(Id);
                m.setLogin(MainActivity.Login);
                MovieRepository mr = new MovieRepository(getContext());
                List<Movie> lm = mr.getAllMoviesbyLogin(MainActivity.Login);
                Boolean test=false;
                for (Movie movie: lm ) {
                    if(movie.getMovie_id().equals(Id)){
                        test=true;
                    }
                }
                if(test){
                    Toast.makeText(getContext(),"Ce film est déjà présent dans votre Watchlist",Toast.LENGTH_SHORT).show();
                }else{
                    mr.addMovie(m);
                    Toast.makeText(getContext(),"Film ajouté à votre Watchlist",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton Imgbutton = (ImageButton) v.findViewById(R.id.button_back);
        Imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(v);
                navController.navigate(R.id.action_fragment_detail_movie_to_fragment_trending);
            }
        });

        ImageButton share = (ImageButton) v.findViewById(R.id.button_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                String shareBody = Titre+":  "+Description;
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "film partagé");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "film partagé"));
            }
        });

        return v;
    }
}