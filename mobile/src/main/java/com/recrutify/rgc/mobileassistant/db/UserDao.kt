package com.recrutify.rgc.mobileassistant.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.recrutify.rgc.mobileassistant.login.LoggedUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(loggedUser: LoggedUser)

    @Query("SELECT * FROM LoggedUser LIMIT 1")
    fun getUser() : LiveData<LoggedUser>

    @Query("DELETE FROM LoggedUser")
    fun removeLoggedUser()
}