package com.example.herwis_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public DatabaseHelper myDB;
    EditText name;
    EditText id;
    EditText phone;
    EditText email;
    String Sname;
    int Iid;
    int Iphone;
    String Semail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        id = (EditText)findViewById(R.id.id);
        email = (EditText)findViewById(R.id.email);


        Button insert = (Button)findViewById(R.id.insert);
        Button goAct2 = (Button)findViewById(R.id.goAct2);
        Button goAct3 = (Button)findViewById(R.id.goAct3);

        myDB=new DatabaseHelper(this);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Sname = name.getText().toString();
        Iid = Integer.parseInt(id.getText().toString());
        Iphone = Integer.parseInt(phone.getText().toString());
        Semail = email.getText().toString();

        SharedPreferences.Editor editor=sharedPref.edit();// we now use this editor to write pairs inside our sharedPref
        editor.putString("Key1",Sname);
        editor.putInt("Key2",Iid);
        editor.putString("Key3",Semail);
        editor.putInt("Key2",Iphone);
        editor.commit(); // we now have a structure (sharedPref) that saves those pairs

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDB.addData(Integer.parseInt(id.getText().toString()),name.getText().toString(),email.getText().toString(),Integer.parseInt(phone.getText().toString()) )==false)
                    Toast.makeText(MainActivity.this,"Data was not entered into the table \nPlease check your input!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data was successfully entered into the table",Toast.LENGTH_LONG).show();}

            });

        goAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });
        goAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity3.class));
            }
        });
    }
}