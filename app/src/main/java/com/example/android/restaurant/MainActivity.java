package com.example.android.restaurant;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHelper ;
    SQLiteDatabase db, userData ;
    EditText name , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userData= dbHelper.getWritableDatabase();
        name = findViewById(R.id.nameEditText);
        password = findViewById(R.id.passwordEditText);
        try {
            dbHelper = new DbHelper(this);
            db = dbHelper.getReadableDatabase();
            Toast.makeText(this,"Database created",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.i("MainActivity", "" + e);

        }
    }
    public void saveIntoDB(View view) {

        ContentValues values =  new ContentValues();
        values.put(Database.UserInfo.NAME,name.getText().toString());
        values.put(Database.UserInfo.PAEEWORD,password.getText().toString());
        userData.insert(Database.UserInfo.TABLE,null,values);
        Intent home = new Intent(this,HomeActivity.class);
        startActivity(home);

    }

    public void currentUserInfo(View view) {
        String[] columns = {Database.UserInfo.NAME,Database.UserInfo.PAEEWORD};
        String[] selectionArg ={name.getText().toString(),password.getText().toString()};
        Cursor c = db.query(Database.UserInfo.TABLE,columns,"name = ? AND password = ?",selectionArg,null,null,null,null);
        if( c.getCount()==0){
            Toast.makeText(this,"User not Found",Toast.LENGTH_SHORT).show();
        }
        String[] selection = {"type"};
        Cursor userType = db.query(Database.UserInfo.TABLE,selection,null,
                selectionArg,null,null,null,null);



    }
}
