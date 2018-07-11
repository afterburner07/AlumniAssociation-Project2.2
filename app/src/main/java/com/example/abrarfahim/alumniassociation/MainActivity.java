package com.example.abrarfahim.alumniassociation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText editTextInput;
    TextView textViewText;
    public MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextInput = (EditText) findViewById(R.id.editText);
        textViewText = (TextView) findViewById(R.id.plainText);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                addStudentStartActivity();
            }
        });

        fab.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Snackbar.make(v, "TAP PLUS icon to ADD new student", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return true;
                    }
                }
        );
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void addStudentStartActivity() {
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }

    public void addButtonClicked(View view) {
        Products product = new Products(editTextInput.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();
    }

    public void deleteButtonClicked(View view) {
        String inputText = editTextInput.getText().toString();
        dbHandler.deleteProducts(inputText);
        printDatabase();
    }

    public void printDatabase() {
        String dbString = dbHandler.databaseToString();
        textViewText.setText(dbString);
        editTextInput.setText("");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            gotoAddStudent(item);

        } else if (id == R.id.nav_slideshow) {
            gotoEditStudent(item);

        } else if (id == R.id.nav_manage) {
            gotoDeleteStudent(item);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void gotoAddStudent(MenuItem item) {
        addStudentStartActivity();
    }

    public void gotoEditStudent(MenuItem item) {
        Intent intent = new Intent(this, EditStudent.class);
        startActivity(intent);
    }

    public void gotoDeleteStudent(MenuItem item) {
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }
}
