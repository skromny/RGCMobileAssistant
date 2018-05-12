package com.recrutify.rgc.mobileassistant.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.recrutify.rgc.mobileassistant.AppExecutors
import com.recrutify.rgc.mobileassistant.Model.Resource
import com.recrutify.rgc.mobileassistant.common.ApiResponse
import com.recrutify.rgc.mobileassistant.common.NetworkBoundResource
import com.recrutify.rgc.mobileassistant.db.LocalDB
import com.recrutify.rgc.mobileassistant.db.UserDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.log

class UserRepository
@Inject constructor(
        private val appExecutors: AppExecutors,
        private val authService: AuthService,
        private val userDao: UserDao) {

    //var loggedUser: MutableLiveData<LoggedUser> = MutableLiveData()

//    fun login(login: String, password: String) : LiveData<ApiResponse<LoggedUser>> = authService.login(LoginRequest(login, password))

    fun login(loginRequest: LoginRequest) : LiveData<Resource<LoggedUser>> {

        return object : NetworkBoundResource<LoggedUser, LoggedUser>(appExecutors) {
            override fun saveCallResult(item: LoggedUser) {
                Log.d("NetworkBoundResource", "NetworkBoundResource")
                userDao.insert(item)
            }

            override fun shouldFetch(data: LoggedUser?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<LoggedUser> = userDao.getUser()
//            override fun loadFromDb(): LiveData<LoggedUser> = object : LiveData<LoggedUser>() {
//                init {
//                    postValue(null)
//                }
//            }

         override fun createCall(): LiveData<ApiResponse<LoggedUser>> = authService.login(loginRequest)

        }.asLiveData()
    }

    fun getLoggedUser(): LiveData<LoggedUser> {
        return userDao.getUser()
    }





//    fun getUser(login: String, password: String) : LiveData<LoggedUser> {
//
//        authService.login(LoginRequest(login, password)).enqueue(object : Callback<LoggedUser> {
//            override fun onResponse(call: Call<LoggedUser>?, response: Response<LoggedUser>?) {
//                loggedUser.value = response?.body()
//            }
//
//            override fun onFailure(call: Call<LoggedUser>?, t: Throwable?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        })
//    }
}