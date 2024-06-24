package fcbe7.bemini.domain.customer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public abstract class Loginable {
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식이 올바르지 않습니다")
    protected final String email;
    @NotBlank(message = "비밀번호가 없습니다")
    protected final String password;
}
