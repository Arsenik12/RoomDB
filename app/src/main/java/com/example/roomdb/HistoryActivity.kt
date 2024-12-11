package com.example.roomdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.adapter.HistoryAdapter
import com.example.roomdb.database.daftarBelanjaDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerView = findViewById<RecyclerView>(R.id.tvHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val db = daftarBelanjaDB.getDatabase(this)

        CoroutineScope(Dispatchers.IO).launch {
            val historyList = db.fundaftarBelanjaDAO().selectHistory()
            withContext(Dispatchers.Main) {
                adapter = HistoryAdapter(historyList)
                recyclerView.adapter = adapter
            }
        }
    }
}
