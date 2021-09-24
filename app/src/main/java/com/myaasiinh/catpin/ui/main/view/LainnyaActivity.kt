package com.myaasiinh.catpin.ui.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.myaasiinh.catpin.R
import com.myaasiinh.catpin.data.model.Catpin
import com.myaasiinh.catpin.ui.main.viewmodel.CatpinViewModel
import java.util.*
import kotlin.properties.Delegates

class LainnyaActivity : AppCompatActivity(R.layout.activity_lainnya) {

    //@SuppressLint("RestrictedApi")
    private lateinit var  getData:String
    private lateinit var catpinviewModel: CatpinViewModel
    private lateinit var date: Date
    private var getCharacter by Delegates.notNull<Long>()
    private lateinit var getDate:String
    private lateinit var currentDate : TextView
    private lateinit var character : TextView
    private lateinit var toolbar1 : MaterialToolbar
    private lateinit var note : EditText

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        note = findViewById(R.id.note)
        currentDate = findViewById(R.id.currentDate)
        character = findViewById(R.id.characters)
        toolbar1 = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar1)
        catpinviewModel= CatpinViewModel()
        val upArrow=ContextCompat.getDrawable(applicationContext, R.drawable.arrow)
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
        note.addTextChangedListener(textWatcher)
    }

    private val textWatcher= object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        @SuppressLint("SetTextI18n")
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            character.text= " | Characters "+s?.length.toString()

        }

    }

    private fun backToHomePage()
    {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    private fun getDate() {
        date=Calendar.getInstance().time
        currentDate.text=date.toString()
    }

    private fun saveDataIntoDatabase() {
        getData=note.text.toString()
        getDate =currentDate.text.toString()
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
                    catpinviewModel.insert(applicationContext,
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