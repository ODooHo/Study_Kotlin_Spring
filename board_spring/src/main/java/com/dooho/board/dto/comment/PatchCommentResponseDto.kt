package com.dooho.board.dto.comment

import com.dooho.board.entity.CommentEntity


data class PatchCommentResponseDto (
    private val comment: CommentEntity? = null
    )