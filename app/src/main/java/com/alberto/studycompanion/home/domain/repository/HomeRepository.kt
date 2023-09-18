package com.alberto.studycompanion.home.domain.repository

import com.alberto.studycompanion.home.domain.models.Method

interface HomeRepository {

    fun getAllMethods() : List<Method>

}