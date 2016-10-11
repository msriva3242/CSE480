package edu.oakland.cit480.cit_480;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginPage extends Activity {
    EditText username, password;
    String Username, Password;
    Context ctx=this;
    String USERNAME=null, PASSWORD=null, EMAIL=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_test);
        username = (EditText) findViewById(R.id.main_name);
        password = (EditText) findViewById(R.id.main_password);

        Username = username.getText().toString();
        Password = password.getText().toString();
    }

    public void main_register(View v){
        startActivity(new Intent(this,RegisterPage.class));
    }

    public void main_login(View v){
        Username = username.getText().toString();
        Password = password.getText().toString();
        BackGround b = new BackGround();
        b.execute(Username, Password);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://www.secs.oakland.edu/~djrasmus/480/login.php");
                String urlParams = "username="+username+"&password="+password;

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
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                USERNAME = user_data.getString("username");
                PASSWORD = user_data.getString("password");
                EMAIL = user_data.getString("email");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }

            Intent i = new Intent(ctx, MainMenu.class);
            i.putExtra("name", USERNAME);
            i.putExtra("password", PASSWORD);
            i.putExtra("email", EMAIL);
            i.putExtra("err", err);

            startActivity(i);





        }

    }
}
