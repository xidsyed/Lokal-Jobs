package com.app.lokaljobs.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.lokaljobs.data.local.JobEntity
import com.app.lokaljobs.data.remote.JobPagingSource
import com.app.lokaljobs.data.remote.JobService
import kotlinx.coroutines.flow.Flow


class GetJobsUseCase(private val service: JobService) {
    operator fun invoke(): Flow<PagingData<JobEntity>> {
        return Pager(config = PagingConfig(10), pagingSourceFactory = {
            JobPagingSource(jobService = service)
        }).flow
    }

}