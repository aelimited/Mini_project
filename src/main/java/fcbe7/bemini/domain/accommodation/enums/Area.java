package fcbe7.bemini.domain.accommodation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Area {
    SEOUL("서울"),
    INCHEON("인천"),
    BUSAN("부산"),
    GYONGKI("경기"),
    GANGWON("강원"),
    CHUNGCHEONG("충청"),
    GYEONGSANG("경상"),
    JEOLLA("전라"),
    JEJU("제주");
    private final String desc;

    public static Area areaMaker(String desc) {
        if(desc == null)
            return null;

        return switch (desc) {
            case "서울" -> SEOUL;
            case "인천" -> INCHEON;
            case "부산" -> BUSAN;
            case "경기" -> GYONGKI;
            case "강원" -> GANGWON;
            case "충청" -> CHUNGCHEONG;
            case "경상" -> GYEONGSANG;
            case "전라" -> JEOLLA;
            case "제주" -> JEJU;
            default -> null;
        };

    }

}
