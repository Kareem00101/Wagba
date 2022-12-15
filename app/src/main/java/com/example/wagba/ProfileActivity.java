package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity
{

    /*View Binding Variable*/
    private ActivityProfileBinding binding;
    Button to_home_btn;
    Button update_btn;
    EditText email_et;
    EditText phone_et;
    EditText username_et;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*Main Code Starts Here*/

        //

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

        // # Update Button
        update_btn = binding.profileUpdateBtn;
        update_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!checkEmpty())
                {
                    updateProfileData();
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

        // # Update Data


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