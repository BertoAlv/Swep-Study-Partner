package com.alberto.studycompanion.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}