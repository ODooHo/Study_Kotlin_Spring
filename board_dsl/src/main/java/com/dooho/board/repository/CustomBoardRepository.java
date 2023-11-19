package com.dooho.board.repository;

import com.dooho.board.entity.BoardEntity;

import java.time.LocalDate;
import java.util.List;

public interface CustomBoardRepository {
    List<BoardEntity> findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(LocalDate boardWriteDate);
}