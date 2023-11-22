package com.dooho.board.api.board.search;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSearchEntity is a Querydsl query type for SearchEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSearchEntity extends EntityPathBase<SearchEntity> {

    private static final long serialVersionUID = 559233053L;

    public static final QSearchEntity searchEntity = new QSearchEntity("searchEntity");

    public final NumberPath<Integer> popularSearchCount = createNumber("popularSearchCount", Integer.class);

    public final StringPath popularTerm = createString("popularTerm");

    public QSearchEntity(String variable) {
        super(SearchEntity.class, forVariable(variable));
    }

    public QSearchEntity(Path<? extends SearchEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSearchEntity(PathMetadata metadata) {
        super(SearchEntity.class, metadata);
    }

}

