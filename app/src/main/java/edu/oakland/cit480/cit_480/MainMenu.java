package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenu extends Activity implements View.OnClickListener{

    Context context;
    Button button2, button3;
    TextView welcomeBox;
    String username;

    ImageView rest, tastep;
    Drawable rest1;

    //Added 10/19
    @Override
    public void onBackPressed(){
        this.finishAffinity();
        //Don't allow user to use back button to return to the login screen
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        context = this.getApplicationContext();

        //rest1 = (R.drawable.food_and_neverage);

        rest = (ImageView) findViewById(R.id.imageView4);
        rest.setOnClickListener(this);

        tastep = (ImageView) findViewById(R.id.imageView5);
        tastep.setOnClickListener(this);
        //tastep.setImageResource(R.drawable.food_and_neverage);

        //button2 = (Button) findViewById(R.id.button2);
        //button2.setOnClickListener(this);

        //button3 = (Button) findViewById(R.id.button3);
        //button3.setOnClickListener(this);
        welcomeBox = (TextView) findViewById(R.id.textView);
        //Added 10/19
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        String username = sp.getString("USER_NAME", "");
        welcomeBox.setText("Welcome, "+username+"!");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ActionBar actionBar = getActionBar();
        MenuItem mainmenu = menu.findItem(R.id.MainMenu);
        mainmenu.setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.History) {
            startActivity(new Intent(getApplicationContext(), History.class));
            return true;
        }
        if (id == R.id.Logout) {
            //Added 10/19
            //Clear sharedprefs
            clearUserPrefs();


            startActivity(new Intent(getApplicationContext(), LoginPage.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Added 10/19
    public void clearUserPrefs(){
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
    private void button2Click() {



        Intent myIntent = new Intent(context, RestaurantList.class);
        //myIntent.putExtra("userInput", userInput.getText().toString());
        startActivity(myIntent);
    }
    private void button3Click() {

        //tastep.getDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        //tastep.setImageResource(R.drawable.food_and_neverage);
        Intent myIntent = new Intent(getApplicationContext(), Questionnaire.class);
        //myIntent.putExtra("userInput", userInput.getText().toString());
        startActivity(myIntent);

    }
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.imageView4:
                button2Click();
                break;
            case R.id.imageView5:
                button3Click();
                break;
        }

    }



}

