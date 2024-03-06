package com.search_traffic.elastic;

import com.search_traffic.rdb.BoardEntity;
import com.search_traffic.rdb.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ElasticServiceTest {
    private final BoardRepository boardRepository;
    private final BoardSearchRepository boardSearchRepository;

    private final ElasticService elasticService;

    @Autowired
    public ElasticServiceTest(BoardRepository boardRepository, BoardSearchRepository boardSearchRepository, ElasticService elasticService) {
        this.boardRepository = boardRepository;
        this.boardSearchRepository = boardSearchRepository;
        this.elasticService = elasticService;
    }

    @Test
    void elasticTest(){
//        List<BoardEntity> boardEntityList = boardRepository.findAll();
//        elasticService.init(boardEntityList);



        long startTime = System.currentTimeMillis();
        List<BoardDocument> boardDocuments = elasticService.searchByTitle("mattis");

        long endTime = System.currentTimeMillis();

        long time = endTime - startTime;

        System.out.println("elastic Time : " + time);
    }
}
