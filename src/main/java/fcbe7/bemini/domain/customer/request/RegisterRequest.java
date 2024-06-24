package fcbe7.bemini.domain.customer.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterRequest extends Loginable {
    @NotBlank(message = "이름이 없습니다")
    private final String name;

    public RegisterRequest(String email, String password, String name) {
        super(email, password);
        this.name = name;
    }
}
