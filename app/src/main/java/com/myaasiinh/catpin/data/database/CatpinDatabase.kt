package com.myaasiinh.catpin.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myaasiinh.catpin.data.model.Catpin
import com.myaasiinh.catpin.data.dao.CatpinDao


@Database(entities = [Catpin::class],version = 2,exportSchema = false)
abstract class CatpinDatabase : RoomDatabase()
{
    abstract fun getDao(): CatpinDao

    companion object{
        private const val DATABASE_NAME="CatpinDatabase"

        @Volatile
        var instance:CatpinDatabase?=null

        fun getInstance(context: Context):CatpinDatabase?
        {
            if(instance == null)
            {
                synchronized(CatpinDatabase::class.java)
                {
                    if(instance == null)
                    {
                        instance= Room.databaseBuilder(context,CatpinDatabase::class.java,
                            DATABASE_NAME).build()
                    }
                }
            }

            return instance
        }
    }
}