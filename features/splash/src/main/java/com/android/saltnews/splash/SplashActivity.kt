package com.android.saltnews.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.android.saltnews.navigation.Activities
import com.android.saltnews.navigation.startFeature

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable {
               run {
                   startFeature(Activities.MainActivity){}
                   finish()
               }
        } , 1500)
    }
}