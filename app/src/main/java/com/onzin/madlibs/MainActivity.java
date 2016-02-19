package com.onzin.madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // initialize views and link them to code
    TextView welcome;
    Button nextButton;

    welcome = (TextView) findViewById(R.id.welcome);
    nextButton = (Button) findViewById(R.id.startButton);

    // change home welcome text
    String welcomeText = getString(R.string.welcomeText);
    welcome.setText(welcomeText);

    // a button to go to the next activity
    nextButton.setOnClickListener(new View.OnClickListener() {


       @Override
        public void onClick(View v) {

            Intent formIntent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(formIntent);
        }
    });

    }
}
