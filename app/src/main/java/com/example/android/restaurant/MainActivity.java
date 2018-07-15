package com.example.android.restaurant;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHelper ;
    SQLiteDatabase db, userData ;
    EditText name , password ,userType;
    Button loginBtn , signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userData= dbHelper.getWritableDatabase();
        name = findViewById(R.id.nameEditText);
        password = findViewById(R.id.passwordEditText);
        userType = findViewById(R.id.userTypeEditText);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        try {
            dbHelper = new DbHelper(this);
            db = dbHelper.getReadableDatabase();
            Toast.makeText(this,"Database created",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.i("MainActivity", "" + e);

        }
    }
    public void currentUserInfo(View view)  {
        String[] columns = {Database.UserInfo.NAME, Database.UserInfo.PAEEWORD};
        String[] selectionArg = {name.getText().toString(), password.getText().toString()};
        Cursor confirmUserNameAndPassword = db.query(Database.UserInfo.TABLE, columns, "name = ? AND password = ? ", selectionArg, null, null, null, null);
        if (confirmUserNameAndPassword.getCount() == 0) {
            Toast.makeText(this, "Incorrect user name or password", Toast.LENGTH_LONG).show();
        } else {
            String[] optainUserType = {Database.UserInfo.TYPE};
            Cursor getUserType = db.query(Database.UserInfo.TABLE, optainUserType, "name = ? AND password = ? ", selectionArg, null, null, null, null);
            if (getUserType.getInt(2) == 0) {
                Intent client = new Intent(this,MenuActivity.class);
                startActivity(client);
            }
            else if(getUserType.getInt(2) == 1){
                Intent driver = new Intent(this,DriverActivity.class);
                startActivity(driver);
            }

            else if(getUserType.getInt(2) == 2){
                Intent owner = new Intent(this,OwnerActivity.class);
                startActivity(owner);
            }

        }
    }



    public void saveIntoDB(View view){
        String[] columns = {Database.UserInfo.NAME,Database.UserInfo.PAEEWORD};
        String[] selectionArg ={name.getText().toString(),password.getText().toString()};
        Cursor c = db.query(Database.UserInfo.TABLE,columns,"name = ? AND password = ? ",selectionArg,null,null,null,null);
        if( c.getCount()==0){
            ContentValues values = new ContentValues();
            values.put(Database.UserInfo.NAME,name.getText().toString());
            values.put(Database.UserInfo.PAEEWORD,password.getText().toString());
            values.put(Database.UserInfo.TYPE,Integer.parseInt(userType.getText().toString()));
            userData.insert(Database.UserInfo.TABLE,null,values);
            if (Integer.parseInt((userType.getText().toString())) == 0 ){
                Intent client = new Intent(this,MenuActivity.class);
                startActivity(client);
            }
            else if(Integer.parseInt((userType.getText().toString())) == 1){
                Intent driver = new Intent(this,DriverActivity.class);
                startActivity(driver);
            }

            else if(Integer.parseInt((userType.getText().toString())) == 2){
                Intent owner = new Intent(this,OwnerActivity.class);
                startActivity(owner);
            }

        }

    }
}
