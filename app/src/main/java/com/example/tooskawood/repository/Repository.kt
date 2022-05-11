package com.example.tooskawood.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.tooskawood.Formula
import com.example.tooskawood.database.MyAppDatabase
import com.example.tooskawood.database.FormulaDao

object Repository {
    lateinit var db: MyAppDatabase
    lateinit var formulaDao: FormulaDao


    fun initDB(context: Context): MyAppDatabase {
        db = MyAppDatabase.getMyDataBase(context)
        formulaDao = db.formulaDao()
        return db
    }

    fun insert(formula: Formula) {
        formulaDao.insert(formula)
    }

    fun getFormulaList(): List<Formula> {
        return formulaDao.getFormulaList()
    }

    fun getFormulaListLiveData():LiveData<List<Formula>>{
        return formulaDao.getFormulaListLiveData()
    }

    fun getCountFormulaLiveData(): LiveData<Int> {
        return formulaDao.getCountFormulaLiveData()
    }

    fun search(code: String): Int {
        return formulaDao.search(code)
    }

    fun getFormula(id: Int): Formula {
        return formulaDao.getFormula(id)
    }

    fun update(formula: Formula) {
        formulaDao.update(formula)
    }

    fun deleteById(id: Int) {
        formulaDao.deleteById(id)
    }
}