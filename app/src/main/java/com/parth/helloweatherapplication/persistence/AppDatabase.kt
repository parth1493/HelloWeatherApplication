package com.parth.helloweatherapplication.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.parth.helloweatherapplication.model.AccountProperties
import com.parth.helloweatherapplication.model.AuthToken

@Database(entities = [AuthToken::class, AccountProperties::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAuthTokenDao(): AuthTokenDao

    abstract fun getAccountPropertiesDao(): AccountPropertiesDao

    companion object{
        val DATABASE_NAME: String = "app_db"
    }
}