package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterPage extends Activity {
    EditText username, password, email;
    String Username, Password, Email;
    Context ctx=this;
    String USERNAME=null, PASSWORD=null, EMAIL=null, RETURN_MESSAGE=null;
    int SUCCESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username = (EditText) findViewById(R.id.register_name);
        password = (EditText) findViewById(R.id.register_password);
        email = (EditText) findViewById(R.id.register_email);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ActionBar actionBar = getActionBar();

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void register_register(View v){
        Username = username.getText().toString();
        Password = password.getText().toString();
        Email = email.getText().toString();
        BackGround b = new BackGround();
        b.execute(Username, Password, Email);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String email = params[2];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://www.secs.oakland.edu/~djrasmus/480/index.php");
                String urlParams = "username="+username+"&password="+password+"&email="+email;

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
            try {

                Log.d("JSON:",""+s);
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                RETURN_MESSAGE = user_data.getString("message");
                USERNAME = user_data.getString("username");
                SUCCESS = user_data.getInt("success");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }
            s = RETURN_MESSAGE;
            switch(SUCCESS){

                case 0:
                    s = "Please enter a valid username, password, and/or email address!";
                    Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
                    break;
                case 1:


                    Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ctx, MainMenu.class);
                    save("USER_NAME", USERNAME);
                    startActivity(i);
                    break;
                default:
                    s = "Unknown Error";
                    Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        public void save(String key, String value){
            SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("USER_NAME", USERNAME);
            editor.commit();
        }

    }
    public void checkDB(){
        Intent i = new Intent(ctx, MainMenu.class);
        startActivity(i);
    }
}