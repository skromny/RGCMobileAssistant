package com.recrutify.rgc.mobileassistant.login

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import java.time.DateTimeException

@Entity(primaryKeys = ["accountId"])
data class LoggedUser(
     val accountId: Int,
     val avatarLink: String?,
     val defaultCompany: String?,
     val firstName: String?,
     val folderId: Int,
     val fullName: String?,
     val isDebug: Boolean,
     val language: String?,
     val lastActivity: String?, //"2018-05-04T23:29:49.6955780+02:00"
     val lastName: String?,
     val login: String,
     val loginTime: String, //2018-05-04T23:29:49.6955745+02:00"
     val roleId: Int,
     val sessionStringLog: String?,
     val token: String,
     val userId: Int
)