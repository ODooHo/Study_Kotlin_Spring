package com.search_traffic.rdb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardEntity> searchByTitle(String title){
        return boardRepository.findByTitleContaining(title);
    }
}
