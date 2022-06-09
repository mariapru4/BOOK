package com.example.bookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    private  static  int SPLASH_SCREEN = 3000;
    //Variables
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();


        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim =AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);

        image.setAnimation(topAnim);
        logo.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               checkUser();

            }
        },SPLASH_SCREEN);

    }

    private void checkUser() {

        //get current user, if logged in
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            //user not logged in
            //start main screen
            startActivity(new Intent(SplashActivity.this,DashboardAdminActivity.class));
            finish();


        }else{
            //user logged in check user type, same as done in login screen
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
            ref.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    //get user type
                    String userType = ""+snapshot.child("userType").getValue();
                    //check user type
                    if(userType.equals("user")){
                        //this is simple user, open  user dashboard
                        startActivity(new Intent(SplashActivity.this,DashboardUserActivity.class));
                        finish();
                    }else{
                        //this is admin user, open admin dashboard
                        startActivity(new Intent(SplashActivity.this,DashboardAdminActivity.class));
                        finish();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }
}