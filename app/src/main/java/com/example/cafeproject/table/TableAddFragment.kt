package com.example.cafeproject.table

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeproject.R

class TableAddFragment : AppCompatActivity() {

    private lateinit var nomormeja : EditText
    private lateinit var btnSave : Button

    private lateinit var database : TableDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_add_fragment)

        nomormeja = findViewById(R.id.edtNumb)
        btnSave = findViewById(R.id.btnSave)

        database = TableDatabase.getInstance(applicationContext)

        val intent = intent.extras
        if(intent != null){
            val id = intent.getInt("id", 0)
            var table = database.TableDao().get(id)

            nomormeja.setText(table.nomor)

        }

        btnSave.setOnClickListener {
            if (nomormeja.text.isNotEmpty()) {
                if (intent!= null){
                    //coding ubah data
                    database.TableDao().update(
                        Meja(
                            intent.getInt("id", 0),
                            nomormeja.text.toString()
                        )

                    )
                }else {
                    //coding tambah data
                    database.TableDao().insertAll(
                        Meja(
                            null,
                            nomormeja.text.toString()

                        )
                    )
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Silahkan isi nomor meja", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}