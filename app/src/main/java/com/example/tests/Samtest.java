package com.example.tests;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;




public class Samtest extends AppCompatActivity {

    private TextView question;
    private TextView timecod;
    private TextView timecod2;
    private TextView timecod3;
    private TextView timecod4;
    private RadioButton answerradioknopka1;
    private RadioButton answerradioknopka2;
    private RadioButton answerradioknopka3;
    private Button knopkanextquestion;
    private Button knopka;
    private Button knopkafinish;
    private Button button_start;
    private boolean start= false;
    private int count4;
    private int count_time;
    private int count_time2=10;
    private int second_count =10;
    private int count2=0;
    private  int count3=0;
    private int count5=0;
    Datahelper datahelper;
    Datahelper2 datahelper2;
   // DatabaseReference databaseReference2;
    private ArrayList<Integer> massiv;
    private ArrayList<String> aray;
  //  private ArrayList<String> aray_number_quation;
    static int stabiln_peremen;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samtest);
      init();
// get_elements_number_quation();




knopkanextquestion.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        knopkanextquestion.setVisibility(View.INVISIBLE);
        knopka.setVisibility(View.VISIBLE);



        count2++;
        //try {


            stabiln_peremen=get_number_random();
            question.setText(test_function().get(stabiln_peremen));
            answerradioknopka1.setText(test_function1().get(stabiln_peremen));
            answerradioknopka2.setText(test_function2().get(stabiln_peremen));
            answerradioknopka3.setText(test_function3().get(stabiln_peremen));

       // }catch (IndexOutOfBoundsException e){
     //       Toast.makeText(Samtest.this, "IndexOutOfBoundsException", Toast.LENGTH_SHORT).show();
   //     }

    }
});
    }



