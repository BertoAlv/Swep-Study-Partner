package com.alberto.studycompanion.home.data.repository

import com.alberto.studycompanion.home.domain.models.Method
import com.alberto.studycompanion.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository {

    override fun getAllMethods(): List<Method> {
        TODO("Not yet implemented")
    }

}