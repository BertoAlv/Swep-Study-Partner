package com.alberto.studycompanion.home.domain.usecases

import com.alberto.studycompanion.home.domain.models.Method
import com.alberto.studycompanion.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetMethodsUseCase @Inject constructor( private val repository: HomeRepository ) {

    operator fun invoke() : List<Method> {
       return repository.getAllMethods()
    }

}