package com.myaasiinh.catpin.ui.main.viewmodel

import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myaasiinh.catpin.data.model.Catpin
import com.myaasiinh.catpin.data.repository.CatpinRepository


class CatpinViewModel : ViewModel() {

    fun insert(context: Context, catpin: Catpin)
    {
        CatpinRepository.insert(context,catpin)
    }

    fun getCardsData(context: Context): LiveData<List<Catpin>>?
    {
        return CatpinRepository.getCardData(context)
    }

    fun update(context: Context, catpin: Catpin)
    {
        CatpinRepository.update(context,catpin)
    }

    fun search(context: Context, data:String): LiveData<List<Catpin>>?
    {
        return CatpinRepository.search(context,data)
    }

    fun delete(context: Context, catpin: Catpin)
    {
        CatpinRepository.delete(context,catpin)
    }
}