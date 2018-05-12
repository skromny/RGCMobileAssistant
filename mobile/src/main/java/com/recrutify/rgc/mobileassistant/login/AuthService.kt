package com.recrutify.rgc.mobileassistant.login

import android.arch.lifecycle.LiveData
import com.recrutify.rgc.mobileassistant.common.ApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * REST API for login/logout etc
 */
interface AuthService {

    @POST
    @FormUrlEncoded
    fun loginUrlEnc(
            @Field("user") user: String,
            @Field("password") password: String
    )

    @POST("/auth/login")
    fun login(@Body user: LoginRequest): LiveData<ApiResponse<LoggedUser>>
}
