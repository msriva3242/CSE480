package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

public class MenuList extends Activity implements View.OnClickListener{

    Context context;

    String NAME=null, RAT=null;

    Button button, button9;
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetector cd;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Google Places
    GooglePlaces googlePlaces;

    // Place Details
    PlaceDetails placeDetails;

    // Progress dialog
    ProgressDialog pDialog;
    ProgressBar progressbar;

    // KEY Strings
    public static String KEY_REFERENCE = "reference"; // id of the place

    TextView rar[] = new TextView[20];

    TextView num[] = new TextView[20];

    float man[] = new float[20];

    String names;

    Context ctx=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);

        Intent i = getIntent();

        //progressbar = (ProgressBar)findViewById(R.id.progressBar2);
        //progressbar.setVisibility(View.GONE);

        // Place reference id
        String reference = i.getStringExtra(KEY_REFERENCE);

        // Calling an Async Background thread
        new LoadSinglePlaceDetails().execute(reference);

        context = this.getApplicationContext();

        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(this);
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
        if (id == R.id.Logout) {
            startActivity(new Intent(getApplicationContext(), LoginPage.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Background Async Task to Load Google places
    class LoadSinglePlaceDetails extends AsyncTask<String, String, String> {

        //Before starting background thread Show Progress Dialog
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MenuList.this);
            pDialog.setMessage("Loading profile ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        //getting Profile JSON
        protected String doInBackground(String... args) {
            String reference = args[0];

            // creating Places class object
            googlePlaces = new GooglePlaces();

            // Check if user is connected to an internet connection
            try {
                placeDetails = googlePlaces.getPlaceDetails(reference);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        //After completing background task, dismiss the progress dialog
        protected void onPostExecute(String file_url) {
            //dismiss the dialog after getting all products
            pDialog.dismiss();
            //updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    //Updating parsed Places into LISTVIEW
                    if(placeDetails != null){
                        String status = placeDetails.status;

                        // check place details status
                        if(status.equals("OK")){
                            if (placeDetails.result != null) {
                                String name = placeDetails.result.name;
                                String address = placeDetails.result.formatted_address;
                                String phone = placeDetails.result.formatted_phone_number;
                                String latitude = Double.toString(placeDetails.result.geometry.location.lat);
                                String longitude = Double.toString(placeDetails.result.geometry.location.lng);

                                Log.d("Place ", name + address + phone + latitude + longitude);

                                // Displaying all the details in the view
                                TextView lbl_name = (TextView) findViewById(R.id.name);
                                TextView lbl_address = (TextView) findViewById(R.id.address);
                                TextView lbl_phone = (TextView) findViewById(R.id.phone);
                                TextView lbl_location = (TextView) findViewById(R.id.location);

                                // Check for null data from google
                                name = name == null ? "Not present" : name; // if name is null display as "Not present"
                                address = address == null ? "Not present" : address;
                                phone = phone == null ? "Not present" : phone;
                                latitude = latitude == null ? "Not present" : latitude;
                                longitude = longitude == null ? "Not present" : longitude;

                                lbl_name.setText(name);
                                lbl_address.setText(address);
                                lbl_phone.setText(Html.fromHtml("<b>Phone:</b> " + phone));
                                lbl_location.setText(Html.fromHtml("<b>Latitude:</b> " + latitude + ", <b>Longitude:</b> " + longitude));
                            }
                        }
                        else if(status.equals("ZERO_RESULTS")){
                            alert.showAlertDialog(MenuList.this, "Near Places",
                                    "Sorry, no places found.",
                                    false);
                        }
                        else if(status.equals("UNKNOWN_ERROR"))
                        {
                            alert.showAlertDialog(MenuList.this, "Places Error",
                                    "Sorry, an unknown error occurred.",
                                    false);
                        }
                        else if(status.equals("OVER_QUERY_LIMIT"))
                        {
                            alert.showAlertDialog(MenuList.this, "Places Error",
                                    "Sorry, query limit to google places is reached",
                                    false);
                        }
                        else if(status.equals("REQUEST_DENIED"))
                        {
                            alert.showAlertDialog(MenuList.this, "Places Error",
                                    "Sorry, an error occurred. Request is denied",
                                    false);
                        }
                        else if(status.equals("INVALID_REQUEST"))
                        {
                            alert.showAlertDialog(MenuList.this, "Places Error",
                                    "Sorry, an error occurred. Invalid Request",
                                    false);
                        }
                        else
                        {
                            alert.showAlertDialog(MenuList.this, "Places Error",
                                    "Sorry, an error occurred.",
                                    false);
                        }
                    }else{
                        alert.showAlertDialog(MenuList.this, "Places Error",
                                "Sorry, an error occurred.",
                                false);
                    }
                }
            });
        }

    }
    private void button1Click() {
        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //progressbar.setVisibility(View.VISIBLE);
                TextView lbl_name = (TextView) findViewById(R.id.name);
                CharSequence name = lbl_name.getText();
                names = name.toString();

                BackGround me = new BackGround();
                me.execute(names);
                //progressbar.setVisibility(View.GONE);
            }
        };


        h.sendEmptyMessageDelayed(0, 1500);
        //progressbar.setVisibility(View.GONE);


    }
    private void button2Click() {

        startActivity(new Intent(getApplicationContext(), Recommendations.class));

    }

    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.button9:
                button1Click();
                break;
        }

    }
    class BackGround extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {
            String name = params[0];

            String data="";
            int tmp;

            try {
                URL url = new URL("http://www.secs.oakland.edu/~djrasmus/480/menus3.php");
                String urlParams = "name="+name;


                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                try {
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            String err=null;
            rar[0] = (TextView) findViewById(R.id.textView38);
            rar[1] = (TextView) findViewById(R.id.textView33);
            rar[2] = (TextView) findViewById(R.id.textView35);
            rar[3] = (TextView) findViewById(R.id.textView37);
            rar[4] = (TextView) findViewById(R.id.textView39);
            rar[5] = (TextView) findViewById(R.id.textView41);
            rar[6] = (TextView) findViewById(R.id.textView43);
            rar[7] = (TextView) findViewById(R.id.textView45);
            rar[8] = (TextView) findViewById(R.id.textView47);
            rar[9] = (TextView) findViewById(R.id.textView49);
            rar[10] = (TextView) findViewById(R.id.textView51);
            rar[11] = (TextView) findViewById(R.id.textView53);
            rar[12] = (TextView) findViewById(R.id.textView55);
            rar[13] = (TextView) findViewById(R.id.textView57);
            rar[14] = (TextView) findViewById(R.id.textView59);
            rar[15] = (TextView) findViewById(R.id.textView61);
            rar[16] = (TextView) findViewById(R.id.textView63);
            rar[17] = (TextView) findViewById(R.id.textView65);
            rar[18] = (TextView) findViewById(R.id.textView67);
            rar[19] = (TextView) findViewById(R.id.textView69);
            num[0] = (TextView) findViewById(R.id.textView28);
            num[1] = (TextView) findViewById(R.id.textView34);
            num[2] = (TextView) findViewById(R.id.textView36);
            num[3] = (TextView) findViewById(R.id.textView38);
            num[4] = (TextView) findViewById(R.id.textView40);
            num[5] = (TextView) findViewById(R.id.textView42);
            num[6] = (TextView) findViewById(R.id.textView44);
            num[7] = (TextView) findViewById(R.id.textView46);
            num[8] = (TextView) findViewById(R.id.textView48);
            num[9] = (TextView) findViewById(R.id.textView50);
            num[10] = (TextView) findViewById(R.id.textView52);
            num[11] = (TextView) findViewById(R.id.textView54);
            num[12] = (TextView) findViewById(R.id.textView56);
            num[13] = (TextView) findViewById(R.id.textView58);
            num[14] = (TextView) findViewById(R.id.textView60);
            num[15] = (TextView) findViewById(R.id.textView62);
            num[16] = (TextView) findViewById(R.id.textView64);
            num[17] = (TextView) findViewById(R.id.textView66);
            num[18] = (TextView) findViewById(R.id.textView68);
            num[19] = (TextView) findViewById(R.id.textView70);



            try {
                JSONObject root = new JSONObject(s);
                JSONArray menu = root.getJSONArray("results");
                for (int i =0; i<rar.length; i++) {
                    JSONObject ff = menu.getJSONObject(i);
                    NAME = ff.getString("NAME");
                    rar[i].setText(NAME);
                    /*RAT = ff.getString("RATING");
                    num[i].setText(RAT);*/
                }



            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }

            for (int i = 0; i<num.length ; i++) {
                Random nums = new Random();
                float temp = nums.nextFloat();
                int temps = nums.nextInt(2);
                float tempss = temp + temps +3;
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                //System.out.println(df.format(decimalNumber));

                num[i].setText(df.format(tempss));
            }

        }

    }
}




