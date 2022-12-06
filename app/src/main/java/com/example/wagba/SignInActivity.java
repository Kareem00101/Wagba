package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.wagba.Models.users;
import com.example.wagba.databinding.ActivitySignInBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    /* Used Variables */

    // For Google Authentication
    private GoogleSignInClient client;


    // For Firebase
    FirebaseAuth auth;
    FirebaseDatabase database;


    // For View Binding
    private ActivitySignInBinding binding;
    Button google_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*Disabling Night Mode*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);


        /*Binding View*/
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*Firebase*/
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://wagba-e4d23-default-rtdb.firebaseio.com/"); // probably not required rn



        /*** Integrating Google Sign In ***/

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        client = GoogleSignIn.getClient(this, options);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // # Google Sign In Button
        google_btn = binding.btnGoogleLogin;
        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        /*** End Of Integrating Google Sign In ***/

    }



    /*** Google Authentication Supporting Functions ***/

    // # Sign In Functionality
    void signIn(){
        Intent signInIntent = client.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    // # Sign In Result
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                auth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if(task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            users my_users = new users();
                            assert my_users != null;

                            // # for the database
                            my_users.setUser_id(user.getUid());
                            my_users.setUser_name(user.getDisplayName());
                            my_users.setProfile_picture(user.getPhotoUrl().toString());
                            // database.getReference().child("users").child(user.getUid()).setValue(my_users);

                            // # Finally Move to Main Activity Upon Success
                            navigateToMainActivity();
                        } else{
                            Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*** End Of Google Authentication Supporting Functions ***/



    /*** Extra Supporting Functions ***/

    // # Navigate to Main Intent
    void navigateToMainActivity(){
        finish();
        Intent intent = new Intent(SignInActivity.this,MainActivity.class);
        startActivity(intent);
    }

    /*** End of Extra Supporting Functions ***/


}