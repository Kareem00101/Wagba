package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wagba.databinding.ActivityTrackOrderBinding;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class TrackOrderActivity extends AppCompatActivity
{


    /*View Binding Variable*/
    private com.example.wagba.databinding.ActivityTrackOrderBinding binding;
    Button back_btn;
    StepView order_track_steps;

    //
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
                onBackPressed();
            }
        });


        /*** End of Navigation Code ***/


        /*** Handling Step View Code ***/


        // # step view
        order_track_steps = binding.trackLocationStepView;
        order_track_steps.getState().steps(new ArrayList<String>() {{
            add("Placed");
            add("Confirmed");
            add("Delivering");
            add("Delivered");
        }}).commit();

        order_track_steps.go(1, true);


        /*** End of Handling Step View Code ***/



    } // end of onCreate


    /*** Supporting Functionalities ***/

    void navigateToMainActivity()
    {
        Intent intent = new Intent(TrackOrderActivity.this,MainActivity.class);
        startActivity(intent);
    }

    /*** End of Supporting Functionalities ***/


//    /*** Handle Back Button ***/
//    @Override
//    public void onBackPressed()
//    {
//        navigateToMainActivity();
//    }
//    /*** End of Handle Back Button ***/



    /*** Lifecycle Methods ***/
    @Override
    protected void onStop()
    {
        super.onStop();
        finish();
    }
    /*** End of Lifecycle Methods ***/


} // end of Class