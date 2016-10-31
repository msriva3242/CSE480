package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.GLU;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Questionnaire extends Activity implements View.OnClickListener{
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
    TextView AI;
    CheckBox Eggs;
    CheckBox Milk;
    CheckBox Fish;
    CheckBox TreeNuts;
    CheckBox Peanuts;
    CheckBox Shellfish;
    CheckBox Soy;
    CheckBox Wheat;
    CheckBox Glutten;
    CheckBox Sesame;
    String[] tag;
    String[] Alergy;


    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);

        doneButton = (Button) findViewById(R.id.DoneButton);
        doneButton.setOnClickListener(this);
        submitButton = (Button) findViewById(R.id.SubmitButton);
        rat = new float[20];
        tag = new String[20];
        Alergy = new String[10];


        Salty = (TextView) findViewById(R.id.textView3);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        ratingBar2 = (RatingBar)findViewById(R.id.ratingBar2);
        rating = (TextView)findViewById(R.id.textView24);
        ratingBar4 = (RatingBar)findViewById(R.id.ratingBar4);
        ratingBar5 = (RatingBar)findViewById(R.id.ratingBar5);
        ratingBar6 = (RatingBar)findViewById(R.id.ratingBar6);
        ratingBar7 = (RatingBar)findViewById(R.id.ratingBar7);
        ratingBar8 = (RatingBar)findViewById(R.id.ratingBar8);
        ratingBar9 = (RatingBar)findViewById(R.id.ratingBar9);
        ratingBar10 = (RatingBar)findViewById(R.id.ratingBar10);
        ratingBar11 = (RatingBar)findViewById(R.id.ratingBar11);
        ratingBar12 = (RatingBar)findViewById(R.id.ratingBar12);
        ratingBar13 = (RatingBar)findViewById(R.id.ratingBar13);
        ratingBar14 = (RatingBar)findViewById(R.id.ratingBar14);
        ratingBar15 = (RatingBar)findViewById(R.id.ratingBar15);
        ratingBar16 = (RatingBar)findViewById(R.id.ratingBar16);
        ratingBar17 = (RatingBar)findViewById(R.id.ratingBar17);
        ratingBar18 = (RatingBar)findViewById(R.id.ratingBar18);
        ratingBar19 = (RatingBar)findViewById(R.id.ratingBar19);
        ratingBar20 = (RatingBar)findViewById(R.id.ratingBar20);
        ratingBar3 = (RatingBar)findViewById(R.id.ratingBar3);
        Eggs = (CheckBox) findViewById(R.id.checkBox5);
        Milk = (CheckBox) findViewById(R.id.checkBox6);
        Fish = (CheckBox) findViewById(R.id.checkBox7);
        TreeNuts = (CheckBox) findViewById(R.id.checkBox8);
        Peanuts = (CheckBox) findViewById(R.id.checkBox9);
        Shellfish = (CheckBox) findViewById(R.id.checkBox10);
        Soy = (CheckBox) findViewById(R.id.checkBox11);
        Wheat = (CheckBox) findViewById(R.id.checkBox12);
        Glutten = (CheckBox) findViewById(R.id.checkBox13);
        Sesame = (CheckBox) findViewById(R.id.checkBox14);



        submitButton.setOnClickListener(this);
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
        Deepfried=(TextView) findViewById(R.id.textView14);
        AI = (TextView) findViewById(R.id.textView23);
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
        Eggs.setVisibility(View.GONE);
        Milk.setVisibility(View.GONE);
        Soy.setVisibility(View.GONE);
        Sesame.setVisibility(View.GONE);
        Fish.setVisibility(View.GONE);
        TreeNuts.setVisibility(View.GONE);
        Peanuts.setVisibility(View.GONE);
        Shellfish.setVisibility(View.GONE);
        Glutten.setVisibility(View.GONE);
        Wheat.setVisibility(View.GONE);
        AI.setVisibility(View.GONE);
        for (int i = 0; i<Alergy.length; i++) {
            Alergy[i] = "Null";
        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        MenuItem mainMenu = menu.findItem(R.id.MainMenu);
        mainMenu.setVisible(false);
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
        if (id == R.id.Logout) {
            //clearUserPrefs();
            startActivity(new Intent(getApplicationContext(), LoginPage.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void clearUserPrefs() {
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
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
        tag[0] = "Salty";
        tag[1] = "Sweet";
        tag[2] = "Umami";
        tag[3] = "Bitter";
        tag[4] = "Sour";
        tag[5] = "Spicy";
        tag[6] = "Creamy";
        tag[7] = "Grilled";
        tag[8] = "Boiled";
        tag[9] = "Baked";
        tag[10] = "Steamed";
        tag[11] = "Deep-fried";
        tag[12] = "Crisp";
        tag[13] = "Crunchy";
        tag[14] = "leafy";
        tag[15] = "Tender";
        tag[16] = "Cheesy";
        tag[17] = "Nutritious";
        tag[18] = "Herbed";
        tag[19] = "Bold";
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        String tempusr = sp.getString("USER_NAME", "");



        for(int i = 0; i<rat.length; i++){
            // ((TextView)findViewById(R.id.textView24)).setText(((TextView)findViewById(R.id.textView24)).getText() + String.valueOf(rat[i]));
            rating.append(String.valueOf(rat[i])+ " ");
            //String Temprat = Float.toString(rat[i]);
            if (rat[i] != 0) {
                String Temprat = Float.toString(rat[i]);
                BackGround b = new BackGround();
                b.execute(tempusr, tag[i], Temprat);
            }

        }

        for (int i = 0; i<Alergy.length; i++) {
            if (Alergy[i] != "Null") {
                BackGround b = new BackGround();
                b.execute(tempusr, Alergy[i], "Null");
            }
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

    public void OnCheckBoxClick(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkBox:
                if (checked) {
                    ratingBar18.setVisibility(View.VISIBLE);
                    ratingBar19.setVisibility(View.VISIBLE);
                    ratingBar20.setVisibility(View.VISIBLE);
                    Nutritious.setVisibility(View.VISIBLE);
                    Herbed.setVisibility(View.VISIBLE);
                    Bold.setVisibility(View.VISIBLE);

                }

                else {
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
                }

                else {
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
                }

                else {
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
                }

                else {
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
            case R.id.checkBox15:
                if (checked) {
                    Eggs.setVisibility(View.VISIBLE);
                    Milk.setVisibility(View.VISIBLE);
                    Soy.setVisibility(View.VISIBLE);
                    Sesame.setVisibility(View.VISIBLE);
                    Fish.setVisibility(View.VISIBLE);
                    TreeNuts.setVisibility(View.VISIBLE);
                    Peanuts.setVisibility(View.VISIBLE);
                    Shellfish.setVisibility(View.VISIBLE);
                    Glutten.setVisibility(View.VISIBLE);
                    Wheat.setVisibility(View.VISIBLE);
                    AI.setVisibility(View.VISIBLE);
                }
                else {
                    Eggs.setVisibility(View.GONE);
                    Milk.setVisibility(View.GONE);
                    Soy.setVisibility(View.GONE);
                    Sesame.setVisibility(View.GONE);
                    Fish.setVisibility(View.GONE);
                    TreeNuts.setVisibility(View.GONE);
                    Peanuts.setVisibility(View.GONE);
                    Shellfish.setVisibility(View.GONE);
                    Glutten.setVisibility(View.GONE);
                    Wheat.setVisibility(View.GONE);
                    AI.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBox5:
                if (checked) {
                    Alergy[0] = "Eggs";
                }
                else {
                    Alergy[0] = "Null";
                }
                break;
            case R.id.checkBox6:
                if (checked) {
                    Alergy[1] = "Fish";
                }
                else {
                    Alergy[1] = "Null";
                }
                break;
            case R.id.checkBox7:
                if (checked) {
                    Alergy[2] = "Milk";
                }
                else {
                    Alergy[2] = "Null";
                }
                break;
            case R.id.checkBox8:
                if (checked) {
                    Alergy[3] = "Tree Nuts";
                }
                else {
                    Alergy[3] = "Null";
                }
                break;
            case R.id.checkBox9:
                if (checked) {
                    Alergy[4] = "Peanuts";
                }
                else {
                    Alergy[4] = "Null";
                }
                break;
            case R.id.checkBox10:
                if (checked) {
                    Alergy[5] = "Shellfish";
                }
                else {
                    Alergy[5] = "Null";
                }
                break;
            case R.id.checkBox11:
                if (checked) {
                    Alergy[6] = "Soy";
                }
                else {
                    Alergy[6] = "Null";
                }
                break;
            case R.id.checkBox12:
                if (checked) {
                    Alergy[7] = "Wheat";
                }
                else {
                    Alergy[7] = "Null";
                }
                break;
            case R.id.checkBox13:
                if (checked) {
                    Alergy[8] = "Glutten";
                }
                else {
                    Alergy[8] = "Null";
                }
                break;
            case R.id.checkBox14:
                if (checked) {
                    Alergy[9] = "Sesame";
                }
                else {
                    Alergy[9] = "Null";
                }
                break;

        }
    }
    class BackGround extends AsyncTask<String,String, String> {

        @Override
        protected String doInBackground(String ... params) {

            String user = params[0] ;
            String name = params[1];
            String rat = params[2];
            String data="";
            int tmp;
            URL url;
            String urlParams;

            try {
                if (rat == "Null") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/Alergy.php");
                    urlParams = "USER=" + user + "&NAME=" + name;
                }
                else {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/quest.php");
                    urlParams = "user=" + user + "&name=" + name + "&rat=" + rat;
                }


                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }
}
