package com.onzin.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    // initialize layout views
    TextView storyBlock;
    Button homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // collect the extras put to this activity
        Bundle extras = getIntent().getExtras();
        String story = extras.getString("story");

        // linking layoutviews with codeviews
        storyBlock = (TextView) findViewById(R.id.textBlock);
        homeButton = (Button) findViewById(R.id.homeButton);

        // displays the story
        storyBlock.setText(story);

        // when button is clicked, go back to home page
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent homeIntent = new Intent(StoryActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
            });

    }
}
