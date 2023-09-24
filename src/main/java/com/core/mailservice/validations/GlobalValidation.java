package com.core.mailservice.validations;

import lombok.*;
import java.util.*;

import com.core.mailservice.constants.ModConstant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class GlobalValidation {
    private String message;
    private HashMap<String,String> errors;
    private  ModConstant.StatusCode statusCode;
}
