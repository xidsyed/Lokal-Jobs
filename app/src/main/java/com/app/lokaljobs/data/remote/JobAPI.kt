package com.app.lokaljobs.data.remote

import com.app.lokaljobs.data.remote.model.JobsDto
import retrofit2.http.GET
import retrofit2.http.Query


interface JobAPI {
//    https://testapi.getlokalapp.com/common/jobs?page=1
    @GET("jobs")
    suspend fun getResponse(
        @Query("page") page: Int
    ) : JobsDto
}