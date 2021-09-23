package com.myaasiinh.catpin.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingwithjks.notepad.ui.Model.Catpin
import com.myaasiinh.catpin.R
import com.myaasiinh.catpin.utils.listener.Listener


class CatpinAdapter(private val context: Context, private var catpinList:ArrayList<Catpin>, private val listener: Listener) : RecyclerView.Adapter<CatpinAdapter.CatpinViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatpinViewHolder {
        return CatpinViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
    }

    override fun getItemCount(): Int =catpinList.size

    override fun onBindViewHolder(holder: CatpinViewHolder, position: Int) {
        val note=catpinList[position]
        holder.data.text=note.data
        holder.date.text=note.date
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(catpinList: ArrayList<Catpin>)
    {
        this.catpinList=catpinList
        notifyDataSetChanged()
    }

    inner class CatpinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val data:TextView=itemView.findViewById(R.id.data)
        val date:TextView=itemView.findViewById(R.id.date)

        init {
            itemView.setOnClickListener {
                listener.onClickListener(adapterPosition)
            }
        }
    }
}