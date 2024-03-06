package com.search_traffic.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BoardSearchRepository extends ElasticsearchRepository<BoardDocument,Integer> {
    List<BoardDocument> findByTitleContaining(String title);
}
