package com.metaverse.station.back.web.exception;

import lombok.Getter;

public enum ValidationErrorCode {

    NOT_NULL("ERROR_CODE_0001","필수값이 누락되었습니다")
    , MIN_VALUE("ERROR_CODE_0002", "최소값보다 커야 합니다.")
    ,NOT_BLANK("ERROR_CODE_003","null 또는 공백을 입력했습니다.")
    ,LENGTH("ERROR_CODE_004","최소 길이를 만족하지 못합니다.")
    ,ENUM("ERROR_CODE_005","없는 카테고리 입니다.")
    ,URL("ERROR_CODE_005","유효하지 않은 URL 입니다.")
    ,PATTERN("ERROR_CODE_006", "형식이 올바르지 않습니다.")
    ;

    @Getter
    private String code;

    @Getter
    private String description;

    ValidationErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
