package com.onzin.madlibs;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class FormActivity extends AppCompatActivity {

    // initialize views, stream, storyclass and variables
    EditText saveWord;
    Button nextButton;
    InputStream storyStream;
    TextView textBlock;
    TextView textToType;
    TextView textWordsLeft;
    Story oldStory;
    String newStory;
    int wordsLeft = 0;

    // random number for random text
    Random rand = new Random();
    int fileNumber = (rand.nextInt(4) + 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // linking layoutviews to codeviews
        saveWord = (EditText) findViewById(R.id.wordInput);
        nextButton = (Button) findViewById(R.id.inputButton);
        textToType = (TextView) findViewById(R.id.textToType);
//        textBlock = (TextView) findViewById(R.id.textBlock);
        textWordsLeft = (TextView) findViewById(R.id.textWordsLeft);

        // setting up an input stream and scanner of the text file
        Scanner storyScanner;
        AssetManager am = getAssets();

        // opening stream and displaying first views
        try {
            storyStream = am.open("madlib"+fileNumber+".txt");
            storyScanner = new Scanner(storyStream);
            oldStory = new Story(storyScanner);

            wordsLeft = oldStory.getPlaceholderCount();

            textWordsLeft.setText("there are " + wordsLeft +" words left." );
            textToType.setText("please type a " + oldStory.getNextPlaceholder());

        } catch (IOException e) {
            e.printStackTrace();
        }

        // what must happen when button is clicked
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // filling placeholder, empty the input box, and update views
                oldStory.fillInPlaceholder(saveWord.getText().toString());
                saveWord.setText("");
                wordsLeft = oldStory.getPlaceholderRemainingCount();
                textToType.setText("please type a " + oldStory.getNextPlaceholder());
                textWordsLeft.setText("there are " + wordsLeft +" words left." );

//                textBlock.setText(oldStory.toString());

                // if all placeholders are filled then go to next activity
                // sending the new story to Story activity
                if (oldStory.isFilledIn()) {

                    newStory = oldStory.toString();

                    // creting new intent to go to.
                    Intent storyIntent = new Intent(FormActivity.this, StoryActivity.class);
                    storyIntent.putExtra("story", newStory);
                    startActivity(storyIntent);
                    finish();
                }
            }
        });

    }

}
