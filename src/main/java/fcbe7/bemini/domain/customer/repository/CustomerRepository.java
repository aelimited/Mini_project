package fcbe7.bemini.domain.customer.repository;

import fcbe7.bemini.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    default Customer findByUserEmail(String email){
        return findByEmail(email).orElseThrow(
                ()->new RuntimeException("유저가 존재하지 않습니다")
        );
    }

    default Customer findByUserEmail(String email, String message){
        return findByEmail(email).orElseThrow(
                ()->new RuntimeException(message)
        );
    }

    Boolean existsByEmail(String email);
}
