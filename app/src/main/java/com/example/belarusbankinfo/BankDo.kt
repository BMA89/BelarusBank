package com.example.belarusbankinfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface BankDo {
    @Query("SELECT * FROM bank")
    suspend fun select() : List<Bank>

    @Query("DELETE FROM bank")
    suspend fun deleteAll()

    @Insert
    suspend fun insertBank(newBank: Bank)

}