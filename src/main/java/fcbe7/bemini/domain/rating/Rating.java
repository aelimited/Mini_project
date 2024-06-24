package fcbe7.bemini.domain.rating;

import fcbe7.bemini.domain.customer.Customer;
import fcbe7.bemini.domain.order.Order;
import fcbe7.bemini.domain.rating.request.RatingRequest;
import fcbe7.bemini.domain.rating.response.CommentResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Order orders;
    @Column(nullable = false)
    private Double score;
    @Column(nullable = false)
    private String comment;

    private Rating(Order orders, Double score, String comment, Customer customer) {
        this.orders = orders;
        this.score = score;
        this.comment = comment;
        this.customer = customer;
    }

    public static Rating toEntity(Order order, RatingRequest request) {
        return new Rating(
                order,
                request.score(),
                request.comment(),
                order.getCustomer()
        );
    }

    public CommentResponse toComment(){
        return new CommentResponse(
                id,
                score,
                comment
        );
    }
}
