package com.example.roomdb

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.database.daftarBelanja
import com.example.roomdb.database.daftarBelanjaDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class adapterDaftar(private val daftarBelanja: MutableList<daftarBelanja>) :
    RecyclerView.Adapter<adapterDaftar.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun delData(dtBelanja: daftarBelanja)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _tvItemBarang: TextView = itemView.findViewById(R.id.tvItemBarang)
        var _tvjumlahBarang: TextView = itemView.findViewById(R.id.tvjumlahBarang)
        var _tvTanggal: TextView = itemView.findViewById(R.id.tvTanggal)
        var _btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        var _btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
        var _btnDone: ImageButton = itemView.findViewById(R.id.btnDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterDaftar.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: adapterDaftar.ListViewHolder, position: Int) {
        val daftar = daftarBelanja[position]

        holder._tvTanggal.text = daftar.tanggal
        holder._tvItemBarang.text = daftar.item
        holder._tvjumlahBarang.text = daftar.jumlah

        holder._btnEdit.setOnClickListener {
            val intent = Intent(it.context, TambahDaftar::class.java)
            intent.putExtra("id", daftar.id)
            intent.putExtra("addEdit", 1)
            it.context.startActivity(intent)
        }

        holder._btnDelete.setOnClickListener {
            onItemClickCallback.delData(daftar)
        }

        holder._btnDone.setOnClickListener {
            val context = it.context
            val db = daftarBelanjaDB.getDatabase(context)

            CoroutineScope(Dispatchers.IO).launch {
                db.fundaftarBelanjaDAO().updateStatus(daftar.id)
            }

            daftarBelanja.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return daftarBelanja.size
    }

    fun isiData(daftar: List<daftarBelanja>) {
        daftarBelanja.clear()
        daftarBelanja.addAll(daftar)
        notifyDataSetChanged()
    }
}
