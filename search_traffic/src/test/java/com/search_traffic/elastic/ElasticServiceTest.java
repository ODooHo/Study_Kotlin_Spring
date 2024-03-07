package com.search_traffic.elastic;

import com.search_traffic.rdb.PublicDataEntity;
import com.search_traffic.rdb.PublicDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ElasticServiceTest {
    private final PublicDataRepository publicDataRepository;
    private final PublicDataSearchRepository publicDataSearchRepository;

    private final ElasticService elasticService;

    @Autowired
    public ElasticServiceTest(PublicDataRepository publicDataRepository, PublicDataSearchRepository publicDataSearchRepository, ElasticService elasticService) {
        this.publicDataRepository = publicDataRepository;
        this.publicDataSearchRepository = publicDataSearchRepository;
        this.elasticService = elasticService;
    }

    @Test
    void elasticTest(){
//        List<PublicDataEntity> dataList = publicDataRepository.findAll();
//        elasticService.init(dataList);


        long startTime = System.currentTimeMillis();
        List<PublicDataDocument> publicDataDocuments = elasticService.searchByName("김지현");

        long endTime = System.currentTimeMillis();

        long time = endTime - startTime;

        System.out.println("elastic Time : " + time);


        System.out.println(publicDataDocuments.size());


        startTime = System.currentTimeMillis();
        publicDataDocuments = elasticService.searchByCompany("주니어김영사");

        endTime = System.currentTimeMillis();

        time = endTime - startTime;

        System.out.println("elastic Time : " + time);


        System.out.println(publicDataDocuments.size());

    }
}
