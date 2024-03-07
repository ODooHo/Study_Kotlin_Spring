package com.search_traffic.parser;

import com.opencsv.CSVReader;
import com.search_traffic.elastic.PublicDataSearchRepository;
import com.search_traffic.rdb.PublicDataEntity;
import com.search_traffic.rdb.PublicDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvService{

    private final PublicDataSearchRepository publicDataSearchRepository;
    private final PublicDataRepository publicDataRepository;


    public void testDataSet() throws Exception {
        CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("/Users/oduho/Desktop/project/study/search_traffic/src/main/resources/traffic.csv"),"EUC-KR"));
        String[] header = csvReader.peek();
        List<PublicDataEntity> dataList = new ArrayList<>();
        System.out.println(String.join(",", header));
        csvReader.skip(1);
        csvReader.forEach(
                line -> dataList.add(PublicDataEntity.of(line[0],line[1],line[2],line[3]))
        );

        publicDataRepository.saveAll(dataList);

    }

}
