package com.app.lokaljobs.domain

import androidx.paging.PagingData
import com.app.lokaljobs.data.local.model.JobEntity
import kotlinx.coroutines.flow.Flow

interface JobsRepository {
    fun getJobs () : Flow<PagingData<JobEntity>>

    fun getBookmarks(): Flow<List<JobEntity>>

    suspend fun getBookmark(id: Int): JobEntity?

    suspend fun deleteBookmark(job: JobEntity)

    suspend fun upsertBookmark(job: JobEntity)
}