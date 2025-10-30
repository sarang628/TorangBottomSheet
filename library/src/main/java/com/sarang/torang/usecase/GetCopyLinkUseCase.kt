package com.sarang.torang.usecase

interface GetCopyLinkUseCase {
    suspend fun invoke(reviewId: Int): String
}