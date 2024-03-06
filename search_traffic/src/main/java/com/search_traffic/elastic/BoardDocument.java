package com.search_traffic.elastic;

import com.search_traffic.rdb.BoardEntity;
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
@Document(indexName = "board")
public class BoardDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private String content;


    public static BoardDocument from(BoardEntity entity){
        return new BoardDocument(
                entity.getId(),
                entity.getTitle(),
                entity.getContent()
        );
    }
}
