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

public class MainMenu extends Activity implements View.OnClickListener{

    Context context;
    Button button2, button3, buttonHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        context = this.getApplicationContext();

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

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
        if (id == R.id.History) {
            startActivity(new Intent(getApplicationContext(), History.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void button2Click() {

        Intent myIntent = new Intent(context, RestaurantList.class);
        //myIntent.putExtra("userInput", userInput.getText().toString());
        startActivity(myIntent);
    }
    private void button3Click() {

        Intent myIntent = new Intent(context, Questionnaire.class);
        //myIntent.putExtra("userInput", userInput.getText().toString());
        startActivity(myIntent);
    }

    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.button2:
                button2Click();
                break;

            case R.id.button3:
                button3Click();
                break;
        }

    }

}
