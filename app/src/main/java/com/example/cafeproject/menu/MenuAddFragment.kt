package com.example.cafeproject.menu

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeproject.R

class MenuAddFragment : AppCompatActivity() {

    private lateinit var namamenu : EditText
    private lateinit var harga : EditText
    private lateinit var jenismenu: Spinner

    private lateinit var btnSave : Button

    private lateinit var database : MenuDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_add_fragment)

        init()
        setDataSpinner()
        val intent = intent.extras
        if(intent != null){
            val id = intent.getInt("id", 0)
            var menu = database.MenuDao().get(id)

            namamenu.setText(menu.nama_menu)
            harga.setText(menu.harga_menu)
        }

        btnSave.setOnClickListener {
            if (namamenu.text.isNotEmpty() && harga.text.isNotEmpty()) {
                if (intent!= null){
                    //coding ubah data
                    database.MenuDao().update(
                        Menu(
                            intent.getInt("id", 0),
                            namamenu.text.toString(),
                            harga.text.toString(),
                            jenismenu.selectedItem.toString()
                        )

                    )
                }else {
                    //coding tambah data
                    database.MenuDao().insertAll(
                        Menu(
                            null,
                            namamenu.text.toString(),
                            harga.text.toString(),
                            jenismenu.selectedItem.toString()
                        ))
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Silahkan isi semua menu", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    fun init(){
        namamenu = findViewById(R.id.edtProduk)
        harga = findViewById(R.id.edtHarga)
        jenismenu = findViewById(R.id.pilihTipe)

        btnSave = findViewById(R.id.btnSave)

        database = MenuDatabase.getInstance(applicationContext)
    }

    private fun setDataSpinner() {
        val adapter = ArrayAdapter.createFromResource(applicationContext,R.array.tipe,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jenismenu.adapter =adapter
    }

}