private void init() {

    knopka = findViewById(R.id.knopkanextquestion2);
    knopkafinish = findViewById(R.id.knopkafinish);
    button_start =findViewById(R.id.knopkastart);
    question=findViewById(R.id.question);
    timecod =findViewById(R.id.timecod);
    timecod2 =findViewById(R.id.timecod2);
    timecod3 =findViewById(R.id.timecod3);
    timecod4 =findViewById(R.id.timecod4);
    datahelper = new Datahelper(this);
    Bundle bundle = getIntent().getExtras();
    assert bundle != null;
    ArrayList<String> arrayList6 = bundle.getStringArrayList("время");
    assert arrayList6 != null;
    count_time=Integer.parseInt(String.valueOf( arrayList6.get(arrayList6.size()-1)));
    count_time+=-1;
    aray = new ArrayList<>();
    datahelper2 = new Datahelper2(this);
    knopkanextquestion =findViewById(R.id.knopkanextquestion);
    answerradioknopka1 = findViewById(R.id.answer1);
    answerradioknopka2 = findViewById(R.id.answer2);
    answerradioknopka3 = findViewById(R.id.radioknopka_answer3);
    answerradioknopka1.setOnClickListener(grupparadioknopak);
    answerradioknopka2.setOnClickListener(grupparadioknopak);
    answerradioknopka3.setOnClickListener(grupparadioknopak);
    massiv=function_mix();
   // databaseReference2 = FirebaseDatabase.getInstance().getReference(Constants.TESTS);
   // aray_number_quation = new ArrayList<>();
}



    private void functiontimecod(){
        start = true;
     new Thread(new Runnable() {
         @Override
         public void run() {

             while (start){

              second_count --;
runOnUiThread(new Runnable() {
    @Override
    public void run() {
timecod.setText(String.valueOf(second_count));
timecod2.setText(String.valueOf(count_time));

if(second_count==0){
    second_count=10;
    count_time--;
}
if(count_time==0){

    second_count=0;
    count_time2--;
    timecod4.setVisibility(View.VISIBLE);
    timecod4.setText(String.valueOf(count_time2));
    timecod.setVisibility(View.INVISIBLE);
    timecod2.setVisibility(View.INVISIBLE);
    timecod3.setVisibility(View.INVISIBLE);



    if(count_time2==0){
        Intent intent = new Intent(Samtest.this, bazaDATA.class);
        intent.putExtra("число", count5);
        intent.putExtra("число_вопросов", count4);
        startActivity(intent);
        overridePendingTransition(R.anim.slidein,R.anim.slideout);
     count_time2=0;
        start=false;
        finish();
    }
}


    }
});
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }

         }
     }).start();

    }


    View.OnClickListener grupparadioknopak = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RadioButton radioButton = (RadioButton)view;
            switch (radioButton.getId()){
                case R.id.answer1 :

                    knopka.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            viewData();

                                    if(test_function1().get(stabiln_peremen).contains(aray.get(stabiln_peremen))){
                                        count5++;
                                        datahelper.insertData("Вопрос " + test_function().get(stabiln_peremen),  "Ваш ответ" + test_function1().get(stabiln_peremen),"+");
                                    }else{

                                        datahelper.insertData("Вопрос " + test_function().get(stabiln_peremen),  "Ваш ответ" + test_function1().get(stabiln_peremen), "Правельный ответ" + aray.get(stabiln_peremen));
                                    }



                            if(count2==count4){

                                           knopkafinish.setVisibility(View.VISIBLE);

                                knopkafinish.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Samtest.this,bazaDATA.class);
                                        intent.putExtra("число", count5);
                                        intent.putExtra("число_вопросов", count4);
                                        start=false;
                                        startActivity(intent);
                                        finish();
                                        overridePendingTransition(R.anim.slidein,R.anim.slideout);
                                    }
                                });
                            }


                                    knopka.setVisibility(View.INVISIBLE);
                                    knopkanextquestion.setVisibility(View.VISIBLE);


                            if(count2==count4){
                                knopkanextquestion.setVisibility(View.INVISIBLE);
                            }

                        }
                    });

                   answerradioknopka2.setChecked(false);
                   answerradioknopka3.setChecked(false);


                    break;

                case R.id.answer2 :

                    knopka.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            viewData();

                                    if(test_function2().get(stabiln_peremen).contains(aray.get(stabiln_peremen))){
                                        count5++;
                                        datahelper.insertData("Вопрос " + test_function().get(stabiln_peremen),"Ваш отвтет " + test_function2().get(stabiln_peremen), "+");
                                    }else{

                                        datahelper.insertData("Вопрос " + test_function().get(stabiln_peremen),"Ваш отвтет " + test_function2().get(stabiln_peremen), "Правельный ответ" + aray.get(stabiln_peremen));
                                    }
                            if(count2==count4){

                                    knopkafinish.setVisibility(View.VISIBLE);



                                knopkafinish.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Intent intent = new Intent(Samtest.this,bazaDATA.class);
                                        intent.putExtra("число", count5);
                                        intent.putExtra("число_вопросов", count4);
                                        start=false;
                                        startActivity(intent);
                                        finish();
                                        overridePendingTransition(R.anim.slidein,R.anim.slideout);

                                    }
                                });
                            }


                                    knopka.setVisibility(View.INVISIBLE);
                                    knopkanextquestion.setVisibility(View.VISIBLE);

                            if(count2==count4){
                                knopkanextquestion.setVisibility(View.INVISIBLE);
                            }


                        }
                    });


                    answerradioknopka1.setChecked(false);
                    answerradioknopka3.setChecked(false);

                    break;

                case R.id.radioknopka_answer3 :

                    knopka.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            viewData();

                             if(test_function3().get(stabiln_peremen).contains(aray.get(stabiln_peremen))){
                                 count5++;
                                        datahelper.insertData("Вопрос " + test_function().get(stabiln_peremen),"Ваш ответ " + test_function3().get(stabiln_peremen),"+");
                                    }else{

                                        datahelper.insertData("Вопрос " + test_function().get(stabiln_peremen),"Ваш ответ " + test_function3().get(stabiln_peremen),"Правельный ответ " + aray.get(stabiln_peremen));
                                    }


                            if(count2==count4){



                                     knopkafinish.setVisibility(View.VISIBLE);




                                knopkafinish.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Intent intent = new Intent(Samtest.this,bazaDATA.class);
                                        intent.putExtra("число", count5);
                                        intent.putExtra("число_вопросов", count4);
                                        start=false;
                                        startActivity(intent);
                                        finish();
                                        overridePendingTransition(R.anim.slidein_y,R.anim.slideout_y);

                                    }
                                });
                            }

                                    knopkanextquestion.setVisibility(View.VISIBLE);
                                    knopka.setVisibility(View.INVISIBLE);

                            if(count2==count4){
                                knopkanextquestion.setVisibility(View.INVISIBLE);
                            }

                        }
                    });
                    answerradioknopka1.setChecked(false);
                    answerradioknopka2.setChecked(false);
                    break;
            }

        }
    };



    private int get_number_random(){
        String str =massiv.get(count3++).toString();

        return Integer.parseInt(str);
    }


   /* private void get_elements_number_quation(){
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(aray_number_quation.size()>0){
                    aray_number_quation.clear();
                }
                for(DataSnapshot ds : snapshot.getChildren()){
                    Constant_Tests tests = ds.getValue(Constant_Tests.class);
                    assert tests != null;
                    aray_number_quation.add(tests.getCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference2.addValueEventListener(valueEventListener);
   /*/// }


    private ArrayList<Integer> function_mix(){
        Bundle bundle2 = getIntent().getExtras();
        assert bundle2 != null;
        ArrayList<String> arrayList5 = bundle2.getStringArrayList("количество_вопросов");
        assert arrayList5 != null;
        count4=Integer.parseInt(String.valueOf( arrayList5.get(arrayList5.size()-1)));
    //    count4=Integer.parseInt(String.valueOf( aray_number_quation.get(aray_number_quation.size()-1)));
        int number_quation = count4;
        number_quation +=-1;
        ArrayList<Integer> lottery = new ArrayList<>();
        for (int i = 0; i <= number_quation; i++) {
            lottery.add(i);
        }
        Collections.shuffle(lottery);
        return lottery;
    }

