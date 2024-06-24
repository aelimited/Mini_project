package fcbe7.bemini.domain.rating.controller;

import fcbe7.bemini.domain.customer.Customer;
import fcbe7.bemini.domain.rating.request.RatingRequest;
import fcbe7.bemini.domain.rating.response.CommentResponse;
import fcbe7.bemini.domain.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/rating/{orderId}")
    public ResponseEntity<String> add(
            @AuthenticationPrincipal Customer customer,
            @RequestBody RatingRequest request,
            @PathVariable Long orderId
    ) {
        ratingService.add(customer, request, orderId);
        return ResponseEntity.ok("평점 등록 완료");
    }

    @GetMapping("/comment/{accommodationId}")
    public ResponseEntity<List<CommentResponse>> getComment(
            @PathVariable("accommodationId") Long id
    ){
        return ResponseEntity.ok(ratingService.comment(id));
    }
}
