package com.myaasiinh.catpin.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "catpin")
data class Catpin (
    var data:String,
    var date:String,
    var characters:Long) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}