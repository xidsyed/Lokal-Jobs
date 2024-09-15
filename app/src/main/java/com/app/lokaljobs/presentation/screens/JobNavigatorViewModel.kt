package com.app.lokaljobs.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.lokaljobs.AppModule
import com.app.lokaljobs.data.local.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JobNavigatorViewModel : ViewModel() {
    private val getJobsUseCase = AppModule.GetJobsUseCase
    private val bookmarkUseCases = AppModule.BookmarkJobUseCases

    val jobs: Flow<PagingData<Job>> = getJobsUseCase().cachedIn(viewModelScope)
    private var _bookmarkedJobs = MutableStateFlow<List<Job>>(emptyList())
    val bookmarkedJobs: StateFlow<List<Job>> get() = _bookmarkedJobs

    init {
        bookmarkUseCases.getJobFlow().onEach { list ->
            _bookmarkedJobs.update { list }
        }.launchIn(viewModelScope)
    }

    fun toggleBookmark(job: Job) {
        viewModelScope.launch {
            val isBookmarked =
                bookmarkedJobs.value.any { bookmarkedJob -> bookmarkedJob.id == job.id }
            if (isBookmarked) bookmarkUseCases.deleteJob(job)
            else bookmarkUseCases.upsertJob(job)
        }
    }


}