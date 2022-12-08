package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wagba.databinding.ActivitySignUpBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity
{


    /* Used Variables */

    // For Google Authentication
    private GoogleSignInClient client;


    // For Firebase
    FirebaseAuth auth;
    FirebaseDatabase database;


    // For View Binding
    private ActivitySignUpBinding binding;
    //--
    TextView to_signin;
    //--
    Button signup_btn;
    EditText user_mail;
    EditText user_passwd;
    EditText user_cpasswd;
    //--


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*Firebase*/
        auth = FirebaseAuth.getInstance();


        /* To Go To Sign In Page Functionality */
        to_signin = binding.linkToLogin;
        to_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        /** Registration Functionality **/

        /* Required Binding */
        signup_btn = binding.btnSignUp;
        user_mail = binding.signupMailInput;
        user_passwd = binding.signupPasswdInput;
        user_cpasswd = binding.signupCpasswdInput;

        signup_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                boolean mail_check = checkMail(user_mail.getText().toString());
                boolean password_check = checkMatchingPasswords(user_passwd.getText().toString(), user_cpasswd.getText().toString());
                if(password_check == true && mail_check == true)
                {
                    createUser(user_mail.getText().toString(), user_passwd.getText().toString());
                }
            }
        });


        /** End of Registration Functionality **/

    } // end of onCreate


    /** Registration Supporting Functions **/

    private boolean checkMail(String user_mail_p)
    {
        if (TextUtils.isEmpty(user_mail_p))
        {
            user_mail.setError("email cannot be empty");
            user_mail.requestFocus();
            return false;
        }
        else if(!(user_mail_p.contains("@eng.asu.edu.eg")))
        {
            user_mail.setError("use an appropriate student mail");
            user_mail.requestFocus();
            return false;
        }
        else
        {
            return true;
        }
    }


    private boolean checkMatchingPasswords(String user_passwd_p, String user_cpasswd_p)
    {
        if(TextUtils.isEmpty(user_passwd_p) || TextUtils.isEmpty(user_cpasswd_p))
        {
            Toast.makeText(getApplicationContext(), "password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(user_cpasswd_p.equals(user_cpasswd_p))
        {
            return true;
        }
        else
        {
            Toast.makeText(getApplicationContext(), "non matching passwords", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void createUser(String user_mail_p, String user_passwd_p)
    {

        auth.createUserWithEmailAndPassword(user_mail_p, user_passwd_p)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                navigateToSignInActivity();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                });
    }


    /** End of Registration Supporting Functions **/



    /** Extra Supporting Functions **/

    void navigateToSignInActivity()
    {
        finish();
        Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
        startActivity(intent);
    }


    /** End of Extra Supporting Functions **/

} // end of class