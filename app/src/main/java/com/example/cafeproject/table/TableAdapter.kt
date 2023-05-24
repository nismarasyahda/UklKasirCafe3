package com.example.cafeproject.table

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeproject.R


class TableAdapter (var list: List<Meja>) : RecyclerView.Adapter<TableAdapter.ViewHolder>() {
private lateinit var dialog : Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var nomormeja : TextView


        init {
                nomormeja = view.findViewById(R.id.txtTableNumb)


            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view  = LayoutInflater.from(parent.context).inflate(R.layout.table_item, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nomormeja.text = list[position].nomor

    }

    override fun getItemCount(): Int {
        return list.size
    }
}