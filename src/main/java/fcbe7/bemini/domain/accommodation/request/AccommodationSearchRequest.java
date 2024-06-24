package fcbe7.bemini.domain.accommodation.request;

import fcbe7.bemini.domain.accommodation.enums.Area;
import fcbe7.bemini.domain.accommodation.enums.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class AccommodationSearchRequest {
    private final Area area;
    private final Category category;
    private final LocalDateTime discountStartDay;
    private final Integer maxPeople;
    private final LocalDateTime discountEndDay;

    public AccommodationSearchRequest(AccommodationSearchControllerRequest request) {
        this.area = Area.areaMaker(request.area());
        this.category = Category.categoryMaker(request.category());
        this.maxPeople = request.maxPeople();
        this.discountStartDay = request.discountStartDay();
        this.discountEndDay = request.discountEndDay();
    }
}

