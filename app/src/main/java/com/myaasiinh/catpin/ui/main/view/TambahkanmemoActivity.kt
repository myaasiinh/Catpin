package com.myaasiinh.catpin.ui.main.view


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.myaasiinh.catpin.R
import com.myaasiinh.catpin.data.model.Catpin
import com.myaasiinh.catpin.databinding.ActivityDetailBinding
import com.myaasiinh.catpin.ui.main.viewmodel.CatpinViewModel
import java.util.*


class TambahkanmemoActivity:AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    private lateinit var date: Date
    private lateinit var  getNote: Catpin
    private lateinit var catpinViewModel: CatpinViewModel
    private var binding : ActivityDetailBinding? = null

    /*
    private var upCharacter : TextView? = null
    private var updateDate : TextView? = null
    private var updateData : FloatingActionButton? = null
    private var updateNote : EditText? = null


     */

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        /*
        upCharacter = findViewById(R.id.updateCharacter)
        updateDate = findViewById(R.id.updateDate)
        updateData = findViewById(R.id.updateData)
        updateNote = findViewById(R.id.updateNote)


         */
        getDate()
        catpinViewModel= CatpinViewModel()
        binding?.updateCharacter?.text="| 0 characters"
        binding?.updateNote?.addTextChangedListener(textWatcher)

        val bundle:Bundle?=intent.extras
        if(bundle != null){
            getNote = bundle.getSerializable("catpin")!! as Catpin
        }
        loadNote(getNote)
        binding?.updateData?.setOnClickListener {
            updateNote()
        }
    }

    private fun loadNote(catpin: Catpin)
    {
        binding?.updateNote?.setText(catpin.data)
        binding?.updateCharacter?.text="${catpin.characters}"
    }

    private fun updateNote()
    {
        getNote.data=binding?.updateNote?.text.toString()
        getNote.date=binding?.updateDate?.text.toString()
        getNote.characters= binding?.updateNote?.text.toString().length.toLong()
        catpinViewModel.update(applicationContext,getNote)
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    private val textWatcher= object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        @SuppressLint("SetTextI18n")
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            binding?.updateCharacter?.text= " | Character "+s?.length.toString()

        }

    }

    private fun getDate() {
        date= Calendar.getInstance().time
        binding?.updateDate?.text =date.toString()
    }

}