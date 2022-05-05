import android.content.Context
import androidx.lifecycle.LiveData
import com.example.tooskawood.Formula
import database.AppDatabase
import database.FormulaDao

object Repository {
    lateinit var db: AppDatabase
    lateinit var formulaDao: FormulaDao


    fun initDB(context: Context): AppDatabase {
        db = AppDatabase.getMyDataBase(context)
        formulaDao = db.formulaDao()
        return db
    }

    fun insert(formula: Formula) {
        formulaDao.insert(formula)
    }

    fun getFormulaList(): List<Formula> {
        return formulaDao.getFormulaList()
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