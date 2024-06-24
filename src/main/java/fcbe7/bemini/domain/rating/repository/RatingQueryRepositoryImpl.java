package fcbe7.bemini.domain.rating.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fcbe7.bemini.domain.rating.QRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RatingQueryRepositoryImpl implements RatingQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Double averages(Long accommodationId) {
        QRating rating = new QRating("q");
        Double avg = queryFactory.select(
                        rating.score.avg()
                )
                .from(rating)
                .where(rating.orders.accommodation.id.eq(accommodationId))
                .fetchOne();

        if (avg != null) {
            avg = Math.round(avg * 2) / 2.0;
        }

        return avg;
    }
}
