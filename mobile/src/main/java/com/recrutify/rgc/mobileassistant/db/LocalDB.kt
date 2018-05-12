package com.recrutify.rgc.mobileassistant.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.recrutify.rgc.mobileassistant.login.LoggedUser

@Database(
    entities = [
        LoggedUser::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocalDB : RoomDatabase() {
    abstract fun userDao() : UserDao
}