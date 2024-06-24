package fcbe7.bemini.domain.order.controller;

import fcbe7.bemini.domain.customer.Customer;
import fcbe7.bemini.domain.order.request.OrderRequest;
import fcbe7.bemini.domain.order.response.OrderAllResponse;
import fcbe7.bemini.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderPayController {
    private final OrderService orderService;

    //  바로 구매
    @PostMapping("/{accommodationId}/{roomId}")
    public ResponseEntity<OrderAllResponse> payNow(
            @AuthenticationPrincipal Customer customer,
            @PathVariable Long accommodationId,
            @PathVariable Long roomId,
            @RequestBody OrderRequest orderRequest
    ) {
        return ResponseEntity.ok(orderService.payNow(orderRequest, customer, accommodationId, roomId));
    }
}
