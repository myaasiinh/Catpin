package com.myaasiinh.catpin.ui.main.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.myaasiinh.catpin.databinding.TentangAplikasiBinding


class TentangAplikasiActivity : AppCompatActivity() {

    private lateinit var binding : TentangAplikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TentangAplikasiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        /////write your code in here

    }

}

