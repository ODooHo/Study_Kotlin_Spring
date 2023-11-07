package com.dooho.board.controller

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.board.LikyDto
import com.dooho.board.entity.LikyEntity
import com.dooho.board.service.LikyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/board")
class LikyController @Autowired constructor(private val likyService: LikyService) {
    @PostMapping("/{boardNumber}/liky/add")
    fun addLiky(@RequestBody requestBody: LikyDto?): ResponseDto<*> {
        return likyService.addLiky(requestBody)
    }

    @GetMapping("/{boardNumber}/liky/get")
    fun getLiky(@PathVariable boardNumber: Int?): ResponseDto<List<LikyEntity?>?> {
        return likyService.getLiky(boardNumber)
    }

    @GetMapping("/{boardNumber}/liky/get/count")
    fun getLikyCount(@PathVariable boardNumber: Int?): ResponseDto<Int?> {
        return likyService.getLikyCount(boardNumber)
    }

    @GetMapping("/{boardNumber}/liky/delete/{likeUserNickname}")
    fun deleteLiky(
        @PathVariable boardNumber: Int?,
        @PathVariable likeUserNickname: String?
    ): ResponseDto<*> {
        return likyService.deleteLiky(boardNumber, likeUserNickname)
    }
}