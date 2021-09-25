package com.myaasiinh.catpin.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.myaasiinh.catpin.databinding.SplashscreenBinding

class SplashscreenActivity : AppCompatActivity() {

    /*

    var splahscreen : ImageView? = null

     */

    private lateinit var binding : SplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashscreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
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