package com.example.projetandroid;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class SignupFragment extends Fragment {
    DatePickerDialog picker;

    public SignupFragment() {
        // Required empty public constructor
    }

    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        EditText eText=(EditText) view.findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        Button btnLog=(Button) view.findViewById(R.id.btnInscription);
        btnLog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView log = (TextView) view.findViewById(R.id.plain_text_inputLogin);
                TextView mdp = (TextView) view.findViewById(R.id.plain_text_inputMdp);
                TextView mdp2 = (TextView) view.findViewById(R.id.plain_text_inputMdp2);
                EditText eText=(EditText) view.findViewById(R.id.editText1);

                if(mdp.getText().toString().equals(mdp2.getText().toString())){
                    if(eText.getText().toString().equals("")){
                        Toast.makeText(getContext(),"aucune date de naissance renseignée",Toast.LENGTH_SHORT).show();
                    }else{
                        UserRepository userRepository =new UserRepository(getContext());
                        if(ExisteLogin(log.getText().toString(),userRepository)){
                            Toast.makeText(getContext(),"Ce login existe déjà",Toast.LENGTH_SHORT).show();
                        }else{
                            User u = new User(log.getText().toString(),mdp.getText().toString());
                            u.setBirthDate(eText.getText().toString());
                            userRepository.addUser(u);
                            Toast.makeText(getContext(),"Identifiants créés",Toast.LENGTH_SHORT).show();
                            NavController navController= Navigation.findNavController(view);
                            navController.navigate(R.id.action_fragment_signup_to_fragment_signing);
                        }
                    }
                }
                else{
                    Toast.makeText(getContext(),"Les deux mots de passe ne sont pas identiques",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean ExisteLogin(String login,UserRepository userRepository){
        boolean res= false;
        List<User> users= userRepository.getAllUsers();
        for (User u:users) {
            if(u.login.toString().equals(login.toString())){
                res=true;
            }
        }
        return res;
    }

}