package me.aprizal.githubusers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import me.aprizal.githubusers.databinding.ActivitySplashScreenBinding;
import me.aprizal.githubusers.main.MainActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashScreenBinding binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread thread = new Thread(() -> {
            long splash = 1500;
            try {
                Thread.sleep(splash);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        });
        thread.start();
    }
}