package com.example.nuvenapi.domain.exception.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

        ENTITY_NOT_FOUND("Entity not found"),
        VALIDATION_ERROR("Error when validating fields"),
        INCOMPREHENSIBLE_MESSAGE("Incomprehensible message"),
        INVALID_PARAMETER("Invalid parameter"),
        METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED"),
        RESOURCE_NOT_FOUND("Resource not found"),
        USER_NOT_FOUND("User not found"),
        INCOMPATIBLE_PASSWORD("Incompatible password"),
        MISSING_TOKEN("The token is missing"),
        SUBJECT_ERROR("Error when extracting subject");



        private String title;

        ErrorCode(String title) {
            this.title = title;
        }
}
