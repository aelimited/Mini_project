package fcbe7.bemini.domain.accommodation.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fcbe7.bemini.domain.accommodation.Accommodation;
import fcbe7.bemini.domain.accommodation.QAccommodation;
import fcbe7.bemini.domain.accommodation.request.AccommodationSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class AccommodationSearchRepositoryImpl implements AccommodationSearchRepository {

    private final JPAQueryFactory queryFactory;

    public AccommodationSearchRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Accommodation> searchPage(AccommodationSearchRequest accommodationSearchRequest, Pageable pageable) {
        QAccommodation accommodation = QAccommodation.accommodation;

        JPAQuery<Accommodation> query =
                queryFactory
                        .selectFrom(accommodation)
                        .where(setBooleanBuilder(accommodationSearchRequest, accommodation))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize());

        for (Sort.Order order : pageable.getSort()) {//정렬 조건이 여러개일 수 있음
            query.orderBy(
                    new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC,
                            //정렬을 위한 QueryDSL API
                            new PathBuilder<>(
                                    accommodation.getType(), accommodation.getMetadata()
                            ).get(order.getProperty())));
        }

        QueryResults<Accommodation> queryResult = query.fetchResults();
        return new PageImpl<>(queryResult.getResults(), pageable, queryResult.getTotal());
    }

    private BooleanBuilder setBooleanBuilder(AccommodationSearchRequest request, QAccommodation accommodation) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (request.getArea() != null) {
            booleanBuilder.and(accommodation.area.eq(request.getArea()));
        }
        if (request.getCategory() != null) {
            booleanBuilder.and(accommodation.category.eq(request.getCategory()));
        }

        if (request.getMaxPeople() != null) {
            booleanBuilder.and(accommodation.maximumPeople.goe(request.getMaxPeople()));
        }

        if (request.getDiscountStartDay() != null && request.getDiscountEndDay() != null) {
            booleanBuilder.and(
                    accommodation.discountDate.between(
                            request.getDiscountStartDay(),
                            request.getDiscountEndDay()));
        }

        return booleanBuilder;
    }
}
