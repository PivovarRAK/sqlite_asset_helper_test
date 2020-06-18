package com.example.sqlite_verbindung;

import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SQLite_Navigator myDb;
    TextView et_vname, et_name;
    Button btn_insert, btn_showData, btn_deleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new SQLite_Navigator(this);
        setupUI();
        setupClicklistener();

    }


    private void setupUI(){
        this.btn_insert = findViewById(R.id.btn_insert);
        this.et_vname = findViewById(R.id.et_vname);
        this.et_name = findViewById(R.id.et_name);
        this.btn_showData = findViewById(R.id.btn_viewData);
        this.btn_deleteAll = findViewById(R.id.btn_delete);
    }

    private void setupClicklistener(){
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_vname.getText().equals("") && !et_name.getText().equals("")) {
                    boolean isInserted = myDb.insertData(et_vname.getText().toString(), et_name.getText().toString());
                    if(isInserted) {
                        et_name.setText("ok");
                        Toast.makeText(MainActivity.this, "Data inserted",Toast.LENGTH_LONG);
                    }else{
                        Toast.makeText(MainActivity.this, "Data not inserted",Toast.LENGTH_LONG);
                    }
                }
            }
        });

        btn_showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = myDb.getAllData();
                if(result.getColumnCount() == 0){
                    Toast.makeText(MainActivity.this,"No data found", Toast.LENGTH_LONG);
                }

                StringBuilder sb = new StringBuilder();
                while(result.moveToNext()){
                    sb.append("ID: " + result.getString(0) +"\n");
                    sb.append("Name: " + result.getString(1) + "\n");
                    sb.append("Nachname: " +  result.getString(2) + "\n");
                }

                showMessage("Data",sb.toString());
            }
        });

        btn_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteAll();
            }
        });
    }


    private void showMessage(String title, String message){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setCancelable(true);
        ab.setTitle(title);
        ab.setMessage(message);
        ab.show();

    }
}
