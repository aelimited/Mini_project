package fcbe7.bemini.domain.order;

import fcbe7.bemini.domain.accommodation.Accommodation;
import fcbe7.bemini.domain.customer.Customer;
import fcbe7.bemini.domain.order.request.OrderRequest;
import fcbe7.bemini.domain.order.response.OrderAllResponse;
import fcbe7.bemini.domain.room.Room;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate checkIn;
    @Column(nullable = false)
    private LocalDate checkOut;
    @Column(nullable = false)
    private Integer peoples;
    @Column(nullable = false)
    private Boolean purchased;
    //True면 구매 완료 상품
    //False면 장바구니에 존재하는 상태
    @ManyToOne
    private Accommodation accommodation;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Customer customer;

    @Builder(access = AccessLevel.PRIVATE)
    private Order(LocalDate checkIn, LocalDate checkOut, Integer peoples, Boolean purchased, Accommodation accommodation, Room room, Customer customer) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.peoples = peoples;
        this.purchased = purchased;
        this.accommodation = accommodation;
        this.room = room;
        this.customer = customer;
    }

    public static Order toEntity(OrderRequest orderRequest, Customer customer, Room room, Accommodation accommodation) {
        return Order.builder()
                .checkIn(orderRequest.getCheckIn())
                .checkOut(orderRequest.getCheckOut())
                .peoples(orderRequest.getPeoples())
                .purchased(false)
                .accommodation(accommodation)
                .room(room)
                .customer(customer)
                .build();
    }

    public OrderAllResponse toDto() {
        return new OrderAllResponse(
                this.id,
                accommodation.toOrderDto(checkIn, checkOut, peoples, room)
        );
    }

    public void checkCustomer(Long customerId) {
        if (!customer.getId().equals(customerId)) {
            throw new RuntimeException("주문을 확인해 주세요");
        }
    }

    public void purchase() {
        if (room.getMaximumPeople() < peoples) {
            throw new RuntimeException("인원 수를 확인해 주세요");
        }
        if (room.getRoomCount() == 0) {
            throw new RuntimeException("남은 방이 없습니다");
        }
        room.purchased();

        if (accommodation.getRoomList().stream().filter(Room::soldOut).count() == accommodation.getRoomList().size()){
            accommodation.soldOut();
        }
        purchased = true;
    }

    public boolean checkDuplicate(OrderRequest request){
        if(!checkIn.equals(request.getCheckIn())){
            return false;
        }
        if (!checkOut.equals(request.getCheckOut())){
            return false;
        }
        return peoples == request.getPeoples();
    }
}
