package fcbe7.bemini.domain.accommodation.repository;

import fcbe7.bemini.domain.accommodation.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long>, AccommodationSearchRepository{

}
