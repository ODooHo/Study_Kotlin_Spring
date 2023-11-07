package com.dooho.board.dto.comment


import java.time.LocalDate


data class PatchCommentDto (
    val commentContent: String? = null,
    val commentWriteDate: LocalDate? = null
)