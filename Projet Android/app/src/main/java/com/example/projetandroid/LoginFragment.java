package com.example.projetandroid;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView text = (TextView) view.findViewById(R.id.signup);
        Button btnLog = (Button) view.findViewById(R.id.btnLog);

        text.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                NavController navController=Navigation.findNavController(view);
                navController.navigate(R.id.action_fragment_signing_to_fragment_signup);
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView log = (TextView) view.findViewById(R.id.plain_text_inputLogin);
                TextView mdp = (TextView) view.findViewById(R.id.plain_text_inputMdp);

                Log.e("Error",log.getText().toString()+" // "+mdp.getText().toString());

                UserRepository userRepository =new UserRepository(getContext());
                User u=userRepository.Connexion(log.getText().toString(),mdp.getText().toString());
                if(u !=null && u.login!=""){
                    MainActivity.Connected=true;
                    MainActivity.Login=u.login;
                    NavController navController=Navigation.findNavController(view);
                    navController.navigate(R.id.action_fragment_signing_to_fragment_trending);
                }else{
                    Toast.makeText(getContext(),"Identifiants incorrects",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}