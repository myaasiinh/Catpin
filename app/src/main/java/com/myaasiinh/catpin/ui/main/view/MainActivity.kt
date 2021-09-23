package com.myaasiinh.catpin.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.myaasiinh.catpin.R
import com.myaasiinh.catpin.databinding.ActivityDetailBinding
import com.myaasiinh.catpin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tambahkanmemo -> {
                    val intent = Intent(this, TambahkanmemoActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.rubahmodememo -> {
                    // Handle search icon press
                    true
                }
                R.id.tentangapp -> {
                    val intent = Intent(this, TentangAplikasiActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}