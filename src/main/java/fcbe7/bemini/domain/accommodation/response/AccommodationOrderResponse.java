package fcbe7.bemini.domain.accommodation.response;

import fcbe7.bemini.domain.room.response.RoomOrderResponse;

import java.time.LocalDate;

public record AccommodationOrderResponse(
        Long accommodationId,
        String area,
        String image,
        String accommodationName,
        String address,
        String category,
        String detail,
        LocalDate checkIn,
        LocalDate checkOut,
        String tel,
        Integer maxPeoples,
        Integer peoples,
        RoomOrderResponse room
) {
}
