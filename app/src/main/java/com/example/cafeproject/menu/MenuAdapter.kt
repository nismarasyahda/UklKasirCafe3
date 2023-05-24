package com.example.cafeproject.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeproject.R

class MenuAdapter (var list: List<Menu>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private lateinit var dialog : Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var namamenu : TextView
        var harga : TextView


        init {
            namamenu = view.findViewById(R.id.txtMenuNama)
            harga = view.findViewById(R.id.txtMenuHarga)


            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view  = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namamenu.text = list[position].nama_menu
        holder.harga.text = list[position].harga_menu

    }

    override fun getItemCount(): Int {
        return list.size
    }
}