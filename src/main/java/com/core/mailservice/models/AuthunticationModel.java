package com.core.mailservice.models;

import lombok.Data;

@Data
public class AuthunticationModel {
    private String client_id;
    private String username;
    private String tokenValue ;
    private String tokenType;
    private String sessionID;
    private boolean isAuthunticated;

}
