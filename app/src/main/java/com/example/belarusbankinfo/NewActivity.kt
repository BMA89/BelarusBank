package com.example.belarusbankinfo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.show_save_info.*
import kotlinx.coroutines.*
import java.lang.Exception

class NewActivity : AppCompatActivity() {
    val scope = MainScope()
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    lateinit var database: BankDatabase
    lateinit var dao: BankDo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_save_info)
        database = Room.databaseBuilder(applicationContext, BankDatabase::class.java, "babk_db").allowMainThreadQueries().build()
        dao = database.BankDo()

    }

    fun goBack(view: View) {
        finish()
    }

    fun deleteAll(view: View) {
        scope.launch {
            try {
                dao.deleteAll()
            } catch (e : Exception){
                Log.d("!!!", e.toString())
            }
        }
//        dao.deleteAll()
    }

    fun showSaveInfo(view: View){
        scope.launch {
            withContext(Dispatchers.Main){
                val info = dao.select()
                result.text = info.toString()
            }
        }
//        val info = dao.select()
//        result.text = info.toString()
    }

}