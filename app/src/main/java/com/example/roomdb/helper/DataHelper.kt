package com.example.roomdb.helper

import android.content.Context
import androidx.room.Room
import com.example.roomdb.database.daftarBelanjaDB
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DataHelper {
    fun getCurrentDate() : String{
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
            Locale.getDefault()
        )
        val date = Date()
        return dateFormat.format(date)
    }

    fun getDatabase(context: Context): daftarBelanjaDB {
        return Room.databaseBuilder(
            context.applicationContext,
            daftarBelanjaDB::class.java,
            "daftarBelanjaDB"
        ).build()
    }
}