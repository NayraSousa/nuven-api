package com.example.nuvenapi.api.filter.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemBody {

    private String status;
    private String title;
    private String detail;
}
