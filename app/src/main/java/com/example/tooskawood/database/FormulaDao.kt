package com.example.tooskawood.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tooskawood.Formula

@Dao
interface FormulaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(formula: Formula)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(formula:Formula)

    @Query("SELECT * FROM Formula" )
    fun getFormulaList():List<Formula>


    @Query("SELECT COUNT(*) FROM Formula" )
    fun getCountFormula():Int

    @Query("SELECT COUNT(*) FROM Formula" )
    fun getCountFormulaLiveData(): LiveData<Int>

    @Query("SELECT id FROM Formula WHERE code = :code")
    fun search(code:String):Int

    @Query("SELECT * FROM Formula WHERE id IN(:id)")
    fun getFormula(id:Int): Formula

    @Delete
    fun delete(formula:Formula)

    @Query("DELETE FROM Formula WHERE id=(:id)")
    fun deleteById(id:Int)

}