package com.example.cafeproject.menu

import androidx.room.*

@Dao
interface MenuDao{

    @Query("SELECT * FROM menu")
    fun getAll():List<Menu>

    @Query("SELECT * FROM menu WHERE mid IN(:userIds)")
    fun loadAllByIds(userIds: IntArray):List<Menu>

    @Insert
    fun insertAll(vararg menus: Menu)

    @Delete
    fun delete(menu: Menu)

    @Query("SELECT * FROM menu WHERE mid = :mid")
    fun get(mid: Int) : Menu

    @Update
    fun update(menu: Menu)

    @Query("SELECT * FROM Menu WHERE jenis = :jenisMenu")
    fun getMenuFilterJenis(jenisMenu: String): List<Menu>

}