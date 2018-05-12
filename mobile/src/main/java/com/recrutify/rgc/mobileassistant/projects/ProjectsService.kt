package com.recrutify.rgc.mobileassistant.projects

import android.arch.lifecycle.LiveData
import com.recrutify.rgc.mobileassistant.common.ApiResponse
import retrofit2.http.*

interface ProjectsService {
    @POST
    @FormUrlEncoded
    fun listProjects(
            @Field("filter") filter: String,
            @Field("limit") limit: Int
    )

    @GET("/project")
    fun getProjects(
            @Query("page") page: Int,
            @Query("statuses") statuses: List<Int>,
            @Query("userAssigned") userAssigned: Int): LiveData<ApiResponse<List<Project>>>
}