package fcbe7.bemini.domain.room;

import fcbe7.bemini.domain.room.response.RoomDetailResponse;
import fcbe7.bemini.domain.room.response.RoomOrderResponse;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //방 이름 - 호실이나 싱글 트윈 등
    @Column(nullable = false)
    private String roomName;
    //방 별 최대 인원 수
    @Column(nullable = false)
    private Integer maximumPeople;
    //방 별 가격
    @Column(nullable = false)
    private Integer price;
    //방 재고
    @Column(nullable = false)
    private Integer roomCount;

    public RoomDetailResponse toRoomDto(){
        return new RoomDetailResponse(
                id,
                roomName,
                maximumPeople,
                price,
                roomCount
        );
    }

    public RoomOrderResponse toOrderDto(){
        return new RoomOrderResponse(
                id,
                roomName,
                price
        );
    }

    public void purchased() {
        if(roomCount<=0){
            throw new RuntimeException("남은 방이 없습니다");
        }
        roomCount--;
    }

    public boolean soldOut(){
        return roomCount<=0;
    }
}
