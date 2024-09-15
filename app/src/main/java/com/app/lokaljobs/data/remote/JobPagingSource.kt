package com.app.lokaljobs.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.lokaljobs.data.local.Job
import com.app.lokaljobs.data.local.toJobs

class JobPagingSource(val jobAPI: JobAPI) : PagingSource<Int, Job>() {
    override fun getRefreshKey(state: PagingState<Int, Job>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Job> {
        return try {
            val page = params.key ?: 1
            val jobsDto = jobAPI.getResponse(page)
            LoadResult.Page(
                data = jobsDto.toJobs(),
                nextKey = if (jobsDto.results.isEmpty()) null else page + 1,
                prevKey = null
            )
        } catch (e: Throwable) {
            Log.d("JobPagingSource", "load: ${e.printStackTrace()}")
            LoadResult.Error(e)
        }
    }
}