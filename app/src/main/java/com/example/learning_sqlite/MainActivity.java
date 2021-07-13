package com.example.learning_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    Button insert,view,update,delete;
    EditText logs,ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        insert=findViewById(R.id.btn1);
        view=findViewById(R.id.btn2);
        update=findViewById(R.id.btn3);
        delete=findViewById(R.id.btn4);
        logs=findViewById(R.id.Logs);
        ID=findViewById(R.id.id);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isinserted = mydb.insertData(String.valueOf(logs.getText()),"4th July " +
                        "2021");
                if(isinserted==true){
                    Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_SHORT).show();
                    logs.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this, "Data insertion failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur = mydb.viewdata();
                if(cur.getCount()==0){
                    Toast.makeText(MainActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while (cur.moveToNext()){
                    buffer.append("ID: "+cur.getString(0)+"\n");
                    buffer.append("Logs: "+cur.getString(1)+"\n");
                    buffer.append("Time: "+cur.getString(2)+"\n");
                }
                showmessage("Data",buffer.toString());
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isupdate=mydb.update(ID.getText().toString(),logs.getText().toString(),
                        "5th July 2021");
                if(isupdate==true){
                    Toast.makeText(MainActivity.this,"Data updated",Toast.LENGTH_SHORT).show();
                    logs.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this, "Data updation failed",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delete_row=mydb.delete(ID.getText().toString());
                if(delete_row>0){
                    Toast.makeText(MainActivity.this, "Row Deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Delete not done", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void showmessage(String title,String message){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.show();
    }
}