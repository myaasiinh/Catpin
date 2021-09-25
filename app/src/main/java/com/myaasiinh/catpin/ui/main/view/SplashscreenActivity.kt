package com.myaasiinh.catpin.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.myaasiinh.catpin.R

class SplashscreenActivity : AppCompatActivity(R.layout.splashscreen) {

    /*

    var splahscreen : ImageView? = null

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        /*
        splahscreen = findViewById(R.id.catpin_logo_splashscreen)


         */

        //delay
        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }

}