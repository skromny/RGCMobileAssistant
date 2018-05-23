package com.recrutify.rgc.mobileassistant.candidates

import android.arch.lifecycle.LiveData
import com.recrutify.rgc.mobileassistant.common.ApiResponse
import retrofit2.http.*

interface CandidatesService {

    @POST
    @FormUrlEncoded
    fun listProjects(
            @Field("filter") filter: String,
            @Field("limit") limit: Int
    )

    @GET("/candidate")
    fun getAllCandidates(
            @HeaderMap headers: Map<String, String>,
            @Query("page") page: Int,
            @Query("statuses") statuses: List<Int>,
            @Query("userAssigned") userAssigned: Int?): LiveData<ApiResponse<List<Candidate>>>

    @GET("/candidate")
    fun getCandidates(
            @Query("query") query: String,
            @Query("statuses") statuses: List<Int>,
            @Query("userAssigned") userAssigned: Int?): LiveData<ApiResponse<List<Candidate>>>
}
