package com.example.tests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class loanding_tests extends AppCompatActivity {
    private ArrayList<String> aray;
    private ArrayList<String> aray2;
    private ArrayList<String> aray3;
    private ArrayList<String> aray4;
    private ArrayList<String> aray5;
    private ArrayList<String> aray6;


    private Button button;
    DatabaseReference databaseReference;
    Datahelper3 datahelper3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loanding_tests);
        init();
 datahelper3 = new Datahelper3(this);
        get_elements();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isNetworkAvailable(loanding_tests.this);
            }
        });


    }


    private void init(){


        aray = new ArrayList<>();
        aray2 = new ArrayList<>();
        aray3 = new ArrayList<>();
        aray4 = new ArrayList<>();
        aray5 = new ArrayList<>();
        aray6 = new ArrayList<>();

        button = findViewById(R.id.kjpka);

        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.TRU_ANSWER);

    }

    private void get_elements(){
        ValueEventListener valueEventListener =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(aray.size()>0){
                    aray.clear();
                }
                if(aray2.size()>0){
                    aray2.clear();
                }
                if(aray3.size()>0){
                    aray3.clear();
                }
                if(aray4.size()>0){
                    aray4.clear();
                }



                for(DataSnapshot ds : snapshot.getChildren()){
                    Tru_answer tests = ds.getValue(Tru_answer.class);
                    assert tests != null;
                    aray.add(tests.getQation());
                    aray2.add(tests.getAnsw1());
                    aray3.add(tests.getAnsw2());
                    aray4.add(tests.getAnsw3());
                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidein,R.anim.slideout);
    }


    public void isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Intent intent = new Intent(loanding_tests.this, Samtest.class);
                intent.putExtra("вопрос",aray);
                  intent.putExtra("ответ1",aray2);
                    intent.putExtra("ответ2",aray3);
                      intent.putExtra("правельный_ответ",aray4);
           viewData();
            intent.putExtra("количество_вопросов",aray5);
            intent.putExtra("время",aray6);
            startActivity(intent);
            overridePendingTransition(R.anim.slidein_y,R.anim.slideout_y);
        } else {
            Toast.makeText(this, "Нету доступа к интернету", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewData() {
        Cursor cursor = datahelper3.viewData2();
        if(cursor.getCount()==0){
            Toast.makeText(this, "Список чист", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                aray5.add(cursor.getString(1));
                aray6.add(cursor.getString(2));
            }

        }
    }


}