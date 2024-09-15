package com.app.lokaljobs.domain


import com.app.lokaljobs.di.JobServiceModule
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.data.local.service.JobDao
import kotlinx.coroutines.flow.Flow

class BookmarkUseCases(val jobDao: JobDao = JobServiceModule.JobDao) {
    fun getJobFlow(): Flow<List<JobEntity>> = jobDao.selectJobs()

    suspend fun getJob(id: Int): JobEntity? = jobDao.selectJob(id)

    suspend fun deleteJob(job: JobEntity) = jobDao.delete(job)

    suspend fun upsertJob(job: JobEntity) = jobDao.upsert(job)
}