package com.example.tooskawood.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tooskawood.Formula
import com.example.tooskawood.repository.Repository

class MainViewModel(app: Application): AndroidViewModel(app) {

    var db = Repository.initDB(app.applicationContext)


    init {
        Repository.initDB(app.applicationContext)

    }

    fun insert(formula: Formula){
        Repository.insert(formula)
    }

    fun getFormulaListLiveData():LiveData<List<Formula>>{
        return Repository.getFormulaListLiveData()
    }

    fun getFormulaList():List<Formula>{
        return Repository.getFormulaList()
    }

    fun getCountFormulaLiveData(): LiveData<Int> {
        return  Repository.getCountFormulaLiveData()
    }

    fun searchFormula(code: String):Int{
        return Repository.search(code)
    }

    fun getFormula(id:Int): Formula{
        return Repository.getFormula(id)
    }

    fun update(formula: Formula){
        Repository.update(formula)
    }

    fun deleteById(id:Int){
        Repository.deleteById(id)
    }
}