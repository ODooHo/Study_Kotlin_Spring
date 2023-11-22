package com.dooho.board.api.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommentEntity is a Querydsl query type for CommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentEntity extends EntityPathBase<CommentEntity> {

    private static final long serialVersionUID = -5743481L;

    public static final QCommentEntity commentEntity = new QCommentEntity("commentEntity");

    public final NumberPath<Integer> boardNumber = createNumber("boardNumber", Integer.class);

    public final StringPath commentContent = createString("commentContent");

    public final NumberPath<Integer> commentId = createNumber("commentId", Integer.class);

    public final StringPath commentUserNickname = createString("commentUserNickname");

    public final StringPath commentUserProfile = createString("commentUserProfile");

    public final DatePath<java.time.LocalDate> commentWriteDate = createDate("commentWriteDate", java.time.LocalDate.class);

    public final StringPath userEmail = createString("userEmail");

    public QCommentEntity(String variable) {
        super(CommentEntity.class, forVariable(variable));
    }

    public QCommentEntity(Path<? extends CommentEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentEntity(PathMetadata metadata) {
        super(CommentEntity.class, metadata);
    }

}

