package com.example.belarusbankinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterBank(val array: MutableList<Bank>, val activity: MainActivity) : RecyclerView.Adapter<AdapterBank.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBank.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.activity_kurs_info, parent, false)
        val holder = ViewHolder(itemView)
        return holder
    }

    override fun onBindViewHolder(holder: AdapterBank.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.text1).text = array[position].USD_in
        holder.itemView.findViewById<TextView>(R.id.text2).text = array[position].USD_out
        holder.itemView.findViewById<TextView>(R.id.text3).text = array[position].EUR_in
        holder.itemView.findViewById<TextView>(R.id.text4).text = array[position].EUR_out
        holder.itemView.findViewById<TextView>(R.id.text5).text = array[position].filials_text
    }

    override fun getItemCount(): Int {
        return array.size
    }

}
