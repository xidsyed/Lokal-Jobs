package com.app.lokaljobs.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.di.JobsModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JobNavigatorViewModel : ViewModel() {
    private val jobsUseCase = JobsModule.JobsUseCase

    val jobs: Flow<PagingData<JobEntity>> = jobsUseCase.getJobs().cachedIn(viewModelScope)
    private var _bookmarkedJobs = MutableStateFlow<List<JobEntity>>(emptyList())
    val bookmarkedJobs: StateFlow<List<JobEntity>> get() = _bookmarkedJobs

    init {
        jobsUseCase.getBookmarks().onEach { list ->
            _bookmarkedJobs.update { list }
        }.launchIn(viewModelScope)
    }

    fun toggleBookmark(job: JobEntity) {
        viewModelScope.launch {
            val isBookmarked =
                bookmarkedJobs.value.any { bookmarkedJob -> bookmarkedJob.id == job.id }
            if (isBookmarked) jobsUseCase.deleteBookmark(job)
            else jobsUseCase.upsertBookmark(job)
        }
    }


}