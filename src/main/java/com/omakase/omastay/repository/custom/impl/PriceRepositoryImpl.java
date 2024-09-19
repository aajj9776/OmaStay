package com.omakase.omastay.repository.custom.impl;
import com.omakase.omastay.repository.custom.PriceRepositoryCustom;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import static com.omakase.omastay.entity.QPrice.price;

public class PriceRepositoryImpl implements PriceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PriceRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Tuple> findAvgPriceByHostIds(List<Integer> hostIds, StartEndVo startEndDay) {
        LocalDateTime start = startEndDay.getStart();
        LocalDateTime end = startEndDay.getEnd();
        // 1. 지금 예약이 가능한 호텔의 예약 가능한 방 중 제일가격이 낮은 방의 가격을 가져옴
        List<Integer> lowestPriceRoomsByHost = queryFactory
                .select(price.roomInfo.id)
                .from(price)
                .where(price.hostInfo.id.in(hostIds))
                .orderBy(price.regularPrice.asc())
                .groupBy(price.hostInfo.id)
                .fetch();

        // 2. 그 중 예약 시작일과 종료일을 price의 peak_start peak_end semi_start semi_end와 비교하여
        // 내가 예약하는 시작일과 종료일의 1박에 해당하는 평균 가격을 가져옴

        return List.of();
    }
    
    /*@Override
    public List<Price> findPricesByHostIdAndDateRange(Long hostId, Integer minPrice, LocalDateTime start, LocalDateTime end) {
        QPrice price = QPrice.price;

        return queryFactory
                .selectFrom(price)
                .where(price.roomInfo.hostInfo.id.eq(hostId)
                        .and(price.startDate.before(end))
                        .and(price.endDate.after(start))
                        .and(price.regularPrice.eq(minPrice)))
                .fetch();
    }*/
}
