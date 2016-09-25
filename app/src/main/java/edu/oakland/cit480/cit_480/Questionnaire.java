package edu.oakland.cit480.cit_480;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
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
