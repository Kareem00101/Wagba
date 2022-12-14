package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.ActivityMainBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements RecyclerViewInterface
{

    /*Firebase*/
    private DatabaseReference ref;
    private FirebaseDatabase database;

    /*View Binding Variable*/
    private ActivityMainBinding binding;

    Button nav_profile;
    Button nav_orders;
    Button logout_btn;
    Button nav_cart;


    RecyclerView restaurantRecyclerView;
    RestaurantAdapter restaurantAdapter;

    public static ArrayList<RestaurantModel> restaurantList;
    public static ArrayList<DishModel> dishList = new ArrayList<>();

    //
    public ShimmerFrameLayout shimmerFrameLayout;


    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        /*Disabling Night Mode*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        /*Reloading Saved Instance State*/
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*Main Code Starts Here*/

        /*** Recycler View Code ***/

        // setup the RecyclerView
        setupRecyclerView();
        // populate the list
        fillRecyclerView();

        /*** End of Recycler View Code ***/



        /*** Navigation Bar Code ***/

        // Navigation Bar Buttons
        setupButtons();

        // # On Click
        nav_profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                navigateToProfileActivity();
            }
        });

        nav_orders.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                navigateToOrdersActivity();
            }
        });

        /* Logout Functionality */

        logout_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                logoutUser();
            }
        });

        /* End of Logout Functionality */


        /*** End of Navigation Bar Code ***/


    } // end of onCreate



    /*** Recycler View Supporting Functionalities ***/

    protected void fillRecyclerView()
    {
        /*** Getting Recycler Data ***/

        // Get a reference to our posts
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("restaurants");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot snapshot)
            {
                int i = 0;
                for (DataSnapshot sp: snapshot.getChildren())
                {
                    for (DataSnapshot so : sp.getChildren())
                    {

                        for(DataSnapshot s : so.getChildren())
                        {
                            HashMap dish = (HashMap) s.getValue();
                            String dish_price = dish.get("price").toString();
                            Double dish_price_double = Double.parseDouble(dish_price.replace("EGP", "").trim());
                            dishList.add(new DishModel(dish.get("name").toString(), dish_price_double, dish.get("image").toString()));
                        }
                    }

                    HashMap rest = (HashMap) sp.getValue();
                    restaurantList.add(new RestaurantModel(rest.get("name").toString(), Integer.parseInt(rest.get("rating").toString()), rest.get("image").toString(),  dishList));
                    restaurantList.get(i).setRestaurantDishes(dishList);
                    Log.d("recyclerDebug = dish", restaurantList.get(i).toString());
                    dishList = new ArrayList<>();
                    Log.d("recyclerDebug = dish", restaurantList.get(i).toString());
                    i++;

                }

                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                restaurantRecyclerView.setVisibility(View.VISIBLE);
                restaurantAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                System.out.println("The read failed: " + databaseError.getCode());
                Toast.makeText(MainActivity.this, "Error: Database Error" + databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        /*** End of Data Population Code ***/


    } // end of fillRecyclerView


    // Initialize the RecyclerView
    protected void setupRecyclerView()
    {
        //
        shimmerFrameLayout = binding.shimmerViewContainer;
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();

        //
        restaurantList = new ArrayList<>();
        restaurantAdapter = new RestaurantAdapter(restaurantList, this, this);

        //
        restaurantRecyclerView = binding.mainRecyclerView;
        restaurantRecyclerView.setAdapter(restaurantAdapter);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    /*** End of Recycler View Supporting Functionalities ***/




    /*** Logout Supporting Functionalities ***/

    protected void logoutUser()
    {

        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    public void onComplete(Task<Void> task)
                    {
                        // user is now signed out
                        startActivity(new Intent(MainActivity.this, SignInActivity.class));
                        finish();
                    }
                });

    }

    /*** End of LogoutSupporting Functionalities ***/




    /*** Navigation Supporting Functionalities ***/

    protected void setupButtons()
    {
        // # Required Buttons
        nav_profile = binding.navProfileBtn;
        logout_btn = binding.logoutButton;
        nav_orders = binding.navOrderBtn;
        //nav_cart = binding.navCartBtn;
    }

    void navigateToProfileActivity()
    {
        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
        startActivity(intent);
    }

    void navigateToCartActivity()
    {
        Intent intent = new Intent(MainActivity.this,CartActivity.class);
        startActivity(intent);
    }

    void navigateToOrdersActivity()
    {
        Intent intent = new Intent(MainActivity.this,OrdersActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRestaurantClick(RestaurantModel restaurantModel)
    {
        Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
        intent.putExtra("restaurant", restaurantModel);
        startActivity(intent);
    }

    /*** End of Navigation Supporting Functionalities ***/




    /** Life cycle methods **/

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    /** End of Life cycle methods **/




} // end of class