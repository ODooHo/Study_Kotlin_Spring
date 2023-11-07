package com.dooho.board.service

import com.dooho.board.dto.ResponseDto
import com.dooho.board.dto.ResponseDto.Companion.setFailed
import com.dooho.board.dto.ResponseDto.Companion.setSuccess
import com.dooho.board.dto.SearchDto
import com.dooho.board.entity.BoardEntity
import com.dooho.board.entity.SearchEntity
import com.dooho.board.repository.BoardRepository
import com.dooho.board.repository.SearchRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SearchService @Autowired constructor(
    private val boardRepository: BoardRepository,
    private val searchRepository: SearchRepository
) {
    fun getSearchList(dto: SearchDto?): ResponseDto<List<BoardEntity?>?> {
        var searchEntity: SearchEntity? = null
        val searchWord = dto?.popularTerm
        var boardList: List<BoardEntity?>? = ArrayList()
        try {
            if (searchRepository.existsByPopularTerm(searchWord)) {
                searchEntity = searchRepository.findById(searchWord).orElse(null)
                val count = searchEntity!!.popularSearchCount!! + 1
                searchEntity.popularSearchCount = count
                searchRepository.save(searchEntity)
            } else {
                searchEntity = SearchEntity(dto)
                searchEntity.popularSearchCount = 1
                searchRepository.save<SearchEntity>(searchEntity)
            }
            boardList = boardRepository.findByBoardTitleContains(searchWord)
        } catch (e: Exception) {
            e.printStackTrace()
            return setFailed<List<BoardEntity?>?>("DataBase Error!")
        }
        return setSuccess("Success", boardList)
    }

    val popularSearchList: ResponseDto<List<SearchEntity?>?>
        get() {
            var popularSearchList: List<SearchEntity?>? = ArrayList()
            popularSearchList = try {
                searchRepository.findTop10ByOrderByPopularSearchCountDesc()
            } catch (e: Exception) {
                e.printStackTrace()
                return setFailed<List<SearchEntity?>?>("DataBase Error!")
            }
            return setSuccess("Success", popularSearchList)
        }
}