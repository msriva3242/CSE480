package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.lang.Math.*;

public class SinglePlaceActivity extends Activity implements View.OnClickListener{

    String s;

    ArrayList<String> arr = new ArrayList<String>();

    Context context;

    String NAME=null, RAT=null;

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

    String rar[] = new String[30];

    float num[] = new float[30];

    DecimalFormat test[] = new DecimalFormat[30];

    float man[] = new float[20];

    String names;

    Context ctx=this;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_place);

        Intent i = getIntent();

        //progressbar = (ProgressBar)findViewById(R.id.progressBar2);
        //progressbar.setVisibility(View.GONE);

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
        if (id == R.id.MainMenu) {
            startActivity(new Intent(SinglePlaceActivity.this, MainMenu.class));
            return true;
        }
        if (id == R.id.Logout) {
            clearUserPrefs();
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
                                //TextView lbl_location = (TextView) findViewById(R.id.location);

                                // Check for null data from google
                                name = name == null ? "Not present" : name; // if name is null display as "Not present"
                                address = address == null ? "Not present" : address;
                                phone = phone == null ? "Not present" : phone;
                                latitude = latitude == null ? "Not present" : latitude;
                                longitude = longitude == null ? "Not present" : longitude;

                                lbl_name.setText(name);
                                lbl_address.setText(address);
                                lbl_phone.setText(Html.fromHtml("<b>Phone:</b> " + phone));
                                //lbl_location.setText(Html.fromHtml("<b>Latitude:</b> " + latitude + ", <b>Longitude:</b> " + longitude));
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
        //progressbar.setVisibility(View.VISIBLE);
        TextView lbl_name = (TextView) findViewById(R.id.name);
        CharSequence name = lbl_name.getText();
        names = name.toString();
        String sig = "a";

        BackGround me = new BackGround();
        me.execute(names, sig);

        //progressbar.setVisibility(View.GONE);
    }
    public void clearUserPrefs() {
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
    private void button2Click() {
        TextView lbl_name = (TextView) findViewById(R.id.name);
        CharSequence name = lbl_name.getText();
        names = name.toString();
        String sig = "b";

        BackGround me = new BackGround();
        me.execute(names, sig);

        /*try {
            JSONObject root = new JSONObject(getMenu(names));
            JSONArray menu = root.getJSONArray("results");


            for (int i =0; i<rar.length; i++) {
                JSONObject ff = menu.getJSONObject(i);
                NAME = ff.getString("NAME");
                rar[i] = NAME;

            }

        } catch (JSONException e) {
            e.printStackTrace();
            err = "Exception: "+e.getMessage();
        }

        //call method to pull user tags, use json to place them into an array
        double[] usr = {
                0.5, 0.6, 0.4, 1.0, 0.9, 0.2, 0.7, 0.4, 0.6, 0.1
        };
        for (int i = 0; i<rar.length; i++) {
            //call method to pull food item tags, use json to place them in an array
            double[] tags = new double[10];
            tags[0] = 1;
            for (int ii = 1; ii<10; ii++) {
                Random nums = new Random();
                int temps = nums.nextInt(1);
                tags[ii] = temps;

            }
            sim[i] = cossim(usr,tags);


        }
        bubbleSort(rar,sim);*/








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
    class BackGround extends AsyncTask<String, String, String> {
        String sigs;
        double[] usr = {
                0.5, 0.6, 0.4, 1.0, 0.9, 0.2, 0.7, 0.4, 0.6, 0.1
        };
        double[] sim = new double[rar.length];


        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String sig = params[1];
            sigs = sig;


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
            ViewHolder holder;
            String err=null;
            /*num[0] = (TextView) findViewById(R.id.textView28);
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
            num[19] = (TextView) findViewById(R.id.textView70);*/

            try {
                JSONObject root = new JSONObject(s);
                JSONArray menu = root.getJSONArray("results");


                for (int i =0; i<rar.length; i++) {
                    JSONObject ff = menu.getJSONObject(i);
                    NAME = ff.getString("NAME");
                    rar[i] = NAME;
                    /*RAT = ff.getString("RATING");
                    num[i].setText(RAT);*/
                }

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }

            /*for (int i = 0; i<num.length ; i++) {
                Random nums = new Random();
                float temp = nums.nextFloat();
                int temps = nums.nextInt(2);
                float tempss = temp + temps +3;
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                //System.out.println(df.format(decimalNumber));

                //num[i] = tempss;
            }*/
            String[] m = new String[rar.length];
            if (sigs == "b") {
                for (int i = 0; i<rar.length; i++) {
                    //call method to pull food item tags, use json to place them in an array
                    double[] tags = new double[10];
                    tags[0] = 1;
                    for (int ii = 1; ii<10; ii++) {
                        Random nums = new Random();
                        int temps = nums.nextInt(2);
                        tags[ii] = temps;

                    }
                    sim[i] = cossim(usr,tags);
                    System.out.println(sim[i]);


                }

                m = bubbleSort(rar,sim);
            }
            String[] mm = new String[5];
            for (int i = 0; i<mm.length; i++) {
                mm[i] = m[i];
            }


            TextView lbl_name = (TextView) findViewById(R.id.name);
            CharSequence name = lbl_name.getText();
            names = name.toString();

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(SinglePlaceActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            View convertView = (View) inflater.inflate(R.layout.list_item2, null);
            holder = new ViewHolder();
            holder.Name = (TextView) convertView.findViewById(R.id.title);
            //String f = holder.Name.getText().toString();
            convertView.setTag(holder);
            alertDialog.setView(convertView);
            if (sigs == "a") {
                alertDialog.setTitle(names+"'s Menu");
            }
            else if (sigs == "b") {
                alertDialog.setTitle("Recommendations");
            }

            alertDialog.setCancelable(true);
            alertDialog.setPositiveButton(
                    "Back",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            ListView lv = (ListView) convertView.findViewById(R.id.listView);
            if (sigs == "a") {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SinglePlaceActivity.this,android.R.layout.simple_list_item_1,rar);
                lv.setAdapter(adapter);
            }
            else {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SinglePlaceActivity.this,android.R.layout.simple_list_item_1,mm);
                lv.setAdapter(adapter);
            }


            alertDialog.show();
            if (sigs == "a") {
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SinglePlaceActivity.this);
                        alertDialog.setTitle("Item Information");
                        alertDialog.setMessage("Rating:\n\n\n Description:");
                        alertDialog.setCancelable(true);
                        alertDialog.setPositiveButton(
                                "Back",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                    }
                });
            }



        }
        class ViewHolder {
            TextView Name;

        }
        public String[] bubbleSort(String[] a, double[] b) {
            boolean swap = true;
            int j = 0;
            double tmp;
            String tmps;
            while (swap) {
                swap = false;
                j++;
                for (int i =0; i< a.length - j; i++) {
                    if (b[i] < b[i + 1]) {
                        tmp = b[i];
                        tmps = a[i];
                        b[i] = b[i + 1];
                        a[i] = a[i + 1];
                        b[i + 1] = tmp;
                        a[i + 1] = tmps;
                        swap = true;


                    }
                }
            }
            return a;
        }
        public double dot(double a[],double b[]) {
            double sum = 0;
            for (int i = 0; i<a.length; i++) {
                sum += a[i] * b[i];
            }
            return sum;
        }
        public double norm(double a[]) {
            double sum = 0;
            for (int i = 0; i<a.length; i++) {
                sum += a[i] * a[i];
            }
            double ll = Math.sqrt(sum);
            return ll;
        }
        public double cossim(double a[], double b[]) {
            return dot(a,b) / (norm(a) * norm(b));
        }
    }
}









