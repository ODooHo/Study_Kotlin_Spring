package com.search_traffic.parser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CsvServiceTest {

    @Autowired
    private CsvService csvService;

    @Test
    public void parseTest() throws Exception {
//        csvService.testDataSet();
    }
}
