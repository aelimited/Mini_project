package fcbe7.bemini.domain.customer.service;

import fcbe7.bemini.domain.customer.Customer;
import fcbe7.bemini.domain.customer.repository.CustomerRepository;
import fcbe7.bemini.domain.customer.request.*;
import fcbe7.bemini.domain.customer.response.CustomerResponse;
import fcbe7.bemini.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CustomerResponse register(RegisterRequest request) {
        return customerRepository.save(
                Customer.from(request, passwordEncoder.encode(request.getPassword()))
        ).toDto();
    }

    public void validateEmail(EmailValidateRequest request) {
        if (customerRepository.existsByEmail(request.email())) {
            throw new RuntimeException("이미 존재하는 이메일 입니다");
        }
    }

    public String makeJwt(Loginable request) {
        return JwtUtils.createToken(
                validateLoginInfo(request).getEmail()
        );
    }

    public CustomerResponse login(Loginable request) {
        return validateLoginInfo(request).toDto();
    }

    private Customer validateLoginInfo(Loginable request) {
        Customer customer = customerRepository.findByUserEmail(request.getEmail(),"아이디와 비밀번호를 확인 해 주세요");

        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new RuntimeException("아이디와 비밀번호를 확인 해 주세요");
        }

        return customer;
    }

    public CustomerResponse validToken(Customer customer) {
        return customer.toDto();
    }
}
