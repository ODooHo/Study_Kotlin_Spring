package com.dooho.board.dto.user

import com.dooho.board.entity.BoardEntity


data class MyPageDto (
    val userEmail: String? = null,
    val userNickname: String? = null,
    val userProfile: String? = null,
    val userBoard: List<BoardEntity?>? = null
    )