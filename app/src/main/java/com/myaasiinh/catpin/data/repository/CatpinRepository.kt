package com.myaasiinh.catpin.data.repository

import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.codingwithjks.notepad.ui.Model.Catpin
import com.myaasiinh.catpin.data.database.CatpinDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CatpinRepository {

    companion object {
        private var catpindatabase: CatpinDatabase? = null

        private fun initialiseDB(context: Context): CatpinDatabase? {
            return CatpinDatabase.getInstance(context)
        }

        fun insert(context: Context, catpin: Catpin) {
            catpindatabase = initialiseDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                catpindatabase?.getDao()?.insert(catpin)
            }
        }

        fun getCardData(context: Context): LiveData<List<Catpin>>? {
            catpindatabase = initialiseDB(context)
            return catpindatabase?.getDao()?.getCardsData()
        }

        fun update(context: Context, catpin: Catpin) {
            catpindatabase = initialiseDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                catpindatabase?.getDao()?.update(catpin)
            }
        }

        fun search(
            context: Context,
            data: String
        ): LiveData<List<Catpin>>? {
            catpindatabase = initialiseDB(context)
            return catpindatabase?.getDao()?.search(data)
        }

        fun delete(context: Context, catpin: Catpin) {
            catpindatabase = initialiseDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                catpindatabase?.getDao()?.delete(catpin)
            }
        }


    }

}