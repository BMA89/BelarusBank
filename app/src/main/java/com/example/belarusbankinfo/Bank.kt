package com.example.belarusbankinfo

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "bank")
data class Bank (val USD_in: String,
                 val USD_out: String,
                 val EUR_in: String,
                 val EUR_out: String,
                 @PrimaryKey val filials_text: String)

