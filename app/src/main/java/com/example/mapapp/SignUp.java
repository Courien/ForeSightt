package com.example.mapapp;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUp";
    private FirebaseFirestore fuegoStore;
    private FirebaseAuth fuegoSignUp;
    private String email;
    private String password;
    private ProgressDialog progressDialog;
    private EditText username;
    private EditText passwordsign;
    private EditText collection;
    private Button Back;
    private  Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fuegoSignUp = FirebaseAuth.getInstance();
        fuegoStore = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);

        username = (EditText) findViewById(R.id.UsernameSign);
        passwordsign = (EditText) findViewById(R.id.Passsword);
        collection = (EditText) findViewById(R.id.Collection);
        Back = (Button) findViewById(R.id.Back);
        Register = (Button) findViewById(R.id.Reg);

    }


    private void RegisterUser(){
    final String user = username.getText().toString().trim();
    final String pass = passwordsign.getText().toString().trim();
    final String coll = collection.getText().toString().trim();
    final Intent Login = new Intent(this, LoginPage.class);
    if (TextUtils.isEmpty(user))
    {
        Toast.makeText(this, "No Username in Field", Toast.LENGTH_LONG).show();
        return;
    }

    if(TextUtils.isEmpty(pass))
    {  Toast.makeText(this,"enter password", Toast.LENGTH_SHORT).show();
            return;
    }
        fuegoStore.collection("user").document(coll).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            Toast.makeText(SignUp.this,"User already There", Toast.LENGTH_LONG).show();
                        }
                        if (!documentSnapshot.exists())
                        {
                            signin(user, pass, coll, Login);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUp.this,"Please try again",Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void signin(final String username, final String password, final String collection, final Intent loginscreen)
    {
        fuegoSignUp.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Map<String, Object> Users = new HashMap<>();
                            Users.put("Email" , username);
                            Users.put("Password", password);
                            fuegoStore.collection("user").document(collection).set(Users);
                            Toast.makeText(SignUp.this,"Registered user.", Toast.LENGTH_SHORT).show();
                            startActivity(loginscreen);
                        }

                        else{
                            Toast.makeText(SignUp.this,"Did not register user, please try again.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    public void onClick(View v)
    {
        if (v == Register)
        {
            RegisterUser();
        }
        if ( v == Back)
        {
            Intent back = new Intent(this, LoginPage.class);
            startActivity(back);
        }
    }
}
