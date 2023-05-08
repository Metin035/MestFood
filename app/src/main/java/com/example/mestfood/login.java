package com.example.mestfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class login extends signin {
    public SQLiteDatabase Database;
    public SQLiteDatabase openDb = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();
        try {
            Database = openOrCreateDatabase("MestFoot",MODE_PRIVATE,null);
            Database.execSQL("CREATE TABLE IF NOT EXISTS Users(username VARCHAR, password VARCHAR, E_mail VARCHAR)");
            Toast.makeText(this, "Oluşturuldu.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception DatabaseException){
            System.out.print("SQLite hatası : ");DatabaseException.printStackTrace();
        }
        TextView notAccount = (TextView) findViewById(R.id.notAcount);
        Button login = (Button) findViewById(R.id.login);
        EditText username = (EditText) findViewById(R.id.usName_login);
        EditText password = (EditText) findViewById(R.id.psw_login);
        ImageView us_warning = (ImageView) findViewById(R.id.us_warning);
        ImageView pass_warning = (ImageView) findViewById(R.id.pass_warning);

        notAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pageSignin = new Intent(login.this,signin.class);
                startActivity(pageSignin);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().isEmpty()){
                    username.setHint("Bu alanı boş bırakamazsınız.");
                    username.setHintTextColor(getResources().getColor(R.color.colorForWelcome));
                    us_warning.setVisibility(View.VISIBLE);

                }
                if(password.getText().toString().isEmpty()){
                    password.setHint("Bu alanı boş bırakamazsınız.");
                    password.setHintTextColor(getResources().getColor(R.color.colorForWelcome));
                    pass_warning.setVisibility(View.VISIBLE);
                }
                else{
                    us_warning.setVisibility(View.INVISIBLE);
                    pass_warning.setVisibility(View.INVISIBLE);
                    try {
                        String[] selectionArgs = {username.getText().toString(), password.getText().toString()};
                        String username_in = username.getText().toString();
                        Cursor cursor = Database.rawQuery("SELECT username FROM Users WHERE username=? AND password=?", selectionArgs);
                        if(cursor.moveToFirst()){
                            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
                            /*System.out.println("Kullanıcı bulundu." + username);*/
                            Intent page = new Intent(login.this, Product.class);
                            page.putExtra("username", (CharSequence) username);
                            startActivity(page);
                        }
                        else {
                            System.out.println("Kullanıcı bulunamadı.");
                            Toast.makeText(login.this, "Kullanıcı adı veya şifre yanlış.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception DatabaseException){
                        System.out.println("Sorgulama hatası "); DatabaseException.printStackTrace();
                    }
                }
            }
        });
    }
}