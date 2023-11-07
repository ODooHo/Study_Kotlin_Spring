package com.dooho.board.dto.board

import java.time.LocalDate


data class PatchBoardDto (
    val boardTitle: String? = null,
    val boardContent: String? = null,
    val boardWriteDate: LocalDate? = null
)