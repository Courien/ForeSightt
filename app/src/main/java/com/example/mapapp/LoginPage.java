package com.example.mapapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

public class LoginPage extends AppCompatActivity {

    private FirebaseAuth login;
    private Button LoginButton;
    private EditText Username;
    private EditText Password;
    private TextView Register;

    private EditText Collection;
    FirebaseFirestore FuegoLogin;
    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = FirebaseAuth.getInstance();
        FuegoLogin = FirebaseFirestore.getInstance();

        Collection = findViewById(R.id.collection);
        dialog = new ProgressDialog(this);
        LoginButton = findViewById(R.id.ButtonSignIn);
        Username = findViewById(R.id.UsernameSignIn);
        Password =  findViewById(R.id.PasswordSignIn);
        Register =  findViewById(R.id.SignUp);



    }


    public void signInUser()
    {
        //final String user = Username.getText().toString().trim();
       // final String pass = Password.getText().toString().trim();
        String collection = Collection.getText().toString().trim();


        if (TextUtils.isEmpty(collection))
        {
            Toast.makeText(LoginPage.this,"user not found, please try again", Toast.LENGTH_SHORT).show();
            return;
        }


        FuegoLogin.collection("user").document(collection).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {

                            String title = documentSnapshot.getString(("Email"));
                            Username.setText(title);
                            String Pass = documentSnapshot.getString("Password");
                            Password.setText(Pass);
                            singUserIn();

                        }
                        if(!documentSnapshot.exists())
                        {
                            Toast.makeText(LoginPage.this,"User not found, please try again",Toast.LENGTH_SHORT).show();


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginPage.this,"User not found, please try again",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void singUserIn()
    {
       final  String user = Username.getText().toString().trim();
       final  String pass = Password.getText().toString().trim();

       dialog.setMessage("Logging in");
       dialog.show();
        login.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent(getApplicationContext(), Sceduler.class));
                        }
                    }
                });

    }

    public void OnClick(View v)
    {


        if (v == Register)
        {
            Intent Loginscreen = new Intent(this, SignUp.class);
            startActivity(Loginscreen);
        }
        if(v == LoginButton)
        {
          signInUser();
        }
    }



}
