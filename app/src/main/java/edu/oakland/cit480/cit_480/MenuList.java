package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;

//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
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

    //Context context;
    //ProgressBar progressbar;
    TextView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);

        menu = (TextView) findViewById(R.id.textView3);

        progressbar = (ProgressBar)findViewById(R.id.progressBar);

        context = this.getApplicationContext();

        progressbar.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.GONE);

        /*TextView myTextView = new TextView(this);
        String myString = menu.getText().toString();
        int i1 = myString.indexOf("B");
        int i2 = myString.indexOf("");
        menu.setMovementMethod(LinkMovementMethod.getInstance());
        menu.setText(myString, TextView.BufferType.SPANNABLE);
        Spannable mySpannable = (Spannable)menu.getText();
        ClickableSpan myClickableSpan = new ClickableSpan()
        {
            @Override
            public void onClick(View widget) {
                Intent myIntent = new Intent(context, RestaurantList.class);
                //myIntent.putExtra("userInput", userInput.getText().toString());
                startActivity(myIntent);
            }
        };
        mySpannable.setSpan(myClickableSpan, i1, i2 + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

    */}

    TextView textview;
    JSONObject json = null;
    String str = "";
    //HttpResponse response;
    Context context;
    ProgressBar progressbar;
    Button button;


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



}
