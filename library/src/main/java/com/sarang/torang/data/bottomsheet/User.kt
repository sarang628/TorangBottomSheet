package com.sarang.torang.data.bottomsheet

data class User(val userId: Int, val userName: String, val picture: String){
    companion object
}

val User.Companion.Sample: User
    get() = User(
        userId = 0,
        userName = "Torang",
        picture = ""
    )