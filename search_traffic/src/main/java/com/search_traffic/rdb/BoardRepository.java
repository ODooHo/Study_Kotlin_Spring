package com.search_traffic.rdb;

import com.search_traffic.elastic.BoardDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
    List<BoardEntity> findByTitleContaining(String title);
}
