package fcbe7.bemini.domain.rating.service;

import fcbe7.bemini.domain.accommodation.Accommodation;
import fcbe7.bemini.domain.customer.Customer;
import fcbe7.bemini.domain.order.Order;
import fcbe7.bemini.domain.order.repository.OrderRepository;
import fcbe7.bemini.domain.rating.Rating;
import fcbe7.bemini.domain.rating.repository.RatingRepository;
import fcbe7.bemini.domain.rating.request.RatingRequest;
import fcbe7.bemini.domain.rating.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final OrderRepository orderRepository;

    public void add(Customer customer, RatingRequest request, Long orderId) {
        if (request.score() % 0.5 != 0 && (request.score() >= 0 || request.score() <= 5)) {
            throw new RuntimeException("score 는 0.0 ~ 5.0 사이 0.5 단위만 입력해주세요");
        }

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("주문 정보를 확인해 주세요")
        );

        if (!order.getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("주문 정보를 확인해 주세요");
        }

        ratingRepository.save(Rating.toEntity(order, request));

        Accommodation orderAccommodation = order.getAccommodation();
        orderAccommodation.setRating(ratingRepository.averages(orderAccommodation.getId()));
    }

    public List<CommentResponse> comment(Long accommodationId) {
        return ratingRepository.findAllByOrders_Accommodation_Id(accommodationId)
                .stream().map(Rating::toComment)
                .toList();
    }
}
