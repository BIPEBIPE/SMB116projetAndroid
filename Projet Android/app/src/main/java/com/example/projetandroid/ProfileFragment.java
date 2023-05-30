package com.example.projetandroid;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProfileFragment extends Fragment {

    ImageView img;
    Bitmap imgFile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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

        View v =inflater.inflate(R.layout.fragment_profile, container, false);

        Button button = (Button) v.findViewById(R.id.btnSave);
        img = (ImageView) v.findViewById(R.id.profile_picture);
        TextView takePhoto = (TextView) v.findViewById(R.id.photo);
        TextView birthday = (TextView) v.findViewById(R.id.birthday);
        TextView getImage = (TextView) v.findViewById(R.id.image);
        TextView newMdp = (TextView) v.findViewById(R.id.new_mdp);
        UserRepository userRepository =new UserRepository(getContext());
        User u=userRepository.getUser(MainActivity.Login);
        birthday.setText("date de naissance: "+u.getBirthDate());
        if(u.getImg_profile()!=null){
            Bitmap imgBtn= BitmapFactory.decodeByteArray(u.getImg_profile(), 0, u.getImg_profile().length);
            img.setImageBitmap(imgBtn);
        }

        takePhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, 123);
            }
        });

        getImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 4);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserRepository userRepository =new UserRepository(getContext());
                if(imgFile!=null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imgFile.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    userRepository.ChangePP(MainActivity.Login,byteArray);
                }
                if(newMdp.getText().equals(null)) {
                }else{
                    if(newMdp.getText().toString().equals("")) {
                        Toast.makeText(getContext(),"mise à jour de votre profil réussie",Toast.LENGTH_SHORT).show();
                    }else{
                        userRepository.ChangeMDP(MainActivity.Login,newMdp.getText().toString());
                        NavController navController= Navigation.findNavController(v);
                        navController.navigate(R.id.action_fragment_profile_to_fragment_signing);
                    }
                }
            }
        });

        ImageButton Imgbutton = (ImageButton) v.findViewById(R.id.button_disconnect);
        Imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.Login="";
                NavController navController= Navigation.findNavController(v);
                navController.navigate(R.id.action_fragment_profile_to_fragment_signing);
            }
        });

        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (requestCode == 123) {
            Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
            img.setImageBitmap(photo);
            MediaStore.Images.Media.insertImage(this.getActivity().getContentResolver(), photo, "ProfilPicture" , "ProfilPicture");
            this.imgFile=null;
            this.imgFile=photo;
        }
        if (requestCode == 4) {
            try {
                final Uri imageUri = imageReturnedIntent.getData();
                final InputStream imageStream = this.getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                this.imgFile=null;
                this.imgFile=ImageResizer.reduceBitmapSize(selectedImage,100000);
                img.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static final String insertImage(ContentResolver cr,
                                           Bitmap source,
                                           String title,
                                           String description) {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, title);
        values.put(MediaStore.Images.Media.DESCRIPTION, description);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        // Add the date meta data to ensure the image is added at the front of the gallery
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());

        Uri url = null;
        String stringUrl = null;    /* value to be returned */

        try {
            url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (source != null) {
                OutputStream imageOut = cr.openOutputStream(url);
                try {
                    source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
                } finally {
                    imageOut.close();
                }

                long id = ContentUris.parseId(url);
                // Wait until MINI_KIND thumbnail is generated.
                Bitmap miniThumb = MediaStore.Images.Thumbnails.getThumbnail(cr, id, MediaStore.Images.Thumbnails.MINI_KIND, null);
                // This is for backward compatibility.
                //storeThumbnail(cr, miniThumb, id, 50F, 50F, MediaStore.Images.Thumbnails.MICRO_KIND);
            } else {
                cr.delete(url, null, null);
                url = null;
            }
        } catch (Exception e) {
            if (url != null) {
                cr.delete(url, null, null);
                url = null;
            }
        }

        if (url != null) {
            stringUrl = url.toString();
        }

        return stringUrl;
    }
}