package com.example.cafeproject.menu

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

class MenuFragment : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var fab : FloatingActionButton

    private var list = mutableListOf<Menu>()
    private var list2 = mutableListOf<Menu>()


    private lateinit var database : MenuDatabase
    private lateinit var adapter : MenuAdapter
    private lateinit var adapter2 : MenuAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_fragment)

        recyclerView = findViewById(R.id.recyclerMakanan)
        recyclerView2 = findViewById(R.id.recyclerMinuman)

        fab = findViewById(R.id.fabAddMenu)

        database = MenuDatabase.getInstance(applicationContext)
        adapter = MenuAdapter(list)
        adapter2 = MenuAdapter(list2)

        adapter.setDialog(object : MenuAdapter.Dialog{

            override fun onClick(position: Int) {
                //membuat dialog view
                val dialog = AlertDialog.Builder(this@MenuFragment)
                dialog.setTitle(list[position].nama_menu)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{ dialog, which ->
                    if (which == 0){
                        //coding ubah
                        val intent = Intent(this@MenuFragment, MenuAddFragment::class.java)
                        intent.putExtra("id", list[position].mid)
                        startActivity(intent)
                        //coding hapus
                    } else if (which == 1){
                        database.MenuDao().delete(list[position])
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
        adapter2.setDialog(object : MenuAdapter.Dialog{
            override fun onClick(position: Int) {
                //membuat dialog view 2
                val dialog2 = AlertDialog.Builder(this@MenuFragment)
                dialog2.setTitle(list2[position].nama_menu)
                dialog2.setItems(R.array.items_option, DialogInterface.OnClickListener { dialog2, which ->
                    if (which == 0) {
                        //coding ubah
                        val intent2 = Intent(this@MenuFragment, MenuAddFragment::class.java)
                        intent2.putExtra("id", list2[position].mid)
                        startActivity(intent2)
                        //coding hapus
                    } else if (which == 1) {
                        database.MenuDao().delete(list2[position])
                        getData()
                        //coding batal
                    } else {
                        dialog2.dismiss()
                    }
                })
                //menampilkan dialog
                val dialogView2 = dialog2.create()
                dialogView2.show()

            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, RecyclerView.VERTICAL))

        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerView2.addItemDecoration(DividerItemDecoration(applicationContext, RecyclerView.VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this, MenuAddFragment::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list2.clear()
//        list.addAll(database.MenuDao().getAll())
        list.addAll(database.MenuDao().getMenuFilterJenis("Makanan"))
        list2.addAll(database.MenuDao().getMenuFilterJenis("Minuman"))
        adapter.notifyDataSetChanged()
        adapter2.notifyDataSetChanged()
    }
}
