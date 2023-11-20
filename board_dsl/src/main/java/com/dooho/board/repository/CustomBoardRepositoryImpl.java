//package com.dooho.board.repository;
//
//
//import com.dooho.board.entity.BoardEntity;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Repository
//public class CustomBoardRepositoryImpl extends QuerydslRepositorySupport implements CustomBoardRepository{
//    @PersistenceContext
//    private EntityManager em;
//
//    public CustomBoardRepositoryImpl() {
//        super(BoardEntity.class);
//    }
//
//    private final JPAQueryFactory queryFactory;
//    private final Q
//
//    @Override
//    public List<BoardEntity> findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(LocalDate boardWriteDate) {
//        return
//    }
//
//    private
//
//
//
//
//}
