package fcbe7.bemini.domain.customer.request;

import jakarta.validation.constraints.Pattern;

public record EmailValidateRequest(
        @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식이 올바르지 않습니다")
        String email
) {
}
