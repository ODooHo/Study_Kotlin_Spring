package com.search_traffic.rdb;

import com.search_traffic.elastic.BoardDocument;
import com.search_traffic.elastic.BoardSearchRepository;
import com.search_traffic.elastic.ElasticService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoardServiceTest {

    private final BoardRepository boardRepository;

    private final BoardService boardService;


    @Autowired
    public BoardServiceTest(BoardRepository boardRepository, BoardService boardService) {
        this.boardRepository = boardRepository;
        this.boardService = boardService;
    }

    @Test
    void jpaTest(){

        long startTime = System.currentTimeMillis();


        List<BoardEntity> boardEntities = boardService.searchByTitle("mattis");

        long endTime = System.currentTimeMillis();

        long time = endTime - startTime;

        System.out.println("JPA Time : " + time);
    }
}
