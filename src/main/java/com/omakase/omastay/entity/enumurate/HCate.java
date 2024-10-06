package com.omakase.omastay.entity.enumurate;

public enum HCate {
    /*모텔
    * 호텔/리조트
    * 풀빌라
    * 게하/한옥
    * */
    MOTEL("모텔"),
    HOTEL_RESORT("호텔/리조트"),
    POOL_VILLA("풀빌라"),
    GUESTHOUSE_HANOK("게하/한옥");

    private final String description;

    HCate(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getHCateTypeInKorean(int typeIndex) {
        // 숫자를 Enum으로 변환
        HCate type = HCate.values()[typeIndex];

        // Enum에 맞는 한국어 문자열을 반환
        return switch (type) {
            case MOTEL -> "모텔";
            case HOTEL_RESORT -> "호텔/리조트";
            case POOL_VILLA -> "풀빌라";
            case GUESTHOUSE_HANOK -> "게하/한옥";
            default -> throw new IllegalArgumentException("Unknown accommodation type index: " + typeIndex);
        };
    }

}
