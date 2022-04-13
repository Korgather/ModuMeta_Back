package com.metaverse.station.back.web.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

@Setter
@Getter
public class ValidationErrorResponse {

    private String code;

    private String description;

    private String error;

    @Builder
    public ValidationErrorResponse(String code, String description, String error) {
        this.code = code;
        this.description = description;
        this.error = error;
    }

//    public ValidationErrorResponse(String code, String description) {
//        this.code = code;
//        this.description = description;
//    }
//
//    public ValidationErrorResponse(String code, String description, String error) {
//        this.code = code;
//        this.description = description;
//        this.error = error;
//    }


    public static ValidationErrorResponse makeErrorResponse(BindingResult bindingResult){
        String code = "";
        String description = "";
        String error = "";

        //에러가 있다면
        if(bindingResult.hasErrors()){
            //DTO에 설정한 meaasge값을 가져온다
            error = bindingResult.getFieldError().getDefaultMessage();

            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();

//            System.out.println(bindResultCode);

            switch (bindResultCode){
                case "NotNull":
                    code = ValidationErrorCode.NOT_NULL.getCode();
                    description = ValidationErrorCode.NOT_NULL.getDescription();
                    break;
                case "Min":
                    code = ValidationErrorCode.MIN_VALUE.getCode();
                    description = ValidationErrorCode.MIN_VALUE.getDescription();
                    break;
                case "NotBlank":
                    code = ValidationErrorCode.NOT_BLANK.getCode();
                    description = ValidationErrorCode.NOT_BLANK.getDescription();
                    break;
                case "Length":
                    code = ValidationErrorCode.LENGTH.getCode();
                    description = ValidationErrorCode.LENGTH.getDescription();
                    break;
                case "Enum":
                    code = ValidationErrorCode.ENUM.getCode();
                    description = ValidationErrorCode.ENUM.getDescription();
                    break;
                case "Url":
                    code = ValidationErrorCode.URL.getCode();
                    description = ValidationErrorCode.URL.getDescription();
            }
        }

        return new ValidationErrorResponse(code, description, error);
    }
}