package com.dooho.board.repository;

import com.dooho.board.entity.BoardEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
public interface CustomBoardRepository {
    List<BoardEntity> findTop3(LocalDate boardWriteDate);
    List<BoardEntity> findList();
}