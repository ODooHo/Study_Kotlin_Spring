package com.search_traffic.elastic;

import com.search_traffic.rdb.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElasticService {
    private final BoardSearchRepository boardSearchRepository;

    public void init(List<BoardEntity> boardEntityList){
        List<BoardDocument> boardList = boardEntityList.stream()
                .map(BoardDocument::from)
                .toList();
        boardSearchRepository.saveAll(boardList);
    }

    public List<BoardDocument> searchByTitle(String title){
        return boardSearchRepository.findByTitleContaining(title);
    }
}
