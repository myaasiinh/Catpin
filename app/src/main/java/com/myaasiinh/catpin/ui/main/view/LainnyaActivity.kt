package com.myaasiinh.catpin.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codingwithjks.notepad.ui.Model.Catpin
import com.google.android.material.appbar.MaterialToolbar
import com.myaasiinh.catpin.R
import com.myaasiinh.catpin.databinding.ActivityLainnyaBinding
import com.myaasiinh.catpin.ui.main.viewmodel.CatpinViewModel
import java.util.*
import kotlin.properties.Delegates

class LainnyaActivity : AppCompatActivity() {

    //@SuppressLint("RestrictedApi")
    private lateinit var  getData:String
    private lateinit var noteViewModel: CatpinViewModel
    private lateinit var date: Date
    private var getCharacter by Delegates.notNull<Long>()
    private lateinit var getDate:String
    private lateinit var binding : ActivityLainnyaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLainnyaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar1 = findViewById<MaterialToolbar>(R.id.toolbar1)
        setSupportActionBar(toolbar1)
        noteViewModel= CatpinViewModel()
        val upArrow=resources.getDrawable(R.drawable.arrow)

        getDate()
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
            setHomeAsUpIndicator(upArrow)
        }
        toolbar1.setNavigationOnClickListener {
            backToHomePage()
        }
        binding.note.addTextChangedListener(textWatcher)
    }

    private val textWatcher= object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            binding.characters.text= " | Characters "+s?.length.toString()

        }

    }

    private fun backToHomePage()
    {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun getDate() {
        date=Calendar.getInstance().time
        binding.currentDate.text=date.toString()
    }

    private fun saveDataIntoDatabase() {
        getData=binding.note.text.toString()
        getDate =binding.currentDate.text.toString()
        getCharacter= getData.trim().length.toLong()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.detailmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.save-> {
                saveDataIntoDatabase()
                if(getData.isNotEmpty())
                {
                    noteViewModel.insert(applicationContext,
                        Catpin(getData, getDate, getCharacter)
                    )
                    backToHomePage()
                }
                else{
                    Toast.makeText(applicationContext,"type any character", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }



}