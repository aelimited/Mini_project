package fcbe7.bemini.domain.rating.repository;

import fcbe7.bemini.domain.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long>, RatingQueryRepository {
    List<Rating> findAllByOrders_Accommodation_Id(Long id);
}
