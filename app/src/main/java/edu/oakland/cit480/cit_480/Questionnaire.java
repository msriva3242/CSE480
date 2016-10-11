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
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;


public class Questionnaire extends Activity implements View.OnClickListener {

    Context context;
    float rat[];
    Button doneButton;
    Button submitButton;
    RatingBar ratingBar;
    RatingBar ratingBar2;
    TextView rating;
    RatingBar ratingBar4;
    RatingBar ratingBar5;
    RatingBar ratingBar6;
    RatingBar ratingBar7;
    RatingBar ratingBar8;
    RatingBar ratingBar9;
    RatingBar ratingBar10;
    RatingBar ratingBar11;
    RatingBar ratingBar12;
    RatingBar ratingBar13;
    RatingBar ratingBar14;
    RatingBar ratingBar15;
    RatingBar ratingBar16;
    RatingBar ratingBar17;
    RatingBar ratingBar18;
    RatingBar ratingBar19;
    RatingBar ratingBar20;
    RatingBar ratingBar3;
    //THIS IS NEW SHIT
    TextView Salty;
    TextView Grilled;
    TextView Boiled;
    TextView Baked;
    TextView Steamed;
    TextView Deepfried;
    TextView Sweet;
    TextView Umami;
    TextView Bitter;
    TextView Sour;
    TextView Spicy;
    TextView Creamy;
    TextView Crisp;
    TextView Crunchy;
    TextView leafy;
    TextView Tender;
    TextView Cheesy;
    TextView Nutritious;
    TextView Herbed;
    TextView Bold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);

        doneButton = (Button) findViewById(R.id.DoneButton);
        doneButton.setOnClickListener(this);
        submitButton = (Button) findViewById(R.id.SubmitButton);
        //I MOVED THIS STUFF UP HERE
        rat = new float[20];
        Salty = (TextView) findViewById(R.id.textView3);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        rating = (TextView) findViewById(R.id.textView24);
        ratingBar4 = (RatingBar) findViewById(R.id.ratingBar4);
        ratingBar5 = (RatingBar) findViewById(R.id.ratingBar5);
        ratingBar6 = (RatingBar) findViewById(R.id.ratingBar6);
        ratingBar7 = (RatingBar) findViewById(R.id.ratingBar7);
        ratingBar8 = (RatingBar) findViewById(R.id.ratingBar8);
        ratingBar9 = (RatingBar) findViewById(R.id.ratingBar9);
        ratingBar10 = (RatingBar) findViewById(R.id.ratingBar10);
        ratingBar11 = (RatingBar) findViewById(R.id.ratingBar11);
        ratingBar12 = (RatingBar) findViewById(R.id.ratingBar12);
        ratingBar13 = (RatingBar) findViewById(R.id.ratingBar13);
        ratingBar14 = (RatingBar) findViewById(R.id.ratingBar14);
        ratingBar15 = (RatingBar) findViewById(R.id.ratingBar15);
        ratingBar16 = (RatingBar) findViewById(R.id.ratingBar16);
        ratingBar17 = (RatingBar) findViewById(R.id.ratingBar17);
        ratingBar18 = (RatingBar) findViewById(R.id.ratingBar18);
        ratingBar19 = (RatingBar) findViewById(R.id.ratingBar19);
        ratingBar20 = (RatingBar) findViewById(R.id.ratingBar20);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);


        submitButton.setOnClickListener(this);
        //NEW SHIT
        Nutritious = (TextView) findViewById(R.id.textView20);
        Herbed = (TextView) findViewById(R.id.textView21);
        Sweet = (TextView) findViewById(R.id.textView4);
        Umami = (TextView) findViewById(R.id.textView5);
        Bitter = (TextView) findViewById(R.id.textView6);
        Sour = (TextView) findViewById(R.id.textView7);
        Spicy = (TextView) findViewById(R.id.textView8);
        Creamy = (TextView) findViewById(R.id.textView9);
        Crisp = (TextView) findViewById(R.id.textView15);
        Crunchy = (TextView) findViewById(R.id.textView16);
        leafy = (TextView) findViewById(R.id.textView17);
        Tender = (TextView) findViewById(R.id.textView18);
        Cheesy = (TextView) findViewById(R.id.textView19);
        Bold = (TextView) findViewById(R.id.textView22);
        Grilled = (TextView) findViewById(R.id.textView10);
        Boiled = (TextView) findViewById(R.id.textView11);
        Baked = (TextView) findViewById(R.id.textView12);
        Steamed = (TextView) findViewById(R.id.textView13);
        Deepfried = (TextView) findViewById(R.id.textView14);
        ratingBar18.setVisibility(View.GONE);
        ratingBar19.setVisibility(View.GONE);
        ratingBar20.setVisibility(View.GONE);
        Nutritious.setVisibility(View.GONE);
        Herbed.setVisibility(View.GONE);
        Bold.setVisibility(View.GONE);
        ratingBar13.setVisibility(View.GONE);
        ratingBar14.setVisibility(View.GONE);
        ratingBar15.setVisibility(View.GONE);
        ratingBar16.setVisibility(View.GONE);
        ratingBar17.setVisibility(View.GONE);
        Crisp.setVisibility(View.GONE);
        Crunchy.setVisibility(View.GONE);
        leafy.setVisibility(View.GONE);
        Tender.setVisibility(View.GONE);
        Cheesy.setVisibility(View.GONE);
        ratingBar8.setVisibility(View.GONE);
        ratingBar9.setVisibility(View.GONE);
        ratingBar10.setVisibility(View.GONE);
        ratingBar11.setVisibility(View.GONE);
        ratingBar12.setVisibility(View.GONE);
        Grilled.setVisibility(View.GONE);
        Boiled.setVisibility(View.GONE);
        Baked.setVisibility(View.GONE);
        Steamed.setVisibility(View.GONE);
        Deepfried.setVisibility(View.GONE);
        ratingBar.setVisibility(View.GONE);
        ratingBar2.setVisibility(View.GONE);
        ratingBar3.setVisibility(View.GONE);
        ratingBar4.setVisibility(View.GONE);
        ratingBar5.setVisibility(View.GONE);
        ratingBar6.setVisibility(View.GONE);
        ratingBar7.setVisibility(View.GONE);
        Sour.setVisibility(View.GONE);
        Creamy.setVisibility(View.GONE);
        Spicy.setVisibility(View.GONE);
        Sweet.setVisibility(View.GONE);
        Umami.setVisibility(View.GONE);
        Bitter.setVisibility(View.GONE);
        Salty.setVisibility(View.GONE);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
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
        rating.setText("");
        rat[1] = ratingBar2.getRating();
        rat[2] = ratingBar3.getRating();
        rat[3] = ratingBar4.getRating();
        rat[4] = ratingBar5.getRating();
        rat[5] = ratingBar6.getRating();
        rat[6] = ratingBar7.getRating();
        rat[7] = ratingBar8.getRating();
        rat[8] = ratingBar9.getRating();
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

        for (int i = 0; i < rat.length; i++) {
            // ((TextView)findViewById(R.id.textView24)).setText(((TextView)findViewById(R.id.textView24)).getText() + String.valueOf(rat[i]));
            rating.append(String.valueOf(rat[i]) + " ");
        }


    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.DoneButton:
                doneButtonClick();
                break;
            case R.id.SubmitButton:
                submitButtonClick();
                break;
        }

    }

    //NEW SHIT
    public void OnCheckBoxClick(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.checkBox:
                if (checked) {
                    ratingBar18.setVisibility(View.VISIBLE);
                    ratingBar19.setVisibility(View.VISIBLE);
                    ratingBar20.setVisibility(View.VISIBLE);
                    Nutritious.setVisibility(View.VISIBLE);
                    Herbed.setVisibility(View.VISIBLE);
                    Bold.setVisibility(View.VISIBLE);

                } else {
                    ratingBar18.setVisibility(View.GONE);
                    ratingBar19.setVisibility(View.GONE);
                    ratingBar20.setVisibility(View.GONE);
                    Nutritious.setVisibility(View.GONE);
                    Herbed.setVisibility(View.GONE);
                    Bold.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBox2:
                if (checked) {
                    ratingBar13.setVisibility(View.VISIBLE);
                    ratingBar14.setVisibility(View.VISIBLE);
                    ratingBar15.setVisibility(View.VISIBLE);
                    ratingBar16.setVisibility(View.VISIBLE);
                    ratingBar17.setVisibility(View.VISIBLE);
                    Crisp.setVisibility(View.VISIBLE);
                    Crunchy.setVisibility(View.VISIBLE);
                    leafy.setVisibility(View.VISIBLE);
                    Tender.setVisibility(View.VISIBLE);
                    Cheesy.setVisibility(View.VISIBLE);
                } else {
                    ratingBar13.setVisibility(View.GONE);
                    ratingBar14.setVisibility(View.GONE);
                    ratingBar15.setVisibility(View.GONE);
                    ratingBar16.setVisibility(View.GONE);
                    ratingBar17.setVisibility(View.GONE);
                    Crisp.setVisibility(View.GONE);
                    Crunchy.setVisibility(View.GONE);
                    leafy.setVisibility(View.GONE);
                    Tender.setVisibility(View.GONE);
                    Cheesy.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBox3:
                if (checked) {
                    ratingBar8.setVisibility(View.VISIBLE);
                    ratingBar9.setVisibility(View.VISIBLE);
                    ratingBar10.setVisibility(View.VISIBLE);
                    ratingBar11.setVisibility(View.VISIBLE);
                    ratingBar12.setVisibility(View.VISIBLE);
                    Grilled.setVisibility(View.VISIBLE);
                    Boiled.setVisibility(View.VISIBLE);
                    Baked.setVisibility(View.VISIBLE);
                    Steamed.setVisibility(View.VISIBLE);
                    Deepfried.setVisibility(View.VISIBLE);
                } else {
                    ratingBar8.setVisibility(View.GONE);
                    ratingBar9.setVisibility(View.GONE);
                    ratingBar10.setVisibility(View.GONE);
                    ratingBar11.setVisibility(View.GONE);
                    ratingBar12.setVisibility(View.GONE);
                    Grilled.setVisibility(View.GONE);
                    Boiled.setVisibility(View.GONE);
                    Baked.setVisibility(View.GONE);
                    Steamed.setVisibility(View.GONE);
                    Deepfried.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBox4:
                if (checked) {
                    ratingBar.setVisibility(View.VISIBLE);
                    ratingBar2.setVisibility(View.VISIBLE);
                    ratingBar3.setVisibility(View.VISIBLE);
                    ratingBar4.setVisibility(View.VISIBLE);
                    ratingBar5.setVisibility(View.VISIBLE);
                    ratingBar6.setVisibility(View.VISIBLE);
                    ratingBar7.setVisibility(View.VISIBLE);
                    Sour.setVisibility(View.VISIBLE);
                    Creamy.setVisibility(View.VISIBLE);
                    Spicy.setVisibility(View.VISIBLE);
                    Sweet.setVisibility(View.VISIBLE);
                    Umami.setVisibility(View.VISIBLE);
                    Bitter.setVisibility(View.VISIBLE);
                    Salty.setVisibility(View.VISIBLE);
                } else {
                    ratingBar.setVisibility(View.GONE);
                    ratingBar2.setVisibility(View.GONE);
                    ratingBar3.setVisibility(View.GONE);
                    ratingBar4.setVisibility(View.GONE);
                    ratingBar5.setVisibility(View.GONE);
                    ratingBar6.setVisibility(View.GONE);
                    ratingBar7.setVisibility(View.GONE);
                    Sour.setVisibility(View.GONE);
                    Creamy.setVisibility(View.GONE);
                    Spicy.setVisibility(View.GONE);
                    Sweet.setVisibility(View.GONE);
                    Umami.setVisibility(View.GONE);
                    Bitter.setVisibility(View.GONE);
                    Salty.setVisibility(View.GONE);
                }
                break;

        }
    }
}