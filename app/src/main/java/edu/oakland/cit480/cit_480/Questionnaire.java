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

public class Questionnaire extends Activity implements View.OnClickListener{

    Context context;
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);

        doneButton = (Button) findViewById(R.id.DoneButton);
        doneButton.setOnClickListener(this);
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
        return super.onOptionsItemSelected(item);
    }
    private void doneButtonClick() {

        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
        //myIntent.putExtra("userInput", userInput.getText().toString());
        startActivity(myIntent);
    }

    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.DoneButton:
                doneButtonClick();
                break;
        }

    }

}
