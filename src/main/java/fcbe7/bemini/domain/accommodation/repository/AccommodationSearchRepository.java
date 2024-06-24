package fcbe7.bemini.domain.accommodation.repository;

import fcbe7.bemini.domain.accommodation.Accommodation;
import fcbe7.bemini.domain.accommodation.request.AccommodationSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccommodationSearchRepository {

    Page<Accommodation> searchPage(AccommodationSearchRequest accommodationSearchRequest, Pageable pageable);
}
