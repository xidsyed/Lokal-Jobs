package com.app.lokaljobs.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.lokaljobs.data.local.JobEntity
import com.app.lokaljobs.data.local.toJobs

class JobPagingSource(val jobService: JobService) : PagingSource<Int, JobEntity>() {
    override fun getRefreshKey(state: PagingState<Int, JobEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobEntity> {
        return try {
            val page = params.key ?: 1
            val jobsDto = jobService.getResponse(page)
            LoadResult.Page(
                data = jobsDto.toJobs(),
                nextKey = if (jobsDto.jobResults.isEmpty()) null else page + 1,
                prevKey = null
            )
        } catch (e: Throwable) {
            Log.d("JobPagingSource", "load: ${e.printStackTrace()}")
            LoadResult.Error(e)
        }
    }
}