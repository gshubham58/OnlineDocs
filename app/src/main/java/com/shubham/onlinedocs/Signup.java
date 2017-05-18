package com.shubham.onlinedocs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;
import java.util.Map;

public class Signup extends AppCompatActivity {
EditText uid,pass,name;
    Button save;
    String usrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        uid=(EditText)findViewById(R.id.usid);
        pass=(EditText)findViewById(R.id.pass);
        name=(EditText)findViewById(R.id.name);
        save=(Button)findViewById(R.id.Savebtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrid=uid.getText().toString();
                String pwd=pass.getText().toString();
                String naam=name.getText().toString();
                Map<String,String> params = new Hashtable<String, String>();
                params.put("Email",usrid);
                params.put("Password",pwd);
                params.put("Name",naam);
                DatabaseReference databaseReference;
                databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
                databaseReference.child(usrid).setValue(params).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Signup.this, "success", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Signup.this,Main2Activity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Signup.this, "fail " + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }
}
