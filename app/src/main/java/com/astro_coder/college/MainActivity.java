package com.astro_coder.college;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dali.astrocoder.college.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);   //  get the support of action bar
        //  Setting the drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        Toggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close);   // Setting the action bar toggle
        drawerLayout.addDrawerListener(Toggle); // adding the listener
        Toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Enable the home button

        /*
         *  Navigation view selections
         */
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent i;
                switch (item.getItemId()){
                    case R.id.insertions :  i = new Intent(MainActivity.this,Insertions.class);
                                            startActivity(i);
                                            finish();
                                            break;
                    case R.id.enseignant :  i = new Intent(MainActivity.this,Enseignants.class);
                                            startActivity(i);
                                            finish();
                                            break;
                    case R.id.eleve :   i = new Intent(MainActivity.this,Eleves.class);
                                        startActivity(i);
                                        finish();
                                        break;
                    case R.id.emplois :     i = new Intent(MainActivity.this,Emplois.class);
                                            startActivity(i);
                                            finish();
                                            break;
                    case R.id.affiches :    i = new Intent(MainActivity.this,Affiches.class);
                                            startActivity(i);
                                            finish();
                                            break;
                    case R.id.quitter   :   onBackPressed(); break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(Toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Quitter l'application ?");
        alertDialogBuilder
                .setMessage("Clicker sur Oui pour quitter!")
                .setCancelable(false)
                .setPositiveButton("Oui",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}