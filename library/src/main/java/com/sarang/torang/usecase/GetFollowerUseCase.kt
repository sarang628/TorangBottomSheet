package com.sarang.torang.usecase

import com.sarang.torang.data.bottomsheet.User

interface GetFollowerUseCase
{
    suspend fun invoke(): List<User>
}