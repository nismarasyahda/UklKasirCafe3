package com.example.cafeproject.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meja (
    @PrimaryKey(autoGenerate = true)
    val tid: Int? = null,
    val nomor : String
)