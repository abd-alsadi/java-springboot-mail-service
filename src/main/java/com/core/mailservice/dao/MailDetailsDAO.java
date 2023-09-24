package com.core.mailservice.dao;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.core.mailservice.models.*;
import com.core.mailservice.repositories.*;

import java.util.*;
import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate; 

@Service
public class MailDetailsDAO{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private MailDetailsRepository repo;


    @Autowired
    private EntityManagerFactory emf;


    public List<MailDetailsModel> GetAll(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            List<MailDetailsModel> data= repo.findAll();
            trans.commit();
            return data;
        }catch(Exception e){
            trans.rollback();
            throw e;
        }
    }

    
    public MailDetailsModel GetByID(UUID id){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
    try{
        trans.begin();
        MailDetailsModel data= repo.findById(id).orElse(null);
        trans.commit();
        return data;
    }catch(Exception e){
        trans.rollback();
        throw e;
    }
    }

    public MailDetailsModel Add(MailDetailsModel object){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            object.setId(UUID.randomUUID()); 
            LocalDate date = LocalDate.now();
            object.setCreatedAt(date.toString());
            object.setUpdatedAt(date.toString());
            trans.begin();
            MailDetailsModel data= repo.save(object);
            trans.commit();
            return data;
         }catch(Exception e){
            trans.rollback();
            throw e;
        }
    }

    public MailDetailsModel Delete(UUID id){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            MailDetailsModel data=GetByID(id);
            trans.begin();
            repo.deleteById(id);
            trans.commit();
            return data;
        }catch(Exception e){
            trans.rollback();
            throw e;
        }
    }

    public MailDetailsModel Update(UUID id,MailDetailsModel object){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            object.setId(id); 
            LocalDate date = LocalDate.now();
            object.setUpdatedAt(date.toString());
            MailDetailsModel data= repo.save(object);
            trans.commit();
            return data;
        }catch(Exception e){
            trans.rollback();
            throw e;
        }
    }
}
