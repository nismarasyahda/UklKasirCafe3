package com.example.cafeproject.table

import androidx.room.*

@Dao
interface TableDao{

    @Query("SELECT * FROM Meja")
    fun getAll():List<Meja>

    @Query("SELECT * FROM Meja WHERE tid IN(:MejaIds)")
    fun loadAllByIds(MejaIds: IntArray):List<Meja>

    @Insert
    fun insertAll(vararg Mejas: Meja)

    @Delete
    fun delete(Meja: Meja)

    @Query("SELECT * FROM Meja WHERE tid = :tid")
    fun get(tid: Int) : Meja

    @Update
    fun update(Meja: Meja)
}