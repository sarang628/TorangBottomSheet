package com.sarang.torang.usecase

interface SendShareUseCase {
    suspend fun invoke(reviewId: Int, userId: List<Int>)
}