package com.search_traffic.rdb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicDataRepository extends JpaRepository<PublicDataEntity,Integer> {
    List<PublicDataEntity> findByNameContaining(String name);

    List<PublicDataEntity> findByCompanyContaining(String locate);
}
