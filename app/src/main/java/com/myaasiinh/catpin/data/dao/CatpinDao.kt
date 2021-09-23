package com.myaasiinh.catpin.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codingwithjks.notepad.ui.Model.Catpin

@Dao
interface CatpinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catpin: Catpin)

    @Query("SELECT * FROM catpin ORDER BY id DESC")
    fun getCardsData(): LiveData<List<Catpin>>

    @Update
    suspend fun update (catpin: Catpin)

    @Query("SELECT * FROM catpin WHERE data LIKE :data")
    fun search(data:String): LiveData<List<Catpin>>

    @Delete
    suspend fun delete(catpin: Catpin)

}