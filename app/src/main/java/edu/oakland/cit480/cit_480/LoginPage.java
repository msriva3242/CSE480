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

public class LoginPage extends Activity implements View.OnClickListener{

    Context context;
    Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        context = this.getApplicationContext();

        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(this);

        buttonRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        buttonRegister.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getActionBar().setDisplayShowTitleEnabled(false);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ActionBar actionBar = getActionBar();

        MenuItem logout = menu.findItem(R.id.Logout);
        logout.setVisible(false);
        MenuItem history = menu.findItem(R.id.History);
        history.setVisible(false);

        return true;
    }
    private void buttonLoginClick() {

        Intent myIntent = new Intent(context, MainMenu.class);
        //myIntent.putExtra("userInput", userInput.getText().toString());
        startActivity(myIntent);
    }
    private void buttonRegisterClick() {

        Intent myIntent = new Intent(context, RegisterPage.class);
        //myIntent.putExtra("userInput", userInput.getText().toString());
        startActivity(myIntent);
    }
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnLogin:
                buttonLoginClick();
                break;

            case R.id.btnLinkToRegisterScreen:
                buttonRegisterClick();
                break;
        }

    }

}
