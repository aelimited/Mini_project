package fcbe7.bemini.domain.order.controller;

import fcbe7.bemini.domain.customer.Customer;
import fcbe7.bemini.domain.order.request.OrderRequest;
import fcbe7.bemini.domain.order.request.OrderUpdateRequest;
import fcbe7.bemini.domain.order.request.PurchaseRequest;
import fcbe7.bemini.domain.order.response.OrderAddResponse;
import fcbe7.bemini.domain.order.response.OrderAllResponse;
import fcbe7.bemini.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // 장바구니 목록조회
    @GetMapping
    public ResponseEntity<List<OrderAllResponse>> cartList(@AuthenticationPrincipal Customer customer) {
        return ResponseEntity.ok(orderService.getCartList(customer));
    }

    // 구매요청
    @PostMapping("/{accommodationId}/{roomId}")
    public ResponseEntity<OrderAddResponse> add(
            @AuthenticationPrincipal Customer customer,
            @PathVariable Long accommodationId,
            @PathVariable Long roomId,
            @RequestBody OrderRequest orderRequest
    ) {
        orderService.add(customer, accommodationId, roomId, orderRequest);
        return ResponseEntity.ok(
                new OrderAddResponse(200, "상품추가성공")
        );
    }

    // 장바구니 항목 삭제
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> delete(
            @AuthenticationPrincipal Customer customer,
            @PathVariable Long orderId
    ) {
        orderService.delete(customer, orderId);
        return ResponseEntity.ok().build();
    }

    // 장바구니 수정
    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderAllResponse> update(
            @AuthenticationPrincipal Customer customer,
            @PathVariable Long orderId,
            @RequestBody OrderUpdateRequest orderUpdateRequest
    ) {

        return ResponseEntity.ok(orderService.update(customer, orderId, orderUpdateRequest));

    }

    // 결제 완료된 상품 조회
    @GetMapping("/paid")
    public ResponseEntity<List<OrderAllResponse>> purchasedList(@AuthenticationPrincipal Customer customer) {
        return ResponseEntity.ok(orderService.getPurchasedList(customer));
    }

    @PostMapping
    public ResponseEntity<List<OrderAllResponse>> purchase(
            @AuthenticationPrincipal Customer customer,
            @RequestBody List<PurchaseRequest> purchasedList
    ) {
        return ResponseEntity.ok(orderService.purchase(customer, purchasedList));
    }
}
