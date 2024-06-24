package fcbe7.bemini.domain.room.response;

public record RoomDetailResponse(
        Long id,
        String roomName,
        Integer maximumPeople,
        Integer price,
        Integer roomCount
) {
}
