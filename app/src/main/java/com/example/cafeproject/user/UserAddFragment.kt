package com.example.cafeproject.user

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cafeproject.R

class UserAddFragment : AppCompatActivity() {

    private lateinit var namauser : EditText
    private lateinit var email : EditText
    private lateinit var phone : EditText
    private lateinit var password : EditText
    private lateinit var btnSave : Button

    private lateinit var database : UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_add_fragment)

        namauser = findViewById(R.id.edtName)
        email = findViewById(R.id.edtEmail)
        phone = findViewById(R.id.edtTelp)
        password = findViewById(R.id.edtPassword)
        btnSave = findViewById(R.id.btnSave)

        database = UserDatabase.getInstance(applicationContext)

        val intent = intent.extras
        if(intent != null){
            val id = intent.getInt("id", 0)
            var user = database.UserDao().get(id)

            namauser.setText(user.nama)
            email.setText(user.email)
            phone.setText(user.telp)
            password.setText(user.password)

        }

        btnSave.setOnClickListener {
            if (namauser.text.isNotEmpty() && email.text.isNotEmpty() && phone.text.isNotEmpty() && password.text.isNotEmpty()) {
                if (intent!= null){
                    //coding ubah data
                    database.UserDao().update(
                        User(
                            intent.getInt("id", 0),
                            namauser.text.toString(),
                            email.text.toString(),
                            phone.text.toString(),
                            password.text.toString()

                        )

                    )
                }else {
                    //coding tambah data
                    database.UserDao().insertAll(
                        User(
                            null,
                            namauser.text.toString(),
                            email.text.toString(),
                            phone.text.toString(),
                            password.text.toString()

                        )
                    )
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Silahkan isi semua data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}