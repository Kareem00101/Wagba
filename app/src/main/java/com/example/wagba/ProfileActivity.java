package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity
{

    /*View Binding Variable*/
    private ActivityProfileBinding binding;
    Button to_home_btn;
    Button update_btn;
    EditText email_et;
    EditText phone_et;
    EditText username_et;
    TextView email_tv;

    /* Room Database */
    MyDatabase db;

    // For Firebase
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Database
        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "UserTable").allowMainThreadQueries().build();

        /*Main Code Starts Here*/

        /* Retrieve User Data */
        loadProfileData();
        updateMailTextView();


        /* Navigation to Home */

        to_home_btn = binding.btnBackToHome;
        to_home_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navigateToMainActivity();
            }
        });

        /* End of Navigation Home */

        /*** Update Profile Data Code */

        // # Binding Views

        // # User Data Views

        username_et = binding.profileUsernameEt;
        phone_et = binding.profilePhoneNumberEt;
        email_et = binding.profileMailEt;

        // # Prevent User from Changing Mail
        email_et.setEnabled(false);

        // # Update Button
        update_btn = binding.profileUpdateBtn;
        update_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkEmpty())
                {
                    updateProfileData();
                    updateProfileElements();
                    Toast.makeText(ProfileActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ProfileActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /**** End of Update Profile Data Code */



    } // End of onCreate




    /*** Profile Update Functionality ***/

    // # Check if empty
    private boolean checkEmpty()
    {
        if (username_et.getText().toString().isEmpty())
        {
            username_et.setError("Username is required");
            username_et.requestFocus();
            return false;
        }

        if (phone_et.getText().toString().isEmpty())
        {
            phone_et.setError("Phone Number is required");
            phone_et.requestFocus();
            return false;
        }

        if (email_et.getText().toString().isEmpty())
        {
            email_et.setError("Email is required");
            email_et.requestFocus();
            return false;
        }
        return true;
    }

    // # Update Profile Data Into Database
    private void updateProfileData()
    {
        // # Get Data from Views
        String user_name = username_et.getText().toString();
        String user_phone = phone_et.getText().toString();
        String user_email = email_et.getText().toString();

        //# Manual Update
        UserTable user = db.userDao().getUserByMail(user_email);
        user.set_user_name(user_name);
        user.set_user_phone(user_phone);

        // # Update Data
        db.userDao().UpdateUser(user);

    }


    // # Update Profile Elements
    private void updateProfileElements()
    {
        // # Get Data from Database
        loadProfileData();

    }


    // Load User Profile Upon Opening The Page
    private void loadProfileData()
    {
        // # Get Current User Mail
        auth = FirebaseAuth.getInstance();
        String user_mail = auth.getCurrentUser().getEmail();

        // # Get Data from Database
        UserTable user = db.userDao().getUserByMail(user_mail);
        if(user != null)
        {
            // # Update Data
            binding.profileMailEt.setText(user_mail);
            binding.profileUsernameEt.setText(user.user_name);
            binding.profilePhoneNumberEt.setText(user.user_phone);
            return;
        }
        else
        {
            createUserEntry();
        }

    }


    // # Create User Entry In Database If Never Created Before
    public void createUserEntry()
    {
        // # Get Current User Mail
        auth = FirebaseAuth.getInstance();
        String user_mail = auth.getCurrentUser().getEmail();

        // # Create User Entry
        UserTable user_to_create;
        user_to_create = new UserTable("", "", user_mail, "profile_picture");
        db.userDao().InsertUser(user_to_create);

        // # Load Updates
        loadProfileData();
        return;
    }



    // # Update User Mail Below Profile Picture
    private void updateMailTextView()
    {
        // # Get Current User Mail
        auth = FirebaseAuth.getInstance();
        String user_mail = auth.getCurrentUser().getEmail();

        // # Update Mail TextView
        binding.profileMail.setText(user_mail);
    }



    /*** End of Profile Update Supporting Functionalities ***/



    /*** Supporting Functionalities ***/

    // # Navigate to Main Intent
    void navigateToMainActivity()
    {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /*** End of Supporting Functionalities ***/



} // End of Class