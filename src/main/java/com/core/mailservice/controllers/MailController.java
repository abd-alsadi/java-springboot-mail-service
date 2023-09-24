package com.core.mailservice.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.core.mailservice.services.*;
import com.core.mailservice.models.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;

// import org.springframework.security.core.Authentication;
import java.util.*;
import com.core.mailservice.constants.*;
import com.core.mailservice.helpers.AuthHelper;
import com.core.mailservice.responses.*;


@RestController
@RequestMapping("api/Mail")
@RequiredArgsConstructor
public class MailController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MailDetailsService service;

    @Autowired 
    Tracer tracer;

    @PostMapping(value="/SendMail")
    public ResponseEntity SendMessage(@RequestBody MailDetailsModel model,Authentication auth){
        DataResponse response=new DataResponse();
        try{     
             AuthunticationModel authUser = AuthHelper.getAuthinticationUser(auth);
             Object data= service.SendMail(authUser,model);
             response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
        }catch(Exception e){
             response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }   

    @GetMapping("/GetAll")
    public ResponseEntity GetAll(Authentication auth){
        Span newSpan=tracer.nextSpan().name("GetAll").tag("tag0","tag0 value");
        logger.info(ModConstant.START_TAG);
        DataResponse response=new DataResponse();
        try(Tracer.SpanInScope  w = this.tracer.withSpan(newSpan.start())){
            newSpan.tag("tag1","tag1 value");
            try{
                AuthunticationModel authUser = AuthHelper.getAuthinticationUser(auth);
                Object data= service.GetAll(authUser);
                response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
                logger.info(ModConstant.SUCCESS_TAG);
                newSpan.tag("tag2","tag2 value");
            }catch(Exception e){
                response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
                logger.info(ModConstant.NOT_SUCCESS_TAG);
            }
        }finally{
            newSpan.end();
            logger.info(ModConstant.END_TAG);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/GetByID/{id}")
    public ResponseEntity<DataResponse> GetByID(@PathVariable("id") UUID id,Authentication auth){
        DataResponse response=new DataResponse();
        try{
            AuthunticationModel authUser = AuthHelper.getAuthinticationUser(auth);
             Object data= service.GetByID(authUser,id);
             response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
        }catch(Exception e){
             response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @PostMapping("/Add")
     public ResponseEntity<DataResponse> Add(@Valid @RequestBody MailDetailsModel object,Authentication auth){
         DataResponse response=new DataResponse();
         try{
            AuthunticationModel authUser = AuthHelper.getAuthinticationUser(auth);
             Object data= service.Add(authUser,object);
             response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
         }catch(Exception e){
             response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
         }
         return new ResponseEntity(response, HttpStatus.OK);
    }
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<DataResponse> Delete(@PathVariable("id") UUID id,Authentication auth){
        DataResponse response=new DataResponse();
        try{
            AuthunticationModel authUser = AuthHelper.getAuthinticationUser(auth);
            Object data= service.Delete(authUser, id);
            response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
        }catch(Exception e){
           response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @PutMapping("/Update/{id}")
    public ResponseEntity<DataResponse> Update(@PathVariable("id") UUID id,@RequestBody MailDetailsModel object,Authentication auth){
        DataResponse response=new DataResponse();
       try{
        AuthunticationModel authUser = AuthHelper.getAuthinticationUser(auth);
            Object data= service.Update(authUser,id,object);
            response = new DataResponse(null, data, ModConstant.StatusCode.SUCCESS);
       }catch(Exception e){
            response = new DataResponse(e.getMessage(), null, ModConstant.StatusCode.ERROR);
       }
       return new ResponseEntity(response, HttpStatus.OK);
    }
}
