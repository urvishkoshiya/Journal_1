package com.example.journal_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText fname,email,gender,doj,mob;
    RadioButton radioButton1,radioButton2,radioButton3;
    Spinner spinner;

    CheckBox chk1;

    Button btn;
    String[] d={"CEO","HR","Developer","Designer","Peon"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        fname=findViewById(R.id.fname);
        email=findViewById(R.id.email);
        gender=findViewById(R.id.gender);
        doj=findViewById(R.id.doj);
        mob=findViewById(R.id.mob);
        radioButton1=findViewById(R.id.radioButton1);
        radioButton2=findViewById(R.id.radioButton2);
        radioButton3=findViewById(R.id.radioButton3);
        spinner=findViewById(R.id.spinner);
        chk1=findViewById(R.id.chk1);
        btn=findViewById(R.id.btn);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,d);
        spinner.setAdapter(adapter);

        doj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(MainActivity.this,MainActivity.this, Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.YEAR));
                datePickerDialog.show();
            }
        });

        btn.setVisibility(View.INVISIBLE);
        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    btn.setVisibility(View.VISIBLE);
                }else {
                    btn.setVisibility(View.INVISIBLE);
                }
            }
        });

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String fname1=fname.getText().toString();
               String email1=email.getText().toString();
               String gender1=gender.getText().toString();
               String doj1=doj.getText().toString();
               String mob1=mob.getText().toString();
               String qual1;
               String desig1=spinner.getSelectedItem().toString();


               if(radioButton1.isChecked())
               {
                   qual1=radioButton1.getText().toString();
               }
               else if(radioButton2.isChecked())
               {
                   qual1=radioButton2.getText().toString();
               }else
               {
                   qual1=radioButton3.getText().toString();
               }

               if(fname1.isEmpty() || email1.isEmpty() || gender1.isEmpty() || doj1.isEmpty() || mob1.isEmpty())
               {
                   Toast.makeText(MainActivity.this, "Please Fill The Required Fields.", Toast.LENGTH_SHORT).show();
               }
               else {
                   Intent i = new Intent(MainActivity.this, MainActivity2.class);
                   i.putExtra("fname",fname1);
                   i.putExtra("email",email1);
                   i.putExtra("gender",gender1);
                   i.putExtra("doj",doj1);
                   i.putExtra("mob",mob1);
                   i.putExtra("qual",qual1);
                   i.putExtra("desig",desig1);
                   startActivity(i);
               }
           }
       });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date=i2+"/"+(i1+1)+"/"+i;
        doj.setText(date);
    }
}