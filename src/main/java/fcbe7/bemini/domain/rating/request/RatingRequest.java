package fcbe7.bemini.domain.rating.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RatingRequest(
        @NotNull
        Double score,
        @NotBlank
        String comment
) {
}
