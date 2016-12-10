package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
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

public class History extends Activity {
    // Places Listview
    ListView lv;
    JSONArray hist;
    JSONArray men;
    JSONObject root;
    String err=null;
    String[] des;
    String[] rar;
    Context ctx=this;
    ProgressDialog pDialog;

    String date;
    String loc;
    String rating;

    String sigs;

    String tempusr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lv = (ListView) findViewById(R.id.listView2);
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        setTempusr(sp.getString("USER_ID", ""));

        sigs = "a";
        BackGround b = new BackGround();
        b.execute(sigs, getTempusr(), "0");
        pDialog = new ProgressDialog(History.this);
        pDialog.setMessage("Loading profile ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
    public void getHistory() {
        sigs = "a";
        BackGround b = new BackGround();
        b.execute(sigs, getTempusr(), "0");
    }

    public void setLvClick() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(History.this, android.R.layout.simple_list_item_1, rar);
        lv.setAdapter(adapter);
        pDialog.dismiss();



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                final String text = (((TextView)view).getText()).toString();// second method
                int ind = 1;

                for (int i = 0; i<rar.length; i++) {
                    if (rar[i] == text) {
                        ind = i;
                        System.out.println("everything");
                    }
                }
                try {
                    JSONObject ff = hist.getJSONObject(ind);
                    rating = "n/a";
                    date = "n/a";
                    loc = "n/a";

                    date = ff.getString("DATE");
                    loc = ff.getString("REST NAME");
                    rating = ff.getString("RATING");




                } catch (JSONException e) {
                    e.printStackTrace();

                }
                LayoutInflater inflater = getLayoutInflater();
                View convertView = (View) inflater.inflate(R.layout.histdia, null);
                final RatingBar urat = (RatingBar) convertView.findViewById(R.id.ratingBar);
                urat.setRating(Float.valueOf(rating));


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(History.this);
                alertDialog.setView(convertView);
                alertDialog.setTitle("Item Information");
                alertDialog.setMessage("Description: " + des[ind] + "\n\n Date: " + date + "\n\n Restaurant: " + loc + "\n\n Rating: ");
                alertDialog.setCancelable(true);
                alertDialog.setNegativeButton(
                        "Back",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setPositiveButton(
                        "Rate this.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Float crat = urat.getRating();
                                uprat(date, crat.toString());



                                dialog.dismiss();
                            }
                        });

                alertDialog.show();


            }
        });
    }
    public void setMen() {
        try {
            for (int i = 0; i<hist.length(); i++) {
                BackGround m = new BackGround();
                sigs = "b";
                JSONObject fm = hist.getJSONObject(i);
                String id = fm.getString("FOOD ID");
                String in = Integer.toString(i);
                m.execute(sigs, id, in);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            err = "Exception: " + e.getMessage();
            System.out.print(err);
        }

    }
    public void uprat(String da, String ura) {
        BackGround bb = new BackGround();
        bb.execute("c",da,ura);
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
        MenuItem history = menu.findItem(R.id.History);
        history.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        if (id == R.id.MainMenu) {
            startActivity(new Intent(History.this, MainMenu.class));
            return true;
        }
        if (id == R.id.Logout) {
            clearUserPrefs();
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
    class BackGround extends AsyncTask<String, String, String> {
        String op;
        String in;
        @Override
        protected String doInBackground(String... params) {
            op = params[0];
            String name = params[1];
            in = params[2];
            System.out.println(op);

            String data = "";
            int tmp;
            URL url = null;
            String urlParams;
            try {
                if (op == "a") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/getHist.php");
                    urlParams = "USERID="+name;
                }
                else if (op == "b") {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/getinf.php");
                    urlParams = "USERID="+name;
                }
                else {
                    url = new URL("http://www.secs.oakland.edu/~djrasmus/480/updatehist.php");
                    urlParams = "DATE="+name + "&RAT=" + in;
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
            String err=null;

            if (op == "a") {
                try {
                    root = new JSONObject(s);

                    hist = root.getJSONArray("results");
                    des = new String[hist.length()];
                    rar = new String[hist.length()];
                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                    System.out.print(err);
                }
                setMen();

            }
            if (op == "b") {
                int j = Integer.valueOf(in);
                try {
                    root = new JSONObject(s);
                    men = root.getJSONArray("results");
                    JSONObject ff = men.getJSONObject(0);
                    des[j] = ff.getString("DESCRIPTION");
                    rar[j] = ff.getString("NAME");

                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                    System.out.print(err);
                }
                if (j == ((hist.length()) - 1)) {
                    setLvClick();
                }

            }
            if (op == "c") {
                if(s.equals("")){
                    s="Data saved successfully.";
                }
                Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
                getHistory();
            }

        }
    }

}