private ArrayList<String> test_function(){
    ArrayList<String> arrayList;
        Bundle bundle = getIntent().getExtras();
    assert bundle != null;
    arrayList = bundle.getStringArrayList("вопрос");

        return arrayList;
    }


    private ArrayList<String> test_function1(){
        ArrayList<String> arrayList2;
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        arrayList2 = bundle.getStringArrayList("ответ1");


        return arrayList2;
    }

    private ArrayList<String> test_function2(){
        ArrayList<String> arrayList3;
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        arrayList3 = bundle.getStringArrayList("ответ2");

        return arrayList3;
    }


    private void viewData() {
        Cursor cursor = datahelper2.viewData2();
        if(cursor.getCount()==0){
            Toast.makeText(this, "Список чист", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                aray.add(cursor.getString(1));
            }

        }
    }

    private ArrayList<String> test_function3(){
        ArrayList<String> arrayList4;
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        arrayList4 = bundle.getStringArrayList("правельный_ответ");

        return arrayList4;
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        start =false;
        overridePendingTransition(R.anim.slidein,R.anim.slideout);
    }


    public void start(View view) {
        count2++;

        knopka.setVisibility(View.VISIBLE);
        button_start.setVisibility(View.INVISIBLE);
        functiontimecod();

       // try {
            stabiln_peremen=get_number_random();
            question.setText(test_function().get(stabiln_peremen));
            answerradioknopka1.setText(test_function1().get(stabiln_peremen));
            answerradioknopka2.setText(test_function2().get(stabiln_peremen));
            answerradioknopka3.setText(test_function3().get(stabiln_peremen));

       // }catch (IndexOutOfBoundsException e){
       //     Toast.makeText(Samtest.this, "IndexOutOfBoundsException", Toast.LENGTH_SHORT).show();
     //   }
    }
}