package com.example.tests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.Objects;

import pl.droidsonroids.gif.GifDrawable;


public class Teatcher_window extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
private EditText email;
private EditText password;
private Button regestration;
private Button entered;
       

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GifDrawable gifFromResource = new GifDrawable( getResources(), R.drawable.light_bulb );
            gifFromResource.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_teatcher_window);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPersonName2);
        regestration = findViewById(R.id.button_registration);
        entered = findViewById(R.id.entered_button);

    }

    public void registation(View view) {
        registration_teather();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(regestration.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void Entered(View view) {


        if(TextUtils.isEmpty(Objects.requireNonNull(email.getText()).toString())){
            Toast.makeText(Teatcher_window.this, "Введите Email", Toast.LENGTH_SHORT).show();

            return;
        }
        if(TextUtils.isEmpty(Objects.requireNonNull(password.getText()).toString())){
            Toast.makeText(Teatcher_window.this, "Введите пороль", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(Teatcher_window.this, "Вы зашли", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Teatcher_window.this, create_tests.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slidein,R.anim.slideout);
                }else{
                    Toast.makeText(Teatcher_window.this, "Что то  пошло не так", Toast.LENGTH_SHORT).show();
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(entered.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

    }









    private void registration_teather(){
        AlertDialog.Builder bilder = new AlertDialog.Builder(Teatcher_window.this);
        bilder.setTitle("Зарегестрироваться");
        bilder.setMessage("Заполните все поля");
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.registration_teather, null);
        bilder.setView(view);
        final MaterialEditText login = view.findViewById(R.id.login);
        final MaterialEditText password = view.findViewById(R.id.password);
        bilder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        bilder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(Objects.requireNonNull(login.getText()).toString())){
                    Toast.makeText(Teatcher_window.this, "Введите Email", Toast.LENGTH_SHORT).show();

                    return;
                }
                if(TextUtils.isEmpty(Objects.requireNonNull(password.getText()).toString())){
                    Toast.makeText(Teatcher_window.this, "Введите пороль", Toast.LENGTH_SHORT).show();

                    return;
                }
                if(password.getText().toString().length()>30){
                    Toast.makeText(Teatcher_window.this, "Достигнуто максимальное значение", Toast.LENGTH_SHORT).show();

                    return;
                }

                if(login.getText().toString().length()>30){
                    Toast.makeText(Teatcher_window.this, "Достигнуто максимальное значение", Toast.LENGTH_SHORT).show();

                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(login.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Teatcher_window.this, "Вы зарегестрированы", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Teatcher_window.this, "Что то  пошло не так", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        bilder.show();

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidein,R.anim.slideout);
    }


}