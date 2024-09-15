package com.app.lokaljobs.data


import com.app.lokaljobs.AppModule
import com.app.lokaljobs.data.local.Job
import com.app.lokaljobs.data.local.JobDao
import kotlinx.coroutines.flow.Flow

class BookmarkUseCases(val jobDao: JobDao = AppModule.JobDao) {
    fun getJobFlow(): Flow<List<Job>> = jobDao.selectJobs()

    suspend fun getJob(id: Int): Job? = jobDao.selectJob(id)

    suspend fun deleteJob(job: Job) = jobDao.delete(job)

    suspend fun upsertJob(job: Job) = jobDao.upsert(job)
}