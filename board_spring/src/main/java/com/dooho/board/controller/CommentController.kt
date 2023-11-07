package com.dooho.board.controller

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.comment.CommentDto
import com.dooho.board.dto.comment.PatchCommentDto
import com.dooho.board.dto.comment.PatchCommentResponseDto
import com.dooho.board.entity.CommentEntity
import com.dooho.board.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/board")
class CommentController @Autowired constructor(private val commentService: CommentService) {
    @PostMapping("/{boardNumber}/comment/register")
    fun register(@RequestBody requestBody: CommentDto?): ResponseDto<*> {
        return commentService.register(requestBody)
    }

    @GetMapping("/{boardNumber}/comment/list")
    fun getComment(@PathVariable boardNumber: Int?): ResponseDto<List<CommentEntity?>?> {
        return commentService.getComment(boardNumber)
    }

    @GetMapping("/{boardNumber}/comment/{commentId}/delete")
    fun deleteComment(@PathVariable boardNumber: Int?, @PathVariable commentId: Int?): ResponseDto<*> {
        return commentService.deleteComment(boardNumber, commentId)
    }

    @CrossOrigin(origins = ["*"])
    @PatchMapping("{boardNumber}/comment/{commentId}/edit")
    fun editComment(
        @PathVariable boardNumber: Int?,
        @PathVariable commentId: Int?,
        @RequestBody requestBody: PatchCommentDto?
    ): ResponseDto<PatchCommentResponseDto?> {
        return commentService.editComment(boardNumber, commentId, requestBody)
    }
}