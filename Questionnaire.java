package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Questionnaire extends Activity implements View.OnClickListener{

    Context context;
    float rat[];
    Button doneButton;
    Button submitButton;
    RatingBar ratingBar4 = (RatingBar)findViewById(R.id.ratingBar4);
    RatingBar ratingBar5 = (RatingBar)findViewById(R.id.ratingBar5);
    RatingBar ratingBar6 = (RatingBar)findViewById(R.id.ratingBar6);
    RatingBar ratingBar7 = (RatingBar)findViewById(R.id.ratingBar7);
    RatingBar ratingBar8 = (RatingBar)findViewById(R.id.ratingBar8);
    RatingBar ratingBar9 = (RatingBar)findViewById(R.id.ratingBar9);
    RatingBar ratingBar10 = (RatingBar)findViewById(R.id.ratingBar10);
    RatingBar ratingBar11 = (RatingBar)findViewById(R.id.ratingBar11);
    RatingBar ratingBar12 = (RatingBar)findViewById(R.id.ratingBar12);
    RatingBar ratingBar13 = (RatingBar)findViewById(R.id.ratingBar13);
    RatingBar ratingBar14 = (RatingBar)findViewById(R.id.ratingBar14);
    RatingBar ratingBar15 = (RatingBar)findViewById(R.id.ratingBar15);
    RatingBar ratingBar16 = (RatingBar)findViewById(R.id.ratingBar16);
    RatingBar ratingBar17 = (RatingBar)findViewById(R.id.ratingBar17);
    RatingBar ratingBar18 = (RatingBar)findViewById(R.id.ratingBar18);
    RatingBar ratingBar19 = (RatingBar)findViewById(R.id.ratingBar19);
    RatingBar ratingBar20 = (RatingBar)findViewById(R.id.ratingBar20);
    RatingBar ratingBar3 = (RatingBar)findViewById(R.id.ratingBar3);
    RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
    RatingBar ratingBar2 = (RatingBar)findViewById(R.id.ratingBar2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);

        doneButton = (Button) findViewById(R.id.DoneButton);
        doneButton.setOnClickListener(this);
        submitButton = (Button) findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
        }
        if (id == R.id.History) {
            startActivity(new Intent(getApplicationContext(), History.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void doneButtonClick() {

        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
        //myIntent.putExtra("userInput", userInput.getText().toString());
        startActivity(myIntent);
    }

    private void submitButtonClick() {
        rat = new float[19];
        rat[1] = ratingBar2.getRating();
        rat[2] = ratingBar3.getRating();
        rat[3] = ratingBar4.getRating();
        rat[4] = ratingBar5.getRating();
        rat[5] = ratingBar6.getRating();
        rat[6] = ratingBar7.getRating();
        rat[7] = ratingBar8.getRating();
        rat[8]= ratingBar9.getRating();
        rat[9] = ratingBar10.getRating();
        rat[10] = ratingBar11.getRating();
        rat[11] = ratingBar12.getRating();
        rat[12] = ratingBar13.getRating();
        rat[13] = ratingBar14.getRating();
        rat[14] = ratingBar15.getRating();
        rat[15] = ratingBar16.getRating();
        rat[16] = ratingBar17.getRating();
        rat[17] = ratingBar18.getRating();
        rat[18] = ratingBar19.getRating();
        rat[19] = ratingBar20.getRating();
        rat[0] = ratingBar.getRating();
        for(int i = 0; i<rat.length; i++){
            ((TextView)findViewById(R.id.textView24)).setText(((TextView)findViewById(R.id.textView24)).getText() + String.valueOf(rat[i]));
        }



    }

    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.DoneButton:
                doneButtonClick();
                break;
            case R.id.SubmitButton:
                submitButtonClick();
                break;
        }

    }

}
