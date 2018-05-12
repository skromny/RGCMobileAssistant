package com.recrutify.rgc.mobileassistant.projects

import android.arch.lifecycle.LiveData
import com.recrutify.rgc.mobileassistant.common.ApiResponse
import com.recrutify.rgc.mobileassistant.login.LoggedUser
import com.recrutify.rgc.mobileassistant.login.LoginRequest
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProjectsService {
    @POST
    @FormUrlEncoded
    fun listProjects(
            @Field("filter") filter: String,
            @Field("limit") limit: Int
    )

    @POST("/projects")
    fun getProjects(@Body filter: String): LiveData<ApiResponse<List<Project>>>
}