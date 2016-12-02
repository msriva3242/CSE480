package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Questionnaire extends Activity implements View.OnClickListener {
    float rat[];
    Button submitButton;
    TextView rating;
    RatingBar[] ratingBar = new RatingBar[20];
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
    TextView Leafy;
    TextView Tender;
    TextView Cheesy;
    TextView Nutritious;
    TextView Herbed;
    TextView Bold;
    TextView AI;
    CheckBox[] alCheckBox = new CheckBox[10];
    String[] tag;
    String[] Alergy;
    String tempusr = "";
    String s;
    Context ctx = this;
    Boolean fetch, fetchal = false;
    String[] TAGNAME = new String[200];
    String[] ALLERGENS = new String[20];
    String[] RATING = new String[200];
    String USERID;
    Toast toast1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);
        submitButton = (Button) findViewById(R.id.SubmitButton);
        rat = new float[20];
        tag = new String[20];
        Alergy = new String[10];
        Salty = (TextView) findViewById(R.id.textView3);
        ratingBar[0] = (RatingBar) findViewById(R.id.ratingBar1);
        ratingBar[1] = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar[2] = (RatingBar) findViewById(R.id.ratingBar3);
        ratingBar[3] = (RatingBar) findViewById(R.id.ratingBar4);
        ratingBar[4] = (RatingBar) findViewById(R.id.ratingBar5);
        ratingBar[5] = (RatingBar) findViewById(R.id.ratingBar6);
        ratingBar[6] = (RatingBar) findViewById(R.id.ratingBar7);
        ratingBar[7] = (RatingBar) findViewById(R.id.ratingBar8);
        ratingBar[8] = (RatingBar) findViewById(R.id.ratingBar9);
        ratingBar[9] = (RatingBar) findViewById(R.id.ratingBar10);
        ratingBar[10] = (RatingBar) findViewById(R.id.ratingBar11);
        ratingBar[11] = (RatingBar) findViewById(R.id.ratingBar12);
        ratingBar[12] = (RatingBar) findViewById(R.id.ratingBar13);
        ratingBar[13] = (RatingBar) findViewById(R.id.ratingBar14);
        ratingBar[14] = (RatingBar) findViewById(R.id.ratingBar15);
        ratingBar[15] = (RatingBar) findViewById(R.id.ratingBar16);
        ratingBar[16] = (RatingBar) findViewById(R.id.ratingBar17);
        ratingBar[17] = (RatingBar) findViewById(R.id.ratingBar18);
        ratingBar[18] = (RatingBar) findViewById(R.id.ratingBar19);
        ratingBar[19] = (RatingBar) findViewById(R.id.ratingBar20);
        alCheckBox[0] = (CheckBox) findViewById(R.id.checkBox5);
        alCheckBox[1] = (CheckBox) findViewById(R.id.checkBox6);
        alCheckBox[2] = (CheckBox) findViewById(R.id.checkBox7);
        alCheckBox[3] = (CheckBox) findViewById(R.id.checkBox8);
        alCheckBox[4] = (CheckBox) findViewById(R.id.checkBox9);
        alCheckBox[5] = (CheckBox) findViewById(R.id.checkBox10);
        alCheckBox[6] = (CheckBox) findViewById(R.id.checkBox11);
        alCheckBox[7] = (CheckBox) findViewById(R.id.checkBox12);
        alCheckBox[8] = (CheckBox) findViewById(R.id.checkBox13);
        alCheckBox[9] = (CheckBox) findViewById(R.id.checkBox14);
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
        Leafy = (TextView) findViewById(R.id.textView17);
        Tender = (TextView) findViewById(R.id.textView18);
        Cheesy = (TextView) findViewById(R.id.textView19);
        Bold = (TextView) findViewById(R.id.textView22);
        Grilled = (TextView) findViewById(R.id.textView10);
        Boiled = (TextView) findViewById(R.id.textView11);
        Baked = (TextView) findViewById(R.id.textView12);
        Steamed = (TextView) findViewById(R.id.textView13);
        Deepfried = (TextView) findViewById(R.id.textView14);
        AI = (TextView) findViewById(R.id.textView23);
        Nutritious.setVisibility(View.GONE);
        Herbed.setVisibility(View.GONE);
        Bold.setVisibility(View.GONE);
        Crisp.setVisibility(View.GONE);
        Crunchy.setVisibility(View.GONE);
        Leafy.setVisibility(View.GONE);
        Tender.setVisibility(View.GONE);
        Cheesy.setVisibility(View.GONE);
        Grilled.setVisibility(View.GONE);
        Boiled.setVisibility(View.GONE);
        Baked.setVisibility(View.GONE);
        Steamed.setVisibility(View.GONE);
        Deepfried.setVisibility(View.GONE);
        Sour.setVisibility(View.GONE);
        Creamy.setVisibility(View.GONE);
        Spicy.setVisibility(View.GONE);
        Sweet.setVisibility(View.GONE);
        Umami.setVisibility(View.GONE);
        Bitter.setVisibility(View.GONE);
        Salty.setVisibility(View.GONE);
        for (CheckBox anAlCheckBox : alCheckBox) {
            anAlCheckBox.setVisibility(View.GONE);
        }
        AI.setVisibility(View.GONE);
        for (RatingBar aRatingBar : ratingBar) {
            aRatingBar.setVisibility(View.GONE);
        }
        for (int i = 0; i < Alergy.length; i++) {
            Alergy[i] = "Null";
        }
        tag[0] = "Salty";
        tag[1] = "Sweet";
        tag[2] = "Savory";
        tag[3] = "Bitter";
        tag[4] = "Sour";
        tag[5] = "Spicy";
        tag[6] = "Creamy";
        tag[7] = "Grilled";
        tag[8] = "Boiled";
        tag[9] = "Baked";
        tag[10] = "Steamed";
        tag[11] = "Fried";
        tag[12] = "Crispy";
        tag[13] = "Crunchy";
        tag[14] = "Leafy";
        tag[15] = "Tender";
        tag[16] = "Cheesy";
        tag[17] = "Juicy";
        tag[18] = "Chewy";
        tag[19] = "Starchy";
        Alergy[0] = "Eggs";
        Alergy[1] = "Fish";
        Alergy[2] = "Milk";
        Alergy[3] = "Tree Nuts";
        Alergy[4] = "Peanuts";
        Alergy[5] = "Shellfish";
        Alergy[6] = "Soy";
        Alergy[7] = "Wheat";
        Alergy[8] = "Gluten";
        Alergy[9] = "Sesame";
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        setTempusr(sp.getString("USER_ID", ""));
        System.out.println(getTempusr() + "eeee");
        getRatings();
    }

    public String getTempusr() {
        return tempusr;
    }

    public void setTempusr(String tempusr) {
        this.tempusr = tempusr;
    }

    private void getRatings() {
        BackGround b = new BackGround();
        b.execute(getTempusr(), "", "", "fetch");
    }

    private void getAllergens() {
        BackGround b = new BackGround();
        b.execute(getTempusr(), "", "", "fetchal");
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
        if (id == android.R.id.home) {
            finish();
        }
        if (id == R.id.History) {
            startActivity(new Intent(getApplicationContext(), History.class));
            return true;
        }
        if (id == R.id.MainMenu) {
            startActivity(new Intent(getApplicationContext(), MainMenu.class));
            return true;
        }
        if (id == R.id.Logout) {
            clearUserPrefs();
            startActivity(new Intent(getApplicationContext(), LoginPage.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doneButtonClick() {
        toast1 = Toast.makeText(ctx, "Changes Saved Successfully!", Toast.LENGTH_SHORT);
        toast1.show();
        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(myIntent);
    }

    private void submitButtonClick() {
        for (int i = 0; i < ratingBar.length; i++) {
            rat[i] = ratingBar[i].getRating();
        }
        for (int i = 0; i < rat.length; i++) {
            if (rat[i] != 0) {
                String Temprat = Float.toString(rat[i] / 5);
                BackGround b = new BackGround();
                b.execute(getTempusr(), tag[i], Temprat, "rat");
            }
        }
        for (int i = 0; i < alCheckBox.length; i++) {
            if (alCheckBox[i].isChecked()) {
                BackGround b = new BackGround();
                b.execute(getTempusr(), Alergy[i], "Null", "al");
            }
            else {
                BackGround b = new BackGround();
                b.execute(getTempusr(), Alergy[i], "Null", "dal");
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SubmitButton:
                submitButtonClick();
                doneButtonClick();
                break;
        }
    }

    public void OnCheckBoxClick(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBox:
                if (checked) {
                    for (int i = 17; i < 20; i++) {
                        ratingBar[i].setVisibility(View.VISIBLE);
                    }
                    Nutritious.setVisibility(View.VISIBLE);
                    Herbed.setVisibility(View.VISIBLE);
                    Bold.setVisibility(View.VISIBLE);

                } else {
                    for (int i = 17; i < 20; i++) {
                        ratingBar[i].setVisibility(View.GONE);
                    }
                    Nutritious.setVisibility(View.GONE);
                    Herbed.setVisibility(View.GONE);
                    Bold.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBox2:
                if (checked) {
                    for (int i = 12; i < 17; i++) {
                        ratingBar[i].setVisibility(View.VISIBLE);
                    }
                    Crisp.setVisibility(View.VISIBLE);
                    Crunchy.setVisibility(View.VISIBLE);
                    Leafy.setVisibility(View.VISIBLE);
                    Tender.setVisibility(View.VISIBLE);
                    Cheesy.setVisibility(View.VISIBLE);
                } else {
                    for (int i = 12; i < 17; i++) {
                        ratingBar[i].setVisibility(View.GONE);
                    }
                    Crisp.setVisibility(View.GONE);
                    Crunchy.setVisibility(View.GONE);
                    Leafy.setVisibility(View.GONE);
                    Tender.setVisibility(View.GONE);
                    Cheesy.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBox3:
                if (checked) {
                    for (int i = 7; i < 12; i++) {
                        ratingBar[i].setVisibility(View.VISIBLE);
                    }
                    Grilled.setVisibility(View.VISIBLE);
                    Boiled.setVisibility(View.VISIBLE);
                    Baked.setVisibility(View.VISIBLE);
                    Steamed.setVisibility(View.VISIBLE);
                    Deepfried.setVisibility(View.VISIBLE);
                } else {
                    for (int i = 7; i < 12; i++) {
                        ratingBar[i].setVisibility(View.GONE);
                    }
                    Grilled.setVisibility(View.GONE);
                    Boiled.setVisibility(View.GONE);
                    Baked.setVisibility(View.GONE);
                    Steamed.setVisibility(View.GONE);
                    Deepfried.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBox4:
                if (checked) {
                    for (int i = 0; i < 7; i++) {
                        ratingBar[i].setVisibility(View.VISIBLE);
                    }
                    Sour.setVisibility(View.VISIBLE);
                    Creamy.setVisibility(View.VISIBLE);
                    Spicy.setVisibility(View.VISIBLE);
                    Sweet.setVisibility(View.VISIBLE);
                    Umami.setVisibility(View.VISIBLE);
                    Bitter.setVisibility(View.VISIBLE);
                    Salty.setVisibility(View.VISIBLE);
                } else {
                    for (int i = 0; i < 7; i++) {
                        ratingBar[i].setVisibility(View.GONE);
                    }
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
                    for (CheckBox anAlCheckBox : alCheckBox) {
                        anAlCheckBox.setVisibility(View.VISIBLE);
                    }
                    AI.setVisibility(View.VISIBLE);
                } else {
                    for (CheckBox anAlCheckBox : alCheckBox) {
                        anAlCheckBox.setVisibility(View.GONE);
                    }
                    AI.setVisibility(View.GONE);
                }
                break;
        }
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String user = params[0];
            String name = params[1];
            String rat = params[2];
            String op = params[3];
            String data = "";
            int tmp;
            URL url = null;
            String urlParams = "user=" + user + "&name=" + name + "&rat=" + rat;
            try {
                if (op == "al") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/Alergy.php");
                    urlParams = "USER=" + user + "&NAME=" + name;
                    setS("Saving data");
                }
                if (op == "rat") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/quest.php");
                    urlParams = "user=" + user + "&name=" + name + "&rat=" + rat;
                    setS("Saving data");
                }
                if (op == "fetch") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/getRatings.php");
                    urlParams = "USERID=" + user;
                    setS("Fetching Info...");
                    fetch = true;
                }
                if (op == "fetchal") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/getAllergens.php");
                    urlParams = "USERID=" + user;
                    setS("Fetching Info...");
                    fetchal = true;
                }
                if (op == "dal") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/DAlergy.php");
                    urlParams = "USER=" + user + "&NAME=" + name;
                    System.out.println(name);
                    System.out.println(user);
                    //setS("Saving data");
                }

                else {
                    //Here's a smiley face instead of some actual code :-)
                }

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {

            if (fetch) {
                try {
                    JSONObject root = new JSONObject(s);
                    JSONArray menu = root.getJSONArray("results");
                    for (int i = 0; i < tag.length; i++) {
                        for (int j = 0; j < menu.length(); j++) {
                            JSONObject ff = menu.getJSONObject(j);
                            TAGNAME[j] = ff.getString("TAG NAME");
                            USERID = ff.getString("USER ID");
                            RATING[j] = ff.getString("RATING");

                            if (TAGNAME[j].equals(tag[i])) {
                                ratingBar[i].setRating(5 * Float.parseFloat((RATING[j])));
                            }
                        }
                    }
                    fetch = false;
                    getAllergens();
                } catch (JSONException e) {
                    e.printStackTrace();
                    String err = "Exception: " + e.getMessage();
                    System.out.print(err);
                }
            }
            if (fetchal) {
                try {
                    JSONObject root = new JSONObject(s);
                    JSONArray menu = root.getJSONArray("results");
                    for (int i = 0; i < Alergy.length; i++) {
                        for (int j = 0; j < menu.length(); j++) {
                            JSONObject ff = menu.getJSONObject(j);
                            ALLERGENS[j] = ff.getString("NAME");
                            USERID = ff.getString("USER ID");
                            if (ALLERGENS[j].equals(Alergy[i])) {
                                alCheckBox[i].setChecked(true);
                            }
                        }
                    }
                    fetchal = false;
                } catch (JSONException e) {
                    e.printStackTrace();
                    String err = "Exception: " + e.getMessage();
                    System.out.print(err);
                }
            }
        }
    }

    public void clearUserPrefs() {
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}




