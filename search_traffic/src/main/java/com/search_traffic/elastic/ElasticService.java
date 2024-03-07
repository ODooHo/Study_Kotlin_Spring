package com.search_traffic.elastic;

import com.search_traffic.rdb.PublicDataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElasticService {
    private final PublicDataSearchRepository publicDataSearchRepository;

    public void init(List<PublicDataEntity> boardEntityList){
        List<PublicDataDocument> boardList = boardEntityList.stream()
                .map(PublicDataDocument::from)
                .toList();
        publicDataSearchRepository.saveAll(boardList);
    }
    public List<PublicDataDocument> searchByName(String name){
        return publicDataSearchRepository.findByNameContaining(name);
    }

    public List<PublicDataDocument> searchByCompany(String locate){
        return publicDataSearchRepository.findByCompanyContaining(locate);
    }

}
