package com.app.lokaljobs.domain

import com.app.lokaljobs.data.local.service.NetworkConnectivityService
import com.app.lokaljobs.di.JobsModule.networkConnectivityService

class IsNetworkAvailableUseCase(networkConnectivityService: NetworkConnectivityService) {
    operator fun invoke() = networkConnectivityService.observe()
}