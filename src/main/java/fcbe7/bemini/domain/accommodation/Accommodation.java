package fcbe7.bemini.domain.accommodation;

import fcbe7.bemini.domain.accommodation.enums.Area;
import fcbe7.bemini.domain.accommodation.enums.Category;
import fcbe7.bemini.domain.accommodation.response.AccommodationItemResponse;
import fcbe7.bemini.domain.accommodation.response.AccommodationOrderResponse;
import fcbe7.bemini.domain.accommodation.response.AccommodationResponse;
import fcbe7.bemini.domain.room.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String accommodationName;
    @Column(nullable = false)
    private Integer minPrice;
    @Column(nullable = false)
    private Integer maximumPeople;
    @Column(nullable = false)
    private String detail;
    @Column(nullable = false)
    private Boolean soldOut;
    @Column(nullable = false)
    private String tel;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Area area;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column
    private Double mapx;
    @Column
    private Double mapy;
    @Column
    private Double discount;
    @Column
    private LocalDateTime discountDate;
    @Column(nullable = false)
    private Integer sigunguCode;
    @Column
    @Setter
    private Double rating;
    @Column
    private String service;
    @OneToMany
    private List<Room> roomList;

    public AccommodationResponse toDto() {
        return new AccommodationResponse(
                id,
                area.getDesc(),
                image,
                accommodationName,
                minPrice,
                address,
                maximumPeople,
                soldOut,
                category.getDesc(),
                tel,
                detail,
                discount,
                discountDate.toLocalDate(),
                rating
        );
    }

    public AccommodationItemResponse toItemDto() {
        return new AccommodationItemResponse(
                id,
                area.getDesc(),
                image,
                accommodationName,
                address,
                category.getDesc(),
                tel,
                detail,
                discount,
                discountDate.toLocalDate(),
                rating,
                roomList.stream()
                        .map(Room::toRoomDto)
                        .toList(),
                new ArrayList<>()
        );
    }

    public AccommodationOrderResponse toOrderDto(LocalDate checkIn, LocalDate checkOut, Integer peoples, Room room) {
        return new AccommodationOrderResponse(
                id,
                area.getDesc(),
                image,
                accommodationName,
                address,
                category.getDesc(),
                detail,
                checkIn,
                checkOut,
                tel,
                room.getMaximumPeople(),
                peoples,
                room.toOrderDto()
        );
    }

    public void soldOut() {
        this.soldOut = true;
    }
}
