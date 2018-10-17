package com.example.josef.rpsgame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"Just for github test",Toast.LENGTH_SHORT).show();
    }
}
