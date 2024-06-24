package fcbe7.bemini.domain.order.response;

import fcbe7.bemini.domain.accommodation.response.AccommodationOrderResponse;

public record OrderAllResponse(
        Long id,
        AccommodationOrderResponse accommodation
) {
}
