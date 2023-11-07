package com.dooho.board.controller

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.SearchDto
import com.dooho.board.entity.BoardEntity
import com.dooho.board.entity.SearchEntity
import com.dooho.board.service.SearchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/search")
class SearchController @Autowired constructor(private val searchService: SearchService) {
    @PostMapping("")
    fun searchBoard(@RequestBody requestBody: SearchDto?): ResponseDto<List<BoardEntity?>?> {
        return searchService.getSearchList(requestBody)
    }

    @get:GetMapping("/popularSearchList")
    val popularSearchList: ResponseDto<List<SearchEntity?>?>
        get() = searchService.popularSearchList
}