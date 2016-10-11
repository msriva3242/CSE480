package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MenuList extends Activity {

    Context context;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);

        progressbar = (ProgressBar)findViewById(R.id.progressBar);

        context = this.getApplicationContext();

        progressbar.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.GONE);

    }
    TextView textview;
    JSONObject json = null;
    String str = "";
    HttpResponse response;
    Context context;
    ProgressBar progressbar;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressbar = (ProgressBar)findViewById(R.id.progressBar1);
        textview = (TextView)findViewById(R.id.textView1);
        button = (Button)findViewById(R.id.button1);

        progressbar.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                progressbar.setVisibility(View.VISIBLE);

                new GetTextViewData(context).execute();

            }
        });
    }


    private class GetTextViewData extends AsyncTask<Void, Void, Void>
    {
        public Context context;


        public GetTextViewData(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {

            HttpClient myClient = new DefaultHttpClient();
            HttpPost myConnection = new HttpPost("http://www.secs.oakland.edu/~djrasmus/480/menus.php");

            try {
                response = myClient.execute(myConnection);
                str = EntityUtils.toString(response.getEntity(), "UTF-8");

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try{
                JSONArray jArray = new JSONArray(str);
                json = jArray.getJSONObject(0);



            } catch ( JSONException e) {
                e.printStackTrace();
            }

            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            try {
                textview.setText(json.getString("ServerData"));

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Hiding progress bar after done loading TextView.
            progressbar.setVisibility(View.GONE);

        }
    }


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
            startActivity(new Intent(getApplicationContext(), RestaurantList.class));
            return true;

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



}
