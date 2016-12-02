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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.lang.Math.*;


import static java.lang.Double.NaN;

public class SinglePlaceActivity extends Activity implements View.OnClickListener{

    String s;
    String tempusr = "";
    String USERID;
    String[] tag = new String[20];
    String[] TAGNAME = new String[200];
    String[] RATING = new String[200];
    String[] rar;
    boolean[] al;
    double[] usr = new double[20];
    double[] frar = new double[20];
    String[] mm = new String[5];
    int[][] foodstuffs;

    ArrayList<String> arr = new ArrayList<String>();

    Context context;
    JSONObject root;
    JSONArray menu;
    JSONArray utag;
    JSONArray mtag;
    JSONArray rat;
    JSONArray atag;

    String des = "w/e";
    String loc = "w/e";
    String fid = "w/e";
    String nam;
    String rating = "w/e";

    String NAME=null, RAT=null;

    Button button, button8;
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetector cd;
    int wait = 0;

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
    public static String KEY_NAME = "name";



    float num[] = new float[30];
    double[] sim;

    DecimalFormat test[] = new DecimalFormat[30];

    float man[] = new float[20];

    String[] biggs;

    String names;

    Context ctx=this;
    String sig;
    String[] m;
    ViewHolder holder;
    ImageView menus, rc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_place);
        menus = (ImageView) findViewById(R.id.imageView2);
        menus.setOnClickListener(this);
        menus.setClickable(false);

        rc = (ImageView) findViewById(R.id.imageView3);
        rc.setOnClickListener(this);
        rc.setClickable(false);

        Intent i = getIntent();

        tag[0] = "savory";
        tag[1] = "sweet";
        tag[2] = "salty";
        tag[3] = "bitter";
        tag[4] = "sour";
        tag[5] = "spicy";
        tag[6] = "creamy";
        tag[7] = "grilled";
        tag[8] = "boiled";
        tag[9] = "baked";
        tag[10] = "steamed";
        tag[11] = "fried";
        tag[12] = "crispy";
        tag[13] = "crunchy";
        tag[14] = "leafy";
        tag[15] = "tender";
        tag[16] = "cheesy";
        tag[17] = "juicy";
        tag[18] = "chewy";
        tag[19] = "starchy";

        //progressbar = (ProgressBar)findViewById(R.id.progressBar2);
        //progressbar.setVisibility(View.GONE);

        // Place reference id
        String reference = i.getStringExtra(KEY_REFERENCE);
        String namee = i.getStringExtra(KEY_NAME);

        // Calling an Async Background thread
        new LoadSinglePlaceDetails().execute(reference);

        context = this.getApplicationContext();



        //button8 = (Button) findViewById(R.id.button8);
        //button8.setOnClickListener(this);
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        setTempusr(sp.getString("USER_ID", ""));
        nam = getTempusr();

        sig = "c";


        BackGround me = new BackGround();
        me.execute(getTempusr(), sig, "0", "0", "0");
        BackGround eee = new BackGround();
        eee.execute(getTempusr(), "e", "0", "0", "0");
        BackGround we = new BackGround();
        we.execute(getTempusr(), "r", "0", "0", "0");
        BackGround mee = new BackGround();
        sig = "b";
        mee.execute(namee, sig, "0", "0", "0");






    }
    public String getTempusr() {
        return tempusr;
    }

    public void setTempusr(String tempusr) {
        this.tempusr = tempusr;
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
            clearUserPrefs();
            startActivity(new Intent(SinglePlaceActivity.this, LoginPage.class));
            return true;
        }
        if (id == R.id.MainMenu) {
            startActivity(new Intent(SinglePlaceActivity.this, MainMenu.class));
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
        me.execute(names, sig, "0", "0", "0");

        //progressbar.setVisibility(View.GONE);
    }
    private void button2Click() {
        String[] srar = new String[rar.length];
        for (int y = 0; y<sim.length; y++) {
            sim[y] = 0;
            System.out.println(al[y]);
        }
        for (int x = 0; x<rar.length; x++) {
            if (al[x]) {

            }
            else {
                srar[x] = rar[x];
                System.out.println(srar[x]);
            }

        }


        for (int i = 0; i<rar.length; i++) {
            if (al[i]) {

            }
            else {
                for (int j = 0; j<frar.length; j++) {
                    frar[j] = foodstuffs[i][j];

                }
                if (cossim(usr,frar) > 0 ) {
                    sim[i] = cossim(usr,frar);
                }
            }





            System.out.println(srar[i]);
            System.out.println(sim[i]);



        }
        m = bubbleSort(srar,sim);
        for (int i = 0; i<5; i++) {
            mm[i] = m[i];
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SinglePlaceActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.list_item2, null);
        holder = new ViewHolder();
        holder.Name = (TextView) convertView.findViewById(R.id.title);
        //String f = holder.Name.getText().toString();
        convertView.setTag(holder);
        alertDialog.setView(convertView);
        alertDialog.setTitle("Recommendations");
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton(
                "Back",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        ListView lv = (ListView) convertView.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SinglePlaceActivity.this, android.R.layout.simple_list_item_1, mm);
        lv.setAdapter(adapter);
        alertDialog.show();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                final String text = (((TextView)view).getText()).toString();// second method
                int ind = 1;

                for (int i = 0; i<rar.length; i++) {
                    if (rar[i] == text) {
                        ind = i;
                    }
                }
                try {
                    JSONObject ff = menu.getJSONObject(ind);
                    rating = "n/a";
                    des = "n/a";

                    des = ff.getString("DESCRIPTION");
                    loc = ff.getString("LOCATION");
                    fid = ff.getString("ID");

                    for (int i = 0; i<rat.length(); i++) {
                        JSONObject fe = rat.getJSONObject(i);
                        String d = fe.getString("FOOD ID");
                        System.out.println(d);
                        String ll = fid;
                        String l = d;


                        if (l.equals(ll)) {
                            rating = fe.getString("RATING");
                            System.out.println(d);
                            System.out.println(fid);
                            System.out.println(rating);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }
                LayoutInflater inflater = getLayoutInflater();
                View convertViews = (View) inflater.inflate(R.layout.histdia, null);
                final RatingBar urat = (RatingBar) convertViews.findViewById(R.id.ratingBar);
                String der = "2.50";
                float derat = Float.valueOf(der);
                urat.setRating(derat);


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SinglePlaceActivity.this);
                alertDialog.setView(convertViews);
                alertDialog.setTitle("Item Information");
                alertDialog.setMessage("Description:" + des +"\nLast rating:"+ rating + "\nCurrent rating:");
                alertDialog.setCancelable(true);
                alertDialog.setNegativeButton(
                        "Back",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setPositiveButton(
                        "I ate this.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                System.out.println("Choosen Country = : " + text);
                                Float crat = urat.getRating();
                                setHistory(fid,nam,loc, crat.toString());

                                dialog.dismiss();
                            }
                        });

                alertDialog.show();

            }
        });


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
    public void setFrar(int w) {
        BackGround c = new BackGround();
        System.out.println(biggs[w]);
        c.execute(biggs[w], "d", Integer.toString(w), "0", "0");



    }
    public void getHistory() {
        BackGround eee = new BackGround();
        eee.execute(getTempusr(), "e", "0", "0", "0");
    }
    public void setHistory(String Fid, String Id, String place, String crat) {
        BackGround H = new BackGround();
        H.execute(Fid,"y", getTempusr(), place, crat);
    }




    public void onClick(View v) {

        switch (v.getId())
        {



            case R.id.imageView2:
                button1Click();
                break;
            case R.id.imageView3:
                button2Click();
                break;

        }

    }

    class BackGround extends AsyncTask<String, String, String> {
        String sigs;
        String foodcount;
        String place;
        String temprat;
        String Date;






        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            sigs = params[1];
            foodcount = params[2];
            place = params[3];
            temprat = params[4];

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date = dateFormat.format(new Date()).toString();






            String data="";
            int tmp;
            URL url;
            String urlParams;
            //System.out.println(sigs);

            try {
                if (sigs == "b") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/menus3.php");
                    urlParams = "name="+name;
                }
                else if (sigs == "c") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/getRatings.php");
                    urlParams = "USERID=" + name;
                }
                else if (sigs == "d"){
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/getMenuRatings.php");
                    urlParams = "USERID="+name;
                }
                else if (sigs == "e") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/getHist.php");
                    urlParams = "USERID="+name;
                }
                else if (sigs == "r") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/getAllergens.php");
                    urlParams = "USERID=" + name;
                }
                else  {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/inHist.php");
                    urlParams = "FID="+name + "&USERID=" + foodcount + "&REST=" + place + "&RAT=" + temprat + "&DATE=" + Date;
                }


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
            if (sigs == "y") {
                System.out.println(s);
                if(s.equals("")){
                    s="Data saved successfully.";
                }
                Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
                getHistory();
            }
            if (sigs == "r") {
                try {
                    root = new JSONObject(s);

                    atag = root.getJSONArray("results");
                    System.out.println(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                    System.out.print(err);
                }
            }
            if (sigs == "e") {
                try {
                    root = new JSONObject(s);

                    rat = root.getJSONArray("results");
                    System.out.println(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                    System.out.print(err);
                }

            }
            if (sigs == "d") {

                try {

                    root = new JSONObject(s);
                    System.out.println(s);

                    mtag = root.getJSONArray("results");


                    for (int i = 0; i < tag.length; i++) {
                        boolean ex = false;
                        int foodone = Integer.valueOf(foodcount);

                        for (int j = 0; j < mtag.length(); j++) {
                            JSONObject ff = mtag.getJSONObject(j);
                            TAGNAME[j] = ff.getString("TAG NAME");
                            //System.out.println(tag[i]);
                            //System.out.println(TAGNAME[j]);
                            for (int r = 0; r<atag.length(); r++) {
                                JSONObject fe = atag.getJSONObject(r);
                                String aler = fe.getString("NAME");
                                if (aler.equals(TAGNAME[j])) {
                                    al[foodone] = true;
                                }


                            }



                            if (TAGNAME[j].equals(tag[i])) {

                                foodstuffs[foodone][i] = 1;

                                ex = true;
                                //System.out.println(foodstuffs[foodone][i]);
                            }



                        }
                        if (ex == false) {
                            Random y = new Random();
                            int fff = y.nextInt(2);

                            foodstuffs[foodone][i] = 0;
                            //System.out.println(foodstuffs[foodone][i]);

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                    System.out.print(err);
                }
                if (Integer.valueOf(foodcount) == (biggs.length - 1 )) {
                    menus.setClickable(true);
                    rc.setClickable(true);
                }
            }
            if (sigs == "c") {
                try {
                    root = new JSONObject(s);
                    System.out.println(s);
                    utag = root.getJSONArray("results");
                    for (int i = 0; i < tag.length; i++) {
                        boolean ex = false;
                        for (int j = 0; j < utag.length(); j++) {
                            JSONObject ff = utag.getJSONObject(j);
                            TAGNAME[j] = ff.getString("TAG NAME");

                            RATING[j] = ff.getString("RATING");


                            if (TAGNAME[j].equals(tag[i])) {
                                usr[i] = Double.valueOf(RATING[j]);
                                ex = true;
                                //System.out.println("ZE WORLDO");
                            }

                        }
                        if (ex == false) {
                            usr[i] = 0.5;
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                    System.out.print(err);
                }
            }

            if (sigs == "b") {
                try {
                    //System.out.println(s);
                    root = new JSONObject(s);
                    System.out.println(s);
                    menu = root.getJSONArray("results");
                    // System.out.println(s);
                    rar = new String[menu.length()];
                    foodstuffs  = new int[rar.length][tag.length];
                    sim = new double[rar.length];
                    biggs = new String[rar.length];
                    m  = new String[rar.length];
                    al = new boolean[rar.length];



                    for (int i =0; i<menu.length(); i++) {
                        JSONObject ff = menu.getJSONObject(i);
                        NAME = ff.getString("NAME");
                        rar[i] = NAME;
                        biggs[i] = ff.getString("ID");

                        //System.out.println(biggs[i]);
                    /*RAT = ff.getString("RATING");
                    num[i].setText(RAT);*/
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: "+e.getMessage();
                }
                if (sigs == "b") {
                    for (int ii = 0; ii<rar.length; ii++) {


                        setFrar(ii);







                    }

                }

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





            if (sigs == "a" | sigs == "f") {
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
                    alertDialog.setTitle(names + "'s Menu");
                } else if (sigs == "f") {
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SinglePlaceActivity.this, android.R.layout.simple_list_item_1, rar);
                    lv.setAdapter(adapter);
                }


                alertDialog.show();

                if (sigs == "a") {
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                final int position, long id) {
                            final String text = (((TextView)view).getText()).toString();// second method
                            int ind = 1;

                            for (int i = 0; i<rar.length; i++) {
                                if (rar[i] == text) {
                                    ind = i;
                                }
                            }
                            try {
                                JSONObject ff = menu.getJSONObject(ind);
                                rating = "n/a";
                                des = "n/a";

                                des = ff.getString("DESCRIPTION");
                                loc = ff.getString("LOCATION");
                                fid = ff.getString("ID");

                                for (int i = 0; i<rat.length(); i++) {
                                    JSONObject fe = rat.getJSONObject(i);
                                    String d = fe.getString("FOOD ID");
                                    System.out.println(d);
                                    String ll = fid;
                                    String l = d;


                                    if (l.equals(ll)) {
                                        rating = fe.getString("RATING");
                                        System.out.println(d);
                                        System.out.println(fid);
                                        System.out.println(rating);
                                    }
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                            LayoutInflater inflater = getLayoutInflater();
                            View convertViews = (View) inflater.inflate(R.layout.histdia, null);
                            final RatingBar urat = (RatingBar) convertViews.findViewById(R.id.ratingBar);
                            String der = "2.50";
                            float derat = Float.valueOf(der);
                            urat.setRating(derat);


                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(SinglePlaceActivity.this);
                            alertDialog.setView(convertViews);
                            alertDialog.setTitle("Item Information");
                            alertDialog.setMessage("Description:" + des +"\nLast rating:"+ rating + "\nCurrent rating:");
                            alertDialog.setCancelable(true);
                            alertDialog.setNegativeButton(
                                    "Back",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.setPositiveButton(
                                    "I ate this.",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            System.out.println("Choosen Country = : " + text);
                                            Float crat = urat.getRating();
                                            setHistory(fid,nam,loc, crat.toString());

                                            dialog.dismiss();
                                        }
                                    });

                            alertDialog.show();

                        }
                    });
                }
            }

        }



    }
    class ViewHolder {
        TextView Name;

    }
    public void clearUserPrefs(){
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
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







































