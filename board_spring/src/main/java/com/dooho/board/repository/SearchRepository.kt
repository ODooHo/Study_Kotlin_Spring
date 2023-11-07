package com.dooho.board.repository

import com.dooho.board.entity.SearchEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface SearchRepository : JpaRepository<SearchEntity?, String?> {
    fun findTop10ByOrderByPopularSearchCountDesc(): List<SearchEntity?>?
    fun existsByPopularTerm(search: String?): Boolean
}