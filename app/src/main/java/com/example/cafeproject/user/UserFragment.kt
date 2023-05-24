package com.example.cafeproject.user

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.cafeproject.MainActivity
import com.example.cafeproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserFragment : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab : FloatingActionButton
    private var list = mutableListOf<User>()

    private lateinit var database : UserDatabase
    private lateinit var adapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_fragment)

        recyclerView = findViewById(R.id.listUser)
        fab = findViewById(R.id.fabAddUser)

        database = UserDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog{

            override fun onClick(position: Int) {
                //membuat dialog view
                val dialog = AlertDialog.Builder(this@UserFragment)
                dialog.setTitle(list[position].nama)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{ dialog, which ->
                    if (which == 0){
                        //coding ubah
                        val intent = Intent(this@UserFragment, UserAddFragment::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                        //coding hapus
                    } else if (which == 1){
                        database.UserDao().delete(list[position])
                        getData()
                        //coding batal
                    } else{
                        dialog.dismiss()
                    }
                })
                //menampilkan dialog
                val dialogView = dialog.create()
                dialogView.show()

            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this, UserAddFragment::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.UserDao().getAll())
        adapter.notifyDataSetChanged()
    }
}
