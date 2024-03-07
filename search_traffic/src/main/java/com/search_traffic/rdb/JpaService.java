package com.search_traffic.rdb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaService {
    private final PublicDataRepository publicDataRepository;

    public List<PublicDataEntity> searchByName(String name){
        return publicDataRepository.findByNameContaining(name);
    }

    public List<PublicDataEntity> searchByCompany(String locate){
        return publicDataRepository.findByCompanyContaining(locate);
    }
}
