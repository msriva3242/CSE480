package edu.oakland.cit480.cit_480;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;

public class MenuList extends Activity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);

        context = this.getApplicationContext();


    }
}
