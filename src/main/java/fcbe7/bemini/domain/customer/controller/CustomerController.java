package fcbe7.bemini.domain.customer.controller;

import fcbe7.bemini.domain.customer.Customer;
import fcbe7.bemini.domain.customer.request.*;
import fcbe7.bemini.domain.customer.response.CustomerResponse;
import fcbe7.bemini.domain.customer.service.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> register(
            @Valid @RequestBody RegisterRequest request,
            HttpServletResponse response
    ) {
        CustomerResponse register = customerService.register(request);
        response.setHeader("JWTTOKEN", customerService.makeJwt(request));
        return ResponseEntity.ok(register);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateEmail(
            @Valid @RequestBody EmailValidateRequest request
    ) {
        customerService.validateEmail(request);
        return ResponseEntity.ok("가입 가능한 이메일 입니다");
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response
    ) {
        response.setHeader("JWTTOKEN", customerService.makeJwt(request));
        return ResponseEntity.ok(customerService.login(request));
    }

    @GetMapping("/authentication")
    public ResponseEntity<CustomerResponse> validToken(
            @AuthenticationPrincipal Customer customer
    ){
        return ResponseEntity.ok(customerService.validToken(customer));
    }
}
