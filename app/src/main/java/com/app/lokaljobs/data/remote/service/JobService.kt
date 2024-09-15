package com.app.lokaljobs.data.remote.service

import com.app.lokaljobs.data.remote.model.JobResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface JobService {
//    https://testapi.getlokalapp.com/common/jobs?page=1
    @GET("jobs")
    suspend fun getResponse(
        @Query("page") page: Int
    ) : JobResponse
}