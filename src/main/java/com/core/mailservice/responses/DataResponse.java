package com.core.mailservice.responses;

import com.core.mailservice.constants.ModConstant;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class DataResponse {
    private String message;
    private Object data;
    private  ModConstant.StatusCode statusCode;
}
