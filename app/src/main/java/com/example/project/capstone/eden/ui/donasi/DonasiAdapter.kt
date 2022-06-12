package com.example.project.capstone.eden.ui.donasi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.capstone.eden.R

class DonasiAdapter(
    private val context: Context,
    private val donasiList: ArrayList<DonasiModel>,
    private val onDonasiClickListener: OnDonasiClickListener): RecyclerView.Adapter<DonasiAdapter.DonasiViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DonasiAdapter.DonasiViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_donasi, parent, false)
        return DonasiViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DonasiAdapter.DonasiViewHolder, position: Int) {
        val donasi: DonasiModel = donasiList[position]

        holder.jumlahDonasi.text = donasi.amount
        Glide.with(context)
            .load(donasi.photoUrl)
            .centerCrop()
            .into(holder.fotoDonasi)
        holder.namaOrganisasi.text = donasi.organizationName
        holder.namaDonasi.text = donasi.title

        holder.itemView.setOnClickListener {
            onDonasiClickListener.onDonasiItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return donasiList.size
    }

    //change to inner from public
    public class DonasiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val jumlahDonasi: TextView = itemView.findViewById(R.id.tv_jumlah)
        val fotoDonasi: ImageView = itemView.findViewById(R.id.iv_donasi)
        val namaOrganisasi: TextView = itemView.findViewById(R.id.tv_penggalang_dana)
        val namaDonasi: TextView = itemView.findViewById(R.id.tv_donasi)

    }
    interface OnDonasiClickListener {
        fun onDonasiItemClicked(position: Int)
    }
}