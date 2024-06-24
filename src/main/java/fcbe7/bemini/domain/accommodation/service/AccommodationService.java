package fcbe7.bemini.domain.accommodation.service;

import fcbe7.bemini.domain.accommodation.Accommodation;
import fcbe7.bemini.domain.accommodation.repository.AccommodationRepository;
import fcbe7.bemini.domain.accommodation.request.AccommodationSearchRequest;
import fcbe7.bemini.domain.accommodation.response.AccommodationItemResponse;
import fcbe7.bemini.domain.accommodation.response.AccommodationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public AccommodationItemResponse findAccommodation(Long id) {
        return accommodationRepository.findById(id).map(Accommodation::toItemDto).orElseThrow(
                () -> new RuntimeException("조회가 되지 않습니다.")
        );
    }

    public Page<AccommodationResponse> searchList(Pageable pageable, AccommodationSearchRequest request) {
        return accommodationRepository.searchPage(request, pageable)
                .map(Accommodation::toDto);

    }
}
