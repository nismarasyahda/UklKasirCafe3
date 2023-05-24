package com.example.cafeproject.menu

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Menu (
    @PrimaryKey(autoGenerate = true)
    val mid: Int? = null,
    val nama_menu : String,
    val harga_menu : String,
    val jenis : String
)