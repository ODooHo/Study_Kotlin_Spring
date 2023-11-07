package com.dooho.board.dto.auth

import com.dooho.board.entity.UserEntity

data class SignInResponseDto (
    val token : String? = null,
    val tokenExprTime : Int? = null,
    val refreshToken : String? = null,
    val refreshTokenExprTime : Int? = null,
    val user : UserEntity? = null
)