package fcbe7.bemini.domain.accommodation.controller;

import fcbe7.bemini.domain.accommodation.request.AccommodationSearchControllerRequest;
import fcbe7.bemini.domain.accommodation.request.AccommodationSearchRequest;
import fcbe7.bemini.domain.accommodation.response.AccommodationItemResponse;
import fcbe7.bemini.domain.accommodation.response.AccommodationResponse;
import fcbe7.bemini.domain.accommodation.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accommodation")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping
    public ResponseEntity<Page<AccommodationResponse>> getSearchTestPage(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @ModelAttribute AccommodationSearchControllerRequest request
    ) {
        return ResponseEntity.ok(accommodationService.searchList(pageable, new AccommodationSearchRequest(request)));
    }

    @GetMapping("/{accommodationId}")
    public ResponseEntity<AccommodationItemResponse> findAccommodation(
            @PathVariable("accommodationId") Long id
    ) {
        return ResponseEntity.ok(accommodationService.findAccommodation(id));
    }

}
