package com.sarang.torang.usecase

interface IsMyReviewUseCase {
    suspend fun invoke(reviewId: Int): Boolean
}