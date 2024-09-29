package com.app.lokaljobs.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.di.JobsModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class JobNavigatorViewModel : ViewModel() {
    private val jobsUseCase = JobsModule.JobsUseCase
    private val isNetworkAvailableUseCase = JobsModule.IsNetworkAvailableUseCase

    val jobsPagingDataFlow: Flow<PagingData<JobEntity>> = jobsUseCase.getJobs()
        .cachedIn(scope = viewModelScope)
    val bookmarkedJobs: StateFlow<List<JobEntity>> = jobsUseCase.getBookmarks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val isNetworkAvailable = isNetworkAvailableUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    fun toggleBookmark(job: JobEntity) {
        viewModelScope.launch {
            val isBookmarked =
                bookmarkedJobs.value.any { bookmarkedJob -> bookmarkedJob.id == job.id }
            if (isBookmarked) jobsUseCase.deleteBookmark(job)
            else jobsUseCase.upsertBookmark(job)
        }
    }


}