package com.search_traffic.elastic;

import com.search_traffic.rdb.PublicDataEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Document(indexName = "public")
public class PublicDataDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String author;
    private String company;
    private String year;


    public static PublicDataDocument of(String name,String author,String company, String year){
        return new PublicDataDocument(null,name,author,company,year);
    }

    public static PublicDataDocument from(PublicDataEntity entity){
        return new PublicDataDocument(
                entity.getId(),
                entity.getName(),
                entity.getAuthor(),
                entity.getCompany(),
                entity.getYear()
        );
    }
}
