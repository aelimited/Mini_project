package fcbe7.bemini.domain.customer.controller;

import fcbe7.bemini.domain.customer.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//다른 컨트롤러 만들 때 참고용
//컨트롤러 생성 후에 삭제 예정
@RequestMapping("/api/products")
@RestController
public class TesterController {
    @GetMapping("/test")
    public ResponseEntity<String> test(
            @AuthenticationPrincipal Customer customer
    ) {
        return ResponseEntity.ok(customer.getEmail());
    }
}
