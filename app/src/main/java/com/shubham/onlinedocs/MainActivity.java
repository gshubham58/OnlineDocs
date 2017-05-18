package com.shubham.onlinedocs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView showtxt;
    static int val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showtxt=(TextView)findViewById(R.id.maintxt);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showtxt.setText(String.valueOf(val));

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
        if (id == R.id.action_Logout) {
            SharedPref sharedPref=new SharedPref(MainActivity.this);
            String uid =sharedPref.getvalue("USERID");
            sharedPref.delete(uid);
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_about) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Map<String,String> params = new Hashtable<String, String>();

        if (id == R.id.nav_aadhar) {
          SharedPref sharedPref=new SharedPref(MainActivity.this);
            String uid =sharedPref.getvalue("USERID");
            String name =sharedPref.getvalue("NAME");

            DatabaseReference databaseReference;
            databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("AADHAR");
            params.put("AADHAR","ad111111");
            databaseReference.setValue(params);
            val=id;
        } else if (id == R.id.nav_account) {
            SharedPref sharedPref=new SharedPref(MainActivity.this);
            String uid =sharedPref.getvalue("USERID");
            String name =sharedPref.getvalue("NAME");
            DatabaseReference databaseReference;
            databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("ACCOUNT");
            params.put("ACCOUNT","ac111111");
            databaseReference.setValue(params);
            val=id;

        } else if (id == R.id.nav_drive) {
            SharedPref sharedPref=new SharedPref(MainActivity.this);
            String uid =sharedPref.getvalue("USERID");
            String name =sharedPref.getvalue("NAME");

            DatabaseReference databaseReference;
            databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("DRIVING");
            params.put("DRIVING","DR111111");
            databaseReference.setValue(params);
            val=id;

        } else if (id == R.id.nav_pan) {
            SharedPref sharedPref=new SharedPref(MainActivity.this);
            String uid =sharedPref.getvalue("USERID");
            String name =sharedPref.getvalue("NAME");

            DatabaseReference databaseReference;
            databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("PAN");
            params.put("PAN","pn111111");
            databaseReference.setValue(params);

            val=id;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
