package fcbe7.bemini.domain.order.repository;

import fcbe7.bemini.domain.customer.Customer;
import fcbe7.bemini.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByPurchasedFalseAndCustomer(Customer customer);

    List<Order> findAllByPurchasedTrueAndCustomer(Customer customer);

    List<Order> findAllByIdIn(List<Long> id);

    List<Order> findAllByAccommodation_IdAndRoom_Id(Long accommodationId, Long roomId);
}
