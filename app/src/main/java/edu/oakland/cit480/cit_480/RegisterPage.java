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

public class RegisterPage extends Activity implements View.OnClickListener{

    Context context;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        buttonRegister = (Button) findViewById(R.id.btnLinkToRegister);
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

        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
        //myIntent.putExtra("userInput", userInput.getText().toString());
        startActivity(myIntent);
    }
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnLinkToRegister:
                buttonRegisterClick();
                break;
        }

    }

}
