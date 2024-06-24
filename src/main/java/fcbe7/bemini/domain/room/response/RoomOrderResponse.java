package fcbe7.bemini.domain.room.response;

public record RoomOrderResponse(
        Long roomId,
        String roomName,
        Integer price
) {
}
