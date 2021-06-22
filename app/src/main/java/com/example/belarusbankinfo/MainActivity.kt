package com.example.belarusbankinfo

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.show_save_info.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    val scope = MainScope()
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    val apiService = ApiService.create()
    val bank = mutableListOf<Bank>()
    lateinit var database: BankDatabase
    lateinit var dao: BankDo
//    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(applicationContext, BankDatabase::class.java, "babk_db").allowMainThreadQueries().build()
        dao = database.BankDo()

        recView.adapter = AdapterBank(bank, this)
        recView.layoutManager = LinearLayoutManager(this)

//        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//            result: ActivityResult ->
//            if (result.resultCode == RESULT_OK) {
//                Log.d("!!!", "OK")
//                val text = result.data?.getStringExtra("main")
//            }
//        }
    }

    fun getInfo(view: View) {
        val call = apiService.getInfo()
        call.enqueue(object : Callback<List<Bank>> {
            override fun onFailure(call: Call<List<Bank>>, t: Throwable) {
                Log.d("!!!", t.toString())
            }

            override fun onResponse(call: Call<List<Bank>>, response: Response<List<Bank>>) {
                response.body()?.let {
                    bank.addAll(it)
                    recView.adapter?.notifyDataSetChanged()
                }
            }
        })
    }

    fun newActivity(view: View) {
        val intent = Intent(this, NewActivity::class.java)
        startActivity(intent)
//        setContentView(R.layout.show_save_info)
//        launcher?.launch(Intent(this, NewActivity::class.java))

    }

    fun saveInfo(view: View) {
        scope.launch {
            dao.deleteAll()
            for (i in bank){
                dao.insertBank(i)
        }
//        dao.deleteAll()
//        for (i in bank){
//            dao.insertBank(i)
//        }

    }}

    fun deleteAll(view: View) {
        scope.launch {
            withContext(Dispatchers.IO){
                dao.deleteAll()
            }
        }
    }
//        dao.deleteAll()
//    }

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