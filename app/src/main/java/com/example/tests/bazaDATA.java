package com.example.tests;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import static com.example.tests.Datahelper.TABLE_Users;

public class bazaDATA extends AppCompatActivity {
    Datahelper databaseHelper;
    ArrayList<String> aray;
    ArrayAdapter<String> adapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bazadata);

    init();
    }


    private void init(){
        databaseHelper = new Datahelper(this);
        aray = new ArrayList<>();
        list = findViewById(R.id.listwie);
        TextView textView = findViewById(R.id.textView);
        viewData();
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        int tru_answer = bundle.getInt("число");
        Bundle bundle2 = getIntent().getExtras();
        assert bundle2 != null;
        int count_quation = bundle.getInt("число_вопросов");
        int procent = (tru_answer*100)/count_quation;
        if(procent>=70){
            textView.setBackground(getResources().getDrawable(R.drawable.firework));
        }
        if(procent<=50&&procent>20){
            textView.setBackground(getResources().getDrawable(R.drawable.nature));
        }
        if(procent<=20){
            textView.setBackground(getResources().getDrawable(R.drawable.dark));
        }
        textView.setText("Тест сдан на " + String.valueOf(procent) + "%");

    }





    private void viewData() {
        Cursor cursor = databaseHelper.viewData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "Список чист", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                aray.add(cursor.getString(1));
                aray.add(cursor.getString(2));
                aray.add(cursor.getString(3));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, aray);
            list.setAdapter(adapter);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(TABLE_Users,null,null);
        db.close();
        overridePendingTransition(R.anim.slidein,R.anim.slideout);
    }



   // @Override
  //  protected void onDestroy() {
  //      super.onDestroy();

//    }
}
