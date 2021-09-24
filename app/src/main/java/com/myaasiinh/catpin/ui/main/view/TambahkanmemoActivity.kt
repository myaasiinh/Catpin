package com.myaasiinh.catpin.ui.main.view


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.myaasiinh.catpin.R
import com.myaasiinh.catpin.data.model.Catpin
import com.myaasiinh.catpin.ui.main.viewmodel.CatpinViewModel
import java.util.*


class TambahkanmemoActivity:AppCompatActivity(R.layout.activity_detail) {

    @SuppressLint("RestrictedApi")
    private lateinit var date: Date
    private lateinit var  getNote: Catpin
    private lateinit var catpinViewModel: CatpinViewModel
    private lateinit var upCharacter : TextView
    private lateinit var updateDate : TextView
    private lateinit var updateData : FloatingActionButton
    private lateinit var updateNote : EditText

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getDate()
        catpinViewModel= CatpinViewModel()
        upCharacter.text="| 0 characters"
        updateNote.addTextChangedListener(textWatcher)

        val bundle:Bundle?=intent.extras
        if(bundle != null){
            getNote = bundle.getSerializable("catpin")!! as Catpin
        }
        loadNote(getNote)
        updateData.setOnClickListener {
            updateNote()
        }
    }

    private fun loadNote(catpin: Catpin)
    {
        updateNote.setText(catpin.data)
        upCharacter.text="${catpin.characters}"
    }

    private fun updateNote()
    {
        getNote.data=updateNote.text.toString()
        getNote.date=updateDate.text.toString()
        getNote.characters= updateNote.text.toString().length.toLong()
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

            upCharacter.text= " | Character "+s?.length.toString()

        }

    }

    private fun getDate() {
        date= Calendar.getInstance().time
        updateDate.text =date.toString()
    }

}