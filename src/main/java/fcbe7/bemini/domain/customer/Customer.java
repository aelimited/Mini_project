package fcbe7.bemini.domain.customer;

import fcbe7.bemini.domain.customer.request.RegisterRequest;
import fcbe7.bemini.domain.customer.response.CustomerResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;

    private Customer(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    //정적 팩토리 메소드
    public static Customer from(RegisterRequest request, String password) {
        return new Customer(
                request.getEmail(),
                password,
                request.getName()
        );
    }

    public CustomerResponse toDto() {
        return new CustomerResponse(
                this.id,
                this.email,
                this.name
        );
    }
}
