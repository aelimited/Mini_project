package fcbe7.bemini.domain.accommodation.response;

import fcbe7.bemini.domain.room.response.RoomDetailResponse;

import java.time.LocalDate;
import java.util.List;

public record AccommodationItemResponse(
        Long id,
        String area,
        String image,
        String accommodationName,
        String address,
        String category,
        String tel,
        String detail,
        Double discount,
        LocalDate discountDate,
        Double rating,
        List<RoomDetailResponse> roomList,
        List<String> service
) {

}
