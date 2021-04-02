package com.example.tests;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;



import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.Objects;

import pl.droidsonroids.gif.GifDrawable;

public class MainActivity extends AppCompatActivity {


 private Button knopka;

    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        knopka = findViewById(R.id.button2);
        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.USER);

        try {
            GifDrawable gifFromResource = new GifDrawable( getResources(), R.drawable.gif_nature );
            gifFromResource.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void KnopkaNext(View view) {
        Intent intent = new Intent(MainActivity.this, loanding_tests.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slidein,R.anim.slideout);
    }

    public void knopkaregistration(View view) {

registrationUser();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(knopka.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void registrationUser(){

        AlertDialog.Builder bilder = new AlertDialog.Builder(MainActivity.this);
        bilder.setTitle("Зарегестрироваться");
        bilder.setMessage("Заполните все поля");
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.registration, null);
        bilder.setView(view);
        final MaterialEditText number_student = view.findViewById(R.id.numberStudent);
        final MaterialEditText firs_name = view.findViewById(R.id.firstname);
        final MaterialEditText second_name = view.findViewById(R.id.secondname);

        bilder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
             dialogInterface.dismiss();
            }
        });

        bilder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(Objects.requireNonNull(number_student.getText()).toString())){
                    Toast.makeText(MainActivity.this, "Введите Класс(Группу)", Toast.LENGTH_SHORT).show();

                    return;
                }
                if(TextUtils.isEmpty(Objects.requireNonNull(firs_name.getText()).toString())){
                    Toast.makeText(MainActivity.this, "Введите имя", Toast.LENGTH_SHORT).show();

                    return;
                }
                if(TextUtils.isEmpty(Objects.requireNonNull(second_name.getText()).toString())){
                    Toast.makeText(MainActivity.this, "Введите фамилию", Toast.LENGTH_SHORT).show();

                    return;
                }
              String id = databaseReference.getKey();
                User user = new User();
                   user.setId(id);
                   user.setFirst_name(firs_name.getText().toString());
                   user.setNumber_student(number_student.getText().toString());
                   user.setSecond_name(second_name.getText().toString());
                   databaseReference.push().setValue(user);
                  isNetworkAvailable(MainActivity.this);
                overridePendingTransition(R.anim.slidein_y,R.anim.slideout_y);

            }
        });
 bilder.show();
    }

    public void isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Intent intent = new Intent(MainActivity.this, loanding_tests.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Нету доступа к интернету", Toast.LENGTH_SHORT).show();
        }
    }


    public void klik_for_teatcher(View view) {
        Intent intent = new Intent(MainActivity.this, Teatcher_window.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slidein,R.anim.slideout);
    }
}