package com.core.mailservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mailservice.models.AuthunticationModel;
import com.core.mailservice.models.MailDetailsModel;
import com.google.common.io.Files;
import com.core.mailservice.dao.MailDetailsDAO;
import com.core.mailservice.helpers.AuthHelper;
import com.core.mailservice.iservices.IMailDetailsService;

import java.util.*;
import java.io.File;
import java.time.LocalDate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.nio.file.*;

@Service
public class MailDetailsService implements IMailDetailsService {
    
    @Autowired
    MailDetailsDAO DAO;
    
    @Autowired 
     JavaMailSender javaMailSender;


    @Override
    public List<MailDetailsModel> GetAll(AuthunticationModel auth){
        List<MailDetailsModel> data= DAO.GetAll();
        return data;
    }    

    @Override
    public String SendMail(AuthunticationModel auth,MailDetailsModel object) {
        MailDetailsModel data= object;
        try{
            LocalDate currentDate = LocalDate.now();

            data.setClientID(auth.getClient_id());
            data.setToken(auth.getTokenValue());
            data.setUserName(auth.getUsername());
            data.setSessionID(auth.getSessionID());
            data.setSentDate(currentDate.toString());
            data.setStatus("success");

            if(data.getAttachment()==null){
                SimpleMailMessage mailMessage= new SimpleMailMessage();
                //mailMessage.setFrom(data.getFrom());
                mailMessage.setTo(data.getRecipient());
                mailMessage.setText(data.getMsgBody());
                mailMessage.setSubject(data.getSubject());
                //mailMessage.setSentDate(currentDate);
    
                if(data.getReplayTo()!=null){
                    mailMessage.setReplyTo(data.getReplayTo());
                }
                if(data.getBcc()!=null && data.getBcc().length()>0){
                    String[]  BccList =data.getBcc().split(",");
                    mailMessage.setBcc(BccList);
                }
                if(data.getCc()!=null && data.getCc().length()>0){
                    String[]  ccList =data.getCc().split(",");
                    mailMessage.setCc(ccList);
                }
                // Sending the mail
                javaMailSender.send(mailMessage);
            }else{
                // Creating a mime message
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper;
                mimeMessageHelper= new MimeMessageHelper(mimeMessage, true);
                //mimeMessageHelper.setFrom(sender);
                mimeMessageHelper.setTo(data.getRecipient());
                mimeMessageHelper.setText(data.getMsgBody());
                mimeMessageHelper.setSubject(data.getSubject());
                //mimeMessageHelper.setSentDate(currentDate);

                if(data.getReplayTo()!=null){
                    mimeMessageHelper.setReplyTo(data.getReplayTo());
                }
                
                if(data.getBcc()!=null && data.getBcc().length()>0){
                    String[]  BccList =data.getBcc().split(",");
                    mimeMessageHelper.setBcc(BccList);
                }
                if(data.getCc()!=null && data.getCc().length()>0){
                    String[]  ccList =data.getCc().split(",");
                    mimeMessageHelper.setCc(ccList);
                }

                // Adding the attachment
             
                // Path cuurentPath = Path.of(attachmentID);
                // Files.createDirectories(cuurentPath);
                // Path targetLocation = cuurentPath.resolve(attachmentID);
                // Files.copy(file.getFile(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                String[] listAttachments = data.getAttachment().split(",");

                String attachmentID="";
                for(int index = 0 ;index<listAttachments.length;index++){
                     attachmentID+=UUID.randomUUID().toString();
                    if(index != listAttachments.length)
                    attachmentID+=",";
                    String attach = listAttachments[index];
                    FileSystemResource file = new FileSystemResource(new File(attach));
                    mimeMessageHelper.addAttachment(file.getFilename(), file);
                }
                data.setAttachment(attachmentID);
              
                // Sending the mail
                javaMailSender.send(mimeMessage);
            }
    
            data = DAO.Add(data);
            return "send mail is success";
        }catch(Exception e){
            data.setStatus("error : " + e.getMessage());
            data = DAO.Add(data);
            return e.getMessage();
        }
    }

    @Override
    public MailDetailsModel GetByID(AuthunticationModel auth,UUID id){
        MailDetailsModel data= DAO.GetByID(id);
        return data;
    }

    @Override
    public MailDetailsModel Add(AuthunticationModel auth,MailDetailsModel object){
        MailDetailsModel data= DAO.Add(object);
        return data;
        
    }

    @Override
    public MailDetailsModel Delete(AuthunticationModel auth,UUID id){
        MailDetailsModel data= DAO.Delete(id);
        return data;
    }

    @Override
    public MailDetailsModel Update(AuthunticationModel auth,UUID id,MailDetailsModel object){
        MailDetailsModel data= DAO.Update(id,object);
        return data;
    }
}
