package com.example.herwis_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends ListActivity {

    TextView txtView;
    int countDel=0;

    List<String> args = new ArrayList<String>();
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        myDB = new DatabaseHelper(this);
        Button goBack = (Button)findViewById(R.id.goAct11);
        Button view = (Button)findViewById(R.id.getData);
        Button del = (Button)findViewById(R.id.deleteData);
        TextView delTxt = (EditText)findViewById(R.id.deletetxt);
        TextView txtView = (TextView) findViewById(R.id.text);
        TextView deleted = (TextView)findViewById(R.id.deleted);
        countDel = Integer.parseInt(deleted.getText().toString());

        Cursor cur = myDB.getListContents();
        StringBuffer buffer = new StringBuffer();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (cur.moveToNext()) { //populating the buffer
                    for (int i = 0; i < cur.getColumnCount(); i++) {
                        buffer.append(cur.getColumnName(i) + ": " + cur.getString(i) + "\n");
                    }
                    buffer.append("\n");
                }


                args.clear();

                int y = 0;
                int z = 0;

                for (int i = 0; i < buffer.length(); i++) {

                    if (buffer.charAt(i) == '\n') {//checking where the \n is located
                        z = z + 1;
                    }

                    try {
                        if (z % 4 == 0 && z > 0) { //here its 4 since we will have \n repeated four times per record
                            args.add(buffer.substring(y, i - 1));
                            z = 0;
                            y = i + 1;
                        }
                    } catch (Exception e) {
                        Toast.makeText(MainActivity3.this, "Error in z method", Toast.LENGTH_LONG).show();
                    }

                }


                setListAdapter(new ArrayAdapter<String>(MainActivity3.this, R.layout.activity_main3, R.id.textlist, args));
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result=myDB.dltRow(delTxt.getText().toString());

                if(result>=1) {
                    Toast.makeText(MainActivity3.this, +result + "Row(s) were susscessfully deleted", Toast.LENGTH_LONG).show();
                    ++countDel;
                }
                else
                    Toast.makeText(MainActivity3.this,"No rows were deleted \nPlease try again",Toast.LENGTH_LONG).show();
                deleted.setText(countDel);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this,MainActivity.class));
            }
        });
    }
    protected Cursor getDataList(){

        return myDB.getListContents();

    }

}