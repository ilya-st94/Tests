package com.example.tests;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.Objects;
public class create_tests extends AppCompatActivity {

    EditText quation;
    Button create;
    EditText answer1;
    EditText answer2;
    Button knopka_options;
    EditText answer3;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    DatabaseReference databaseReference, databaseReference2;
    Datahelper2 datahelper2;
    Datahelper3 datahelper3;
    private String id, id2;
    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tests);
        init();
        radioButton1.setOnClickListener(grupparadioknopak);
        radioButton2.setOnClickListener(grupparadioknopak);
        radioButton3.setOnClickListener(grupparadioknopak);
    }

    private void init(){



        create = findViewById(R.id.create);
        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        quation = findViewById(R.id.quation);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.true_answer);
        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.TRU_ANSWER);
        databaseReference2 = FirebaseDatabase.getInstance().getReference(Constants.TESTS);

        datahelper2 = new Datahelper2(this);
        datahelper3 = new Datahelper3(this);
        knopka_options = findViewById(R.id.options);
    }

    private void options(){
        AlertDialog.Builder bilder = new AlertDialog.Builder(create_tests.this);
        bilder.setTitle("Настройки теста");
        bilder.setMessage("Заполните все поля");
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.options, null);
        bilder.setView(view);
        final MaterialEditText time = view.findViewById(R.id.time_test);
       final MaterialEditText kol = view.findViewById(R.id.coust_quation);
        bilder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        bilder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(TextUtils.isEmpty(Objects.requireNonNull(time.getText()).toString())){
                    Toast.makeText(create_tests.this, "Введите время", Toast.LENGTH_SHORT).show();

                    return;
                }
                if(TextUtils.isEmpty(Objects.requireNonNull(kol.getText()).toString())){
                    Toast.makeText(create_tests.this, "Введите количество вопросов", Toast.LENGTH_SHORT).show();

                    return;
                }
                if(kol.getText().toString().length()>3){
                    Toast.makeText(create_tests.this, "Ошибка", Toast.LENGTH_SHORT).show();
                }






              /*  ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(arrayList1.size()>0){
                            arrayList1.clear();
                        }
                        for(DataSnapshot ds : snapshot.getChildren()){
                            Tests tests = ds.getValue(Tests.class);

                            assert tests != null;
                            arrayList1.add(tests.getQuation());
                            if(Integer.parseInt(arrayList1.get(arrayList1.size()-1))==Integer.parseInt(kol.toString())){
                                Toast.makeText(create_tests.this, "Нету стольколь вопросов", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
              /*/
              //databaseReference.addValueEventListener(valueEventListener);

                if(kol.getText().toString().contains(",") || kol.getText().toString().contains("#")|| kol.getText().toString().contains("*") || kol.getText().toString().contains("-") || kol.getText().toString().contains("+") || kol.getText().toString().contains(")")|| kol.getText().toString().contains("(")|| kol.getText().toString().contains(";")|| kol.getText().toString().contains("/")|| kol.getText().toString().contains("N")||  time.getText().toString().contains("," ) ||  time.getText().toString().contains("#")||  time.getText().toString().contains("*") ||  time.getText().toString().contains("-") ||  time.getText().toString().contains("+")||  time.getText().toString().contains(")")||  time.getText().toString().contains("(")||  time.getText().toString().contains(";")||  time.getText().toString().contains("/")||  time.getText().toString().contains("N") ){

                    AlertDialog.Builder bilder = new AlertDialog.Builder(create_tests.this);
                    bilder.setTitle("Ошибка!");
                    bilder.setMessage("Не допустимые символы * / - + # N ; , ");
                    bilder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                     bilder.show();

                 }else{
                     datahelper3.insertData(kol.getText().toString(),time.getText().toString());

                    id = databaseReference2.getKey();
                    Constant_Tests tru_answer4 = new Constant_Tests();
                    tru_answer4.setCount(kol.getText().toString());
                    tru_answer4.setMinute(time.getText().toString());
                    databaseReference2.push().setValue(tru_answer4);
                }



                isNetworkAvailable(create_tests.this);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(knopka_options.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        bilder.show();
    }







    View.OnClickListener grupparadioknopak = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RadioButton radioButton = (RadioButton)view;
            switch (radioButton.getId()){
                case R.id.radioButton :

                    create.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(TextUtils.isEmpty(Objects.requireNonNull(quation.getText()).toString()) || TextUtils.isEmpty(Objects.requireNonNull(answer1.getText()).toString()) || TextUtils.isEmpty(Objects.requireNonNull(answer2.getText()).toString()) || TextUtils.isEmpty(Objects.requireNonNull(answer3.getText()).toString())){
                                Toast.makeText(create_tests.this, "Все поля не заполнены", Toast.LENGTH_SHORT).show();
                           return;
                            }else{
                                id2 = databaseReference.getKey();
                                Tru_answer tru_answer = new Tru_answer();
                                tru_answer.setAnsw1(answer1.getText().toString());
                                tru_answer.setQation(quation.getText().toString());
                                tru_answer.setAnsw2(answer2.getText().toString());
                                tru_answer.setAnsw3(answer3.getText().toString());
                                tru_answer.setId(id2);
                                databaseReference.push().setValue(tru_answer);
                                datahelper2.insertData(answer1.getText().toString());
                                count++;
                                Toast.makeText(create_tests.this, "Создан " + count + " Тест", Toast.LENGTH_SHORT).show();
                                quation.setText("");
                                answer1.setText("");
                                answer2.setText("");
                                answer3.setText("");
                            }


                            isNetworkAvailable(create_tests.this);
                            InputMethodManager imm3 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm3.hideSoftInputFromWindow(create.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                        }
                    });
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);

                    break;

                case R.id.radioButton2 :

                    create.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(TextUtils.isEmpty(Objects.requireNonNull(quation.getText()).toString()) || TextUtils.isEmpty(Objects.requireNonNull(answer1.getText()).toString()) || TextUtils.isEmpty(Objects.requireNonNull(answer2.getText()).toString()) || TextUtils.isEmpty(Objects.requireNonNull(answer3.getText()).toString())){
                                Toast.makeText(create_tests.this, "Все поля не заполнены", Toast.LENGTH_SHORT).show();
                                return;
                            }else{

                                id2 = databaseReference.getKey();
                                Tru_answer tru_answer2 = new Tru_answer();
                                tru_answer2.setAnsw1(answer1.getText().toString());
                                tru_answer2.setQation(quation.getText().toString());
                                tru_answer2.setAnsw2(answer2.getText().toString());
                                tru_answer2.setAnsw3(answer3.getText().toString());
                                tru_answer2.setId(id2);
                                databaseReference.push().setValue(tru_answer2);
                                 datahelper2.insertData(answer2.getText().toString());
                                count++;
                                Toast.makeText(create_tests.this, "Создан " + count + " Тест", Toast.LENGTH_SHORT).show();
                                quation.setText("");
                                answer1.setText("");
                                answer2.setText("");
                                answer3.setText("");
                            }
                            isNetworkAvailable(create_tests.this);
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(create.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    });
                    radioButton1.setChecked(false);
                    radioButton3.setChecked(false);

                    break;

                case R.id.radioButton3 :
                    create.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(TextUtils.isEmpty(Objects.requireNonNull(quation.getText()).toString()) || TextUtils.isEmpty(Objects.requireNonNull(answer1.getText()).toString()) || TextUtils.isEmpty(Objects.requireNonNull(answer2.getText()).toString()) || TextUtils.isEmpty(Objects.requireNonNull(answer3.getText()).toString())){
                                Toast.makeText(create_tests.this, "Все поля не заполнены", Toast.LENGTH_SHORT).show();
                                return;
                            }else{


                                id2 = databaseReference.getKey();
                                Tru_answer tru_answer3 = new Tru_answer();
                                tru_answer3.setAnsw1(answer1.getText().toString());
                                tru_answer3.setQation(quation.getText().toString());
                                tru_answer3.setAnsw2(answer2.getText().toString());
                                tru_answer3.setAnsw3(answer3.getText().toString());
                                tru_answer3.setId(id2);
                                databaseReference.push().setValue(tru_answer3);
                                 datahelper2.insertData(answer3.getText().toString());
                                 count++;
                                 Toast.makeText(create_tests.this, "Создан " + count + " Тест", Toast.LENGTH_SHORT).show();
                                quation.setText("");
                                answer1.setText("");
                                answer2.setText("");
                                answer3.setText("");
                            }
                            isNetworkAvailable(create_tests.this);
                            InputMethodManager imm2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm2.hideSoftInputFromWindow(create.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                        }
                    });

                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    break;
            }

        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidein,R.anim.slideout);
    }

    public void isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null ) {
            Toast.makeText(this, "Нету доступа к интернету", Toast.LENGTH_SHORT).show();
        }
    }




    public void Options(View view) {

options();

    }
}