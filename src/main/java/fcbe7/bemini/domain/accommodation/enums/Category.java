package fcbe7.bemini.domain.accommodation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    HOTEL("호텔"),
    MOTEL("모텔"),
    GUESTHOUSE("게스트하우스"),
    CONDO("콘도"),
    PENSION("펜션");
    private final String desc;
    public static Category categoryMaker(String desc) {
        if(desc == null)
            return null;

        return switch (desc) {
            case "호텔" -> HOTEL;
            case "모텔" -> MOTEL;
            case "게스트하우스" -> GUESTHOUSE;
            case "콘도" -> CONDO;
            case "펜션" -> PENSION;
            default -> null;
        };

    }
}
