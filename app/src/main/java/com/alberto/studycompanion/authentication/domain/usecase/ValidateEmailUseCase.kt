package com.alberto.studycompanion.authentication.domain.usecase

import com.alberto.studycompanion.authentication.domain.matcher.EmailMatcher
import javax.inject.Inject


class ValidateEmailUseCase @Inject constructor(private val emailMatcher: EmailMatcher) {

    operator fun invoke(email: String): Boolean {
        return emailMatcher.isValid(email)
    }

}
