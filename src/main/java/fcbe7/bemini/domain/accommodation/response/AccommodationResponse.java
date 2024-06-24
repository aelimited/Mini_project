package fcbe7.bemini.domain.accommodation.response;

import java.time.LocalDate;

public record AccommodationResponse(
        Long id,
        String area,
        String image,
        String accommodationName,
        Integer minPrice,
        String address,
        Integer maximumPeople,
        Boolean soldOut,
        String category,
        String tel,
        String detail,
        Double discount,
        LocalDate discountDate,
        Double rating
) {

}
