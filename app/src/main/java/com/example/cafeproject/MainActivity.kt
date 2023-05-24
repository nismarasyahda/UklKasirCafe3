package com.example.cafeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.cafeproject.menu.MenuFragment
import com.example.cafeproject.table.TableFragment
import com.example.cafeproject.user.UserAddFragment
import com.example.cafeproject.user.UserFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val idUser = findViewById<CardView>(R.id.user_id)
//        val idTable = findViewById<CardView>(R.id.table_id)
//
//        idUser.setOnClickListener {
//            val intent = Intent(this@MainActivity, UserFragment::class.java)
//            startActivity(intent)
//        }
//        idTable.setOnClickListener {
//            val moveIntent = Intent(this@MainActivity,TableFragment::class.java)
//            startActivity(moveIntent)
//        }
//    }
//}


        val btnMoveActivity: CardView = findViewById(R.id.user_id)
        btnMoveActivity.setOnClickListener(this)

        val btnPindahActivity: CardView = findViewById(R.id.table_id)
        btnPindahActivity.setOnClickListener(this)

        val btnMenuActivity: CardView = findViewById(R.id.menu_id)
        btnMenuActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.user_id -> {
                val moveIntent1 = Intent(this@MainActivity, UserFragment::class.java)
                startActivity(moveIntent1)
            }
            R.id.table_id -> {
                val moveIntent2 = Intent(this@MainActivity, TableFragment::class.java)
                startActivity(moveIntent2)
            }
            R.id.menu_id -> {
                val moveIntent3 = Intent(this@MainActivity, MenuFragment::class.java)
                startActivity(moveIntent3)
            }
        }
    }

}




