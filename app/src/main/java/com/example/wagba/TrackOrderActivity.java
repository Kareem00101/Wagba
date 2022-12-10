package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.databinding.ActivityTrackOrderBinding;

public class TrackOrderActivity extends AppCompatActivity
{


    /*View Binding Variable*/
    private ActivityTrackOrderBinding binding;
    Button back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        /*Binding View*/
        binding = ActivityTrackOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*** Navigation Code ***/

        // # buttons
        back_btn = binding.trackOrderBackBtn;

        // # on click
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navigateToMainActivity();
            }
        });



        /*** End of Navigation Code***/




    } // end of onCreate


    /*** Supporting Functionalities ***/

    void navigateToMainActivity()
    {
        Intent intent = new Intent(TrackOrderActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    /*** End of Supporting Functionalities ***/


} // end of Class