package com.sryang.torang.usecase

import com.sryang.torang.data.User

interface GetFollowerUseCase
{
    suspend fun invoke(): List<User>
}