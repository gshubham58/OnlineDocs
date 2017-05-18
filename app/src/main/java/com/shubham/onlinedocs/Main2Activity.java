package com.shubham.onlinedocs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    EditText uid, pwd;
    Button btn;
    String usrid, pass;
    TextView header;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        uid = (EditText) findViewById(R.id.edt);
        pwd = (EditText) findViewById(R.id.edt2);
        btn = (Button) findViewById(R.id.btn);
        header = (TextView) findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrid = uid.getText().toString();
                pass = pwd.getText().toString();
                final DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(usrid);
                Log.e("URL", "" + databaseReference);
                if (databaseReference != null) {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getChildrenCount() > 0) {
                                Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();

                                String email = (String) td.get("Email");
                                String passwrd = (String) td.get("Password");
                                String name = (String) td.get("Name");
                                Log.e("value", email + "");
                                Log.e("value", passwrd + "");
                                Log.e("value", name + "");
                                if (passwrd.equals(pass)) {
                                    Toast.makeText(Main2Activity.this, "login Success", Toast.LENGTH_SHORT).show();
                                    SharedPref sharedPref = new SharedPref(Main2Activity.this);
                                    sharedPref.setvalue("NAME", name);
                                    sharedPref.setvalue("USERID", email);
                                    sharedPref.setvalue("PASSWORD", passwrd);
                                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    pwd.requestFocus();
                                    pwd.setError("Invalid password");
                                }
                            } else {
                                uid.requestFocus();
                                uid.setError("Invalid username");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }


            }

        });
    }

    public void signup(View view) {
        Intent intent = new Intent(Main2Activity.this, Signup.class);
        startActivity(intent);
    }
}
