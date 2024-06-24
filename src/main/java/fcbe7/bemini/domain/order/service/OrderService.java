package fcbe7.bemini.domain.order.service;

import fcbe7.bemini.domain.accommodation.Accommodation;
import fcbe7.bemini.domain.accommodation.repository.AccommodationRepository;
import fcbe7.bemini.domain.customer.Customer;
import fcbe7.bemini.domain.order.Order;
import fcbe7.bemini.domain.order.repository.OrderRepository;
import fcbe7.bemini.domain.order.request.OrderRequest;
import fcbe7.bemini.domain.order.request.OrderUpdateRequest;
import fcbe7.bemini.domain.order.request.PurchaseRequest;
import fcbe7.bemini.domain.order.response.OrderAllResponse;
import fcbe7.bemini.domain.room.Room;
import fcbe7.bemini.domain.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final AccommodationRepository accommodationRepository;
    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public List<OrderAllResponse> getCartList(Customer customer) {
        return orderRepository.findAllByPurchasedFalseAndCustomer(customer)
                .stream().map(Order::toDto)
                .toList();
    }

    public void add(Customer customer, Long accommodationId, Long roomId, OrderRequest orderRequest) {
        validAccommodationOrRoom(customer, accommodationId, roomId, orderRequest);
    }

    private Order validAccommodationOrRoom(Customer customer, Long accommodationId, Long roomId, OrderRequest orderRequest) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow(
                () -> new RuntimeException("숙소 아이디를 확인해 주세요")
        );
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new RuntimeException("ROOM 아이디를 확인해 주세요")
        );

        if (!accommodation.getRoomList().contains(room)) {
            throw new RuntimeException("숙소정보를 확인해 주세요");
        }

        List<Order> alreadyOrders = orderRepository.findAllByAccommodation_IdAndRoom_Id(accommodationId, roomId);

        for (Order alreadyOrder : alreadyOrders) {
            if (alreadyOrder.checkDuplicate(orderRequest)) {
                throw new RuntimeException("중복되는 주문이 존재합니다");
            }
        }

        Order order = Order.toEntity(orderRequest, customer, room, accommodation);

        return orderRepository.save(order);
    }

    public void delete(Customer customer, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("주문을 확인해 주세요")
        );

        if (!order.getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("고객정보가 동일하지 않습니다");
        }

        orderRepository.delete(order);
    }

    public OrderAllResponse update(Customer customer, Long orderId, OrderUpdateRequest orderUpdateRequest) {
        return null;
    }

    @Transactional(readOnly = true)
    public List<OrderAllResponse> getPurchasedList(Customer customer) {
        return orderRepository.findAllByPurchasedTrueAndCustomer(customer)
                .stream().map(Order::toDto)
                .toList();
    }

    public List<OrderAllResponse> purchase(Customer customer, List<PurchaseRequest> purchasedList) {
        List<Order> orderList = orderRepository.findAllByIdIn(
                purchasedList.stream().map(PurchaseRequest::orderId).toList()
        );

        orderList.forEach(order -> {
            order.checkCustomer(customer.getId());
            order.purchase();
        });

        return orderList.stream().map(Order::toDto)
                .toList();
    }

    public OrderAllResponse payNow(OrderRequest orderRequest, Customer customer, Long accommodationId, Long roomId) {
        Order order = validAccommodationOrRoom(customer, accommodationId, roomId, orderRequest);
        order.purchase();

        return order.toDto();
    }
}
