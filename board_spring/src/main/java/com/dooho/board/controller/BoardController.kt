package com.dooho.board.controller

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.board.PatchBoardDto
import com.dooho.board.dto.board.PatchBoardResponseDto
import com.dooho.board.entity.BoardEntity
import com.dooho.board.service.BoardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/board")
class BoardController @Autowired constructor(private val boardService: BoardService) {
    @PostMapping("/register")
    fun register(
        @RequestParam("boardTitle") boardTitle: String?,
        @RequestParam("boardContent") boardContent: String?,
        @RequestParam("boardWriterEmail") boardWriterEmail: String?,
        @RequestParam("boardWriterProfile") boardWriterProfile: String?,
        @RequestParam("boardWriterNickname") boardWriterNickname: String?,
        @RequestParam("boardWriteDate") boardWriteDate: String?,
        @RequestParam(value = "boardImage", required = false) boardImage: MultipartFile?,
        @RequestParam(value = "boardVideo", required = false) boardVideo: MultipartFile?,
        @RequestParam(value = "boardFile", required = false) boardFile: MultipartFile?
    ): ResponseDto<BoardEntity?> {
        return boardService.register(
            boardTitle, boardContent, boardWriterEmail, boardWriterProfile, boardWriterNickname, boardWriteDate,
            boardImage, boardVideo, boardFile
        )
    }

    @GetMapping("/top3")
    fun getTop3(): ResponseDto<List<BoardEntity?>?> {
        return boardService.top3
    }

    @GetMapping("/list")
    fun getList(): ResponseDto<List<BoardEntity?>?> {
        return boardService.list
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping("/{boardNumber}")
    fun getBoard(@PathVariable boardNumber: Int?): ResponseDto<BoardEntity?> {
        return boardService.getBoard(boardNumber)
    }


    @GetMapping("/{boardNumber}/delete")
    fun deleteBoard(@PathVariable boardNumber: Int?): ResponseDto<*> {
        return boardService.deleteBoard(boardNumber)
    }

    @CrossOrigin(origins = ["*"])
    @PatchMapping("{boardNumber}/edit")
    fun editBoard(
        @PathVariable boardNumber: Int?,
        @RequestBody requestBody: PatchBoardDto?
    ): ResponseDto<PatchBoardResponseDto?> {
        return boardService.editBoard(boardNumber, requestBody)
    }
}