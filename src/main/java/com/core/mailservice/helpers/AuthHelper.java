package com.core.mailservice.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import com.core.mailservice.models.AuthunticationModel;

public class AuthHelper{
    public  static AuthunticationModel getAuthinticationUser(Authentication auth){
      AuthunticationModel model = new AuthunticationModel();
      model.setUsername(auth.getPrincipal().toString());
      model.setClient_id("mq_service");
      OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)auth.getDetails();
      model.setTokenValue(details.getTokenValue());
      model.setTokenType(details.getTokenType());
      model.setSessionID(details.getSessionId());
      model.setAuthunticated(true);
      return model;
    } 
}