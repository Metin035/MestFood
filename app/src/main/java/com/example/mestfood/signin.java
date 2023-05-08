package com.example.mestfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class signin extends AppCompatActivity {
    public SQLiteDatabase Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        getSupportActionBar().hide();

        //Creating new database for our application.
        try {
            Database = openOrCreateDatabase("MestFoot",MODE_PRIVATE,null);
            Database.execSQL("CREATE TABLE IF NOT EXISTS Users(username VARCHAR, password VARCHAR, E_mail VARCHAR)");
            //Toast.makeText(this, "Database is created.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception DatabaseException){
            System.out.println("Database Creating error : ");DatabaseException.printStackTrace();
        }
        try {

            Database.execSQL("DELETE FROM Users WHERE username = '';");

        }
        catch (Exception DeleteException){
            DeleteException.printStackTrace();
        }

        //Creating our object for program.
        TextView haveAccount = (findViewById(R.id.haveAccount));
        TextView username_in = (findViewById(R.id.username_signin));
        TextView password_in = (findViewById(R.id.password_signin));
        TextView e_mail_in = (findViewById(R.id.e_mail_signin));
        Button sign_in = (Button) findViewById(R.id.signin);


        //Click Events
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pageLogin = new Intent(signin.this,login.class);
                startActivity(pageLogin);
            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String username = username_in.getText().toString();
                    String password = password_in.getText().toString();
                    String e_mail = e_mail_in.getText().toString();
                    Database.execSQL("INSERT INTO Users (username,password,E_mail) VALUES ('"+username+"','"+password+"','"+e_mail+"')");
                    //Toast.makeText(signin.this, "Kaydınız başarılı bir şekilde oluşturulmuştur.", Toast.LENGTH_SHORT).show();
                    Intent page = new Intent(signin.this, signin_succesfull.class);
                    startActivity(page);

                }
                catch (Exception InsertException){

                    System.out.println("Database inserting error : ");InsertException.printStackTrace();
                }
            }
        });
    }
}