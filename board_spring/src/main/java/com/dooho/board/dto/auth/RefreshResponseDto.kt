package com.dooho.board.dto.auth

data class RefreshResponseDto(
    var token: String? = null,
    var tokenExprTime: Int? = null
)
