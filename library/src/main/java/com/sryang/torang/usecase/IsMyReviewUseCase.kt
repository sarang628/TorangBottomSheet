package com.sryang.torang.usecase

interface IsMyReviewUseCase {
    suspend fun invoke(reviewId: Int): Boolean
}