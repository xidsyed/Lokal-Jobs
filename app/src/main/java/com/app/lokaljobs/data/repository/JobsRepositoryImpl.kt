package com.app.lokaljobs.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.data.local.service.JobDao
import com.app.lokaljobs.data.remote.service.JobPagingSource
import com.app.lokaljobs.data.remote.service.JobService
import com.app.lokaljobs.domain.JobsRepository
import kotlinx.coroutines.flow.Flow

class JobsRepositoryImpl(private val service: JobService, private val jobDao: JobDao) : JobsRepository {

    override fun getJobs(): Flow<PagingData<JobEntity>> {
        return Pager(config = PagingConfig(10), pagingSourceFactory = {
            JobPagingSource(jobService = service)
        }).flow
    }

    override fun getBookmarks(): Flow<List<JobEntity>> = jobDao.selectJobs()

    override suspend fun getBookmark(id: Int): JobEntity? = jobDao.selectJob(id)

    override suspend fun deleteBookmark(job: JobEntity) = jobDao.delete(job)

    override suspend fun upsertBookmark(job: JobEntity) = jobDao.upsert(job)
}