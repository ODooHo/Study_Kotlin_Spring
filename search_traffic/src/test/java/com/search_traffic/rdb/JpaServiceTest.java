package com.search_traffic.rdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JpaServiceTest {

    private final PublicDataRepository publicDataRepository;

    private final JpaService jpaService;


    @Autowired
    public JpaServiceTest(PublicDataRepository publicDataRepository, JpaService jpaService) {
        this.publicDataRepository = publicDataRepository;
        this.jpaService = jpaService;
    }

    @Test
    void jpaTest(){

        long startTime = System.currentTimeMillis();


        List<PublicDataEntity> boardEntities = jpaService.searchByName("김지현");

        long endTime = System.currentTimeMillis();

        long time = endTime - startTime;

        System.out.println("JPA Time : " + time);
        System.out.println(boardEntities.size());

        startTime = System.currentTimeMillis();


        boardEntities = jpaService.searchByCompany("주니어김영사");

        endTime = System.currentTimeMillis();

        time = endTime - startTime;

        System.out.println("JPA Time : " + time);
        System.out.println(boardEntities.size());

    }
}
