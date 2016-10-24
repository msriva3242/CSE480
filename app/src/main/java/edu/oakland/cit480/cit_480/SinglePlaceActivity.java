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
import android.widget.TextView;

public class SinglePlaceActivity extends Activity implements View.OnClickListener{

    Context context;

    Button button, button8;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_place);

        Intent i = getIntent();

        progressbar = (ProgressBar)findViewById(R.id.progressBar2);
        progressbar.setVisibility(View.GONE);

        // Place reference id
        String reference = i.getStringExtra(KEY_REFERENCE);

        // Calling an Async Background thread
        new LoadSinglePlaceDetails().execute(reference);

        context = this.getApplicationContext();

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(this);
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
            pDialog = new ProgressDialog(SinglePlaceActivity.this);
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
                            alert.showAlertDialog(SinglePlaceActivity.this, "Near Places",
                                    "Sorry, no places found.",
                                    false);
                        }
                        else if(status.equals("UNKNOWN_ERROR"))
                        {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry, an unknown error occurred.",
                                    false);
                        }
                        else if(status.equals("OVER_QUERY_LIMIT"))
                        {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry, query limit to google places is reached",
                                    false);
                        }
                        else if(status.equals("REQUEST_DENIED"))
                        {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry, an error occurred. Request is denied",
                                    false);
                        }
                        else if(status.equals("INVALID_REQUEST"))
                        {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry, an error occurred. Invalid Request",
                                    false);
                        }
                        else
                        {
                            alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
                                    "Sorry, an error occurred.",
                                    false);
                        }
                    }else{
                        alert.showAlertDialog(SinglePlaceActivity.this, "Places Error",
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
                progressbar.setVisibility(View.VISIBLE);

                Intent i = new Intent().setClass(context, MenuList.class);
                startActivity(i);
            }
        };


        h.sendEmptyMessageDelayed(0, 1500);
        progressbar.setVisibility(View.GONE);


    }
    private void button2Click() {

        startActivity(new Intent(getApplicationContext(), Recommendations.class));

    }

    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.button:
                button1Click();
                break;
            case R.id.button8:
                button2Click();
                break;
        }

    }
}
