package com.example.cafeproject.user


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeproject.R

class UserAdapter(var list: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var dialog : Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var namauser : TextView
        var email : TextView
        var phone : TextView


        init {
            namauser = view.findViewById(R.id.txtUserName)
            email = view.findViewById(R.id.txtUserEmail)
            phone = view.findViewById(R.id.txtUserTelp)


            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view  = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namauser.text = list[position].nama
        holder.email.text = list[position].email
        holder.phone.text = list[position].telp

    }

    override fun getItemCount(): Int {
        return list.size
    }
}