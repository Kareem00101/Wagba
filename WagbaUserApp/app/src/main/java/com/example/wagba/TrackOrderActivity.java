package com.example.wagba;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wagba.databinding.ActivityTrackOrderBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class TrackOrderActivity extends AppCompatActivity
{


    /*View Binding Variable*/
    private com.example.wagba.databinding.ActivityTrackOrderBinding binding;

    Button back_btn;
    StepView order_track_steps;

    TextView trackDeliveryGateTxt;
    TextView trackDeliveryPeriodTxt;
    TextView trackTotalPrice;

    /*Firebase*/
    private DatabaseReference ref;
    private FirebaseDatabase database;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        // ** Binding View * /
        binding = ActivityTrackOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // ** Setup Text
        setupTexts();


        /*** Navigation Code Start ***/

        // buttons
        back_btn = binding.trackOrderBackBtn;

        // on click
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        /*** Navigation Code End **/



        /*** Setting Up The Step View Code ***/


        // # step view
        order_track_steps = binding.trackLocationStepView;
        order_track_steps.getState().steps(new ArrayList<String>() {{
            add("Placed");
            add("Confirmed");
            add("Delivering");
            add("Delivered");
        }}).commit();

        /*** End of Setting Up Step View Code ***/


        /*** Handling Step View Updates ***/

        database = FirebaseDatabase.getInstance();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ref = database.getReference("orders/" + userID + "/" + getIntent().getStringExtra("orderID"));

        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.exists())
                {
                    String orderStatus = snapshot.child("orderStatus").getValue().toString();
                    if (orderStatus.equals("Placed"))
                    {
                        order_track_steps.go(0, true);
                    }
                    else if (orderStatus.equals("Confirmed"))
                    {
                        order_track_steps.go(1, true);
                    }
                    else if (orderStatus.equals("Delivering"))
                    {
                        order_track_steps.go(2, true);
                    }
                    else if (orderStatus.equals("Delivered"))
                    {
                        order_track_steps.go(3, true);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(TrackOrderActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        /*** End of Handling Step View Updates ***/




    } // end of onCreate



    /*** Supporting Functionalities ***/

    void setupTexts()
    {
        trackTotalPrice = binding.trackTotalPriceTxt;
        trackDeliveryGateTxt = binding.trackDeliveryGateTxt;
        trackDeliveryPeriodTxt = binding.trackDeliveryPeriodTxt;

        trackTotalPrice.setText(getIntent().getStringExtra("priceTxt"));
        trackDeliveryGateTxt.setText(getIntent().getStringExtra("gateTxt"));
        trackDeliveryPeriodTxt.setText(getIntent().getStringExtra("periodTxt"));
    }

    /*** End of Supporting Functionalities ***/



    /*** Lifecycle Methods ***/
    @Override
    protected void onStop()
    {
        super.onStop();
        finish();
    }
    /*** End of Lifecycle Methods ***/


} // end of Class