package com.shamilov.core.profile.data.respository

import com.shamilov.core.profile.data.remote.ProfileNetworkApi
import com.shamilov.core.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileNetworkApi,
) : ProfileRepository