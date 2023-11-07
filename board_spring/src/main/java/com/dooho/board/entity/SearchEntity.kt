package com.dooho.board.entity

import com.dooho.board.dto.SearchDto
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity(name = "Search")
@Table(name = "Search")
data class SearchEntity(
    @Id
    var popularTerm: String? = null,
    var popularSearchCount: Int? = null
) {
    constructor(dto: SearchDto?) : this(
        popularTerm = dto?.popularTerm,
        popularSearchCount = dto?.popularSearchCount
    )
}