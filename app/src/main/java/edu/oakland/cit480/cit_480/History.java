package edu.oakland.cit480.cit_480;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class History extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        MenuItem history = menu.findItem(R.id.History);
        history.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        if (id == R.id.MainMenu) {
            startActivity(new Intent(History.this, MainMenu.class));
            return true;
        }
        if (id == R.id.Logout) {
            clearUserPrefs();
            startActivity(new Intent(getApplicationContext(), LoginPage.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clearUserPrefs() {
        SharedPreferences sp = getSharedPreferences("mealreel_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();

    }
}

