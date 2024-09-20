package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 874856803L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final StringPath accessToken = createString("accessToken");

    public final com.omakase.omastay.vo.QAddressVo addressVo;

    public final QGrade grade;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.omakase.omastay.vo.QUserProfileVo memberProfile;

    public final StringPath memBirth = createString("memBirth");

    public final EnumPath<com.omakase.omastay.entity.enumurate.BooleanStatus> memEmailCheck = createEnum("memEmailCheck", com.omakase.omastay.entity.enumurate.BooleanStatus.class);

    public final EnumPath<com.omakase.omastay.entity.enumurate.Gender> memGender = createEnum("memGender", com.omakase.omastay.entity.enumurate.Gender.class);

    public final DateTimePath<java.time.LocalDateTime> memJoinDate = createDateTime("memJoinDate", java.time.LocalDateTime.class);

    public final StringPath memName = createString("memName");

    public final StringPath memNone = createString("memNone");

    public final StringPath memPhone = createString("memPhone");

    public final EnumPath<com.omakase.omastay.entity.enumurate.Social> memSocial = createEnum("memSocial", com.omakase.omastay.entity.enumurate.Social.class);

    public final StringPath refreshToken = createString("refreshToken");

    public final ListPath<Reservation, QReservation> reservations = this.<Reservation, QReservation>createList("reservations", Reservation.class, QReservation.class, PathInits.DIRECT2);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addressVo = inits.isInitialized("addressVo") ? new com.omakase.omastay.vo.QAddressVo(forProperty("addressVo")) : null;
        this.grade = inits.isInitialized("grade") ? new QGrade(forProperty("grade")) : null;
        this.memberProfile = inits.isInitialized("memberProfile") ? new com.omakase.omastay.vo.QUserProfileVo(forProperty("memberProfile")) : null;
    }

}

