package com.example.belarusbankinfo
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Bank::class), version = 1)
abstract class BankDatabase : RoomDatabase() {
    abstract fun BankDo() : BankDo
}