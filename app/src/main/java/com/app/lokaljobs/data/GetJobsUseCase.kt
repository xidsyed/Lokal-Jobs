package com.app.lokaljobs.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.lokaljobs.AppModule
import com.app.lokaljobs.AppModule.jobApi
import com.app.lokaljobs.data.local.Job
import com.app.lokaljobs.data.remote.JobAPI
import com.app.lokaljobs.data.remote.JobPagingSource
import kotlinx.coroutines.flow.Flow


class GetJobsUseCase(jobApi: JobAPI) {

    operator fun invoke(): Flow<PagingData<Job>> {
        return Pager(config = PagingConfig(10), pagingSourceFactory = {
            JobPagingSource(jobAPI = jobApi)
        }).flow
    }

}