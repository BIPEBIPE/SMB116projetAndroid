package com.example.projetandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailMovieFragment extends Fragment {

    private String Titre;

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    public static DetailMovieFragment newInstance(String param1) {
        DetailMovieFragment fragment = new DetailMovieFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Titre = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_detail_movie, container, false);
        TextView tv = v.findViewById(R.id.header);
        tv.setText(Titre);
        return v;
    }
}