package fcbe7.bemini.domain.accommodation.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AccommodationSearchControllerRequest(
        String area,
        String category,
        Integer maxPeople,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", shape = JsonFormat.Shape.STRING)
        LocalDateTime discountStartDay,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", shape = JsonFormat.Shape.STRING)
        LocalDateTime discountEndDay
) {
}

