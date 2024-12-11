package com.example.roomdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.database.daftarBelanja

class HistoryAdapter(
    private var historyList: List<daftarBelanja>
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.tvItemBarang.text = item.item
        holder.tvjumlahBarang.text = item.jumlah
        holder.tvTanggal.text = item.tanggal
        holder.btnEdit.visibility = View.GONE
        holder.btnDelete.visibility = View.GONE
        holder.btnDone.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun updateData(newData: List<daftarBelanja>) {
        historyList = newData
        notifyDataSetChanged()
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemBarang: TextView = itemView.findViewById(R.id.tvItemBarang)
        val tvjumlahBarang: TextView = itemView.findViewById(R.id.tvjumlahBarang)
        val tvTanggal: TextView = itemView.findViewById(R.id.tvTanggal)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
        val btnDone: ImageButton = itemView.findViewById(R.id.btnDone)
    }
}
