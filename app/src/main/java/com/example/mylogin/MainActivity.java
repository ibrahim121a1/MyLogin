package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signin(View v)
    {
        Intent intent=new Intent(MainActivity.this,SigninAct.class);
        startActivity(intent);
    }

    public void signup(View v)
    {
        Intent intent=new Intent(MainActivity.this,SignUpAct.class);
        startActivity(intent);
    }
}
