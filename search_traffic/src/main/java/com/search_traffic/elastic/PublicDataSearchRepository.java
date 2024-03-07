package com.search_traffic.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PublicDataSearchRepository extends ElasticsearchRepository<PublicDataDocument,Integer> {
    List<PublicDataDocument> findByNameContaining(String name);

    List<PublicDataDocument> findByCompanyContaining(String company);
}
