package com.app.lokaljobs.domain

import androidx.paging.PagingData
import com.app.lokaljobs.data.local.model.JobEntity
import kotlinx.coroutines.flow.Flow

class JobsUseCase(private val repository: JobsRepository) {
    fun getJobs(): Flow<PagingData<JobEntity>> = repository.getJobs()
    fun getBookmarks(): Flow<List<JobEntity>> = repository.getBookmarks()
    suspend fun getBookmark(id: Int): JobEntity? = repository.getBookmark(id)
    suspend fun upsertBookmark(jobEntity: JobEntity) = repository.upsertBookmark(jobEntity)
    suspend fun deleteBookmark(jobEntity: JobEntity) = repository.deleteBookmark(jobEntity)
}