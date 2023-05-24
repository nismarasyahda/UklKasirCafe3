package com.example.cafeproject.table

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafeproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TableFragment : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab : FloatingActionButton
    private var list = mutableListOf<Meja>()

    private lateinit var database : TableDatabase
    private lateinit var adapter : TableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_fragment)

        recyclerView = findViewById(R.id.listTable)
        fab = findViewById(R.id.fabAddTable)

        database = TableDatabase.getInstance(applicationContext)
        adapter = TableAdapter(list)
        adapter.setDialog(object : TableAdapter.Dialog{

            override fun onClick(position: Int) {
                //membuat dialog view
                val dialog = AlertDialog.Builder(this@TableFragment)
                dialog.setTitle(list[position].nomor)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{ dialog, which ->
                    if (which == 0){
                        //coding ubah
                        val intent = Intent(this@TableFragment, TableAddFragment::class.java)
                        intent.putExtra("id", list[position].tid)
                        startActivity(intent)
                        //coding hapus
                    } else if (which == 1){
                        database.TableDao().delete(list[position])
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
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, RecyclerView.VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this, TableAddFragment::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.TableDao().getAll())
        adapter.notifyDataSetChanged()
    }
}
