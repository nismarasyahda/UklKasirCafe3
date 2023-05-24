package com.example.cafeproject.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val uid: Int? = null,
    val nama : String,
    val email : String,
    val telp: String,
    val password: String,
)