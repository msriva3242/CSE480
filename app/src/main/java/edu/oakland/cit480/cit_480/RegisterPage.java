package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.asus.thechatapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterPage extends Activity implements View.OnClickListener{

    EditText username, password, email;
    String Username, Password, Email;
    Context ctx=this;

    Context context;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username = (EditText) findViewById(R.id.register_name);
        password = (EditText) findViewById(R.id.register_password);
        email = (EditText) findViewById(R.id.register_email);

        buttonRegister = (Button) findViewById(R.id.register_register);
        buttonRegister.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        MenuItem logout = menu.findItem(R.id.Logout);
        logout.setVisible(false);
        MenuItem history = menu.findItem(R.id.History);
        history.setVisible(false);
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
    private void buttonRegisterClick() {

        Username = username.getText().toString();
        Password = password.getText().toString();
        Email = email.getText().toString();
        BackGround b = new BackGround();
        b.execute(Username, Password, Email);

        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
        //myIntent.putExtra("userInput", userInput.getText().toString());
        startActivity(myIntent);
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
                URL url = new URL("http://www.secs.oakland.edu/~djrasmus/480/register.php");
                String urlParams = "username="+username+"&password="+password+"&email="+email;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
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
            if(s.equals("")){
                s="User has been successfully registered!";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }


    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.register_register:
                buttonRegisterClick();
                break;
        }

    }

}
