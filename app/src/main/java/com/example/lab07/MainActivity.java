package com.example.lab07;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtKey;
    EditText txtValue;
    DB mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtKey=findViewById(R.id.TxtKey);
        txtValue= findViewById(R.id.TxtValue);
        mydb = new DB (this , "myhase.db",null,1);

    }
    public void OnInsertClick(View v)
    {toString();
        String value = txtValue.getText().toString();
        String key = txtKey.getText().toString();
        if ((mydb.check(key))==true) {

            mydb.do_insert(key, value);
        }
        else
        {
            Toast.makeText(this, "Данный ключ уже занят", Toast.LENGTH_SHORT).show();
        }
    }
    public void OnSelectClick(View v)
    {
        String key = txtKey.getText().toString();
        String value = mydb.do_select(key);
        txtValue.setText(value);

    }
    public void OnUpdate(View v)
    {
        String value= txtValue.getText().toString();
        String key = txtKey.getText().toString();

        mydb.do_upgrade(key,value);

    }
    public void OnDeleteClick(View v)
    {
        String key = txtKey.getText().toString();
        mydb.do_delete(key);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Вы ходитет удалить данную запись?");
        builder.setMessage("Вы уверены?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener(){
            @Override
            public void  onClick(DialogInterface dialog, int which)
            {
                mydb.do_delete(key);
                }


            });


        builder.setNegativeButton("NO", null );
        builder.show();










    }

}