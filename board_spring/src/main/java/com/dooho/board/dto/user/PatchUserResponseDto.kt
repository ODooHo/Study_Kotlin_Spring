package com.dooho.board.dto.user

import com.dooho.board.entity.UserEntity


data class PatchUserResponseDto (
    private val user: UserEntity? = null
)