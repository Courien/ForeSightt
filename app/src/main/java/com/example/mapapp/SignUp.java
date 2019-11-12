package com.example.mapapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUp";
    private FirebaseFirestore fuegoStore;
    private FirebaseAuth fuegoSignUp;
    private String email;
    private String password;
    private ProgressDialog progressDialog;
    private EditText username;
    private EditText passwordsign;
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

        Back = (Button) findViewById(R.id.Back);
        Register = (Button) findViewById(R.id.Reg);

    }


    private void Register(){
    final String user = username.getText().toString().trim();
    final String pass = passwordsign.getText().toString().trim();

    if (TextUtils.isEmpty(user))
    {
        Toast.makeText(this, "No Username in Field", Toast.LENGTH_LONG).show();
    }
        fuegoStore.collection("user").document(user).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists())
                        {
                            Toast.makeText(SignUp.this,"User already There", Toast.LENGTH_LONG).show();
                        }
                        if (!documentSnapshot.exists())
                        {
                        }
                    }
                });
    }


    public void signin(final String username, final String password, final String collection, final Intent firstScreen)
    {

    }
}
