package edu.oakland.cit480.cit_480;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

public class Recommendations extends Activity {

    Context context;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);

        s="The menu item has been successfully saved to your history page!";

        AlertDialog.Builder builder1 = new AlertDialog.Builder(Recommendations.this);
        builder1.setTitle("Recommendation #1:");
        builder1.setMessage("Slow-Roasted Prime Rib\n\nWould you like to order this item?");
        builder1.setCancelable(true);

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Recommendations.this);
                        builder1.setTitle("Recommendation #2:");
                        builder1.setMessage("Mushroom Marsala \n\nWould you like to order this item?");
                        builder1.setCancelable(true);

                        builder1.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Recommendations.this);
                                        builder1.setTitle("Recommendation #3:");
                                        builder1.setMessage("Teriyaki Filet Medallions\n\nWould you like to order this item?");
                                        builder1.setCancelable(true);

                                        builder1.setPositiveButton(
                                                "Yes",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        Toast.makeText(Recommendations.this, s, Toast.LENGTH_LONG).show();
                                                        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
                                                        //myIntent.putExtra("userInput", userInput.getText().toString());
                                                        startActivity(myIntent);
                                                    }
                                                });

                                        builder1.setNegativeButton(
                                                "No",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Recommendations.this);
                                                        builder1.setTitle("Recommendation #4:");
                                                        builder1.setMessage("Aussie Chicken Tacos\n\nWould you like to order this item?");
                                                        builder1.setCancelable(true);

                                                        builder1.setPositiveButton(
                                                                "Yes",
                                                                new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int id) {
                                                                        Toast.makeText(Recommendations.this, s, Toast.LENGTH_LONG).show();
                                                                        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
                                                                        //myIntent.putExtra("userInput", userInput.getText().toString());
                                                                        startActivity(myIntent);
                                                                    }
                                                                });

                                                        builder1.setNegativeButton(
                                                                "No",
                                                                new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int id) {
                                                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Recommendations.this);
                                                                        builder1.setTitle("Recommendation #5:");
                                                                        builder1.setMessage("Alice Springs Chicken Quesadillas\n\nWould you like to order this item?");
                                                                        builder1.setCancelable(true);

                                                                        builder1.setPositiveButton(
                                                                                "Yes",
                                                                                new DialogInterface.OnClickListener() {
                                                                                    public void onClick(DialogInterface dialog, int id) {
                                                                                        Toast.makeText(Recommendations.this, s, Toast.LENGTH_LONG).show();
                                                                                        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
                                                                                        //myIntent.putExtra("userInput", userInput.getText().toString());
                                                                                        startActivity(myIntent);
                                                                                    }
                                                                                });

                                                                        builder1.setNegativeButton(
                                                                                "No",
                                                                                new DialogInterface.OnClickListener() {
                                                                                    public void onClick(DialogInterface dialog, int id) {
                                                                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Recommendations.this);
                                                                                        builder1.setMessage("We're all out of recommendations. Have a look at the restaurant's menu.");
                                                                                        builder1.setCancelable(true);

                                                                                        builder1.setPositiveButton(
                                                                                                "Ok",
                                                                                                new DialogInterface.OnClickListener() {
                                                                                                    public void onClick(DialogInterface dialog, int id) {
                                                                                                        Intent myIntent = new Intent(getApplicationContext(), MenuList.class);
                                                                                                        //myIntent.putExtra("userInput", userInput.getText().toString());
                                                                                                        startActivity(myIntent);
                                                                                                    }
                                                                                                });

                                                                                        AlertDialog alert11 = builder1.create();
                                                                                        alert11.show();


                                                                                    }
                                                                                });
                                                                        AlertDialog alert11 = builder1.create();
                                                                        alert11.show();
                                                                    }
                                                                });

                                                        AlertDialog alert11 = builder1.create();
                                                        alert11.show();
                                                    }
                                                });

                                        AlertDialog alert11 = builder1.create();
                                        alert11.show();
                                    }
                                });

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(Recommendations.this, s, Toast.LENGTH_LONG).show();
                                        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
                                        //myIntent.putExtra("userInput", userInput.getText().toString());
                                        startActivity(myIntent);
                                    }
                                });


                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }


                });

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(Recommendations.this, s, Toast.LENGTH_LONG).show();
                        Intent myIntent = new Intent(getApplicationContext(), MainMenu.class);
                        //myIntent.putExtra("userInput", userInput.getText().toString());
                        startActivity(myIntent);
                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
